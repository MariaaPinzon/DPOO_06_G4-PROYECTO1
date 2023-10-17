package uniandes.dpoo.proyecto1.consola;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Interfaz que define métodos para calcular tarifas y proporcionar información relacionada con el proceso de reservas y alquiler.
 */
public interface Tarifable {
	Cliente getCliente();
	String getFechaIni();
	String getFechaFin();
	String getHoraFin();
	String getSedeinicial();
	String getSedefinal();
	int getCategoria();
	long modificarTarifaBase() throws IOException;
	long calcularCostoConductoresAd() throws IOException;
	long calcularCosto70P() throws IOException;
	ArrayList<String> getSegurosCliente();
	boolean esTemporadaAlta();
	boolean esEntregaOtraSede();
	int getDiasAlquiler();
	int diferenciadias(String fechaIni, String fechaFin);
}
