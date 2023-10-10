package uniandes.dpoo.proyecto1.consola;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;



public class Inventario {
	private ArrayList<Vehiculo> catalogo;
	public Inventario() {
		
	}

	public Inventario(String enlace) throws Exception {
		ArrayList<Vehiculo> catalogo = new ArrayList<Vehiculo>();
		BufferedReader br = new BufferedReader(new FileReader(enlace));
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
				Vehiculo carro = new Vehiculo(placa,marca,modelo,categoria,color,transmision,sede,disponible);
				catalogo.add(carro);
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
				Vehiculo carro = new Vehiculo(placa,marca,modelo,categoria,color,transmision,sede,cliente, fechaDev);
				catalogo.add(carro);
			}
			linea = br.readLine();
		}
		br.close();
		this.catalogo= catalogo;
		
		
		
	}
	public void mostrarinventariodisponible () {
		for (int i=0; i<catalogo.size(); i++) {
			Vehiculo carro = catalogo.get(i);
			String s = carro.getinfo();
			String disponible = carro.getDisponible();
			if (disponible != null) {
				if (disponible.equals("disponible")){
					System.out.println(s);
				}
			}
		}
	}
	public void mostrarinventariototal () {
		for (int i=0; i<catalogo.size(); i++) {
			String s = null;
			Vehiculo carro = catalogo.get(i);
			String disponible = carro.getDisponible();
			if (disponible == null) {
				s= carro.getinfoAlquilado();
			}
			else {
				s=carro.getinfo();
			}
			
			System.out.println(s);
			}
	}
	public ArrayList<String> mostrarinventarioalquilado(){
		ArrayList<String> alquilados = new ArrayList<>();
		for (int i=0; i<catalogo.size(); i++) {
			String s = null;
			Vehiculo carro = catalogo.get(i);
			String disponible = carro.getCliente();
			if (disponible != null) {
				s= carro.getinfoAlquilado();
				alquilados.add(s);
			}
		}
		return alquilados;
	}
	public void mostrarinventarioSEDE(String sede) {
		
	}
	public ArrayList<Vehiculo> getCatalogo() {
		return catalogo;
	}
}
