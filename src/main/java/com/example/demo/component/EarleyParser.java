package com.example.demo.component;

public class EarleyParser {

	private Grammar grammar;
	private String[] sentence;
	private Chart[] charts;
	private Chart[] da_duoc_duyet_trong_tung_cot;
	static boolean isJoin = false;

	public EarleyParser(Grammar g) {
		grammar = g;
	}

	public Grammar getGrammar() {
		return grammar;
	}

	public Chart[] getCharts() {
		return charts;
	}

	public boolean parseSentence(String[] s) {
		sentence = s;
		charts = new Chart[sentence.length + 1];
		da_duoc_duyet_trong_tung_cot = new Chart[sentence.length + 1];
		for (int i = 0; i < charts.length; i++) {
			charts[i] = new Chart();
			da_duoc_duyet_trong_tung_cot[i] = new Chart();
		}
		String[] start1 = { "@", "S" };
		RHS startRHS = new RHS(start1);
		State start = new State("$", startRHS, 0, 0);
		charts[0].addState(start);
		boolean flag = false;
		for (int i = 0; i < charts.length; i++) {
			System.out.println("Dang o cot thu: " + i);
			for (int j = 0; j < charts[i].size(); j++) {
				State st = charts[i].getState(j);
				String next_term = st.getAfterDot();
				for (int z = 0; z < da_duoc_duyet_trong_tung_cot[i].size(); z++) {
					System.out.println("Kiem tra da duyet trong cot thu: " + i);
					State item = da_duoc_duyet_trong_tung_cot[i].getState(z);
					if (next_term == item.getLhs()) {
						flag = true;
						break;
					}
				}
				if (flag) {
					flag = false;
				} else {
					if (st.isDotLast()) // State's RHS = ... @
					{
						completer(st);
						flag = false;
					} else if (grammar.isPartOfSpeech(next_term)) // RHS = ... @
																	// A ...,
																	// where A
																	// is a part
																	// of
																	// speech.
					{
						scanner(st);
						flag = false;
						if (isJoin) {
							predictor(st);
						}
					} else {
						predictor(st); // A is NOT a part of speech.
						flag = false;
					}
				}
			}
		}
		// Determine whether a successful parse.
		String[] fin = { "S", "@" };
		RHS finRHS = new RHS(fin);
		State finish = new State("$", finRHS, 0, sentence.length);
		State last = charts[sentence.length].getState(charts[sentence.length].size() - 1);
		return finish.equals(last);
	}

	private void predictor(State s) {
		String lhs = s.getAfterDot();
		RHS[] rhs = grammar.getRHS(lhs);
		if (rhs != null) {
			int j = s.getJ();
			// cai tien buoc duyet bang cach doc truoc 2 tu loai
			if (j != sentence.length) {
				String pos = grammar.getPOS(sentence[j]);
				// for (int p = 0; p < pos.length; p++) {
				System.out.println("POS " + pos);
				for (int z = 0; z < rhs.length; z++) {
					// asd
					System.out.println("rhs" + rhs[z].getTerms()[0]);
					int lengthTerm = 0;
					for (int l = 0; l < rhs[z].getTerms().length; l++) {
						String left1 = rhs[z].getTerms()[l];
						RHS[] rhsOfLeft1 = grammar.getRHS(left1);
						if (rhsOfLeft1 != null) {
							int min = 100;
							for (int g = 0; g < rhsOfLeft1.length; g++) {
								if (rhsOfLeft1[g].getTerms().length < min) {
									min = rhsOfLeft1[g].getTerms().length;
								}
							}
							lengthTerm += min;
						}
					}
					if (lengthTerm + j <= sentence.length || grammar.isPartOfSpeech(rhs[z].getTerms()[0])) {
						if (rhs[z].getTerms()[0].indexOf(pos) > -1 || rhs[z].getTerms()[0].equalsIgnoreCase(lhs)) {
							State ns = new State(lhs, rhs[z].addDot(), j, j);
							da_duoc_duyet_trong_tung_cot[j].addState(ns);
							charts[j].addState(ns);
						} else {
							String left = rhs[z].getTerms()[0];
							System.out.println("LEFT" + left);
							RHS[] rhsOfLeft = grammar.getRHS(left);
							if (rhsOfLeft != null && left != lhs) {
								for (int m = 0; m < rhsOfLeft.length; m++) {
									System.out.println("RHS" + rhsOfLeft[m].getTerms()[0]);
									if (rhsOfLeft[m].getTerms()[0].indexOf(pos) > -1) {
										State ns = new State(lhs, rhs[z].addDot(), j, j);
										da_duoc_duyet_trong_tung_cot[j].addState(ns);
										charts[j].addState(ns);
										break;
									}
								}
							}
						}
					}
					// }
				}
			} else {
				// for (int i = 0; i < rhs.length; i++) {
				// State ns = new State(lhs, rhs[i].addDot(), j, j);
				// da_duoc_duyet_trong_tung_cot[j].addState(ns);
				// charts[j].addState(ns);
				// }
			}

		}
	}

	private void scanner(State s) {
		isJoin = true;
		String lhs = s.getAfterDot();
		RHS[] rhs = grammar.getRHS(lhs);
		int i = s.getI();
		int j = s.getJ();
		for (int a = 0; a < rhs.length; a++) {
			String[] terms = rhs[a].getTerms();
			if (terms.length == 1 && j < sentence.length && terms[0].compareToIgnoreCase(sentence[j]) == 0) {
				State ns = new State(lhs, rhs[a].addDotLast(), j, j + 1); // fix
																			// j
																			// -
																			// >
																			// i
				charts[j + 1].addState(ns);
				da_duoc_duyet_trong_tung_cot[j].addState(ns);
				isJoin = false;
			}
		}
	}

	private void completer(State s) {
		String lhs = s.getLhs();
		for (int a = 0; a < charts[s.getI()].size(); a++) {
			State st = charts[s.getI()].getState(a);
			String after = st.getAfterDot();
			if (after != null && lhs.compareTo(after) == 0) {
				State ns = new State(st.getLhs(), st.getRhs().moveDot(), st.getI(), s.getJ());
				charts[s.getJ()].addState(ns);
			}
		}
	}
}