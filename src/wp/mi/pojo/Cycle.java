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
public class Cycle {
    private int idCycle;
    private String libelleCycle;
    
    public Cycle(){}

    public Cycle(int idCycle, String libelleCycle) {
        this.idCycle = idCycle;
        this.libelleCycle = libelleCycle;
    }

    public int getIdCycle() {
        return idCycle;
    }

    public void setIdCycle(int idCycle) {
        this.idCycle = idCycle;
    }

    public String getLibelleCycle() {
        return libelleCycle;
    }

    public void setLibelleCycle(String libelleCycle) {
        this.libelleCycle = libelleCycle;
    }
    
    
}
