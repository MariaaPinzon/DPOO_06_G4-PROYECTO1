package uniandes.dpoo.proyecto1.consola;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class AlquilerVehiculo {
	
	private String placa;
	private String marca;
	private int modelo;
	private String categoria;
	private String clienteAlquiler;
	private String fechaIni;
	private String horaIni;
	private String fechaFin;
	private String horaFin;
	private String sedeinicial;
	private String sedefinal;
	private long costoTotal;

	public AlquilerVehiculo() {
		// TODO Auto-generated constructor stub
	}

	public AlquilerVehiculo(String placa, String marca, int modelo, String categoria, String clienteAlquiler,
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

	public HashMap<String, ArrayList<HashMap<String, String>>> cargarLogAlquileres(){
		
        HashMap<String, ArrayList<HashMap<String, String>>> historialesAlquileres = new HashMap<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("./src/datos/Alquileres.txt"));
            String linea;
            String placa = "";
            ArrayList<HashMap<String, String>> alquileres = new ArrayList<>();

            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("Placa del vehiculo: ")) {
                    if (!placa.isEmpty()) {
                        historialesAlquileres.put(placa, alquileres);
                        alquileres = new ArrayList<>();
                    }
                    placa = linea.split(": ")[1];
                } 
                else 
                {
                    HashMap<String, String> alquiler = new HashMap<>();
                    alquiler.put(linea.split(": ")[0], linea.split(": ")[1]);
                    alquileres.add(alquiler);
                }
            }

            if (!placa.isEmpty()) {
                historialesAlquileres.put(placa, alquileres);
            }

            br.close();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo log: " + e.getMessage());
        }

        return historialesAlquileres;
    }
}
