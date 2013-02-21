/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sandbox;

/**
 *
 * @author noflaxe
 */
 import java.sql.*;

public class mysqlconnect{
  public static void main(String[] args) {
  System.out.println("MySQL Connect Example.");
  Connection conn = null;
  String url = "jdbc:mysql://localhost:3306/";
  String dbName = "facultative";
  String driver = "com.mysql.jdbc.Driver";
  String userName = "root"; 
  String password = "";
  try { 
  Class.forName(driver).newInstance();
  conn = DriverManager.getConnection(url+dbName,userName,password);
  if(conn == null)
  {
  System.out.println("yay");
  }
  System.out.println("Connected to the database");
  conn.close();
  System.out.println("Disconnected from database");
  } catch (Exception e) {
  e.printStackTrace();
  }
  }
}