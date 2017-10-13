/*
 RETOURNE UNE CONNECTION A UNE BASE DE DONEES MSQL
ELLE IMPLEMENTE LE PATTERN SINGLETON
 */
package fr.seb.database.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnection {
    private static Connection instance;
    
    //constructeur privé pour éviter linstenciation depuis l'extérieur
    private DatabaseConnection(){
        
    }
    
    public static Connection getInstance() throws SQLException{
        FileInputStream fis = null;
        try {
            //instance d'un objet properties qui contiendra la configuration
            Properties config = new Properties();
            fis = new FileInputStream("./config/app.properties");
            
            //chargement des données du fichier dans l'objet proerties
            config.load(fis);
            fis.close();
            
            //recuperation des données dans des variables
            String dbHost = config.getProperty("db.host", "localhost"); 
            String dbName = config.getProperty("db.name", "bibliotheque");
            String dbUser = config.getProperty("db.user", "root");
            String dbPass = config.getProperty("db.pass", "");
            if (instance == null){
                
                
            instance = DriverManager.getConnection("jdbc:mysql://" + dbHost + ":3306/" + dbName, dbUser, dbPass);
            }   
        } 
        catch (IOException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return instance;
}
}
