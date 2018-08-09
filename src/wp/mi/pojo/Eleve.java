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
public class Eleve extends Personne{
    
    private Personne numeroPersonne;
    private String matriculeEleve;
    private String lieuNaissance;
    private String dateNaissance;
    
    public Eleve(){}

    public Eleve(Personne numeroPersonne, String matriculeEleve, String lieuNaissance, String dateNaissance) {
        this.numeroPersonne = numeroPersonne;
        this.matriculeEleve = matriculeEleve;
        this.lieuNaissance = lieuNaissance;
        this.dateNaissance = dateNaissance;
    }

    public Personne getNumeroPersonne() {
        return numeroPersonne;
    }

    public void setNumeroPersonne(Personne numeroPersonne) {
        this.numeroPersonne = numeroPersonne;
    }

    public String getMatriculeEleve() {
        return matriculeEleve;
    }

    public void setMatriculeEleve(String matriculeEleve) {
        this.matriculeEleve = matriculeEleve;
    }

    public String getLieuNaissance() {
        return lieuNaissance;
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
    
    
}
