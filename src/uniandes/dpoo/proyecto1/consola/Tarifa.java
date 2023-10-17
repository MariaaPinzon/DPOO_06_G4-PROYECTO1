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
    
     /**
     * Carga las tarifas de alquiler desde un archivo y las almacena en un HashMap.
     * precond: Las categorías del sistema ya deben estar definidas.
     *          El archivo de tarifas debe existir y ser accesible para lectura.
     * postcond: El método cargará las tarifas de alquiler desde el archivo y las almacenará en un HashMap.
     *           Se devolverá un HashMap que contiene las tarifas, donde la llave es la categoría y el valor es la tarifa correspondiente.
     * @param - N/A
     * @return Un HashMap que contiene las tarifas de alquiler, donde la llave es la categoría y el valor es la tarifa correspondiente.
     * @throws IOException Si ocurre un error al leer el archivo de tarifas, como problemas de acceso al archivo o un formato incorrecto.
     */
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

    /**
     * Cargar las tarifas de seguros desde un archivo y las almacena en un HashMap.
     * precond: El archivo de seguros (Seguros.txt) debe existir y ser accesible para lectura.
     *          El archivo de seguros debe estar en un formato válido, con cada línea que contenga:
     *              - Un identificador de seguro (ID de seguro).
     *              - Una descripción de seguro.
     *              - Un costo de seguro.
     * postcond: Se cargará las tarifas de seguros desde el archivo y las almacenará en un HashMap.
     *           Se devolverá un HashMap donde la clave es el ID de seguro y el valor es una lista que contiene:
     *              - La descripción del seguro (índice 0).
     *              - El costo del seguro (índice 1).
     * @param - N/A
     * @return Un HashMap que contiene las tarifas de los seguros.
     * @throws IOException Si ocurre un error al leer el archivo de seguros, como problemas de acceso al archivo.
     */
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

    /**
     * Carga la información de conductores adicionales desde un archivo y las almacena en un HashMap.
     * precond: El archivo de conductores adicionales (ConductoresAdicionales.txt) debe existir y ser accesible para lectura.
     *          El archivo de conductores adicionales debe estar en un formato válido, con cada línea que contenga:
     *            - La licencia del cliente principal.
     *            - La licencia del conductor adicional
     * postcond: Se cargará la información de conductores adicionales desde el archivo y la almacenará en un HashMap.
     *           Se devolverá un HashMap donde la llave es la licencia del cliente principal y el valor es una lista que contiene las licencias de los conductores adicionales asociados a ese cliente.
     * @return Un HashMap que contiene la información de conductores adicionales asociados a los clientes principales.
     * @throws IOException Si ocurre un error al leer el archivo de conductores adicionales, como problemas de acceso al archivo.
     */
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

    /**
     * Modifica la tarifa base de acuerdo a ciertas condiciones.
     * precond: La variable `tarifable` debe ser válida y proporcionar información sobre la temporada alta y la entrega en otra sede.
     * postcond: El método calculará una tarifa modificada en función de las condiciones proporcionadas por `tarifable`.
     *           Si la temporada alta está activa (según `tarifable.esTemporadaAlta()`), se aplicará un incremento del 20% a la tarifa base.
     *           Si la entrega se realiza en otra sede (según `tarifable.esEntregaOtraSede()`), se aplicará un incremento del 15% adicional a la tarifa base.
     *           Se devolverá el valor de la tarifa modificada después de aplicar los incrementos.
     * @param - N/A
     * @return Un valor long que respresenta la tarifa modificada.
     * @throws - N/A
     */
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

    /**
     * Calcular el costo total de los seguros contratados por el cliente.
     * precond: La variable `segurosCliente` debe contener una lista válida de identificadores de seguros.
     *          La variable `tarifasSeguros` debe contener las tarifas de los seguros.
     *          Se debe saber por cúantos días es el alquiler.
     * postcond: El método calculará el costo total sumando el costo de los seguros contratados por el cliente.
     *           El costo total se calculará multiplicando el costo de los seguros por la duración del alquiler en días (obtenida desde `tarifable`).
     * @param - N/A
     * @return Un valor long que representa el costo total de los seguros contratados.
     * @throws - N/A
     */
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

    /**
     * Calcula el costo adicional de conductores para el cliente.
     * precond: Se debe tener la información de la reserva o alquiler, pues se necesita tener información del cliente.
     *          Se debe tener la información de los conductores adicionales.
     * postcond: Se calculará el costo adicional de conductores multiplicando el número de conductores adicionales por 50,000 COP
     * @param - N/A
     * @return Un valor long que respresenta el costo de conductores adicionales.
     * @throws IOException Si ocurre un error al cargar los datos de conductores adicionales.
     */
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

    /**
     * Calcular el costo total del alquiler, incluyendo tarifa base modificada y el costo total de seguros. 
     * precond: Se debe tener la forma de calcular la tarifa base modificada.
     *          Se debe tener la forma de calcular el costo total de los seguros seleccionados por el cliente.
     *          Se debe tener infromación de la reserva o alquiler, pues se necesita saber por cuántos días se hará el alquiler.
     * postcond: Se calculará el costo total del alquiler.
     * @param - N/A
     * @return Un valor long que representa el costo final del alquiler. 
     * @throws - N/A
     */
    public long calcularCostoFinal() {
        long costoTotalSeguros = calcularCostoTotalSeguros();
        long costoTarifaBaseModificada = modificarTarifaBase() * tarifable.getDiasAlquiler();
        return costoTarifaBaseModificada + costoTotalSeguros ;
    }
    
    /**
     * Calcular el costo equivalente al 30% del costo final del alquiler.
     * precond: Se debe tener la forma de calcular la tarifa base modificada.
     *          Se debe tener la forma de calcular el costo total de los seguros seleccionados por el cliente.
     *          Se debe tener la forma de calcular el costo final del alquiler.
     *          Se debe tener infromación de la reserva o alquiler, pues se necesita saber por cuántos días se hará el alquiler.
     * postcond: Se calculará el 30% de la cuenta final del alquiler.
     * @param - N/A
     * @return Un valor long que representa el 30% del costo final del alquiler. 
     * @throws - N/A
     */
    public long calcularCosto30P() {
		long preciofinal = calcularCostoFinal();
		long precio30 = (long) (preciofinal*0.3);
		return precio30;
    }

    /**
     * Calcular el costo equivalente al 70% del costo final del alquiler.
     * precond: Se debe tener la forma de calcular la tarifa base modificada.
     *          Se debe tener la forma de calcular el costo total de los seguros seleccionados por el cliente.
     *          Se debe tener la forma de calcular el costo final del alquiler.
     *          Se debe tener infromación de la reserva o alquiler, pues se necesita saber por cuántos días se hará el alquiler.
     * postcond: Se calculará el 70% de la cuenta final del alquiler.
     * @param - N/A
     * @return Un valor long que representa el 70% del costo final del alquiler. 
     * @throws - N/A
     */
    public long calcularCosto70P() {
		long preciofinal = calcularCostoFinal();
		long precio70 = (long) (preciofinal*0.7);
		return precio70;
    }

}
