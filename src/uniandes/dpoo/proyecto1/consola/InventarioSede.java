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
