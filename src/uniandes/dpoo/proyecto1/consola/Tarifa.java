package uniandes.dpoo.proyecto1.consola;
import java.io.IOException;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Tarifa {
	
	private Reserva reserva;
    private long tarifaBase; //la tarifa base es la misma de cada categoria 
    private HashMap <String, Long> tarifasSeguros;
    private ArrayList<String>  segurosCliente;
    private HashMap <String, String> conductoresAdicionales;
    
    
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
    private HashMap<String, Long> cargarTarifasSeguros(String rutaArchivo) throws IOException {
        HashMap<String, Long> tarifasSeguros = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));
        String linea;
        linea = br.readLine();
        while (linea != null) {
            String[] partes = linea.split(",");
            String nombreSeguro = partes[0];
            long tarifaSeguro = Long.parseLong(partes[2]);
            tarifasSeguros.put(nombreSeguro, tarifaSeguro);
            linea = br.readLine();
        }
        br.close();
        return tarifasSeguros;
    }
    private HashMap<String, String> cargarConductoresAdicionales(String rutaArchivo) throws IOException {
        HashMap<String, String> conductoresAdicionales = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));
        String linea;
        linea = br.readLine();
        while (linea != null) {
            String[] partes = linea.split(",");
            String idReserva = partes[0];
            String licenciaConductor = partes[3];
            conductoresAdicionales.put(idReserva, licenciaConductor);
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
        for (int i=0; i<segurosCliente.size(); i++) {
        	String seguro = segurosCliente.get(i);
        	System.out.println(seguro);
            long costo = 0;
            costo = tarifasSeguros.get(seguro);
            if (costo != 0){
                costoTotal += costo;
            }
        }
        return costoTotal * reserva.getDiasAlquiler(); 
    }
    public long calcularCostoConductoresAd() {
        long costo = 0;
        for (String idReserva : conductoresAdicionales.keySet()) {
        	String idrevisar = String.valueOf(reserva.getID());
            if (idReserva.equals(idrevisar)) {
                costo += 50000;
            }
        }
        return costo;

    }

    public long calcularCostoFinal() {
        long costoTotalSeguros = calcularCostoTotalSeguros();
        long costoTarifaBaseModificada = modificarTarifaBase() * reserva.getDiasAlquiler();
        long costoConductoresAdicionales = calcularCostoConductoresAd();
        return costoTarifaBaseModificada + costoTotalSeguros + costoConductoresAdicionales;
    }


}