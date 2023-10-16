package uniandes.dpoo.proyecto1.consola;

import java.io.IOException;
import java.util.ArrayList;

// tarifable son las clases a las que se les puede sacar una tarifa
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
