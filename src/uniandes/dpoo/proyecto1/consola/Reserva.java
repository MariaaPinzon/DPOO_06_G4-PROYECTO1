package uniandes.dpoo.proyecto1.consola;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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
	private Empleado empleado;
	

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

	    br.write(ID + "," + nombreempleado + "," + categoria + "," + diasAlquiler + "," + fechaIni + ","+ fechaFin + ","+ sedeinicial + "," + sedefinal +",ESPECIAL"+"\n");
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

	            Reserva reserva = new Reserva(ID, cliente, fechaIni, horaIni, fechaFin, horafin, sedein, sedeout, categoria, seguros);
	            br.close();
	            return reserva; 
	        }
	        linea = br.readLine();
	    }
	    br.close();
	    return null; 
	}
	
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

	@Override
	public long modificarTarifaBase() throws IOException {
        Tarifa tarifa = new Tarifa(this); 
        return tarifa.modificarTarifaBase();
	}

	@Override
	public long calcularCostoConductoresAd() throws IOException {
        Tarifa tarifa = new Tarifa(this);
        return tarifa.calcularCostoConductoresAd();
	}

	@Override
	public long calcularCosto70P() throws IOException {
        Tarifa tarifa = new Tarifa(this);
        return tarifa.calcularCosto70P();
	}



}