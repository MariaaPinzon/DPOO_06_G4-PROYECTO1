package uniandes.dpoo.proyecto1.consola;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Reserva {
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
	

	public Reserva(Cliente pana, String fechaIni, String horaIni, String fechaFin, String horaFin, String sedeIn, String sedeFin, int categoria, ArrayList<String> seguros) {
		this.cliente= pana;
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
	public Reserva(int ID,Cliente pana, String fechaIni, String horaIni, String fechaFin, String horaFin, String sedeIn, String sedeFin, int categoria,ArrayList<String> seguros) {
		this.cliente= pana;
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
	public Reserva(Empleado chambeador, int categoria, String fecha, String sede1, String sede2) {
		this.empleado=chambeador;
		this.categoria=categoria;
		this.fechaIni=fecha;
		this.fechaFin=fecha;
		this.sedeinicial=sede1;
		this.sedefinal=sede2;
		this.diasAlquiler=diferenciadias(fecha,fecha);
		this.ID= crearID();
	}
	
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
	public void escribirTXT(String enlace) throws Exception {
		FileWriter output = new FileWriter(enlace, true);
		BufferedWriter br = new BufferedWriter(output);
		String nombrecliente = cliente.getNombre();

	    String segurosStr = String.join(";", seguros);  //  ';' para separar los seguros en la cadena

	    br.write(ID + "," + nombrecliente + "," + categoria + "," + diasAlquiler + "," + fechaIni + "," + horaIni + "," + fechaFin + "," + horaFin + "," + sedeinicial + "," + sedefinal + "," + segurosStr+"\n");
	    br.close();
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
			resp = "económico";
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

	public boolean esEntregaOtraSede() {
		String sedeInicial = getSedeinicial();
		String sedeFinal = getSedefinal();
		boolean esOtraSede = true;
		if (sedeInicial.equals(sedeFinal)) {
			esOtraSede = false;
		}
		return esOtraSede;
	}
	public String getFechaIni() {
		return fechaIni;
	}
	public String getHoraIni() {
		return horaIni;
	}
	public String getFechaFin() {
		return fechaFin;
	}
	public String getHoraFin() {
		return horaFin;
	}
	public int getDiasAlquiler() {
		return diasAlquiler;
	}
	public String getSedeinicial() {
		return sedeinicial;
	}
	public String getSedefinal() {
		return sedefinal;
	}
	public int getID() {
		return ID;
	}
	public int getCategoria() {
		return categoria;
	}
	public ArrayList<String> getSegurosCliente() {
        return seguros;
    }
	public Empleado getEmpleado() {
		return empleado;
	}

}