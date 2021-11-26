package com.api.fmc.utils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Constants {
	
	public static final String citaErrorFecha = "La fecha debe ser mayor al día del registro";
	public static final String CITA_NOT_FOUND= "Cita no encontrada";
	public static final String IMAGEN_NO_VALIDA="Imagen no valida";

	public static boolean verificarFechaCita(LocalDate date) {
		LocalDate compare = LocalDate.now();
		if(date.isBefore(compare) || date.isEqual(compare))
			return false;
		return true;
	}
	
	public static int obtenerEdad(LocalDate date) {
		LocalDate today = LocalDate.now();
		return (int) ChronoUnit.YEARS.between(date, today);
	}
}
