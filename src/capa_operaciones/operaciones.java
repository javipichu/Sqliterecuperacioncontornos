/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package capa_operaciones;
//importamos paquetes necesarios
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
// importamos el paquete de conexion
import Capa_conexion.conexion;
/**
 *Case operaciones, donde declaro todos los metodos 
 * 
 * @author javi
 */
public class operaciones {
    //creamos las variables para la conexion
    static Connection cn;
    static Statement s;
    static ResultSet rs;
    DefaultTableModel modelo= new DefaultTableModel();
  //creamos la operacion para mostrar datos en una jtable en el jform
    public DefaultTableModel lista(){
        
    try{
    cn = conexion.Enlace(cn);
    Statement s= cn.createStatement();
    //consuta a mostrar
    String query = "select * from producto";
    rs = s.executeQuery(query);
   ResultSetMetaData rsmd=rs.getMetaData();
   //obtenemos numero de columnas 
   int CanColumns = rsmd.getColumnCount();
    //comprobamos 
   for(int i=1;i<=CanColumns;i++){
       //cargamos columnas en modelo
   modelo.addColumn(rsmd.getColumnLabel(i));
   }
   while (rs.next()){
   //creamos array 
       Object[] fila=new Object[CanColumns];
   //cargamos datos a modelo
   for(int i=0;i<CanColumns;i++){
   fila[i] = rs.getObject(i+1);
   }
   modelo.addRow(fila);
   }
    }catch(Exception e){JOptionPane.showMessageDialog(null, e);}
    //retornamos modelo para jtable
    return modelo;
    
    }
    
    //creamos metodo para insertar datos
    public void AgregarConsulta(String nombre,String precio,String ciudad){
    //dentro de try cacht por si los errores
        try{
        Statement s=cn.createStatement();
        String query = "INSERT INTO producto(nombre,precio,ciudad)values ('"+nombre+"',"+precio+",'"+ciudad+"')";
        s.executeUpdate(query);
        s.close();
        cn.close();
        JOptionPane.showMessageDialog(null, "Agregado Correctamente");
        }catch(Exception e){JOptionPane.showMessageDialog(null,"Error al agregar la consulta");}
    }
        //CREAMOS METODO PARA ELIMINAR DATOS
        public void EliminarConsulta(String id){
       try{
       Statement s=cn.createStatement();
       String query="DELETE FROM producto WHERE id="+id+"";
       s.executeUpdate(query);
       s.close();
       cn.close();
       JOptionPane.showMessageDialog(null, "Eliminado Correctamente");
       }catch(Exception e){JOptionPane.showMessageDialog(null, "Error al eliminar una consulta, Prueba otra vez");}
        
        }
        //creamos metodo para modificar datos
        public void ModificarConsulta(String nombre,String precio,String ciudad,String id){
        try{
        Statement s=cn.createStatement();
        String query="UPDATE producto SET nombre='"+nombre+"',precio="+precio+",ciudad='"+ciudad+"' WHERE id="+id+"";
        s.executeUpdate(query);
        s.close();
        cn.close();
        JOptionPane.showMessageDialog(null, "Modificado correctamente");
        }catch(Exception e){JOptionPane.showMessageDialog(null, "No se ha modificado la consulta, algo está mal....");}
        
        
        }

   
        
    }
    
    
    

