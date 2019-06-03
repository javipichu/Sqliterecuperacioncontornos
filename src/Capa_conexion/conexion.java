/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_conexion;
//importamos 
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * Metodo conexion, donde me conecto con la base de datos que he creado fuera del programa, donde tambien he hecho directamente el create table dentro de ese programa(SQLITE SHELL)
 * 
 * @author javi
 */
public class conexion {
    //creamos la clase conexion
    static Connection cn = null;
    //creamos la conexion
    public static Connection Enlace(Connection cn)throws SQLException, ClassNotFoundException{
        //ruta de la base de datos que creamos 
        String ruta="/home/local/DANIELCASTELAO/jalvarezotero/Escritorio/pruebaProyectoBD/BDproducto.db";
        try{
            Class.forName("org.sqlite.JDBC"); 
            cn = DriverManager.getConnection("jdbc:sqlite:"+ruta);
        
            
            if (cn!= null){
                DatabaseMetaData meta =cn.getMetaData();
              
                System.out.println("Conectado");
                
            }
                
        }catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null, "Error, imposible conectar");
        }
        
        return cn;
    }
    
}
