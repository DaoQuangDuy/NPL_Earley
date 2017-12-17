/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package component;

/**
 *
 * @author Dell
 */
public class Terminal {

    private String terminal;
    private String[] POS;

    public Terminal(String terminals, String[] POS) {
        this.terminal = terminals;
        this.POS = POS;
    }

    public String getTerminal() {
        return terminal;
    }

    public String[] getPOS() {
        return POS;
    }
}
