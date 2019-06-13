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
 * Case operaciones, donde declaro todos los metodos
 * @author javi
 * @param value boolean en cada metodo de la tabla
 * @return tab retornamos la variable tab
 * @version 2.1
 */
public class operaciones {

    //creamos las variables para la conexion
    static Connection cn;
    static Statement s;
    static ResultSet rs;
    DefaultTableModel modelo = new DefaultTableModel();
    //creamos la operacion para mostrar datos en una jtable en el jform

    public DefaultTableModel lista() {

        try {
            cn = conexion.Enlace(cn);
            Statement s = cn.createStatement();
            //consuta a mostrar
            String query = "select * from producto";
            rs = s.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            //obtenemos numero de columnas 
            int CanColumns = rsmd.getColumnCount();
            //comprobamos 
            for (int i = 1; i <= CanColumns; i++) {
                //cargamos columnas en modelo
                modelo.addColumn(rsmd.getColumnLabel(i));
            }
            while (rs.next()) {
                //creamos array 
                Object[] fila = new Object[CanColumns];
                //cargamos datos a modelo
                for (int i = 0; i < CanColumns; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                modelo.addRow(fila);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        //retornamos modelo para jtable
        return modelo;

    }

    //creamos metodo para insertar datos
    public static boolean AgregarConsulta(String nombre, String precio, String ciudad) {
        //dentro de try cacht por si los errores
        boolean tab=false;
        try {
            Statement s = cn.createStatement();
            String query = "INSERT INTO producto(nombre,precio,ciudad)values ('" + nombre + "'," + precio + ",'" + ciudad + "')";
            s.executeUpdate(query);
            s.close();
            cn.close();
            JOptionPane.showMessageDialog(null, "Agregado Correctamente");
           JOptionPane.showMessageDialog(null, "Agregado 1 Columna ");// solo se puede hacer de una en una
            tab=true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al agregar la consulta");
                 return tab=false;
        }
        return tab;
        
    }
    //CREAMOS METODO PARA ELIMINAR DATOS

    public static boolean EliminarConsulta(String id) {
      
        boolean tab=false;
        try {// si es true borraria la columna que quisieramos
            //devuelve el numero de columnas eliminadas 
            
            Statement s = cn.createStatement();
            String query = "DELETE FROM producto WHERE id=" + id + "";
            s.executeUpdate(query);
            s.close();
            cn.close();
            JOptionPane.showMessageDialog(null, "Eliminado Correctamente");
            JOptionPane.showMessageDialog(null, "1 Columna eliminada");// solo se puede hacer de una en una
           tab=true;
        } catch (Exception e) {
            //si devuelve false saltaria el error con su mensaje
            //lo mismo en los demas metodos
            JOptionPane.showMessageDialog(null, "Error al eliminar una consulta, Prueba otra vez");
            return tab=false;
        
    }
        return tab;
    }
    //creamos metodo para modificar datos

    public static boolean ModificarConsulta(String nombre, String precio, String ciudad, String id) {
       boolean tab=false;
        try {
            Statement s = cn.createStatement();
            String query = "UPDATE producto SET nombre='" + nombre + "',precio=" + precio + ",ciudad='" + ciudad + "' WHERE id=" + id + "";
            s.executeUpdate(query);
            s.close();
            cn.close();
            JOptionPane.showMessageDialog(null, "Modificado correctamente");
            JOptionPane.showMessageDialog(null, " 1 Columna modificada ");
            tab=true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se ha modificado la consulta, algo está mal....");
            tab=false;
            
        }
return tab;
    }

}
