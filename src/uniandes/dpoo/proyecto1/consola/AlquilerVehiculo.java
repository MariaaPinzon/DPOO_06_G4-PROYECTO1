package uniandes.dpoo.proyecto1.consola;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class AlquilerVehiculo implements Tarifable{
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
		this.setCliente(cliente);
		this.fechaIni = fechaIni;
		this.horaIni = horaIni;
		this.fechaFin = fechaFin;
		this.horaFin = horaFin;
		this.sedeinicial = sedeinicial;
		this.sedefinal = sedefinal;
		this.categoria = categoria;
		this.setSeguros(seguros);
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

	@Override
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
	@Override
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
	@Override
	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	@Override
	public String getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}

	@Override
	public String getSedeinicial() {
		return sedeinicial;
	}

	public void setSedeinicial(String sedeinicial) {
		this.sedeinicial = sedeinicial;
	}
	@Override
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
	@Override
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public ArrayList<String> getSegurosCliente() {
        return seguros;
    }

	public void setSeguros(ArrayList<String> seguros) {
		this.seguros = seguros;
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

	@Override
	public int getDiasAlquiler() {
		return diferenciadias( fechaIni,  fechaFin);
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
}

