package component;

import java.util.HashMap;
import java.util.Vector;

public class Grammar {

    HashMap<String, RHS[]> Rules;
    Vector<String> POS;

    public Grammar() {
        Rules = new HashMap<String, RHS[]>();
        POS = new Vector<String>();
    }

    public RHS[] getRHS(String lhs) {
        RHS[] rhs = null;
        if (Rules.containsKey(lhs)) {
            rhs = Rules.get(lhs);
        }
        return rhs;
    }

    public boolean isPartOfSpeech(String s) {
        return POS.contains(s);
    }
}
