package uniandes.dpoo.proyecto1.consola;

public class Vehiculo {
	private String placa;
	private String marca;
	private int modelo;
	private String categoria;
	private String color;
	private String transmision;
	private String sede;
	private String disponible;
	private String cliente;
	private String fechaDev;
	
	public Vehiculo(String placa,String marca,int modelo,String categoria, String color, String transmision,String sede,String disponible ) {
		this.placa = placa;
		this.marca = marca;
		this.setModelo(modelo);
		this.setCategoria(categoria);
		this.setColor(color);
		this.setTransmision(transmision);
		this.setSede(sede);
		this.setDisponible(disponible);
		
	}
	public Vehiculo(String placa,String marca,int modelo,String categoria, String color, String transmision,String sede,String cliente, String fechaDev ) {
		this.placa = placa;
		this.marca = marca;
		this.setModelo(modelo);
		this.setCategoria(categoria);
		this.setColor(color);
		this.setTransmision(transmision);
		this.setSede(sede);
		this.setCliente(cliente);
		this.setFechaDev(fechaDev);
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public int getModelo() {
		return modelo;
	}
	public void setModelo(int modelo) {
		this.modelo = modelo;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getTransmision() {
		return transmision;
	}
	public void setTransmision(String transmision) {
		this.transmision = transmision;
	}
	public String getSede() {
		return sede;
	}
	public void setSede(String sede) {
		this.sede = sede;
	}
	public String getDisponible() {
		return disponible;
	}
	public void setDisponible(String disponible) {
		this.disponible = disponible;
	}
	public String getinfo () {
		return placa+","+marca+","+modelo+","+categoria+","+color+","+transmision+","+sede+","+disponible;
	}
	public String getinfoAlquilado () {
		return placa+","+marca+","+modelo+","+categoria+","+color+","+transmision+","+sede+","+cliente+","+fechaDev;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getFechaDev() {
		return fechaDev;
	}
	public void setFechaDev(String fechaDev) {
		this.fechaDev = fechaDev;
	}

}