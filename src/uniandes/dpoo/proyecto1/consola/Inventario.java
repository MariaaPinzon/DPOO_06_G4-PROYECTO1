package uniandes.dpoo.proyecto1.consola;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;



public class Inventario {
	private ArrayList<Vehiculo> catalogo;
	
    public Inventario() {
        this.catalogo = new ArrayList<Vehiculo>();
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
				Vehiculo vehiculo = new Vehiculo(placa,marca,modelo,categoria,color,transmision,sede,disponible);
				catalogo.add(vehiculo);
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
				Vehiculo vehiculo = new Vehiculo(placa,marca,modelo,categoria,color,transmision,sede,cliente, fechaDev);
				catalogo.add(vehiculo);
			}
			linea = br.readLine();
		}
		br.close();
		this.catalogo= catalogo;
		
		
		
	}
	public void mostrarinventariodisponible () {
		for (int i=0; i<catalogo.size(); i++) {
			Vehiculo vehiculo = catalogo.get(i);
			String s = vehiculo.getinfo();
			String disponible = vehiculo.getDisponible();
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
			Vehiculo vehiculo = catalogo.get(i);
			String disponible = vehiculo.getDisponible();
			if (disponible == null) {
				s= vehiculo.getinfoAlquilado();
			}
			else {
				s=vehiculo.getinfo();
			}
			
			System.out.println(s);
			}
	}
	public ArrayList<String> mostrarinventarioalquilado(){
		ArrayList<String> alquilados = new ArrayList<>();
		for (int i=0; i<catalogo.size(); i++) {
			String s = null;
			Vehiculo vehiculo = catalogo.get(i);
			String disponible = vehiculo.getCliente();
			if (disponible != null) {
				s= vehiculo.getinfoAlquilado();
				alquilados.add(s);
			}
		}
		return alquilados;
	}
	

    public void añadirVehiculoInventario(Vehiculo vehiculo) throws IOException {
        FileWriter output = new FileWriter("./src/datos/InventarioGENERAL.txt", true);
        BufferedWriter br = new BufferedWriter(output);
        String linea = vehiculo.getPlaca() + "," + vehiculo.getMarca() + "," + vehiculo.getModelo() + "," + vehiculo.getCategoria() + "," + vehiculo.getColor() + "," + vehiculo.getTransmision() + "," + vehiculo.getSede() + "," + vehiculo.getDisponible();
        br.append(linea + "\n");
        br.close();
    }
    
    public boolean eliminarVehiculoInventario(String placa) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("./src/datos/InventarioGENERAL.txt"));
        String linea = br.readLine();
        ArrayList<String> lista = new ArrayList<>();
        boolean encontrar_carro = false;

        while (linea != null) {
            String info[] = linea.split(",");
            String placacomp = info[0];
            if (!placa.equals(placacomp)) {
                lista.add(linea + "\n");
            } else {
                encontrar_carro = true;
            }
            linea = br.readLine();
        }
        br.close();

		if (encontrar_carro) {
			FileWriter output = new FileWriter("./src/datos/InventarioGENERAL.txt");
			for (int i=0; i<lista.size(); i++) {
				String dato = lista.get(i);
				output.append(dato);
			}
			output.close();
        }

        return encontrar_carro;
    }

    public Vehiculo encontrarVehiculoPorSedeYCateg(String sede,int categoria) throws IOException {
        try (BufferedReader inventarioBR = new BufferedReader(new FileReader("./src/datos/InventarioGENERAL.txt"))) {
			String lineainv = inventarioBR.readLine();
			ArrayList<String> listaInventario = new ArrayList<>();
			boolean encontrar_auto = false;

			while (lineainv != null) {
			    String[] info = lineainv.split(",");
			    String categoriacomp = info[3];
			    String sedecomp = info[6];
			    String es_disponible = info[7];

			    if (!categoriacomp.equals(Vehiculo.findcategoria(categoria)) || !sedecomp.equals(sede) || encontrar_auto) {
			        listaInventario.add(lineainv);
			    } else if (es_disponible.equals("disponible")) {
			        encontrar_auto = true;
			        String placa = info[0];
			        String marca = info[1];
			        int modelo = Integer.parseInt(info[2]);
			        String color = info[4];
			        String transmision = info[5];
			        return new Vehiculo(placa, marca, modelo, categoriacomp, color, transmision, sedecomp, es_disponible);
			    } else {
			        listaInventario.add(lineainv);
			    }
			    lineainv = inventarioBR.readLine();
			}
			inventarioBR.close();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null; 
    }
    public static void actualizarVehiculoAlquilado(Vehiculo vehiculo, String sedeFinal, String clienteNombre, String fechaFin) throws IOException {
        BufferedReader inventarioBR = new BufferedReader(new FileReader("./src/datos/InventarioGENERAL.txt"));
        String lineainv;
        ArrayList<String> listaInventario = new ArrayList<>();
        
        while ((lineainv = inventarioBR.readLine()) != null) {
            String[] info = lineainv.split(",");
            if (info[0].equals(vehiculo.getPlaca())) {
                String lineareescribir = vehiculo.getPlaca() + "," + vehiculo.getMarca() + "," + info[2] + "," + info[3]
                        + "," + info[4] + "," + info[5] + "," + sedeFinal + "," + clienteNombre + "," + fechaFin;
                listaInventario.add(lineareescribir);
            } else {
                listaInventario.add(lineainv);
            }
        }
        inventarioBR.close();

    }
    
	public Vehiculo buscarVehiculoPorCategoriaMax(String sede,int categoria) throws IOException {
		Vehiculo vehiculoEncontrado = null;
	    while (vehiculoEncontrado == null && categoria <= 6) {
	        vehiculoEncontrado = encontrarVehiculoPorSedeYCateg(sede,categoria);
	        if (vehiculoEncontrado == null) {
	            categoria++;
	            if (categoria <= 6) {
	                String categorianueva = Vehiculo.findcategoria(categoria);
	                System.out.println("No se encontró ningún carro en la categoría solicitada en la sede " + sede + ". Se intentará buscar un vehículo de la clase " + categorianueva);
	            }
	        }
	    }
	    return vehiculoEncontrado;
	}
    
	public void mostrarinventarioSEDE(String sede) {
		
	}
	public ArrayList<Vehiculo> getCatalogo() {
		return catalogo;
	}
}
