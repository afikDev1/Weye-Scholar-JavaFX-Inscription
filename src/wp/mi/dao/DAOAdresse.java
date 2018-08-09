/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wp.mi.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import wp.mi.configuration.ArchitectureDAO;
import wp.mi.configuration.DataServer;
import wp.mi.pojo.Adresse;

/**
 *
 * @author Joyce-Kabala
 */
public class DAOAdresse implements ArchitectureDAO<Adresse>{

    private PreparedStatement ps;
    private ResultSet rs;
    
    @Override
    public boolean create(Adresse adre) {
    
        String query = "INSERT INTO T_Adresse (numero,avenue,quartier,Commune) VALUES (?, ?, ?, ?)";
        
        try
        {
            ps = DataServer.getConnectionInstance().prepareStatement(query);
            ps.setString(1, adre.getNumero());
            ps.setString(2, adre.getAvenue());
            ps.setString(3, adre.getQuartier());
            ps.setString(4, adre.getCommune());
            
            int isUpdated = ps.executeUpdate();
            
            if (isUpdated > 0)
            {
                return true;
            }
                    
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DAOAdresse.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public ArrayList<Adresse> listAll() {
    
        ArrayList<Adresse> lesAdresses = new ArrayList<>();
        
        String query = "SELECT * FROM T_Adresse";

        try
        {
            ps = DataServer.getConnectionInstance().prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next())
            {
                    Adresse oAdresse = new Adresse();

                oAdresse.setId(rs.getInt("idAdresse"));
                oAdresse.setNumero(rs.getString("numero"));
                oAdresse.setAvenue(rs.getString("Avenue"));
                oAdresse.setQuartier(rs.getString("quartier"));
                oAdresse.setCommune(rs.getString("Commune"));

                lesAdresses.add(oAdresse);
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DAOAdresse.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lesAdresses; 
    }

    @Override
    public boolean update(Adresse adre) {
    
        String query = "UPDATE T_Adresse SET numero = ?, avenue = ?, quartier = ?, commune = ?  WHERE idAdresse = ?";

        try
        {
            ps = DataServer.getConnectionInstance().prepareStatement(query);
            ps.setString(1, adre.getNumero());
            ps.setString(2, adre.getAvenue());
            ps.setString(3, adre.getQuartier());
            ps.setString(4, adre.getCommune());
            //
            ps.setInt(5, adre.getId());

            int isUpdated = ps.executeUpdate();

            if (isUpdated > 0)
            {
                return true;
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DAOAdresse.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(Adresse obj) {
    
        String query = " DELETE FROM T_Adresse WHERE idAdresse = ?";
        
        try
        {
            ps = DataServer.getConnectionInstance().prepareStatement(query);
            ps.setInt(1, obj.getId());
            
            int isUpdated = ps.executeUpdate();
            
            if (isUpdated > 0)
            {
                return true;
            }
            
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DAOAdresse.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Adresse searchById(int id) {
    
        String query = " select * from t_adresse where idAdresse = "+id;
        
        try
        {
            ps = DataServer.getConnectionInstance().prepareStatement(query);
            rs = ps.executeQuery();
            
            if (rs.next())
            {
                //new Adresse
                return new Adresse(rs.getInt("idAdresse"),rs.getString("numero"), rs.getString("avenue"), rs.getString("quartier"),  rs.getString("Commune"));
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DAOAdresse.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Adresse searchByObject(Adresse obj) {
    
        String query = "select * from t_adresse where numero = ? AND quartier = ? AND avenue = ? AND Commune = ? ";
        
        try
        {
            ps = DataServer.getConnectionInstance().prepareStatement(query);
            ps.setString(1, obj.getNumero());
            ps.setString(2, obj.getQuartier());
            ps.setString(3, obj.getAvenue());
            ps.setString(4, obj.getCommune());
            
            rs = ps.executeQuery();
            
            if (rs.next())
            {
                return new Adresse(rs.getInt("idAdresse"), rs.getString("quartier"), rs.getString("avenue"), rs.getString("numero"), rs.getString("Commune"));
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DAOAdresse.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Object findId(Adresse adr) {
    
        String query = " SELECT idAdresse WHERE avenue = ? and numero = ? and quartier = ? and Commune = ?";
        
        try
        {
            ps = DataServer.getConnectionInstance().prepareStatement(query);
            ps.setString(1, adr.getAvenue());
            ps.setString(2, adr.getNumero());
            ps.setString(3, adr.getQuartier());
            ps.setString(4, adr.getCommune());
            
            rs = ps.executeQuery();
            
            if (rs.next())
            {
                return rs.getInt("idAdresse");
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DAOAdresse.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
