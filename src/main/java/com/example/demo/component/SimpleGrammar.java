package com.example.demo.component;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.example.demo.entity.Word;

@Component
public class SimpleGrammar extends Grammar {

	public SimpleGrammar() {
		super();
		initialize();
	}

	@PostConstruct
	private void initialize() {
		initRules();
	}

	private RHS[] insertArr(RHS[] one, RHS[] two) {
		RHS[] total = new RHS[one.length + two.length];

		for (int i = 0; i < one.length; i++) {
			total[i] = one[i];
		}
		for (int j = 0; j < two.length; j++) {
			total[one.length + j] = two[j];
		}
		return total;
	}

	public void addRules(List<Word> listWords) {
		for (int i = 0; i < listWords.size(); i++) {
			String[] word = { listWords.get(i).getValue() };
			RHS[] wordRhs = { new RHS(word) };
			RHS[] oldValue = Rules.get(listWords.get(i).getCategory());
			RHS[] totalValue = null;
			if (oldValue == null) {
				totalValue = wordRhs;
			} else {
				totalValue = insertArr(oldValue, wordRhs);
			}
			System.out.println("totlaValue" + totalValue);
			Rules.put(listWords.get(i).getCategory(), totalValue);
			addPosTerminal(listWords.get(i));
		}
	}

	private void addPosTerminal(Word word) {
		Terminals.add(new Terminal(word.getValue(), word.getCategory()));
		POS.add(word.getCategory());
	}

	private void initRules() {
		// Rules
		// C
		String[] s1 = { "CN", "VN" };
		String[] s2 = { "TrN", "CN", "VN" };
		String[] s3 = { "TrN", "ĐN", "CN", "VN" };
		String[] s4 = { "DTN", "CN", "VN" };
		RHS[] sRHS = { new RHS(s1), new RHS(s2), new RHS(s3), new RHS(s4) };
		Rules.put("S", sRHS);
		// CN
		String[] np1 = { "CN", "TrN" };
		String[] np5 = { "CN", "GN" };
		String[] np2 = { "P" };
		String[] np3 = { "N" };
		String[] np4 = { "DN" };
		RHS[] npRHS = { new RHS(np1), new RHS(np2), new RHS(np3), new RHS(np4), new RHS(np5) };
		Rules.put("CN", npRHS);
		// VN
		String[] vp1 = { "A" };
		String[] vp2 = { "TN" };
		String[] vp3 = { "V" };
		String[] vp4 = { "ĐN" };
		String[] vp5 = { "VN", "TrN" };
		String[] vp6 = { "VN", "GN" };
		RHS[] vpRHS = { new RHS(vp1), new RHS(vp2), new RHS(vp3), new RHS(vp4), new RHS(vp5), new RHS(vp6) };
		Rules.put("VN", vpRHS);
		// TrN
		String[] trn1 = { "R" };
		String[] trn2 = { "R", "N" };
		String[] trn3 = { "R", "DN" };
		RHS[] trnRHS = { new RHS(trn1), new RHS(trn2), new RHS(trn3) };
		Rules.put("TrN", trnRHS);
		//DTN
		String[] dtn1 = { "N" };
		RHS[] dtnRHS = { new RHS(dtn1)};
		Rules.put("DTN", dtnRHS);
		// GN
		String[] gn1 = { "E", "DN" };
		String[] gn2 = { "C", "P" };
		String[] gn3 = { "C", "ĐN" };
		String[] gn4 = { "C", "DN" };
		String[] gn5 = { "I", "N" };
		RHS[] gnRHS = { new RHS(gn1), new RHS(gn2), new RHS(gn3), new RHS(gn4), new RHS(gn5) };
		Rules.put("GN", gnRHS);
		// TN
		String[] tn1 = { "A", "A" };
		String[] tn2 = { "A", "N" };
		String[] tn3 = { "A", "V" };
		String[] tn4 = { "A", "DN" };
		String[] tn5 = { "A", "ĐN" };
		String[] tn6 = { "I", "A" };
		String[] tn7 = { "I", "TN" };
		String[] tn8 = { "R", "A" };
		String[] tn9 = { "R", "TN" };
		RHS[] tnRHS = { new RHS(tn1), new RHS(tn2), new RHS(tn3), new RHS(tn4), new RHS(tn5), new RHS(tn6),
				new RHS(tn7), new RHS(tn8), new RHS(tn9) };
		Rules.put("TN", tnRHS);
		// DN
		String[] dn1 = { "N", "N" };
		String[] dn2 = { "N", "A" };
		String[] dn3 = { "N", "V" };
		String[] dn4 = { "N", "DN" };
		String[] dn5 = { "N", "ĐN" };
		String[] dn6 = { "N", "M" };
		String[] dn7 = { "N", "R" };
		String[] dn9 = { "N", "I" };
		RHS[] dnRHS = { new RHS(dn1), new RHS(dn2), new RHS(dn3), new RHS(dn4), new RHS(dn5), new RHS(dn6),
				new RHS(dn7), new RHS(dn9) };
		Rules.put("DN", dnRHS);
		// N
		String[] n5 = { "M", "N" };
		String[] n6 = { "M", "DN" };
		String[] n7 = { "L", "DN" };
		String[] n8 = { "L", "N" };
		String[] n9 = { "L", "ĐN" };
		RHS[] nRHS = { new RHS(n5), new RHS(n6), new RHS(n7), new RHS(n8), new RHS(n9) };
		Rules.put("N", nRHS);
		// ĐN
		String[] dgn5 = { "V", "V" };
		String[] dgn6 = { "V", "ĐN" };
		String[] dgn7 = { "V", "N" };
		String[] dgn8 = { "V", "A" };
		String[] dgn9 = { "V", "I" };
		String[] dgn10 = { "V", "DN" };
		String[] dgn11 = { "ĐN", "N" };
		RHS[] dgnRHS = { new RHS(dgn5), new RHS(dgn6), new RHS(dgn7), new RHS(dgn8), new RHS(dgn9), new RHS(dgn10),
				new RHS(dgn11) };
		Rules.put("ĐN", dgnRHS);
		// V
		String[] v1 = { "R", "V" };
		String[] v2 = { "R", "ĐN" };
		RHS[] ppRHS = { new RHS(v1), new RHS(v2) };
		Rules.put("V", ppRHS);

		//

	}

	// private void initPOS() {
	// // POS.add("DT");
	// // POS.add("ĐgT");
	// // POS.add("TT");
	//
	// /////////////////////////
	// // POS.add("N");
	// // POS.add("V");
	// // POS.add("NP");
	// // POS.add("P");
	// // POS.add("Det");
	// // POS.add("A");
	// // POS.add("V");
	// // POS.add("R");
	// // POS.add("P");
	// POS.add("A");
	// POS.add("V");
	// POS.add("N");
	// }
}
