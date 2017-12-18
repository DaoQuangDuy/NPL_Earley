package component;

public class EarleyParser {

    private Grammar grammar;
    private String[] sentence;
    private Chart[] charts;
    private Chart[] da_duoc_duyet_trong_tung_cot;

    public EarleyParser(Grammar g) {
        grammar = g;
    }

    Grammar getGrammar() {
        return grammar;
    }

    Chart[] getCharts() {
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
        String[] start1 = {"@", "C"};
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
                    if (next_term == item.getLHS()) {
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
                    } else if (grammar.isPartOfSpeech(next_term)) // RHS = ... @ A ..., where A is a part of speech.
                    {
                        scanner(st);
                        flag = false;
                    } else {
                        predictor(st); // A is NOT a part of speech.
                        flag = false;
                    }
                }
            }
        }
        // Determine whether a successful parse.
        String[] fin = {"C", "@"};
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
            for (int i = 0; i < rhs.length; i++) {
                State ns = new State(lhs, rhs[i].addDot(), j, j);
                da_duoc_duyet_trong_tung_cot[j].addState(ns);
                charts[j].addState(ns);
            }
        }
    }

    private void scanner(State s) {
        String lhs = s.getAfterDot();
        RHS[] rhs = grammar.getRHS(lhs);
        int i = s.getI();
        int j = s.getJ();
        for (int a = 0; a < rhs.length; a++) {
            String[] terms = rhs[a].getTerms();
            if (terms.length == 1 && j < sentence.length && terms[0].compareToIgnoreCase(sentence[j]) == 0) {
                State ns = new State(lhs, rhs[a].addDotLast(), j, j + 1); // fix j - > i
                charts[j + 1].addState(ns);
                da_duoc_duyet_trong_tung_cot[j].addState(ns);
            }
        }
    }

    private void completer(State s) {
        String lhs = s.getLHS();
        for (int a = 0; a < charts[s.getI()].size(); a++) {
            State st = charts[s.getI()].getState(a);
            String after = st.getAfterDot();
            if (after != null && lhs.compareTo(after) == 0) {
                State ns = new State(st.getLHS(), st.getRHS().moveDot(), st.getI(), s.getJ());
                charts[s.getJ()].addState(ns);
            }
        }
    }
}
