/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crud_funcionarios;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dcmr3
 */
public class Funcionarios {

    int id;
    String nombre;
    String Apellido;
    String Documento;
    String estadocivil;
    String genero;
    String direccion;
    String telefono;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public String getDocumento() {
        return Documento;
    }

    public void setDocumento(String Documento) {
        this.Documento = Documento;
    }

    public String getEstadoCivil() {
        return estadocivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadocivil = estadoCivil;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Funcionarios() {
    }

    public Funcionarios(int id, String nombre, String Apellido, String Documento,
            String estadocivil, String genero, String direccion, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.Apellido = Apellido;
        this.Documento = Documento;
        this.estadocivil = estadocivil;
        this.genero = genero;
        this.direccion = direccion;
        this.telefono = telefono;

    }

    public void MostrarFuncionarios(JTable totalFuncionarios) {

        Conexion objetoConexion = new Conexion();

        DefaultTableModel modelo = new DefaultTableModel();

        String sql = "";

        modelo.addColumn("id");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellidos");
        modelo.addColumn("Documento");
        modelo.addColumn("estadocivil");
        modelo.addColumn("genero");
        modelo.addColumn("direccion");
        modelo.addColumn("telefono");

        totalFuncionarios.setModel(modelo);

        sql = "select * from Funcionarios";

        String[] datos = new String[8];
        Statement st;

        try {
            st = objetoConexion.establecerConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                datos[6] = rs.getString(7);
                datos[7] = rs.getString(8);

                modelo.addRow(datos);

            }

            totalFuncionarios.setModel(modelo);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error en la visualización de datos" + e.toString());
        }
    }
    //METODO PARA INSERTAR FUNCIONARIOS
    public void insertarFuncionario( JTextField txtNombre, JTextField txtApellidos, JTextField txtDocumento,
            JTextField txtestadocivil, JTextField txtgenero, JTextField txtDireccion, JTextField txtTelefono) {

        
        setNombre(txtNombre.getText());
        setApellido(txtApellidos.getText());
        setDocumento(txtDocumento.getText());
        setEstadoCivil(txtestadocivil.getText());
        setGenero(txtgenero.getText());
        setDireccion(txtDireccion.getText());
        setTelefono(txtTelefono.getText());
        

        Conexion objetoConexion = new Conexion();

        String consulta = "insert into funcionarios (nombre,apellidos,documento,estado_civil,genero,direccion,telefono) values (?,?,?,?,?,?,?);";

        try {

            CallableStatement cs = objetoConexion.establecerConexion().prepareCall(consulta);
            cs.setString(1, getNombre());
            cs.setString(2, getApellido());
            cs.setString(3, getDocumento());
            cs.setString(4, getEstadoCivil());
            cs.setString(5, getGenero());
            cs.setString(6, getDireccion());
            cs.setString(7, getTelefono());
           

            cs.execute();
            JOptionPane.showMessageDialog(null, "Funcionario registrado");

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error en registro" + e.toString());
        }

    }
    //METODO PARA SELECCIONAR EL FUNCIONARIO A CAMBIAR
    public void seleccionarFuncionario(JTable parametroTabla,JTextField parametroCodigo,
            JTextField txtNombre,JTextField txtApellidos,JTextField txtDocumento,
            JTextField txtestadocivil,
            JTextField txtgenero,JTextField txtDireccion,JTextField txtTelefono ){
        
        try {
            int fila = parametroTabla.getSelectedRow();
            
            if (fila>=0){
                parametroCodigo.setText(parametroTabla.getValueAt(fila,0).toString());
                txtNombre.setText(parametroTabla.getValueAt(fila,1).toString());
                txtApellidos.setText(parametroTabla.getValueAt(fila,2).toString());
                txtDocumento.setText(parametroTabla.getValueAt(fila,3).toString());
                txtestadocivil.setText(parametroTabla.getValueAt(fila,4).toString());
                txtgenero.setText(parametroTabla.getValueAt(fila,5).toString());
                txtDireccion.setText(parametroTabla.getValueAt(fila,6).toString());
                txtTelefono.setText(parametroTabla.getValueAt(fila,7).toString());
            }else{
                JOptionPane.showMessageDialog(null, "fila no seleccionada");
            }
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Error " + e.toString());
        }
    }
    //METODO PARA CAMBIAR LOS DATOS
    public void ModificarFuncionario(JTextField parametroCodigo,JTextField txtNombre, JTextField txtApellidos, JTextField txtDocumento,
            JTextField txtestadocivil, JTextField txtgenero, JTextField txtDireccion, JTextField txtTelefono) {
        
        setId(Integer.parseInt(parametroCodigo.getText()));
        setNombre(txtNombre.getText());
        setApellido(txtApellidos.getText());
        setDocumento(txtDocumento.getText());
        setEstadoCivil(txtestadocivil.getText());
        setGenero(txtgenero.getText());
        setDireccion(txtDireccion.getText());
        setTelefono(txtTelefono.getText());
      
        Conexion objetoConexion = new Conexion();

        String consulta ="UPDATE Funcionarios SET nombre=?,apellidos=?,documento=?,estado_civil=?,genero=?,direccion=?,telefono=? WHERE Funcionarios.id=?;";

        try {

            CallableStatement cs = objetoConexion.establecerConexion().prepareCall(consulta);
            cs.setString(1, getNombre());
            cs.setString(2, getApellido());
            cs.setString(3, getDocumento());
            cs.setString(4, getEstadoCivil());
            cs.setString(5, getGenero());
            cs.setString(6, getDireccion());
            cs.setString(7, getTelefono());
            cs.setInt(8,getId());
            

            cs.execute();
            JOptionPane.showMessageDialog(null, "MODIFICACION REALIZADA");

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error" + e.toString());
        }

    }
    
     public void EliminarFuncionario(JTextField txtCodigo) {
        
        setId(Integer.parseInt(txtCodigo.getText()));
        Conexion objetoConexion = new Conexion();

        String consulta ="DELETE FROM funcionarios WHERE funcionarios.id=?;";

        try {

            CallableStatement cs = objetoConexion.establecerConexion().prepareCall(consulta);
            cs.setInt(1, getId());
            

            cs.execute();
            JOptionPane.showMessageDialog(null, "SE ELIMINO REGISTRO");

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error" + e.toString());
        }

    }
}

    
