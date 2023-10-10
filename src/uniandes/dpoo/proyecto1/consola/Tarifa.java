package uniandes.dpoo.proyecto1.consola;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;

public class Tarifa {

	private Reserva reserva;
    private double tarifaBase; //la tarifa base es la misma de cada categoria 
    private HashMap <String, Double> tarifasSeguros;
    private ArrayList<String>  segurosCliente;
    private HashMap <String, String> conductoresAdicionales;

    
    public Tarifa(Reserva reserva) throws IOException {
    	this.reserva = reserva; 
    	HashMap<String, Double> tarifasBase = cargarTarifas("./src/datos/jerarquia.txt");
        this.tarifaBase = tarifasBase.get(reserva.getCategoria());
        HashMap <String, Double> tarifasSeguros = cargarTarifasSeguros("./src/datos/Seguros.txt");
        this.tarifasSeguros = cargarTarifasSeguros("./src/datos/Seguros.txt");
        this.segurosCliente =reserva.getSegurosCliente();
        this.conductoresAdicionales = cargarConductoresAdicionales("./src/datos/ConductoresAdicionales.txt");
    }
    private HashMap<String,Double> cargarTarifas(String rutaArchivo) throws IOException{
    	HashMap<String,Double> tarifas = new HashMap<>();
    	BufferedReader  br = new BufferedReader (new FileReader(rutaArchivo));
    	String linea = "";
    	while ((linea = br.readLine()) != null) {
    		String[] partes = linea.split(", ");
            String numeroCategoria = partes[0];
            double tarifa = Double.parseDouble(partes[2]);
            tarifas.put(numeroCategoria, tarifa);    		
    	}
    	br.close();
        return tarifas;
    }
    private HashMap<String, Double> cargarTarifasSeguros(String rutaArchivo) throws IOException {
        HashMap<String, Double> tarifasSeguros = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));
        String linea;
        while ((linea = br.readLine()) != null) {
            String[] partes = linea.split(",");
            String nombreSeguro = partes[0];
            double tarifaSeguro = Double.parseDouble(partes[2]);
            tarifasSeguros.put(nombreSeguro, tarifaSeguro);
        }
        br.close();
        return tarifasSeguros;
    }
    
    private HashMap<String, String> cargarConductoresAdicionales(String rutaArchivo) throws IOException {
        HashMap<String, String> conductoresAdicionales = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));
        String linea;
        while ((linea = br.readLine()) != null) {
            String[] partes = linea.split(",");
            String idReserva = partes[0];
            String licenciaConductor = partes[3];
            conductoresAdicionales.put(idReserva, licenciaConductor);
        }
        br.close();
        return conductoresAdicionales;
    }
    
	    public double modificarTarifaBase() {
	        double tarifaModificada = this.tarifaBase;
	        if (reserva.esTemporadaAlta()) {
	            tarifaModificada += (tarifaBase * 0.2);
	        }
	        if (reserva.esEntregaOtraSede()) {
	            tarifaModificada += (tarifaBase * 0.15);
	        }
	        return tarifaModificada;
	    }
    
        public double calcularCostoTotalSeguros() {
            double costoTotal = 0;
            for (String seguro : segurosCliente) {
                Double costo = tarifasSeguros.get(seguro);
                if (costo != null){
                    costoTotal += costo;
                }
            }
            return costoTotal * reserva.getDiasAlquiler(); 
        }
        
        public double calcularCostoConductoresAd() {
            double costo = 0;
            for (String idReserva : conductoresAdicionales.keySet()) {
                if (idReserva.equals(reserva.getID())) {
                    costo += 50000;
                }
            }
            return costo;
        
        }
        
        public double calcularCostoFinal() {
            double costoTotalSeguros = calcularCostoTotalSeguros();
            double costoTarifaBaseModificada = modificarTarifaBase() * reserva.getDiasAlquiler();
            double costoConductoresAdicionales = calcularCostoConductoresAd();
            return costoTarifaBaseModificada + costoTotalSeguros + costoConductoresAdicionales;
            
        }
    	
    }
