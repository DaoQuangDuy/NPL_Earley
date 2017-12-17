package component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public class Grammar {

    HashMap<String, RHS[]> Rules;
    ArrayList Terminals;
    Vector<String> POS;

    public Grammar() {
        Rules = new HashMap<String, RHS[]>();
        Terminals = new ArrayList();
        POS = new Vector<String>();
    }

    public RHS[] getRHS(String lhs) {
        RHS[] rhs = null;
        if (Rules.containsKey(lhs)) {
            rhs = Rules.get(lhs);
        }
        return rhs;
    }

    public String[] getPOS(String terminal) {
        String[] pos = null;
        for (int i = 0; i < Terminals.size(); i++) {
            Terminal temp = (Terminal) Terminals.get(i);
            if (temp.getTerminal().equalsIgnoreCase(terminal)) {
                pos = temp.getPOS();
            }
        }
        return pos;
    }

    public boolean isPartOfSpeech(String s) {
        return POS.contains(s);
    }
}
