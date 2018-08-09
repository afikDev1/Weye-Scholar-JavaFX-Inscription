/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wp.mi.pojo;

import java.time.LocalDate;

/**
 *
 * @author Joyce-Kabala
 */
public class Inscription {
    private int numeroInscription;
    private LocalDate dateInscription;
    private Eleve matriculeEleve;
    private Promotion promotionEleve;
    
    public Inscription(){}

    public int getNumeroInscription() {
        return numeroInscription;
    }

    public void setNumeroInscription(int numeroInscription) {
        this.numeroInscription = numeroInscription;
    }

    public LocalDate getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(LocalDate dateInscription) {
        this.dateInscription = dateInscription;
    }

    public Eleve getMatriculeEleve() {
        return matriculeEleve;
    }

    public void setMatriculeEleve(Eleve matriculeEleve) {
        this.matriculeEleve = matriculeEleve;
    }

    public Promotion getPromotionEleve() {
        return promotionEleve;
    }

    public void setPromotionEleve(Promotion promotionEleve) {
        this.promotionEleve = promotionEleve;
    }
    
    
}
