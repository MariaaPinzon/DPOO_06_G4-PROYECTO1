package uniandes.dpoo.proyecto1.consola;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class AlquilerVehiculo {
	private Cliente cliente;
	private String placa;
	private String marca;
	private int modelo;
	private int categoria;
	private String clienteAlquiler;
	private String fechaIni;
	private String horaIni;
	private String fechaFin;
	private String horaFin;
	private String sedeinicial;
	private String sedefinal;
	private long costoTotal;
	private ArrayList<String> seguros; 


		public AlquilerVehiculo() {
			// TODO Auto-generated constructor stub
		}

	public AlquilerVehiculo(Cliente cliente, String fechaIni, String horaIni, String fechaFin, String horaFin, String sedeinicial, String sedefinal, int categoria, ArrayList<String> seguros) {
		this.cliente = cliente;
		this.fechaIni = fechaIni;
		this.horaIni = horaIni;
		this.fechaFin = fechaFin;
		this.horaFin = horaFin;
		this.sedeinicial = sedeinicial;
		this.sedefinal = sedefinal;
		this.categoria = categoria;
		this.seguros = seguros;
	}

	public AlquilerVehiculo(String placa, String marca, int modelo, int categoria, String clienteAlquiler,
			String fechaIni, String horaIni, String fechaFin, String horaFin, String sedeinicial, String sedefinal, long costoTotal) {
		super();
		this.placa = placa;
		this.marca = marca;
		this.categoria = categoria;
		this.clienteAlquiler = clienteAlquiler;
		this.fechaIni = fechaIni;
		this.horaIni = horaIni;
		this.fechaFin = fechaFin;
		this.horaFin = horaFin;
		this.sedeinicial = sedeinicial;
		this.sedefinal = sedefinal;
		this.costoTotal = costoTotal;
	}

	public void escribirLog(String placa, String marca, String categoria, String clienteAlquiler,
			String fechaIni, String horaIni, String fechaFin, String horaFin, String sedeinicial, String sedefinal, long costoTotal ) {
			try {
	        FileWriter logWriter = new FileWriter("./src/datos/Alquileres.txt", true);

	        logWriter.write("Placa del vehiculo: " + placa + "\n");
	        logWriter.write("Marca del vehiculo: " + marca + "\n");
	        logWriter.write("Nombre del cliente: " + clienteAlquiler + "\n");
	        logWriter.write("Fecha en la que el cliente recoge el vehiculo : " + fechaIni + "\n");
	        logWriter.write("Hora en la que el cliente recoge el vehiculo : " + horaIni + "\n");
	        logWriter.write("Fecha en la que el cliente devuelve el vehiculo : " + fechaFin + "\n");
	        logWriter.write("Hora en la que el cliente devuelve el vehiculo: " + horaFin + "\n");
	        logWriter.write("Costo total: " + costoTotal + "\n");
	        logWriter.write("--------------------------------------------------\n");

	        logWriter.close();
	    } catch (IOException e) {
	        System.out.println("Error al escribir en el archivo log: " + e.getMessage());
	    }
	}

	public HashMap<String, ArrayList<AlquilerVehiculo>> cargarLogAlquileres() {
	    HashMap<String, ArrayList<AlquilerVehiculo>> historialesAlquileres = new HashMap<>();

	    try {
	        BufferedReader br = new BufferedReader(new FileReader("./src/datos/Alquileres.txt"));
	        String linea;
	        AlquilerVehiculo alquilerActual = null;
	        ArrayList<AlquilerVehiculo> alquileres = null;
	        String placaActual = "";

	        while ((linea = br.readLine()) != null) {
	            if (linea.startsWith("Placa del vehiculo: ")) {
	                if (alquilerActual != null && !placaActual.isEmpty()) {
	                    if (!historialesAlquileres.containsKey(placaActual)) {
	                        historialesAlquileres.put(placaActual, new ArrayList<>());
	                    }
	                    historialesAlquileres.get(placaActual).add(alquilerActual);
	                }
	                placaActual = linea.split(": ")[1];
	                alquilerActual = new AlquilerVehiculo();
	                alquilerActual.setPlaca(placaActual);
	            } else if (linea.startsWith("Marca del vehiculo: ")) {
	                alquilerActual.setMarca(linea.split(": ")[1]);
	            } else if (linea.startsWith("Nombre del cliente: ")) {
	                alquilerActual.setClienteAlquiler(linea.split(": ")[1]);
	            } else if (linea.startsWith("Fecha en la que el cliente recoge el vehiculo : ")) {
	                alquilerActual.setFechaIni(linea.split(": ")[1]);
	            } else if (linea.startsWith("Hora en la que el cliente recoge el vehiculo : ")) {
	                alquilerActual.setHoraIni(linea.split(": ")[1]);
	            } else if (linea.startsWith("Fecha en la que el cliente devuelve el vehiculo : ")) {
	                alquilerActual.setFechaFin(linea.split(": ")[1]);
	            } else if (linea.startsWith("Hora en la que el cliente devuelve el vehiculo: ")) {
	                alquilerActual.setHoraFin(linea.split(": ")[1]);
	            } else if (linea.startsWith("Costo total: ")) {
	                alquilerActual.setCostoTotal(Long.parseLong(linea.split(": ")[1]));
	            }
	        }

	        // Aca ya llegamos al ultimo alquiler
	        if (alquilerActual != null && !placaActual.isEmpty()) {
	            if (!historialesAlquileres.containsKey(placaActual)) {
	                historialesAlquileres.put(placaActual, new ArrayList<>());
	            }
	            historialesAlquileres.get(placaActual).add(alquilerActual);
	        }

	        br.close();
	    } catch (IOException e) {
	        System.out.println("Error al leer el archivo log: " + e.getMessage());
	    }

	    return historialesAlquileres;
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

	public int getCategoria() {
		return categoria;
	}

	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}

	public String getClienteAlquiler() {
		return clienteAlquiler;
	}

	public void setClienteAlquiler(String clienteAlquiler) {
		this.clienteAlquiler = clienteAlquiler;
	}

	public String getFechaIni() {
		return fechaIni;
	}

	public void setFechaIni(String fechaIni) {
		this.fechaIni = fechaIni;
	}

	public String getHoraIni() {
		return horaIni;
	}

	public void setHoraIni(String horaIni) {
		this.horaIni = horaIni;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}

	public String getSedeinicial() {
		return sedeinicial;
	}

	public void setSedeinicial(String sedeinicial) {
		this.sedeinicial = sedeinicial;
	}

	public String getSedefinal() {
		return sedefinal;
	}

	public void setSedefinal(String sedefinal) {
		this.sedefinal = sedefinal;
	}

	public long getCostoTotal() {
		return costoTotal;
	}

	public void setCostoTotal(long costoTotal) {
		this.costoTotal = costoTotal;
	}
}

