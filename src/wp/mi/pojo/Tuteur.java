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
public class Tuteur extends Personne{
    private Personne personne;
    private String degreParentee;
    
    public Tuteur(){}

    public Tuteur(Personne personne, String degreParentee) {
        this.personne = personne;
        this.degreParentee = degreParentee;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public String getDegreParentee() {
        return degreParentee;
    }

    public void setDegreParentee(String degreParentee) {
        this.degreParentee = degreParentee;
    }
    
    
}
