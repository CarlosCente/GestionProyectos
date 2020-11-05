package com.cjhercen.gestion.proyectos.dao.proyectos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.NamingException;
import com.cjhercen.gestion.proyectos.utils.ConexionBD;

public class ProyectosBorrado {

	String queryBorradoProyectoPorId = "DELETE FROM PROYECTOS WHERE id_proyecto = ?";
	
	String queryBorradoProyectoPorNombre = "DELETE FROM PROYECTOS WHERE nombre_proyecto = ?";


	public void borrarProyectoPorId(int id) {
		PreparedStatement preparedStmt = null;
		Connection conexion = null;
		
		try {
			conexion = ConexionBD.getConnection();
			preparedStmt = conexion.prepareStatement(queryBorradoProyectoPorId);
			preparedStmt.setInt(1, id);
			
		    // execute the preparedstatement
		    preparedStmt.execute();
			
			 
			// Se cierra la conexión con la base de datos.
            conexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (preparedStmt != null)
					preparedStmt.close();
				if(conexion != null)
					conexion.close();
			} catch (Exception e) {
			}
		}
	}
	
	public void borrarProyectoPorNombre(String nombre_proyecto) {
		PreparedStatement preparedStmt = null;
		Connection conexion = null;
		
		try {
			conexion = ConexionBD.getConnection();
			preparedStmt = conexion.prepareStatement(queryBorradoProyectoPorNombre);
			preparedStmt.setString(1, nombre_proyecto);
			
		    // execute the preparedstatement
		    preparedStmt.execute();
			
			 
			// Se cierra la conexión con la base de datos.
            conexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (preparedStmt != null)
					preparedStmt.close();
				if(conexion != null)
					conexion.close();
			} catch (Exception e) {
			}
		}
	}
	
}
