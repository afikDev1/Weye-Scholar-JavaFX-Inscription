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
public class Adresse {
    private int id;
    private String numero;
    private String avenue;
    private String quartier;
    private String commune;
    
    public Adresse(){}

    public Adresse(int id, String numero, String avenue, String quartier, String commune) {
        this.id = id;
        this.numero = numero;
        this.avenue = avenue;
        this.quartier = quartier;
        this.commune = commune;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getAvenue() {
        return avenue;
    }

    public void setAvenue(String avenue) {
        this.avenue = avenue;
    }

    public String getQuartier() {
        return quartier;
    }

    public void setQuartier(String quartier) {
        this.quartier = quartier;
    }

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }
    
    
}
