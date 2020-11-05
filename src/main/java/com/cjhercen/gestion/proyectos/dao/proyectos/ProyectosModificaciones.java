package com.cjhercen.gestion.proyectos.dao.proyectos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.naming.NamingException;

import com.cjhercen.gestion.proyectos.models.Proyecto;
import com.cjhercen.gestion.proyectos.utils.ConexionBD;
import com.cjhercen.gestion.proyectos.utils.FechaUtils;

public class ProyectosModificaciones {

	String queryModificarProyecto = "UPDATE PROYECTOS SET nombre_proyecto = ? , ultima_modificacion = ? , descripcion = ? WHERE proyecto_id = ?";

	FechaUtils fechautils = new FechaUtils();
	
	/**
	 * Metodo para modificar el nombre o la descripcion de un proyecto
	 * @param proyecto contiene los nuevos datos que se van a modificar
	 * @param idProyecto identificador del proyecto
	 */
	public void modificarProyecto(Proyecto proyecto, int idProyecto) {
		PreparedStatement preparedStmt = null;
		Connection conexion = null;
		
		try {
			conexion = ConexionBD.getConnection();
			preparedStmt = conexion.prepareStatement(queryModificarProyecto);
			
			preparedStmt.setString(1, proyecto.getNombre_proyecto());

			String ultimaModiString = proyecto.getUltima_modificacion();
			Timestamp ultimaModi = new Timestamp(Long.parseLong(ultimaModiString));
			preparedStmt.setTimestamp(2, ultimaModi);
			
			preparedStmt.setString(3, proyecto.getDescripcion());
			preparedStmt.setInt(4, idProyecto);
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
