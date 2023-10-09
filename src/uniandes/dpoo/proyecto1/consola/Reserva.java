package uniandes.dpoo.proyecto1.consola;

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
	

	public Reserva(Cliente pana, String fechaIni, String horaIni, String fechaFin, String horaFin, String sedeIn, String sedeFin, int categoria) {
		this.cliente= pana;
		this.fechaIni= fechaIni;
		this.horaIni= horaIni;
		this.fechaFin= fechaFin;
		this.horaFin= horaFin;
		this.sedeinicial= sedeIn;
		this.sedefinal= sedeFin;
		//Se asume que todos los meses tienen 30 días
		this.diasAlquiler= diferenciadias(fechaIni,fechaFin);
		this.setCategoria(categoria);
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
	public String getinfo() {
		String nombrecliente = cliente.getNombre();
		String categoriaS = findcategoria();
		return "Cliente que realiza la reserva: "+nombrecliente+"\nBusca un carro de la categoría:"+categoriaS+" por "+diasAlquiler+" dias"
				+"\nDesde el día "+fechaIni+" a la hora "+horaIni+"\nHasta el día "+fechaFin+" a la hora "+horaFin
				+"\nArrendado en la sede "+sedeinicial+" y regresado en la sede "+sedefinal
				+"\nSu identificación de la reserva es: "+ID;
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
	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}

}
