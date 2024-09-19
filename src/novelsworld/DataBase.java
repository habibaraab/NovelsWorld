/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package novelsworld;

import java.sql.*;

/**
 *
 * @author elagamy
 */
public class DataBase {
    
public static Connection ConDb(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con =DriverManager.getConnection("jdbc:mysql://localhost/novel", "root", "");
            return con;
       }
        catch(ClassNotFoundException | SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    
}


    
