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
        String[] s1 = {"N", "VP"};
        String[] s2 = {"P", "VP"};
        String[] s3 = {"N", "AP"};
        String[] s4 = {"VP", "AP"};

        RHS[] sRHS = {new RHS(s1), new RHS(s2), new RHS(s3), new RHS(s4)};
        Rules.put("S", sRHS);

        String[] vp1 = {"V", "N"};
        String[] vp2 = {"V", "NP"};
        RHS[] vpRHS = {new RHS(vp1), new RHS(vp2)};
        Rules.put("VP", vpRHS);
        String[] np1 = {"N", "N"};
        String[] np2 = {"N", "A"};
        RHS[] npRHS = {new RHS(np1), new RHS(np2)};
        Rules.put("NP", npRHS);
        String[] ap = {"R", "A"};
        RHS[] apRHS = {new RHS(ap)};
        Rules.put("AP", apRHS);

        String[] noun1 = {"hoc sinh"};
        String[] noun2 = {"sinh hoc"};
        RHS[] nounRHS = {new RHS(noun1), new RHS(noun2)};
        Rules.put("N", nounRHS);
        String[] verb1 = {"hoc"};
        String[] verb2 = {"sinh"};
        RHS[] verbRHS = {new RHS(verb1), new RHS(verb2)};
        Rules.put("V", verbRHS);

        String n1 = "hoc sinh";
        String n2 = "sinh hoc";
        String v1 = "hoc";
        String v2 = "sinh";
        String[] noun = {"N"};
        String[] verb = {"V"};

        Terminal n10 = new Terminal(n1, noun);
        Terminal n11 = new Terminal(n2, noun);
        Terminal v10 = new Terminal(v1, verb);
        Terminal v11 = new Terminal(v2, verb);

        Terminals.add(n10);
        Terminals.add(n11);
        Terminals.add(v10);
        Terminals.add(v11);
    }

    private void initPOS() {
        POS.add("N");
        POS.add("V");
    }
}
