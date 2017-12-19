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
public class Word {

    private String word;
    private String cate;

    public Word(String word, String cate) {
        this.word = word;
        this.cate = cate;
    }

    public String getWord() {
        return word;
    }

    public String getCate() {
        return cate;
    }
}
