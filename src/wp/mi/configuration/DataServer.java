/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wp.mi.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joyce-Kabala
 */
public class DataServer {
    private static Connection con;
    
    public static Connection getConnectionInstance()
    {
        if (con == null)
        {
            try
            {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/databasename", "root", "");
                System.out.println("Connect to Server !");
                
            }
            catch (SQLException ex)
            {
                Logger.getLogger(DataServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return con;
    }
}
