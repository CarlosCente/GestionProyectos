package com.cjhercen.gestion.proyectos.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class FechaUtils {

	private static Logger myLog = LoggerFactory.getLogger(FechaUtils.class);
	
	public Timestamp pasarStringAtimestamp(String fecha) {
		Timestamp timestamp = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		Date parsedDate;
		try {
			parsedDate = dateFormat.parse(fecha);
			timestamp = new java.sql.Timestamp(parsedDate.getTime());
		} catch (ParseException e) {
			myLog.error("Error a la hora de parsear las fechas");
			e.printStackTrace();
		}
		
		return timestamp;
	}
	
	/**
	 * Metodo que devuelve la fecha actual en milisegundos para pasarla posteriormente al formato de BD
	 * @return
	 */
	public String obtenerFechaActual () {
		Long fechaActualMilis = null;
		
		Calendar c2 = new GregorianCalendar();
		fechaActualMilis = c2.getTimeInMillis();
		
		return fechaActualMilis.toString();
	}
	
}
