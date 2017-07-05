/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.*;
   
public class ConexionBD {

    private Connection connection = null;
    private String user = null;
    private String pass = null;
    private String bd = null;

    @SuppressWarnings("empty-statement")

    public ConexionBD(String bd, String user, String pass) {
        this.modConnection(bd, user, pass);
    }

    public void modConnection(String bd, String user, String pass) {
        this.bd = bd;
        this.user = user;
        this.pass = pass;
    }

    public boolean connect() {
        
        boolean re = false;
        try {
            String driverName = "org.postgresql.Driver";
            String url = "jdbc:postgresql://localhost:5432/" + this.bd;
            Class.forName(driverName);
            Connection conn = DriverManager.getConnection(url, this.user, this.pass);
            if (conn != null)
               System.out.print("Conexion Establecida con Exito\n");
            
            this.connection = conn;
            re = true;

        } 
        catch (ClassNotFoundException e) {
            System.out.println("No se encontró el Driver de la BD\n" + e.getMessage());
        } 
        catch (SQLException e) {
            System.out.println("Error: " + e.getMessage() + "\n");
        }
        return re;
    }

    public boolean disconnect() {
        try {
            if (this.connection != null) {
                this.connection.close();
                this.connection = null;
                System.out.println("Desconectado!\n");
            }
            return true;
        }
        catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* Informacion de la conexion */

    public String getUser() {
        return this.user;
    }

    public String getPass() {
        return this.pass;
    }

    public String getDataBase() {
        return this.bd;
    }

    public Connection getConnection() {
        return this.connection;
    }

    public String getUserType() {
        Statement st;
        ResultSet rs;
        String re = null;
        String sql = "";
        sql += "SELECT rolname FROM pg_roles ";
        sql += "WHERE pg_has_role('" + this.user + "', oid, 'member') ";
        sql += "AND (rolname = 'administrador' OR rolname = 'trabajador') ";
        sql += "LIMIT 1;";
        try {
            st = this.connection.createStatement();
            rs = st.executeQuery(sql);
            if(rs.next()) {
                re = rs.getString(1);
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return re;
    }

    //
    /**** Aqui deberian ir las operaciones ****/
}
