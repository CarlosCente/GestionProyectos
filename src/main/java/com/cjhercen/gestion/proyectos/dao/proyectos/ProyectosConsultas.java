package com.cjhercen.gestion.proyectos.dao.proyectos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.cjhercen.gestion.proyectos.models.Proyecto;
import com.cjhercen.gestion.proyectos.utils.ConexionBD;

public class ProyectosConsultas {

	String queryConsultaProyectos = "SELECT * FROM PROYECTOS";
	
	String queryConsultaProyectoPorId = "SELECT * FROM PROYECTOS WHERE id_proyecto = ?";
	
	String queryConsultaProyectoPorNombre = "SELECT * FROM PROYECTOS WHERE nombre_proyecto = ?";

	String queryConsultaProyectosUltimaModif = "SELECT nombre_proyecto, ultima_modificacion FROM PROYECTOS ORDER BY ultima_modificacion";
	
	ConexionBD conexionBD = new ConexionBD();
	
	public List<Proyecto> consultarProyectos() {
		List<Proyecto> listaProyectos = new ArrayList<Proyecto>();
		Statement st = null;
		ResultSet rs = null;
		Connection conexion = null;
		
		try {
			conexion = ConexionBD.getConnection();
			st = conexion.createStatement();
			rs = st.executeQuery (queryConsultaProyectos);
			
			 // Se recorre el ResultSet, mostrando por pantalla los resultados.
            while (rs.next())
            {
            	Proyecto proyecto = new Proyecto();
            	proyecto.setId_proyecto(rs.getInt(1));
            	proyecto.setNombre_proyecto(rs.getString(2));
            	proyecto.setCreateAt(rs.getString(3));            	
            	proyecto.setUltima_modificacion(rs.getString(4));
            	proyecto.setDescripcion(rs.getString(5));
            	
            	listaProyectos.add(proyecto);
            }
			
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
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
				if(conexion != null)
					conexion.close();
			} catch (Exception e) {
			}
		}
		
		return listaProyectos;
	}
	
	public List<Proyecto> consultarProyectosUltimaModif() {
		List<Proyecto> listaProyectos = new ArrayList<Proyecto>();
		Statement st = null;
		ResultSet rs = null;
		Connection conexion = null;
		
		try {
			conexion = ConexionBD.getConnection();
			st = conexion.createStatement();
			rs = st.executeQuery (queryConsultaProyectosUltimaModif);
			
			 // Se recorre el ResultSet, mostrando por pantalla los resultados.
            while (rs.next())
            {
            	Proyecto proyecto = new Proyecto();
            	proyecto.setNombre_proyecto(rs.getString(1));
            	proyecto.setUltima_modificacion(rs.getString(2));
            	//Quitar el .0 que viene la final del timestamp
    
            	listaProyectos.add(proyecto);
            }
			
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
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
				if(conexion != null)
					conexion.close();
			} catch (Exception e) {
			}
		}
		
		return listaProyectos;
	}
	
}
