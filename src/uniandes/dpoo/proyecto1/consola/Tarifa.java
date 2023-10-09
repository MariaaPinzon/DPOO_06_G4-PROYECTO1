package uniandes.dpoo.proyecto1.consola;

import java.io.IOException;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;

public class Tarifa {

	private Reserva reserva;
    private double tarifaBase; //la tarifa base es la misma de cada categoria 
    private HashMap <String, Double> tarifasSeguros;
    private String[] segurosCliente;

    public Tarifa(Reserva reserva) throws IOException {
    	this.reserva = reserva; 
    	HashMap<String, Double> tarifas = cargarTarifas("./src/datos/jerarquia.txt");
        this.tarifaBase = tarifas.get(reserva.getCategoria());
        this.tarifasSeguros = cargarTarifasSeguros("./src/datos/Seguros.txt");
        this.segurosCliente =reserva.getSegurosCliente();
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
    
        public double calcularCostoTotalSeguros() {
            double costoTotal = 0;
            for (String seguro : segurosCliente) {
                Double costo = tarifasSeguros.get(seguro);
                if (costo != null) {
                    costoTotal += costo;
                }
            }
            return costoTotal * reserva.getDiasAlquiler(); 
        }
        
        public double calcularCostoFinal() {
            double costoTotalSeguros = calcularCostoTotalSeguros();
            double costoTarifaBase = tarifaBase * reserva.getDiasAlquiler();
            return costoTarifaBase + costoTotalSeguros;
        }
    	
    }
