package com.cjhercen.gestion.proyectos.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Permite obtener transacciones y conexiones a BD
 */
public final class ConexionBD {

	private static Logger myLog = LoggerFactory.getLogger(ConexionBD.class);

    /**
	 * Obtiene la conexion a la base de datos mediante JDBC
     * @return Una conexión JDBC.
     * @throws NamingException Si hay error al localizar el DataSource 
     * @throws SQLException Si hay error al abrir la conexión
     *      */
    public static Connection getConnection()
            throws SQLException, NamingException {
    	Connection conexion = null;
    	try
    	{
    	   Class.forName("com.mysql.cj.jdbc.Driver");
       	   //Se establece la conexion con la BD
    	   conexion = DriverManager.getConnection ("jdbc:mysql://localhost/db_gestionproyectos?serverTimezone=Europe/Madrid&allowPublicKeyRetrieval=true&useSSL=false","root", "c13j12h92cZA*");
    	   myLog.info("La conexion con la BD se ha establecido con éxito");
    	} catch (Exception e)
    	{
    	   e.printStackTrace();
    	} 
    	

        return conexion;
    }

}