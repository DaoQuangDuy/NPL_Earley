package component;

public class Main {

    public static String[] dauvao;

    public static void main(String[] args) {
//        String[] sentence1
//                = {"John", "called", "Mary"};
//        String[] sentence2
//                = {"hoc sinh", "hoc", "sinh hoc"};
//        String[] sentence2
//                = {"Papa", "ate", "the", "caviar", "with", "a", "spoon"};
//        String[] sentence2
//                = {"Bò", "vàng", "gặm", "cỏ", "non"};
        String[] sentence2
                = {"Tôi", "sẽ", "cố gắng", "thi", "tốt"};
        Grammar grammar = new SimpleGrammar();
        EarleyParser parser = new EarleyParser(grammar);
//        test(sentence1, parser);
        test(sentence2, parser);
    }

    static void test(String[] sent, EarleyParser parser) {
        StringBuffer out = new StringBuffer();
        for (int i = 0; i < sent.length - 1; i++) {
            out.append(sent[i] + " ");
        }
        out.append(sent[sent.length - 1] + ".");
        String sentence = out.toString();
        System.out.println(
                "\nSentence: \"" + sentence + "\"");
        boolean successful
                = parser.parseSentence(sent);
        System.out.println(
                "Parse Successful:" + successful);
        Chart[] charts = parser.getCharts();
        System.out.println("");
        System.out.println("Charts produced by the sentence\"" + sentence + "\"");
        for (int i = 0; i < charts.length; i++) {
            System.out.println("Chart " + i + ":");
            System.out.println("////////////////////////////");
            for (int j = 0; j < charts[i].size(); j++) {
                State st = charts[i].getState(j);
                String[] temp = st.getRHS().getTerms();
                String rhs = "";
                for (int z = 0; z < temp.length; z++) {
                    rhs = rhs + temp[z] + " ";
                }
                System.out.println(st.getI() + " " + st.getLHS() + "->" + rhs);
            }
        }
    }
}
