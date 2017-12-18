package component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

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
//        FileReader fr;
//        try {
//            fr = new FileReader("d:/g.txt");
//            BufferedReader br1 = new BufferedReader(fr);
//            String tgrammer = null;
//            //function for early parser
//            for (int t = 0; t < 55; t++) {
//                String tempgrammer[] = tgrammer.split(" ");
//                if (tempgrammer[0] == "C") {
//                    String[] C = new String[tempgrammer.length - 1];
//                    for (int i = 1; i < tempgrammer.length; i++) {
//                        C[i - 1] = tempgrammer[i];
//                    }
//                }
//
//                s1.RHS[] sRHS = {new RHS(s1)};
//                Rules.put("S", sRHS);
//            }
////            while ((tgrammer = br1.readLine()) != null) //read the grammer file
////            {
//
////            }
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(SimpleGrammar.class.getName()).log(Level.SEVERE, null, ex);
//        }
        // C
        String[] s1 = {"CN", "VN"};
        String[] s2 = {"TrN", ",", "CN", "VN"};
        String[] s3 = {"TrN", "ĐN", ",", "CN", "VN"};
        RHS[] sRHS = {new RHS(s1), new RHS(s2), new RHS(s3)};
        Rules.put("C", sRHS);
        // CN
        String[] np1 = {"CN", "TrN"};
        String[] np5 = {"CN", "GN"};
        String[] np2 = {"P"};
        String[] np3 = {"N"};
        String[] np4 = {"DN"};
        RHS[] npRHS = {new RHS(np1), new RHS(np2), new RHS(np3), new RHS(np4), new RHS(np5)};
        Rules.put("CN", npRHS);
        // VN
        String[] vp1 = {"A"};
        String[] vp2 = {"TN"};
        String[] vp3 = {"V"};
        String[] vp4 = {"ĐN"};
        String[] vp5 = {"VN", "TrN"};
        String[] vp6 = {"VN", "GN"};
        RHS[] vpRHS = {new RHS(vp1), new RHS(vp2), new RHS(vp3), new RHS(vp4), new RHS(vp5), new RHS(vp6)};
        Rules.put("VN", vpRHS);
        // TrN
        String[] trn1 = {"R"};
        String[] trn2 = {"R", "N"};
        String[] trn3 = {"R", "DN"};
        RHS[] trnRHS = {new RHS(trn1), new RHS(trn2), new RHS(trn3)};
        Rules.put("TrN", trnRHS);
        // GN
        String[] gn1 = {"E", "DN"};
        String[] gn2 = {"C", "P"};
        String[] gn3 = {"C", "ĐN"};
        String[] gn4 = {"C", "DN"};
        String[] gn5 = {"I", "N"};
        RHS[] gnRHS = {new RHS(gn1), new RHS(gn2), new RHS(gn3), new RHS(gn4), new RHS(gn5)};
        Rules.put("GN", gnRHS);
        // TN
        String[] tn1 = {"A", "A"};
        String[] tn2 = {"A", "N"};
        String[] tn3 = {"A", "V"};
        String[] tn4 = {"A", "DN"};
        String[] tn5 = {"A", "ĐN"};
        String[] tn6 = {"I", "A"};
        String[] tn7 = {"I", "TN"};
        String[] tn8 = {"R", "A"};
        String[] tn9 = {"R", "TN"};
        RHS[] tnRHS = {new RHS(tn1), new RHS(tn2), new RHS(tn3), new RHS(tn4), new RHS(tn5), new RHS(tn6), new RHS(tn7), new RHS(tn8), new RHS(tn9)};
        Rules.put("TN", tnRHS);
        // DN
        String[] dn1 = {"N", "N"};
        String[] dn2 = {"N", "A"};
        String[] dn3 = {"N", "V"};
        String[] dn4 = {"N", "DN"};
        String[] dn5 = {"N", "ĐN"};
        String[] dn6 = {"N", "M"};
        String[] dn7 = {"N", "R"};
        String[] dn8 = {"N", "A"};
        String[] dn9 = {"N", "I"};
        RHS[] dnRHS = {new RHS(dn1), new RHS(dn2), new RHS(dn3), new RHS(dn4), new RHS(dn5), new RHS(dn6), new RHS(dn7), new RHS(dn8), new RHS(dn9)};
        Rules.put("DN", dnRHS);
        // N
        String[] n5 = {"M", "N"};
        String[] n6 = {"M", "DN"};
        String[] n7 = {"L", "DN"};
        String[] n8 = {"L", "N"};
        String[] n9 = {"L", "ĐN"};
        RHS[] nRHS = {new RHS(n5), new RHS(n6), new RHS(n7), new RHS(n8), new RHS(n9)};
        Rules.put("N", nRHS);
        // ĐN
        String[] dgn5 = {"V", "V"};
        String[] dgn6 = {"V", "ĐN"};
        String[] dgn7 = {"V", "N"};
        String[] dgn8 = {"V", "A"};
        String[] dgn9 = {"V", "I"};
        String[] dgn10 = {"V", "DN"};
        String[] dgn11 = {"ĐN", "N"};
        RHS[] dgnRHS = {new RHS(dgn5), new RHS(dgn6), new RHS(dgn7), new RHS(dgn8), new RHS(dgn9), new RHS(dgn10), new RHS(dgn11)};
        Rules.put("ĐN", dgnRHS);
        // V
        String[] v1 = {"R", "V"};
        String[] v2 = {"R", "ĐN"};
        RHS[] ppRHS = {new RHS(v1), new RHS(v2)};
        Rules.put("V", ppRHS);

        String[] prouns = {"Tôi"};
        RHS[] prounsRHS = {new RHS(prouns)};
        Rules.put("P", prounsRHS);
        
        String[] noun2 = {"cố gắng"};
        String[] noun3 = {"thi"};
        RHS[] nounRHS = {new RHS(noun2), new RHS(noun3)};
        Rules.put("V", nounRHS);
        
        String[] verb = {"tốt"};
        RHS[] verbRHS = {new RHS(verb)};
        Rules.put("A", verbRHS);
        
        String[] verb1 = {"sẽ"};
        RHS[] verb1RHS = {new RHS(verb1)};
        Rules.put("R", verb1RHS);
//        String[] prep = {"from"};
//        RHS[] prepRHS = {new RHS(prep)};
//        Rules.put("Prep", prepRHS);
////////////////////////////////////////
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
    }

    private void initPOS() {
        POS.add("A");
        POS.add("V");
        POS.add("R");
        POS.add("P");
    }
}
