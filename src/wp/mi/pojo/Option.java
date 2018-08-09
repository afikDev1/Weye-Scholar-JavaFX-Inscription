/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wp.mi.pojo;

/**
 *
 * @author Joyce-Kabala
 */
public class Option {
    private int idOption;
    private String libelleOption;
    private Section section;
    
    public Option(){}

    public int getIdOption() {
        return idOption;
    }

    public void setIdOption(int idOption) {
        this.idOption = idOption;
    }

    public String getLibelleOption() {
        return libelleOption;
    }

    public void setLibelleOption(String libelleOption) {
        this.libelleOption = libelleOption;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }
    
    
}
