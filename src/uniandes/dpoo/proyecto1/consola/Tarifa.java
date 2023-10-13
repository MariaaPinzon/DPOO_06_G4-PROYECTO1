package uniandes.dpoo.proyecto1.consola;
import java.io.IOException;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Tarifa {
	
	private Reserva reserva;
    private long tarifaBase; //la tarifa base es la misma de cada categoria 
    private HashMap <String, ArrayList<String>> tarifasSeguros;
    private ArrayList<String>  segurosCliente;
    private HashMap<String, ArrayList<String>> conductoresAdicionales;
    
    
    public Tarifa(Reserva reserva) throws IOException {
    	this.reserva = reserva; 
    	HashMap<String, Long> tarifasBase = cargarTarifas("./src/datos/jerarquia.txt");
    	String categoriastr = reserva.findcategoria();
        this.tarifaBase = tarifasBase.get(categoriastr);
        this.tarifasSeguros = cargarTarifasSeguros("./src/datos/Seguros.txt");
        this.segurosCliente =reserva.getSegurosCliente();
        this.conductoresAdicionales = cargarConductoresAdicionales("./src/datos/ConductoresAdicionales.txt");
    }
    
    private HashMap<String,Long> cargarTarifas(String rutaArchivo) throws IOException{
    	HashMap<String,Long> tarifas = new HashMap<>();
    	BufferedReader  br = new BufferedReader (new FileReader(rutaArchivo));
    	String linea = "";
    	linea = br.readLine();
    	while (linea  != null) {
    		String[] partes = linea.split(",");
            String nombre = partes[1];
            long tarifa = Long.parseLong(partes[2]);
            tarifas.put(nombre, tarifa); 
            linea = br.readLine();
    	}
    	br.close();
        return tarifas;
    }
    public static HashMap<String, ArrayList<String>> cargarTarifasSeguros(String rutaArchivo) throws IOException {
        HashMap<String, ArrayList<String>> tarifasSeguros = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));
        String linea;
        linea = br.readLine();
        while (linea != null) {
            String[] partes = linea.split(",");
            String idSeguro = partes[0];
            ArrayList<String> datosSeguro = new ArrayList<>();
            datosSeguro.add(partes[1]); // Descripción seguro
            datosSeguro.add(partes[2]); // Costo seguro
            tarifasSeguros.put(idSeguro, datosSeguro);
            linea = br.readLine();
        }
        br.close();
        return tarifasSeguros;
    }
    private HashMap<String, ArrayList<String>> cargarConductoresAdicionales(String rutaArchivo) throws IOException {
        HashMap<String, ArrayList<String>> conductoresAdicionales = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));
        String linea;
        linea = br.readLine();
        int numeroLinea = 1;
        while (linea != null) {
            String[] partes = linea.split(",");
            if (partes.length < 3) {
                System.out.println("Error en la línea " + numeroLinea + ": " + linea); // Mensaje de depuración
                continue; 
            }
            String licenciaCliente = partes[0];
            String licenciaConductor = partes[2];
            ArrayList<String> conductores = conductoresAdicionales.get(licenciaCliente);
            if (conductores == null) {
                conductores = new ArrayList<>();
                conductoresAdicionales.put(licenciaCliente, conductores);
            }
            conductores.add(licenciaConductor);
            linea = br.readLine();
        }
        br.close();
        return conductoresAdicionales;
    }

	    public long modificarTarifaBase() {
	        long tarifaModificada = this.tarifaBase;
	        if (reserva.esTemporadaAlta()) {
	            tarifaModificada += (tarifaBase * 0.2);
	        }
	        if (reserva.esEntregaOtraSede()) {
	            tarifaModificada += (tarifaBase * 0.15);
	        }
	        return tarifaModificada;
	    }

	    public long calcularCostoTotalSeguros() {
	        long costoTotal = 0;
	        for (int i = 0; i < segurosCliente.size(); i++) {
	            String idSeguro = segurosCliente.get(i);
	            ArrayList<String> datosSeguro = tarifasSeguros.get(idSeguro);
	            if (datosSeguro != null && datosSeguro.size() > 1) {
	                long costo = Long.parseLong(datosSeguro.get(1)); //  El costo que es el segundo elemento  ArrayList
	                costoTotal += costo;
	            }
	        }
	        return costoTotal * reserva.getDiasAlquiler();
	    }
    public long calcularCostoConductoresAd() {
        long costo = 0;
    	String licenciaClienteRevisar = reserva.getCliente().getNumeroLicencia();
        ArrayList<String> conductores = conductoresAdicionales.get(licenciaClienteRevisar);
        if (conductores != null) {
            costo = conductores.size() * 50000;
        }
        return costo;

    }

    public long calcularCostoFinal() {
        long costoTotalSeguros = calcularCostoTotalSeguros();
        long costoTarifaBaseModificada = modificarTarifaBase() * reserva.getDiasAlquiler();
        return costoTarifaBaseModificada + costoTotalSeguros ;
    }


}
