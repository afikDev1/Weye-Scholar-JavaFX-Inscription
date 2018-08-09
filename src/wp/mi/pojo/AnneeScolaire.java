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
public class AnneeScolaire {
    private int idAnne;
    private String libelleAnnee;
    
    public AnneeScolaire(){}

    public AnneeScolaire(int idAnne, String libelleAnnee) {
        this.idAnne = idAnne;
        this.libelleAnnee = libelleAnnee;
    }

    public int getIdAnne() {
        return idAnne;
    }

    public void setIdAnne(int idAnne) {
        this.idAnne = idAnne;
    }

    public String getLibelleAnnee() {
        return libelleAnnee;
    }

    public void setLibelleAnnee(String libelleAnnee) {
        this.libelleAnnee = libelleAnnee;
    }
    
    
}
