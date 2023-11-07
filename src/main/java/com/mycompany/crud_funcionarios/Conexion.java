/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crud_funcionarios;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author dcmr3
 */
public class Conexion {
    Connection con = null;
    String usuario = "postgres";
    String contrasenia = "1234";
    String bd = "CRUD";
    String ip = "localhost";
    String puerto = "5432";
    
    String cadena = "jdbc:postgresql://"+ip+":"+puerto+"/"+bd;
    
    public Connection establecerConexion(){
        try{
            Class.forName("org.postgresql.Driver");
            
            con = DriverManager.getConnection(cadena,usuario,contrasenia);
            JOptionPane.showMessageDialog(null,"Conexión exitosa");
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null," Falla en la Conexión "+e.toString());
        }
        return con;
    }
          
    
}
