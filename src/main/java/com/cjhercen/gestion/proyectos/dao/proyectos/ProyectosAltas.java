package com.cjhercen.gestion.proyectos.dao.proyectos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.naming.NamingException;

import com.cjhercen.gestion.proyectos.models.Proyecto;
import com.cjhercen.gestion.proyectos.utils.ConexionBD;
import com.cjhercen.gestion.proyectos.utils.FechaUtils;

public class ProyectosAltas {

	String queryInsertarProyecto = "INSERT INTO PROYECTOS (nombre_proyecto, createAt, ultima_modificacion, descripcion) VALUES (?,?,?,?)";

	FechaUtils fechautils = new FechaUtils();
	
	public void insertarProyecto(Proyecto proyecto) {
		PreparedStatement preparedStmt = null;
		Connection conexion = null;
		
		try {
			conexion = ConexionBD.getConnection();
			preparedStmt = conexion.prepareStatement(queryInsertarProyecto);
			preparedStmt.setString(1, proyecto.getNombre_proyecto());
			
			//Pasar las fechas de string a timestamp
			String createAtString = proyecto.getCreateAt();
			String ultimaModiString = proyecto.getUltima_modificacion();
			Timestamp createAt = new Timestamp(Long.parseLong(createAtString));
			Timestamp ultimaModi = new Timestamp(Long.parseLong(ultimaModiString));
			
			preparedStmt.setTimestamp(2, createAt );
			preparedStmt.setTimestamp(3, ultimaModi);
			preparedStmt.setString(4, proyecto.getDescripcion());
			
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
