package uniandes.dpoo.proyecto1.consola;
import java.io.IOException;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public  class Tarifa {
	
	private Tarifable tarifable;
    private long tarifaBase; //la tarifa base es la misma de cada categoria 
    private HashMap <String, ArrayList<String>> tarifasSeguros;
    private ArrayList<String>  segurosCliente;
    private HashMap<String, ArrayList<String>> conductoresAdicionales;
    
    
    public Tarifa(Tarifable tarifable) throws IOException {
    	this.tarifable = tarifable;
    	HashMap<String, Long> tarifasBase = cargarTarifas();
    	String categoriastr = String.valueOf(tarifable.getCategoria());
    	this.tarifaBase = tarifasBase.get(categoriastr);
        this.tarifasSeguros = cargarTarifasSeguros();
        this.segurosCliente =tarifable.getSegurosCliente();
        this.conductoresAdicionales = cargarConductoresAdicionales();
    }
    
    private HashMap<String,Long> cargarTarifas() throws IOException{
    	HashMap<String,Long> tarifas = new HashMap<>();
    	BufferedReader  br = new BufferedReader (new FileReader(("./src/datos/jerarquia.txt")));
    	String linea = "";
    	linea = br.readLine();
    	while (linea  != null) {
    		String[] partes = linea.split(",");
    		String categoria = partes[0];
    		long tarifa = Long.parseLong(partes[2]);
    		tarifas.put(categoria, tarifa);
            linea = br.readLine();
    	}
    	br.close();
        return tarifas;
    }
    public static HashMap<String, ArrayList<String>> cargarTarifasSeguros() throws IOException {
        HashMap<String, ArrayList<String>> tarifasSeguros = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader("./src/datos/Seguros.txt"));
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
    private HashMap<String, ArrayList<String>> cargarConductoresAdicionales() throws IOException {
        HashMap<String, ArrayList<String>> conductoresAdicionales = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader("./src/datos/ConductoresAdicionales.txt"));
        String linea;
        linea = br.readLine();
        while (linea != null) {
            String[] partes = linea.split(",");
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
	        if (tarifable.esTemporadaAlta()) {
	            tarifaModificada += (tarifaBase * 0.2);
	        }
	        if (tarifable.esEntregaOtraSede()) {
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
	        return costoTotal * tarifable.getDiasAlquiler();
	    }
    public long calcularCostoConductoresAd() throws IOException {
        this.conductoresAdicionales = cargarConductoresAdicionales();
        long costo = 0;
    	String licenciaClienteRevisar = tarifable.getCliente().getNumeroLicencia();
        ArrayList<String> conductores = conductoresAdicionales.get(licenciaClienteRevisar);
        if (conductores != null) {
            costo = conductores.size() * 50000;
        }
        return costo;

    }

    public long calcularCostoFinal() {
        long costoTotalSeguros = calcularCostoTotalSeguros();
        long costoTarifaBaseModificada = modificarTarifaBase() * tarifable.getDiasAlquiler();
        return costoTarifaBaseModificada + costoTotalSeguros ;
    }
    
    public long calcularCosto30P() {
		long preciofinal = calcularCostoFinal();
		long precio30 = (long) (preciofinal*0.3);
		return precio30;
    }

    public long calcularCosto70P() {
		long preciofinal = calcularCostoFinal();
		long precio70 = (long) (preciofinal*0.7);
		return precio70;
    }

}
