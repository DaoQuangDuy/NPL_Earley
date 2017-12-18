package component;

public class SimpleGrammar extends Grammar {

    public SimpleGrammar() {
        super();
        initialize();
    }

    private void initialize() {
        initRules();
        initPOS();
    }

    private void initRules() {
//        String[] s1 = {"NP", "VP"};
//        RHS[] sRHS = {new RHS(s1)};
//        Rules.put("S", sRHS);
//        String[] np1 = {"NP", "PP"};
//        String[] np2 = {"Noun"};
//        RHS[] npRHS = {new RHS(np1), new RHS(np2)};
//        Rules.put("NP", npRHS);
//        String[] vp1 = {"Verb", "NP"};
//        String[] vp2 = {"VP", "PP"};
//        RHS[] vpRHS = {new RHS(vp1), new RHS(vp2)};
//        Rules.put("VP", vpRHS);
//        String[] pp1 = {"Prep", "NP"};
//        RHS[] ppRHS = {new RHS(pp1)};
//        Rules.put("PP", ppRHS);
//        String[] noun1 = {"John"};
//        String[] noun2 = {"Mary"};
//        String[] noun3 = {"Denver"};
//        RHS[] nounRHS = {new RHS(noun1), new RHS(noun2), new RHS(noun3)};
//        Rules.put("Noun", nounRHS);
//        String[] verb = {"called"};
//        RHS[] verbRHS = {new RHS(verb)};
//        Rules.put("Verb", verbRHS);
//        String[] prep = {"from"};
//        RHS[] prepRHS = {new RHS(prep)};
//        Rules.put("Prep", prepRHS);
//        String[] s1 = {"N", "VP"};
//        String[] s2 = {"P", "VP"};
//        String[] s3 = {"N", "AP"};
//        String[] s4 = {"VP", "AP"};
//
//        RHS[] sRHS = {new RHS(s1), new RHS(s2), new RHS(s3), new RHS(s4)};
//        Rules.put("S", sRHS);
//
//        String[] vp1 = {"V", "N"};
//        String[] vp2 = {"V", "NP"};
//        RHS[] vpRHS = {new RHS(vp1), new RHS(vp2)};
//        Rules.put("VP", vpRHS);
//        String[] np1 = {"N", "N"};
//        String[] np2 = {"N", "A"};
//        RHS[] npRHS = {new RHS(np1), new RHS(np2)};
//        Rules.put("NP", npRHS);
//        String[] ap = {"R", "A"};
//        RHS[] apRHS = {new RHS(ap)};
//        Rules.put("AP", apRHS);
//
//        String[] noun1 = {"hoc sinh"};
//        String[] noun2 = {"sinh hoc"};
//        RHS[] nounRHS = {new RHS(noun1), new RHS(noun2)};
//        Rules.put("N", nounRHS);
//        String[] verb1 = {"hoc"};
//        String[] verb2 = {"sinh"};
//        RHS[] verbRHS = {new RHS(verb1), new RHS(verb2)};
//        Rules.put("V", verbRHS);
//
//        String n1 = "hoc sinh";
//        String n2 = "sinh hoc";
//        String v1 = "hoc";
//        String v2 = "sinh";
//        String[] noun = {"N"};
//        String[] verb = {"V"};
//
//        Terminal n10 = new Terminal(n1, noun);
//        Terminal n11 = new Terminal(n2, noun);
//        Terminal v10 = new Terminal(v1, verb);
//        Terminal v11 = new Terminal(v2, verb);
//
//        Terminals.add(n10);
//        Terminals.add(n11);
//        Terminals.add(v10);
//        Terminals.add(v11);
        String[] s1 = {"CN", "VN"};

        RHS[] sRHS = {new RHS(s1)};
        Rules.put("C", sRHS);

        String[] vp1 = {"ĐgN"};
        RHS[] vpRHS = {new RHS(vp1)};
        Rules.put("VN", vpRHS);
        String[] np1 = {"DN"};
        String[] np2 = {"ĐgN"};
        RHS[] npRHS = {new RHS(np1), new RHS(np2)};
        Rules.put("CN", npRHS);

        String[] dn1 = {"DT", "TT"};
        String[] dn2 = {"DN", "TT"};
        RHS[] dnRHS = {new RHS(dn1), new RHS(dn2)};
        Rules.put("DN", dnRHS);
        
        String[] dng1 = {"ĐgT", "DN"};
        String[] dng2 = {"ĐgN", "GN"};
        RHS[] dngRHS = {new RHS(dng1), new RHS(dng2)};
        Rules.put("ĐgN", dngRHS);
        
        String[] gn = {"GT", "DN"};
        RHS[] gnRHS = {new RHS(gn)};
        Rules.put("GN", gnRHS);

        String[] noun1 = {"bò"};
        String[] noun2 = {"cỏ"};
        RHS[] nounRHS = {new RHS(noun1), new RHS(noun2)};
        Rules.put("DT", nounRHS);

        String[] verb1 = {"gặm"};
        RHS[] verbRHS = {new RHS(verb1)};
        Rules.put("ĐgT", verbRHS);

        String[] d1 = {"vàng"};
        String[] d2 = {"non"};
        RHS[] dRHS = {new RHS(d1), new RHS(d2)};
        Rules.put("TT", dRHS);

        String n1 = "bò";
        String n2 = "cỏ";
        String v1 = "gặm";
        String det1 = "vàng";
        String det2 = "non";
        String[] noun = {"DT"};
        String[] verb = {"ĐgT"};
        String[] det = {"TT"};

        Terminal n10 = new Terminal(n1, noun);
        Terminal n11 = new Terminal(n2, noun);
        Terminal v10 = new Terminal(v1, verb);
        Terminal det11 = new Terminal(det1, det);
        Terminal det12 = new Terminal(det2, det);

        Terminals.add(n10);
        Terminals.add(n11);
        Terminals.add(v10);
        Terminals.add(det11);
        Terminals.add(det12);
//        String[] s1 = {"NP", "VP"};
//
//        RHS[] sRHS = {new RHS(s1)};
//        Rules.put("S", sRHS);
//
//        String[] vp1 = {"VP", "PP"};
//        String[] vp2 = {"V", "NP"};
//        RHS[] vpRHS = {new RHS(vp1), new RHS(vp2)};
//        Rules.put("VP", vpRHS);
//        String[] np1 = {"Det", "N"};
//        String[] np2 = {"NP", "PP"};
//        String[] np3 = {"Papa"};
//        RHS[] npRHS = {new RHS(np1), new RHS(np2), new RHS(np3)};
//        Rules.put("NP", npRHS);
//
//        String[] pp = {"P", "NP"};
//        RHS[] ppRHS = {new RHS(pp)};
//        Rules.put("PP", ppRHS);
//
//        String[] noun1 = {"caviar"};
//        String[] noun2 = {"spoon"};
//        RHS[] nounRHS = {new RHS(noun1), new RHS(noun2)};
//        Rules.put("N", nounRHS);
//
//        String[] verb1 = {"ate"};
//        RHS[] verbRHS = {new RHS(verb1)};
//        Rules.put("V", verbRHS);
//
//        String[] p1 = {"with"};
//        RHS[] pRHS = {new RHS(p1)};
//        Rules.put("P", pRHS);
//
//        String[] d1 = {"the"};
//        String[] d2 = {"a"};
//        RHS[] dRHS = {new RHS(d1), new RHS(d2)};
//        Rules.put("Det", dRHS);
//
//        String n1 = "caviar";
//        String n2 = "spoon";
//        String v1 = "ate";
//        String p = "with";
//        String np = "Papa";
//        String det1 = "the";
//        String det2 = "a";
//        String[] noun = {"N"};
//        String[] nounp = {"NP"};
//        String[] verb = {"V"};
//        String[] P = {"P"};
//        String[] det = {"Det"};
//
//        Terminal n10 = new Terminal(n1, noun);
//        Terminal n11 = new Terminal(n2, noun);
//        Terminal v10 = new Terminal(v1, verb);
//        Terminal p11 = new Terminal(p, P);
//        Terminal np11 = new Terminal(np, nounp);
//        Terminal det11 = new Terminal(det1, det);
//        Terminal det12 = new Terminal(det2, det);
//
//        Terminals.add(n10);
//        Terminals.add(n11);
//        Terminals.add(v10);
//        Terminals.add(p11);
//        Terminals.add(np11);
//        Terminals.add(det11);
//        Terminals.add(det12);
    }

    private void initPOS() {
        POS.add("DT");
        POS.add("ĐgT");
        POS.add("TT");
//        POS.add("N");
//        POS.add("V");
//        POS.add("NP");
//        POS.add("P");
//        POS.add("Det");
    }
}
