package org.crud.sregion;
/*CRUD : CREATE, READ, UPDATE, DELETE
 * 
 * ESTE SERIA EL PRIMER CRUD QUE HACEMOS EN JAVA,
 * 
 * NOTA MUY IMPORTANTE
 * 
 * 
 * */

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class S_Region {

	// CONEXION A LA BASE DE DATOS

	@SuppressWarnings("unused")
	private static Connection connection = null;
	private static String driver = "oracle.jdbc.driver.OracleDriver";
	private static String URL = "jdbc:oracle:thin:@localhost:1521:orcl";

	public static void connectDataBaseOracle() throws IOException, SQLException {

		try {
			Class.forName(driver).newInstance();
			System.out.println("CARGO EL DRIVER CORRECTAMENTE: ojdbc6.jar");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exeption: " + e.getMessage());
		}

		try {
			connection = DriverManager.getConnection(URL, "SYSTEM", "admin123");
			System.out.println("CONEXION EXITOSA: Oracle11g");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exeption: " + e.getMessage());
		}
	}

	public static void main(String[] args) throws IOException, SQLException {
		// insertarS_Region(11, "HIDALGO");
		// updatS_Region("HIDALGO MX", 11);
		// delateS_Region(11);
		// selectS_Region(1);
		// selectTodoS_Region();
		invocaProcedureS_Region(11, "SONORA");
	}

	// METODO DE INSERTAR

	public static void insertarS_Region(int id, String name) throws IOException, SQLException {
		try {
			connectDataBaseOracle();
			String sql = "INSERT INTO S_REGION (ID, NAME) VALUES (?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.executeQuery();
			System.out.println("INSERTO EL REGISTRO : " + id + "," + name);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exeption: " + e.getMessage());
		}
	}

	public static void updatS_Region(String name, int id) throws IOException, SQLException {
		try {
			connectDataBaseOracle();
			String sql = "UPDATE S_REGION SET NAME = ? WHERE ID = ?";
			PreparedStatement pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, name);
			pStatement.setInt(2, id);
			pStatement.executeQuery();
			System.out.println("ACTUALIZANDO EL REGISTRO: " + id + "," + name);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exeption: " + e.getMessage());
		}
	}

	public static void delateS_Region(int id) throws IOException, SQLException {
		try {
			connectDataBaseOracle();
			String sql = "DELETE FROM S_REGION WHERE ID = ?";
			PreparedStatement pStatement = connection.prepareStatement(sql);
			pStatement.setInt(1, id);
			pStatement.executeQuery();
			System.out.println("ELIMINADO EL REGISTRO: " + id);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exeption: " + e.getMessage());
		}

	}

	public static void selectS_Region(int id) throws IOException, SQLException {
		try {
			connectDataBaseOracle();
			String sql = "SELECT * FROM S_REGION WHERE ID = ?";
			PreparedStatement pStatement = connection.prepareStatement(sql);
			pStatement.setInt(1, id);
			ResultSet resultSet = pStatement.executeQuery();
			if (resultSet.next()) {
				System.out.println(resultSet.getInt("id") + " " + resultSet.getString("name"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exeption: " + e.getMessage());
		}

	}

	public static void selectTodoS_Region() throws IOException, SQLException {
		try {
			connectDataBaseOracle();
			String sql = "SELECT * FROM S_REGION";
			PreparedStatement pStatement = connection.prepareStatement(sql);
			ResultSet resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				System.out.println(resultSet.getInt("id") + " " + resultSet.getString("name"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exeption: " + e.getMessage());
		}
	}
	
	public static void invocaProcedureS_Region(int id, String name) throws IOException, SQLException {
		try {
			connectDataBaseOracle();
			String sql = "CALL proc(?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			CallableStatement cs = connection.prepareCall(sql);
			cs.setInt(1, id);
			cs.setString(2, name);
			cs.execute();
			System.out.println("EJECUTÓ CORRECTAMENTE EL PROCEDIMIENTO PROC");
			
		} catch (Exception e) {
			System.out.println("Exception" + e.getMessage());
		}

	}
}
