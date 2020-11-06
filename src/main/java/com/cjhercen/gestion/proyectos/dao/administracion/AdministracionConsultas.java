package com.cjhercen.gestion.proyectos.dao.administracion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.cjhercen.gestion.proyectos.models.AdministracionKilometraje;
import com.cjhercen.gestion.proyectos.utils.ConexionBD;

public class AdministracionConsultas {

	String queryConsultaKilometrajes = "SELECT * FROM PROV_LOC_KM";
	
	ConexionBD conexionBD = new ConexionBD();
	
	public List<AdministracionKilometraje> consultarKilometrajes() {
		List<AdministracionKilometraje> listaKilometrajes = new ArrayList<AdministracionKilometraje>();
		Statement st = null;
		ResultSet rs = null;
		Connection conexion = null;
		
		try {
			conexion = ConexionBD.getConnection();
			st = conexion.createStatement();
			rs = st.executeQuery (queryConsultaKilometrajes);
			
			 // Se recorre el ResultSet, mostrando por pantalla los resultados.
            while (rs.next())
            {
            	AdministracionKilometraje admKilometraje = new AdministracionKilometraje();
            	admKilometraje.setId_reg(rs.getInt(1));
            	admKilometraje.setLocalidad(rs.getString(2));
            	admKilometraje.setProvincia(rs.getString(3));            	
            	admKilometraje.setKilometros(rs.getDouble(4));
            	
            	listaKilometrajes.add(admKilometraje);
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
		
		return listaKilometrajes;
	}
	
}
