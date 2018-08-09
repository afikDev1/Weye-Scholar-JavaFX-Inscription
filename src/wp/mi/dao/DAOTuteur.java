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
import wp.mi.pojo.Tuteur;

/**
 *
 * @author Joyce-Kabala
 */
public class DAOTuteur implements ArchitectureDAO<Tuteur>{

    PreparedStatement ps;
    ResultSet rs;
    
    @Override
    public boolean create(Tuteur obj) {
    
        String queryOne = " INSERT INTO T_Personne (nom, postnom, prenom, sexe, nationalite, adresse) "
                + " VALUES (?,?,?,?,?,?)";

        try {
            ps = DataServer.getConnectionInstance().prepareStatement(queryOne);
            ps.setString(1, obj.getNom());
            ps.setString(2, obj.getPostnom());
            ps.setString(3, obj.getPrenom());
            ps.setString(4, obj.getSexe());
            ps.setString(5, obj.getNationalite());
            ps.setInt(6, obj.getAdresse().getId());

            int isUpdated = ps.executeUpdate();

            if (isUpdated > 0) {

                int idpersonne = (int) findId(obj);

                String queryTwo = " INSERT INTO T_Tuteur (degreparentee) "
                        + " VALUES (?,?,?,?)";

                ps = DataServer.getConnectionInstance().prepareStatement(queryTwo);
                ps.setInt(1, idpersonne);
                ps.setString(2, obj.getDegreParentee());
               
                int isOk = ps.executeUpdate();

                if (isOk > 0) {
                    return true;
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOTuteur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;}

    @Override
    public ArrayList<Tuteur> listAll() {
    
        ArrayList<Tuteur> lesTuteurs = new ArrayList<>();

        String query = "select * from t_personne as p inner join t_tuteur as t on t.numeroPersonne = p.numeroPersonne";

        try {
            ps = DataServer.getConnectionInstance().prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                Tuteur t = new Tuteur();

                t.setNom(rs.getString("nom"));
                t.setPostnom(rs.getString("postnom"));
                t.setPrenom(rs.getString("prenom"));
                t.setSexe(rs.getString("sexe"));
                t.setAdresse(new DAOAdresse().searchById(rs.getInt("adresse")));

                lesTuteurs.add(t);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOTuteur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lesTuteurs;
    }

    @Override
    public boolean update(Tuteur obj) {
    
        int idpersonne = (int) findId(obj);

        String query = "update t_personne set nom = ?, postnom = ?, prenom = ?, sexe = ?, nationalite = ?, adresse = ? "
                + "where idPersonne = ? ";

        try {
            ps = DataServer.getConnectionInstance().prepareStatement(query);
            ps.setString(1, obj.getNom());
            ps.setString(2, obj.getPostnom());
            ps.setString(3, obj.getPrenom());
            ps.setString(4, obj.getSexe());
            ps.setString(5, obj.getNationalite());
            ps.setInt(6, obj.getAdresse().getId());
            //
            ps.setInt(7, idpersonne);

            int isUpdated = ps.executeUpdate();

            if (isUpdated > 0) {

                String queryTwo = " update t_tuteur set degreparente = ? "
                        + "where numeroPersonne = ? ";

                ps=DataServer.getConnectionInstance().prepareStatement(queryTwo);
                ps.setString(1, obj.getDegreParentee());
                //
                ps.setInt(3, idpersonne);

                int isUpdatedTwo = ps.executeUpdate();

                if (isUpdatedTwo > 0) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOTuteur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(Tuteur obj) {
    
        String query = " delete from t_personne where numeroPersonne = ?";

        try {
            ps = DataServer.getConnectionInstance().prepareStatement(query);
            ps.setInt(1, (int) findId(obj));

            int isUpdated = ps.executeUpdate();

            if (isUpdated > 0) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAOTuteur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;}

    @Override
    public Tuteur searchById(int id) {
    
        String query = " select * from t_personne p, t_eleve e where e.personne = p.numeropersonne and p.numeropersonne = "+id;
        
        try
        {
            ps = DataServer.getConnectionInstance().prepareStatement(query);
            rs = ps.executeQuery();
            
            if (rs.next())
            {
                Tuteur t = new Tuteur();

                t.setNom(rs.getString("nom"));
                t.setPostnom(rs.getString("postnom"));
                t.setPrenom(rs.getString("prenom"));
                t.setSexe(rs.getString("sexe"));
                t.setNationalite(rs.getString("nationalite"));
                t.setAdresse(new DAOAdresse().searchById(rs.getInt("adresse")));

                return t;
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DAOTuteur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;}

    @Override
    public Tuteur searchByObject(Tuteur obj) {
    
        String query = " select * from t_personne as p inner join t_tuteur as t on t.numeropersonne = p.numeroPersonne "
                + "WHERE p.nom = ? and p.postnom = ? and p.prenom = ? ";

        try {
            ps = DataServer.getConnectionInstance().prepareStatement(query);
            ps.setString(1, obj.getNom());
            ps.setString(2, obj.getPostnom());
            ps.setString(3, obj.getPrenom());

            rs = ps.executeQuery();

            if (rs.next()) {
                Tuteur t = new Tuteur();

                t.setNom(rs.getString("nom"));
                t.setPostnom(rs.getString("postnom"));
                t.setPrenom(rs.getString("prenom"));

                return t;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOTuteur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Object findId(Tuteur obj) {
    
        String query = "select numeropersonne from t_tuteur as t, t_personne as p where "
                + " t.numeropersonne = p.numeropersonne and nom = ? and postnom = ? and prenom = ?";

        try {
            ps = DataServer.getConnectionInstance().prepareStatement(query);
            ps.setString(1, obj.getNom());
            ps.setString(2, obj.getPostnom());
            ps.setString(3, obj.getPrenom());

            rs = ps.executeQuery();

            if (rs.next()) {
                int idpersonne = rs.getInt("numeroPersonne");
                return idpersonne;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOTuteur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
