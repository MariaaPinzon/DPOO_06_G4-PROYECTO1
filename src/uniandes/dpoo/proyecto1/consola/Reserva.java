package uniandes.dpoo.proyecto1.consola;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Reserva implements Tarifable {
	private Cliente cliente;
	private String fechaIni;
	private String horaIni;
	private String fechaFin;
	private String horaFin;
	private int diasAlquiler;
	private String sedeinicial;
	private String sedefinal;
	private int ID;
	private int categoria;
	private ArrayList<String> seguros; 
	private static Empleado empleado;
	private boolean esEspecial = false;

	public Reserva(Cliente cliente, String fechaIni, String horaIni, String fechaFin, String horaFin, String sedeIn, String sedeFin, int categoria, ArrayList<String> seguros) {
		this.cliente= cliente;
		this.fechaIni= fechaIni;
		this.horaIni= horaIni;
		this.fechaFin= fechaFin;
		this.horaFin= horaFin;
		this.sedeinicial= sedeIn;
		this.sedefinal= sedeFin;
		//Se asume que todos los meses tienen 30 días
		this.diasAlquiler= diferenciadias(fechaIni,fechaFin);
		this.categoria = categoria;
		this.seguros = seguros;
		this.ID= crearID();
	}

	public Reserva(int ID,Cliente cliente, String fechaIni, String horaIni, String fechaFin, String horaFin, String sedeIn, String sedeFin, int categoria,ArrayList<String> seguros) {
		this.cliente= cliente;
		this.fechaIni= fechaIni;
		this.horaIni= horaIni;
		this.fechaFin= fechaFin;
		this.horaFin= horaFin;
		this.sedeinicial= sedeIn;
		this.sedefinal= sedeFin;
		this.diasAlquiler= diferenciadias(fechaIni,fechaFin);
		this.categoria = categoria;
		this.ID= ID;
		this.seguros = seguros;
		this.setEsEspecial(true);

	}
	public Reserva(Empleado empleado, int categoria, String fecha, String sede1, String sede2) {
		this.empleado=empleado;
		this.categoria=categoria;
		this.fechaIni=fecha;
		this.fechaFin=fecha;
		this.sedeinicial=sede1;
		this.sedefinal=sede2;
		this.diasAlquiler=diferenciadias(fecha,fecha);
		this.ID= crearID();
	}
	public Reserva(Empleado empleado, int ID, int categoria, String fecha, String sede1, String sede2) {
		this.empleado=empleado;
		this.categoria=categoria;
		this.fechaIni=fecha;
		this.fechaFin=fecha;
		this.sedeinicial=sede1;
		this.sedefinal=sede2;
		this.diasAlquiler=diferenciadias(fecha,fecha);
		this.ID= ID;
		this.esEspecial=true;
	}

	/** 
	 * Calcula la diferecia entre las dos fechas de la reserva, para lo cual asume que todos los meses tienen 30 días
	 * precond: La reserva debe estar inicializada.
	 * 			Las fechas de inicio y fin deben estar disponibles y ser válidas en el formato "dd/mm".
 	 *			El formato de fecha debe ser consistente y seguir el patrón "día/mes".
	 * postcond: Se calcula exitosamente el número de días de diferencia, incluyendo el día de inicio y fin.
	 * @param fechaIni La fecha de inicio.
	 * @param fechaFin La fecha de fin.
	 * @return En forma de int, retorna los días que hay entre la fecha de inicio y fin.
	 * @throws - N/A
	 */
	@Override
	public int diferenciadias(String fechaIni, String fechaFin) {
	//Se asume que todos los meses tienen 30 días
	int diferencia = 0;
	String inicio[] = fechaIni.split("/");
	int diainicio = Integer.parseInt(inicio[0]);
	int mesinicio = Integer.parseInt(inicio[1]);
	String fin[] = fechaFin.split("/");
	int diafin = Integer.parseInt(fin[0]);
	int mesfin = Integer.parseInt(fin[1]);
	if (mesinicio!=mesfin) {
		int diferenciames = (mesfin-mesinicio)-1;
		int dias_para_acabar_mes_inicial = 30-diainicio;
		diferencia = (30*diferenciames)+dias_para_acabar_mes_inicial+diafin;
	}
	else {
		diferencia = diafin-diainicio;
	}
	
	return diferencia+1;
	}
	
	public int crearID() {
		Random x = new Random();
		int rando = x.nextInt(9999);
		return rando;
	}
	public void escribirTXT() throws Exception {
		FileWriter output = new FileWriter("./src/datos/ListaReserva.txt", true);
		BufferedWriter br = new BufferedWriter(output);
		String nombrecliente = cliente.getNombre();

	    String segurosStr = String.join(";", seguros);  //  ';' para separar los seguros en la cadena

	    br.write(ID + "," + nombrecliente + "," + categoria + "," + diasAlquiler + "," + fechaIni + "," + horaIni + "," + fechaFin + "," + horaFin + "," + sedeinicial + "," + sedefinal + "," + segurosStr+"\n");
	    br.close();
	}
	@Override
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public void escribirTXTespecial(String enlace) throws Exception {
		FileWriter output = new FileWriter(enlace, true);
		BufferedWriter br = new BufferedWriter(output);
		String nombreempleado = empleado.getNombre();

	    br.write(ID + "," + nombreempleado + "," + categoria + "," + diasAlquiler + "," + fechaIni + ","+ fechaFin + ","+ sedeinicial + "," + sedefinal +",ESPECIAL"+",NA,NA"+"\n");
	    br.close();
	}
	
	
	public String getinfo() {
		String nombrecliente = cliente.getNombre();
		String categoriaS = findcategoria();
		String segurosStr = String.join(";", seguros); 

		return "Cliente que realiza la reserva: "+nombrecliente+"\nBusca un carro de la categoría:"+categoriaS+" por: "+diasAlquiler+" dias"
				+"\nDesde el día "+fechaIni+" a la hora "+horaIni+"\nHasta el día "+fechaFin+" a la hora "+horaFin
				+"\nArrendado en la sede "+sedeinicial+" y regresado en la sede "+sedefinal
				+"\nSu identificación de la reserva es: "+ID+"\nSeguros seleccionados: " + segurosStr;
	}
	public String getinfoEspecial() {
		String nombreempleado = empleado.getNombre();
		String categoriaS = findcategoria();
		return "Empleado que realiza la reserva: "+nombreempleado
				+"\nCarro de la categoría "+categoriaS
				+"\nel día "+fechaIni+" de la sede "+sedeinicial+" a la sede "+ sedefinal+"\nCon el identificador: "+ID;
	}

	/**
	 * Busca la categoría del vehículo de la reserva en forma de String.
	 * precond: La reserva debe estar inicializada.
	 * 			El valor de la categoría del vehículo de la reserva debe estar correctamente establecido. Debe ser un número entre 1-6.
	 * postcond: Se encuentra exitosamente la categoría del vehículo.
	 * @param - N/A
	 * @return En forma de String, se retorna la categoría del vehículo. Las categorías establecidad son:
	 * 			- economico
	 * 			- estándar
	 * 			- van
	 * 			- SUV
	 * 			- todoterreno
	 * 			- lujo
	 * @throws- N/A
	 */
	public String findcategoria() {
		String resp = "";
		if (categoria==1) {
			resp = "economico";
		}
		if (categoria==2) {
			resp = "estándar";
		}
		if (categoria==3) {
			resp = "van";
		}
		if (categoria==4) {
			resp = "SUV";
		}
		if (categoria==5) {
			resp = "todoterreno";
		}
		if (categoria==6) {
			resp = "lujo";
		}
		return resp;
	}

	/**
	 * Dadas las fechas de inicio y fin de una reserva, el método define si está o no en temporada alta.
	 * precond: La reserva debe estar inicializada.
	 * 			Las fechas de inicio y fin de la reserva deben estar disponibles y ser válidas.
	 * postcond: Se define si un período de alquiler está en temporada alta o no.
	 * @param - N/A
	 * @return Un booleano que es true si el periodo de alquiler cae en temporada alta, o falso en caso contrario. 
	 * @throws - N/A
	 */
	@Override
	public boolean esTemporadaAlta() {
	    String[] fechaIniParts = fechaIni.split("/");
	    int mesFechaIni = Integer.parseInt(fechaIniParts[1]);
	    String[] fechaFinParts = fechaFin.split("/");
	    int mesFechaFin = Integer.parseInt(fechaFinParts[1]);
		boolean  esAlta = false;

	    for (int mes = mesFechaIni; mes <= mesFechaFin; mes++) { // de junio (6) a diciembre (12) es alta
	        if (mes >= 6 && mes <= 12) {
	            esAlta = true;
	        }
	    }
		return esAlta;
		}

	/**
	 * Verifica si la entrega del vehículo de una reserva se realiza en una sede diferente a la sede inicial y devuelve un valor booleano.
	 * precond: La reserva debe estar inicializada.
	 * 			Los datos de las sedes inciales y finales de la reserva deben ser válidos y estar disponibles.
	 * postcond:Se verifica si la entrega del vehpiculo se realiza en una sede diferente a la sede inicial.
	 * @param - N/A
	 * @return Un booleano que es true si la entrega del vehículo de la reserva se realiza en una sede diferente a la sede inicial o false en caso contrario.
	 * @throws - N/A
	 */
	@Override
	public boolean esEntregaOtraSede() {
		String sedeInicial = getSedeinicial();
		String sedeFinal = getSedefinal();
		boolean esOtraSede = true;
		if (sedeInicial.equals(sedeFinal)) {
			esOtraSede = false;
		}
		return esOtraSede;
	}
	
	/**
	 * Busca una reserva por su ID.
	 * precond: El archivo especificado por la ruta "datos/ListaReserva.txt" debe existir y ser accesible para lectura.
	 * 			El ID de la reserva, representado por "idComparador", debe ser un número entero válido.
	 * 			El formato de las reservas en el archivo debe ser consistente y seguir el patrón "ID, nombrecliente, categoria, ..., seguros".
	 * 			El cliente asociado a la reserva debe existir en el archivo "datos/Usuarios.txt".
	 * postcond: El método busca una reserva por su ID y la devuelve como un objeto Reserva si se encuentra.
	 *			 El objeto Reserva contendrá información relevante de la reserva, como el cliente, fechas, sedes, categoría y seguros.
	 * 			 Si la reserva no se encuentra o el cliente asociado no existe, el método devuelve null. 
	 * @param idComparador El ID de la reserva que se desea buscar.
	 * @return Se retorna el objeto Reserva que se encontró.
	 * @throws IOException Si hay algún error al leer el archivo de reservas, como problemas de acceso al archivo o si el cliente no se encuentra.
 	 */
	public static Reserva encontrarReservaPorID( int idComparador) throws IOException {
	    BufferedReader br = new BufferedReader(new FileReader("./src/datos/ListaReserva.txt"));
	    String linea = br.readLine();
	    while (linea != null) {
	        String[] inforeserva = linea.split(",");
	        int ID = Integer.parseInt(inforeserva[0]);
	        if (ID == idComparador) {
	            String nombrecliente = inforeserva[1];
	            int categoria = Integer.parseInt(inforeserva[2]);
	            String fechaIni = inforeserva[4];
	            String horaIni = inforeserva[5];
	            String fechaFin = inforeserva[6];
	            String horafin = inforeserva[7];
	            String sedein = inforeserva[8];
	            String sedeout = inforeserva[9];
	            
	            ArrayList<String> seguros = new ArrayList<>();
	            String segurosgrande = inforeserva[10];
	            String[] seguroslistagrande = segurosgrande.split(";");
	            for (int i = 0; i < seguroslistagrande.length; i++) {
	                seguros.add(seguroslistagrande[i]);
	            }

	            Cliente cliente = Cliente.encontrarClientePorNombre("./src/datos/Usuarios.txt", nombrecliente);
	            if (cliente == null) {
	                br.close();
	                throw new IOException("Cliente no encontrado");
	            }
	            if (! inforeserva[8].equals("ESPECIAL")) {
	            Reserva reserva = new Reserva(ID, cliente, fechaIni, horaIni, fechaFin, horafin, sedein, sedeout, categoria, seguros);
	            br.close();
	            return reserva; 
	            }
	            else {
	            	BufferedReader br2 = new BufferedReader(new FileReader("./src/datos/Usuarios.txt"));
	        	    String linearev = br2.readLine();
	        	    Empleado empleado = null;
	        	    while (linearev != null) {
	        	        String[] info = linea.split(",");
	        	        if (nombrecliente.equals(info[0])) {
	        	        	empleado = new Empleado(info[0], info[1], info[2], info[3], info[4], info[5], info[6], info[7]);
	        	            br2.close();
	        	        	}
	        	        linearev = br2.readLine();
	        	    
	        	        
	        	    }
	        	    br2.close();
	            	Reserva reserva = new Reserva(empleado,ID, categoria, fechaIni, sedein, sedeout);
	            	return reserva;
	        	     }
	            
	        }
	        linea = br.readLine();
	    }
	    br.close();
	    return null; 
	}
	
	/**
	 * Busca una reserva por el nombre del cliente.
	 * precond: El archivo especificado por la ruta "datos/ListaReserva.txt" debe existir y ser accesible para lectura.
	 * 			El nombre del cliente, representado por "nombreClienteComparador", debe ser una cadena no nula y no vacía.
	 *			El formato de las reservas en el archivo debe ser consistente y seguir el patrón "ID, nombrecliente, categoria, ..., seguros".
	 *			El cliente asociado a la reserva debe existir en el archivo "datos/Usuarios.txt".
	 * postcond: El método busca una reserva por el nombre del cliente y la devuelve como un objeto Reserva si se encuentra.
	 *			 El objeto Reserva contendrá información relevante de la reserva, como el cliente, fechas, sedes, categoría y seguros.
	 *			 Si la reserva no se encuentra o el cliente asociado no existe, el método devuelve null.
	 * @param nombreClienteComparador El nombre del cliente para el cual se desea realizar la búsqueda de la reserva.
	 * @return Un objeto Reserva si encuentra la reserva, o null si no se encuentra.
	 * @throws IOException Si hay algún error al leer el archivo de reservas, como problemas de acceso al archivo o si el cliente no se encuentra.
	 */
	public static Reserva encontrarReservaPorNombre( String nombreClienteComparador) throws IOException {
	    BufferedReader br = new BufferedReader(new FileReader("./src/datos/ListaReserva.txt"));
	    String linea = br.readLine();
	    while (linea != null) {
	        String[] inforeserva = linea.split(",");
	        String nombre = inforeserva[1];
	        if (nombre.equals(nombreClienteComparador)) {
	            int ID = Integer.parseInt(inforeserva[0]);
	            int categoria = Integer.parseInt(inforeserva[2]);
	            String fechaIni = inforeserva[4];
	            String horaIni = inforeserva[5];
	            String fechaFin = inforeserva[6];
	            String horafin = inforeserva[7];
	            String sedein = inforeserva[8];
	            String sedeout = inforeserva[9];
	            
	            ArrayList<String> seguros = new ArrayList<>();
	            String segurosgrande = inforeserva[10];
	            String[] seguroslistagrande = segurosgrande.split(";");
	            for (int i = 0; i < seguroslistagrande.length; i++) {
	                seguros.add(seguroslistagrande[i]);
	            }

	            Cliente cliente = Cliente.encontrarClientePorNombre("./src/datos/Usuarios.txt", nombreClienteComparador);
	            if (cliente == null) {
	                br.close();
	                throw new IOException("Cliente no encontrado");
	            }

	            Reserva reserva = new Reserva(ID, cliente, fechaIni, horaIni, fechaFin, horafin, sedein, sedeout, categoria, seguros);
	            br.close();
	            return reserva; 
	        }
	        linea = br.readLine();
	    }
	    br.close();
	    return null; 
	}
	
	/**
	 * Elimina una reserva dado su ID.
	 * precond: El archivo especificado por la ruta "datos/ListaReserva.txt" debe existir y ser accesible para lectura.
 	 *			El ID proporcionado para eliminar la reserva debe ser un valor entero positivo.
	 * postcond: El método busca una reserva por su ID y, si se encuentra, la elimina del archivo de reservas.
	 *			 Se actualiza la lista temporal "listaTemporal" con las reservas restantes que no se eliminaron.
	 * @param id El identificador único de la reserva que se desea eliminar.
	 * @param listaTemporal Una lista temporal que se actualiza con las reservas restantes depués de elminiar la reserva. Itera por todas
	 * 						las reservas y las guarda en esta lista, todas MENOS la del id dado por el usuario. Así se elimina la reserva.
	 * @return Si se encuentra y elimina con éxito una reserva con el ID proporcionado, el retorno será true, de caso contratio será false.
	 * @throws IOException
	 */
    public static boolean eliminarReservaPorID(int id, ArrayList<String> listaTemporal) throws IOException {
        Reserva reservaEncontrada = encontrarReservaPorID(id);
        boolean encontro_reserva = false;
        
        if (reservaEncontrada != null) {
        encontro_reserva = true;}
        
        if (encontro_reserva) {
            String IDorigin = String.valueOf(id);
            try (BufferedReader lect_usuario = new BufferedReader(new FileReader("./src/datos/ListaReserva.txt"))) {
				String linearev = lect_usuario.readLine();
                while (linearev != null) {
                    String[] separar = linearev.split(",");
                    String IDcomparar = separar[0];
                    if (!IDorigin.equals(IDcomparar)) {
                        listaTemporal.add(linearev);
                    }
                    linearev = lect_usuario.readLine();
                }
			}
            }
        
        return encontro_reserva;
    }

	/**
	 * Se elimina una reserva por su ID único, y se actualiza el archivo de reservas con la lista temporal.
	 * precond: El archivo especificado por la ruta "datos/ListaReserva.txt" debe existir y ser accesible para lectura y escritura.
 	 *			El ID proporcionado para eliminar la reserva debe ser un valor entero positivo.
	 * postcond: El método busca una reserva por su ID y, si se encuentra, la elimina del archivo de reservas.
 	 * 			 Se actualiza el archivo "datos/ListaReserva.txt" con las reservas restantes en la lista temporal.
	 * 			 Si se encuentra y elimina con éxito una reserva con el ID proporcionado, el archivo de reservas se actualiza y no se produce ninguna excepción.
	 * 			 Si no se encuentra una reserva con el ID especificado, no se realiza ninguna modificación en el archivo.
	 * @param id El identificador único de la reserva que se quiere eliminar.
	 * @throws IOException Si hay algún error al leer o escribir el archivo de reservas, como problemas de acceso al archivo.
	 */
    public static void eliminarReservaYActualizarArchivo(int id) throws IOException {
        ArrayList<String> listaTemporal = new ArrayList<>();
        boolean encontro_reserva = eliminarReservaPorID(id, listaTemporal);
        
        if (encontro_reserva) {
            FileWriter output = new FileWriter("./src/datos/ListaReserva.txt");
            for (String linea : listaTemporal) {
                output.write(linea + "\n");
            }
            output.close();
        }
    }
    public static void eliminarReservaEspecial(int id) throws IOException {
        ArrayList<String> listaTemporal = new ArrayList<>();
        boolean encontro_reserva = eliminarReservaPorID(id, listaTemporal);
        
        if (encontro_reserva) {
            FileWriter output = new FileWriter("./src/datos/ListaReserva.txt");
            for (String linea : listaTemporal) {
                output.write(linea + "\n");
            }
            output.close();
        }
    }
    
	@Override
	public String getFechaIni() {
		return fechaIni;
	}
	public String getHoraIni() {
		return horaIni;
	}
	@Override
	public String getFechaFin() {
		return fechaFin;
	}
	@Override
	public String getHoraFin() {
		return horaFin;
	}
	@Override
	public int getDiasAlquiler() {
		return diasAlquiler;
	}
	@Override
	public String getSedeinicial() {
		return sedeinicial;
	}
	@Override
	public String getSedefinal() {
		return sedefinal;
	}
	public int getID() {
		return ID;
	}
	@Override
	public int getCategoria() {
		return categoria;
	}
	
	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}
	@Override
	public ArrayList<String> getSegurosCliente() {
        return seguros;
    }
	public Empleado getEmpleado() {
		return empleado;
	}

	/**
	 * Modifica la tarifa base del sistema y devuelve la nueva tarifa base resultante.
	 * precond: la reserva debe estar inicializada.
	 * 			Los datos necesarios para calcular la nueva tarifa deben estar disponibles y correctos en el sistema.
	 * 			Se debe tener acceso a la clase Tarifa.
	 * postcond: Se modifica la tarifa base del sistema.
	 * @param - N/A
	 * @return En un long, la nueva tarifa base del sistema después de la modificación.
	 * @throws IOException Si ocurre un error durante la modificación de la tarifa base, como problemas de acceso a los datos necesarios.
	 */
	@Override
	public long modificarTarifaBase() throws IOException {
        Tarifa tarifa = new Tarifa(this); 
        return tarifa.modificarTarifaBase();
	}

	/**
	 * Calcula el costo adicional por conductores y devuelve el resultado.
	 * precond: La reserva debe estar inicializada.
	 * 			Los datos necesarios para calcular el costo adicional deben estar disponibles y correctos en el sistema, incluyendo la información del vehículo y los conductores adicionales.
	 * 			El sistema debe tener acceso a la clase Tarifa para realizar el cálculo.
	 * 			El vehículo debe estar reservado y sus detalles deben estar registrados en el sistema.
	 * 			Los conductores adicionales asociados al vehículo deben estar registrados y sus detalles deben estar disponibles.
	 * postcond: Se calcula exitosamente el costo adicional por conductores adicional.
	 * @param - N/A
	 * @return En un long, el costo adicional por conductores adicional.
	 * @throws IOException Si ocurre un error durante el cálculo del costo adicional, como problemas de acceso a los datos necesarios.
 	 */
	@Override
	public long calcularCostoConductoresAd() throws IOException {
        Tarifa tarifa = new Tarifa(this);
        return tarifa.calcularCostoConductoresAd();
	}

	/**
	 * Calcula el 70% de toda la tarifa de la reserva.
	 * precond: La reserva debe estar inicializada.
	 * 			Los datos necesarios para calcular el costo adicional del 70% por temporada alta deben estar disponibles y correctos en el sistema, incluyendo la información de la reserva y las fechas de inicio y fin.
	 * 			El sistema debe tener acceso a la clase Tarifa para realizar el cálculo.
	 * 			Las fechas de inicio y fin de la reserva deben ser válidas y estar disponibles.
	 * postcond: Se calcula exitosamente el 70% adicional para la temporada alta.
	 * @param - N/A
	 * @return En un long, el costo del 70% de la cuenta total.
	 * @throws IOException Si ocurre un error durante el cálculo del costo adicional, como problemas de acceso a los datos necesario.
	 */
	@Override
	public long calcularCosto70P() throws IOException {
        Tarifa tarifa = new Tarifa(this);
        return tarifa.calcularCosto70P();
	}

	public boolean isEsEspecial() {
		return esEspecial;
	}

	public void setEsEspecial(boolean esEspecial) {
		this.esEspecial = esEspecial;
	}

	public static Reserva encontrarReservaEspecialPorID(String identificador, Empleado empleado) throws Exception {
		BufferedReader br;
		
			br = new BufferedReader(new FileReader("./src/datos/ListaReserva.txt"));
	    String linea = br.readLine();
	    while (linea != null) {
	        String[] inforeserva = linea.split(",");
	        String ID = inforeserva[0];
	        if (identificador.equals(ID)) {
	        	int IDint = Integer.parseInt(ID);
	            String nombre = inforeserva[1];
	            int categoria = Integer.parseInt(inforeserva[2]);
	            int dias = Integer.parseInt(inforeserva[3]);
	            String fechaIni = inforeserva[4];
	            String fechaFin = inforeserva[5];
	            String sedein = inforeserva[6];
	            String sedeout = inforeserva[7];

	            Cliente cliente = Cliente.encontrarClientePorNombre("./src/datos/Usuarios.txt", nombre);
	            if (cliente == null) {
	                br.close();
	                throw new IOException("Cliente no encontrado");
	            }

	            Reserva reserva = new Reserva(empleado, IDint, categoria, fechaIni,sedein, sedeout);
	            br.close();
	            return reserva; 
	        }
	        linea = br.readLine();
	    }
	    try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return null; 
	}
	

	

}
