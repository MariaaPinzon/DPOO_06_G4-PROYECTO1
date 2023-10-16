package uniandes.dpoo.proyecto1.consola;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class InventarioSede extends Inventario {
	@SuppressWarnings("unused")
	private ArrayList<Vehiculo> catalogo;
	
	
	public InventarioSede(String enlace, String sedetext, String sedeenlace) throws Exception {
		ArrayList<Vehiculo>carros = new ArrayList<Vehiculo>(); 
		
		BufferedReader br = new BufferedReader(new FileReader(enlace));
		FileWriter output = new FileWriter(sedeenlace, false);
		BufferedWriter bw = new BufferedWriter(output);
		String linea = null;
		linea = br.readLine();
		while (linea!=null) 
		{
			String[] info = linea.split(",");
			if (info.length== 8) {
				String placa = info[0];
				String marca = info[1];
				int modelo =Integer.parseInt(info[2]);
				String categoria = info[3];
				String color = info[4];
				String transmision = info[5];
				String sede = info[6];
				String disponible = info[7];
				if (sede.equals(sedetext)) {
					
					Vehiculo carro = new Vehiculo(placa,marca,modelo,categoria,color,transmision,sede,disponible);
					carros.add(carro);
					bw.write(linea+"\n");
					
				}
				
			}
			else if (info.length== 9) {
				String placa = info[0];
				String marca = info[1];
				int modelo =Integer.parseInt(info[2]);
				String categoria = info[3];
				String color = info[4];
				String transmision = info[5];
				String sede = info[6];
				String cliente = info[7];
				String fechaDev= info[8];
				if (sede.equals(sedetext)) {
					
					Vehiculo carro = new Vehiculo(placa,marca,modelo,categoria,color,transmision,sede,cliente, fechaDev);
					carros.add(carro);
					bw.write(linea+"\n");
				}
			}
			linea = br.readLine();
		}
		br.close();
		bw.close();
		this.catalogo = carros;
		
	}
	
	
	/** 
	 * Método constructor responsable de crear el inventario por sede.
	 * precond:El catálogo general debe estar previamente inicializado.
	 * postcond: Se crea el inventario de la sede exitosamente.
	 * @param opcionSede La sede, puede ser sede 1, 2 o 3.
	 * @throws Exception Si ocurre un error inesperado al crear el inventario por sede. Esto puede incluir:
	 *                  - Problemas al abrir o leer el catálogo general.
	 *                  - Errores al crear o escribir en el inventario de la sede.
	 *                  - La opción de sede no es válida.
	 */
	public static InventarioSede crearInventarioPorSede(String opcionSede) throws Exception {
	    switch (opcionSede) {
	        case "1":
	            return new InventarioSede("./src/datos/InventarioGENERAL.txt", "s1", "./src/datos/inventarioSede1.txt");
	        case "2":
	            return new InventarioSede("./src/datos/InventarioGENERAL.txt", "s2", "./src/datos/inventarioSede2.txt");
	        case "3":
	            return new InventarioSede("./src/datos/InventarioGENERAL.txt", "s3", "./src/datos/inventarioSede3.txt");
	        default:
	            throw new IllegalArgumentException("Opción de sede no válida");
	    }
	}
	
	/** 
	 * Revisar los carros disponibles del inventario de la sede. Como disponibles se entiende que no estén en mantenimiento ni en limpieza ni alquilado.
	 * precond: En el sistema ya existe un empleado, pues estos son los que pueden revisar el inventario general.
	 * 			El catálogo de la sede ya fue inicializado.
	 * 			Hay vehículos en el catálogo de la sede.
	 * postcond: Se imprime, uno por uno, los vehículos que estén disponibles del inventario de la sede, con toda su información.
	 * @param N/A
	 * @throws N/A
	 */
	@Override
	public void mostrarinventariodisponible () {
		for (int i=0; i<catalogo.size(); i++) {
			
			Vehiculo carro = catalogo.get(i);
			String s = carro.getinfo();
			String disponible = carro.getDisponible();
			if (disponible!=null) {
				if (disponible.equals("disponible")){
					System.out.println(s);
				
				}
			}
		
		}
	}
	

}
