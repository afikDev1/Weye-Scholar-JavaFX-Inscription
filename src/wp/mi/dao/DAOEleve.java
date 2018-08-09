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
import wp.mi.pojo.Eleve;

/**
 *
 * @author Joyce-Kabala
 */
public class DAOEleve implements ArchitectureDAO<Eleve>{

    PreparedStatement ps;
    ResultSet rs;
    
    @Override
    public boolean create(Eleve obj) {
    
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

                String queryTwo = " INSERT INTO T_Eleve (numeroPersonne, matriculeEleve, lieuNaissance, dateNaissance) "
                        + " VALUES (?,?,?,?)";

                ps = DataServer.getConnectionInstance().prepareStatement(queryTwo);
                ps.setInt(1, idpersonne);
                ps.setString(2, obj.getMatriculeEleve());
                ps.setString(3, obj.getLieuNaissance());
                ps.setString(4, obj.getDateNaissance());

                int isOk = ps.executeUpdate();

                if (isOk > 0) {
                    return true;
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOEleve.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public ArrayList<Eleve> listAll() {
    
        ArrayList<Eleve> lesEleves = new ArrayList<>();

        String query = "select * from t_personne as p inner join t_eleve as el on el.numeroPersonne = p.numeroPersonne";

        try {
            ps = DataServer.getConnectionInstance().prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                Eleve el = new Eleve();

                el.setMatriculeEleve(rs.getString("matriculeEleve"));
                el.setNom(rs.getString("nom"));
                el.setPostnom(rs.getString("postnom"));
                el.setPrenom(rs.getString("prenom"));
                el.setSexe(rs.getString("sexe"));
                el.setAdresse(new DAOAdresse().searchById(rs.getInt("adresse")));

                lesEleves.add(el);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOEleve.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lesEleves;
    }

    @Override
    public boolean update(Eleve obj) {
    
        int idpersonne = (int) findId(obj);

        String query = "update t_personne set nom = ?, postnom = ?, prenom = ?, sexe = ?, nationalite = ?, adresse = ? "
                + "where numeroPersonne = ? ";

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

                String queryTwo = " update t_eleve set lieuNaissance = ?, dateNaissance= ? "
                        + "where numeroPersonne = ? ";

                ps=DataServer.getConnectionInstance().prepareStatement(queryTwo);
                ps.setString(1, obj.getLieuNaissance());
                ps.setString(2, obj.getDateNaissance());
                //
                ps.setInt(3, idpersonne);

                int isUpdatedTwo = ps.executeUpdate();

                if (isUpdatedTwo > 0) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOEleve.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(Eleve obj) {
    
        String query = " delete from t_personne where numeroPersonne = ?";

        try {
            ps = DataServer.getConnectionInstance().prepareStatement(query);
            ps.setInt(1, (int) findId(obj));

            int isUpdated = ps.executeUpdate();

            if (isUpdated > 0) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAOEleve.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Eleve searchById(int id) {
    
        String query = " select * from t_personne p, t_eleve e where e.personne = p.numeropersonne and p.numeropersonne = "+id;
        
        try
        {
            ps = DataServer.getConnectionInstance().prepareStatement(query);
            rs = ps.executeQuery();
            
            if (rs.next())
            {
                Eleve el = new Eleve();

                el.setMatriculeEleve(rs.getString("matriculeeleve"));
                el.setNom(rs.getString("nom"));
                el.setPostnom(rs.getString("postnom"));
                el.setPrenom(rs.getString("prenom"));
                el.setSexe(rs.getString("sexe"));
                el.setNationalite(rs.getString("nationalite"));
                el.setLieuNaissance("lieunaissance");
                el.setDateNaissance(rs.getString("datenaissance"));
                el.setAdresse(new DAOAdresse().searchById(rs.getInt("adresse")));

                return el;
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DAOEleve.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Eleve searchByObject(Eleve obj) {
    
        String query = " select * from t_personne as p inner join t_eleve as el on el.numeropersonne = p.numeroPersonne "
                + "WHERE el.matriculeEleve = ?";

        try {
            ps = DataServer.getConnectionInstance().prepareStatement(query);
            ps.setString(1, obj.getMatriculeEleve());

            rs = ps.executeQuery();

            if (rs.next()) {
                Eleve el = new Eleve();

                el.setMatriculeEleve(rs.getString("matriculeeleve"));
                el.setNom(rs.getString("nom"));
                el.setPostnom(rs.getString("postnom"));

                return el;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOEleve.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Object findId(Eleve obj) {
    
        String query = "select numeropersonne from t_eleve as el, t_personne as p where "
                + " el.numeropersonne = p.numeropersonne and nom = ? and postnom = ? and prenom = ?";

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
            Logger.getLogger(DAOEleve.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
