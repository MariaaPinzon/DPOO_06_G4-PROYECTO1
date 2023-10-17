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
	
	/** 
	 * Revisar los carros disponibles del inventario general. Como disponibles se entiende que no estén en mantenimiento ni en limpieza ni alquilado.
	 * precond: En el sistema ya existe un empleado, pues estos son los que pueden revisar el inventario general.
	 * 			El catálogo ya fue inicializado.
	 * 			Hay vehículos en el catálogo.
	 * postcond: Se imprime, uno por uno, los vehículos que estén disponibles del inventario total, con toda su información.
	 * @param - N/A
	 * @throws - N/A
	 */
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
	
	/** 
	 * Revisar los carros del inventario general
	 * precond: En el sistema ya existe un empleado, pues estos son los que pueden revisar el inventario general.
	 * 			El catálogo ya fue inicializado.
	 * 			Hay vehículos en el catálogo.
	 * postcond: Se imprime, uno por uno, los vehículos del inventario total con toda su información.
	 * @param - N/A
	 * @throws - N/A
	 */
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
	
	/** 
	 * Revisar los carros del inventario general que estén alquilados en el momento.
	 * precond: En el sistema ya existe un empleado, pues estos son los que pueden revisar el inventario general.
	 * 			El catálogo ya fue inicializado.
	 * 			Hay vehículos en el catálogo.
	 * postcond: El inventario en forma de un ArrayList<String>, con la información de los carros alquilados.
	 * @param - N/A
	 * @throws - N/A
	 */
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

	/** 
	 * Añade un carro nuevo al inventario general.
	 * precond: En el sistema ya existe un administrador general, pues este es el único que puede editar el inventario general.
	 * 			El catálogo ya fue inicializado.
	 * postcond: El vehículo se agraga, con toda su información, con éxito al inventario general.
	 * @param vehiculo El vehículo que se quiere añadir al inventario
	 * @throws IOException Si hay algun error para escribir en el archivo. Algunos de estos errores pueden ser:
	 * 					   -file not found
	 * 					   -permission issues
	 * 					   -u otros errores tipo I/O.
	 */
    public void añadirVehiculoInventario(Vehiculo vehiculo) throws IOException {
        FileWriter output = new FileWriter("./src/datos/InventarioGENERAL.txt", true);
        BufferedWriter br = new BufferedWriter(output);
        String linea = vehiculo.getPlaca() + "," + vehiculo.getMarca() + "," + vehiculo.getModelo() + "," + vehiculo.getCategoria() + "," + vehiculo.getColor() + "," + vehiculo.getTransmision() + "," + vehiculo.getSede() + "," + vehiculo.getDisponible();
        br.append(linea + "\n");
        br.close();
    }
  
	/** 
	 * Se elminia un carro del inventario
	 * precond: En el sistema ya existe un administrador general, pues este es el único que puede editar el inventario general.
	 * 			El catálogo ya fue inicializado.
	 * 			Hay vehículos en el sistema.
	 * postcond: Si el vehículo existe en el sistema, es eliminado con éxito.
	 * @param placa La placa del vehículo que se quiere eliminar. Es importante entender que la placa es el identificador único de los vehículos.
	 * @throws IOException Si hay algun error para escribir en el archivo. Algunos de estos errores pueden ser:
	 * 					   -file not found
	 * 					   -permission issues
	 * 					   -u otros errores tipo I/O.
	 */
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

	/** 
	 * Se busca un carro dada cierta categoría y sede. Este proceso es para seleccionar el carro en el proceso de reserva y alquiler.
	 * precond: En el sistema ya existe un empleado, pues este maneja el sistema de reservas y alquiler.
	 * 			El catálogo ya fue inicializado.
	 * 			Hay vehículos en el sistema.
	 * 			Puede haber una reserva relacionada a esta búsqueda. 
	 * postcond: El vehículo que esté en una sede específica y sea de una categoría específica. Este es el vehículo seleccionado para un alquiler o reserva.
	 * @param categoria La categoría del carro que se desea encontrar. En la aplicación de este método, dada una reserva
	 * 					es la categoría del carro que el cliente quiere alquilar.
	 * @param sede La sede del carro que se desea encontrar. En la aplicación de este método, dada una reserva, es la sede
	 * 			   del carro que el cliente quiere alquilar.
	 * @throws IOException Si hay algun error para escribir en el archivo. Algunos de estos errores pueden ser:
	 * 					   -file not found
	 * 					   -permission issues
	 * 					   -u otros errores tipo I/O.
	 */    
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
    
	/** 
	 * Actualizar el estado de un vehículo cuando este esté alquilado.
	 * precond: En el sistema ya existe un empleado, pues este maneja el sistema de reservas y alquiler.
	 * 			El catálogo ya fue inicializado.
	 * 			Hay vehículos en el sistema.
	 * 			Se está en el proceso de alquiler de un carro, por lo que ya se tiene la información del vehículo que va a ser alquilado.
	 * postcond: El estado del vehículo alquilado se ha actualizado.
	 * @param vehiculo El vehículo que se está alquilando y cuya información se actualizará en el inventario.
	 * @param sedeFinal La sede final en la que se devuelve el vehículo alquilado.
	 * @param clienteNombre El nombre del cliente que alquiló el vehículo.
	 * @param fechaFin La fecha en la que se devuelve el vehículo alquilado.
	 * @throws IOException Si hay algun error para escribir en el archivo. Algunos de estos errores pueden ser:
	 * 					   -file not found
	 * 					   -permission issues
	 * 					   -u otros errores tipo I/O.
	 */
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

        BufferedWriter inventarioBW = new BufferedWriter(new FileWriter("./src/datos/InventarioGENERAL.txt"));
        for (int i = 0; i < listaInventario.size(); i++) {
            String linea = listaInventario.get(i);
            inventarioBW.write(linea);
            inventarioBW.newLine();
        }
        inventarioBW.close();
    
    }

	/**
	 * Actualiza el estado de un vehículo a que esté en limpieza, en el inventario general.
	 * precond: El inventario general debe estar inicializado y debe tener vehículos. 
	 * postcond: Se buscará el vehículo con la placa especificada en el inventario general y cambiará su estado a "limpieza".
 	 *     		 Si el vehículo se encuentra en la lista de vehículos alquilados, no se realizará la actualización.
	 * @param placa La placa del vehículo que se desea actualizar.
	 * @return Un valor booleano que indica si la actualización fue exitosa.
	 * @throws Exception Si ocurre un error en la lectura o escritura del archivo de inventario general.
	 */
    public boolean actualizarLimpiezaVehiculo(String placa) throws Exception {
    	Inventario inventariogeneral = new Inventario("./src/datos/InventarioGENERAL.txt");
		ArrayList<String> inventalquilado = inventariogeneral.mostrarinventarioalquilado();
    	boolean carroalquilado = false;
		for (int i=0;i<inventalquilado.size();i++) {
			String linea = inventalquilado.get(i);
			String lineaarr[] = linea.split(",");
			String placacomp = lineaarr[0];
			if (placa.equals(placacomp)) {
				carroalquilado = true;	
			}
			BufferedReader brinventario = new BufferedReader(new FileReader("./src/datos/InventarioGENERAL.txt"));
			String linealect = null;
			linealect = brinventario.readLine();
			ArrayList<String> inventalleno = new ArrayList<>();
			String cambioinfo = null;
			String marca = null;
			while (linealect!=null) {
				String info[] = linealect.split(",");
				String placacomp2 = info[0];
				if (!placa.equals(placacomp2)) {
					inventalleno.add(linealect);
				}
				else {
					cambioinfo= info[0]+","+info[1]+","+info[2]+","+info[3]+","+info[4]+","+info[5]+","+info[6]+","+"limpieza";
					marca = info[1];
				}
				linealect = brinventario.readLine();
			}
			if (carroalquilado) {
				FileWriter fw = new FileWriter("./src/datos/InventarioGENERAL.txt");
				for (int b=0;b<inventalleno.size();b++) {
					String dato = inventalleno.get(b);
					fw.write(dato+"\n");
				}
				fw.write(cambioinfo);
				fw.close();

			}
			brinventario.close();
		}
		return carroalquilado;
    }
    
	/**
	 * Actualiza el estado de un vehículo a que esté en mantenimiento, en el inventario general.
	 * precond: El inventario general debe estar inicializado y debe tener vehículos. 
	 * postcond: Se buscará el vehículo con la placa especificada en el inventario general y cambiará su estado a "mantenimiento".
 	 *     		 Si el vehículo se encuentra en la lista de vehículos alquilados, no se realizará la actualización.
	 * @param placa La placa del vehículo que se desea actualizar.
	 * @return Un valor booleano que indica si la actualización fue exitosa.
	 * @throws Exception Si ocurre un error en la lectura o escritura del archivo de inventario general.
	 */
    public boolean actualizarMantenimientoVehiculo(String placa) throws Exception {
    	Inventario inventariogeneral = new Inventario("./src/datos/InventarioGENERAL.txt");
		ArrayList<String> inventalquilado = inventariogeneral.mostrarinventarioalquilado();
		boolean revisar_carro = false;
		BufferedReader brinventario = new BufferedReader(new FileReader("./src/datos/InventarioGENERAL.txt"));
		String linealect = null;
		linealect = brinventario.readLine();
		ArrayList<String> inventalleno = new ArrayList<>();
		String cambioinfo = null;
		String marca = null;
		while (linealect!=null) {
			String info[] = linealect.split(",");
			String placacomp2 = info[0];
			String disponible = info[7];
			if (!placa.equals(placacomp2)) {
				inventalleno.add(linealect);
			}
			else if (disponible.equals("disponible")){
				revisar_carro= true;
				cambioinfo= info[0]+","+info[1]+","+info[2]+","+info[3]+","+info[4]+","+info[5]+","+info[6]+","+"mantenimiento";
				marca = info[1];
			}
			linealect = brinventario.readLine();
		}
		brinventario.close();
		if (revisar_carro) {
			FileWriter fw = new FileWriter("./src/datos/InventarioGENERAL.txt");
			for (int b=0;b<inventalleno.size();b++) {
				String dato = inventalleno.get(b);
				fw.write(dato+"\n");
			}
			fw.write(cambioinfo);
			fw.close();
		}
	
		brinventario.close();
		return revisar_carro;
    }
    
	/**
	 * Devuelve un vehículo al estado "disponible" después de la limpieza o mantenimiento.
	 * precond: El catálogo ya fue inicializado.
	 * 			Hay vehículos en el sistema.
	 * 			El vehículo debe estar en el estado de "limpieza" o "mantenimiento " para que vuelva a estar disponible.
	 * postcond: El método buscará el vehículo con la placa especificada en el inventario general y cambiará su estado a "disponible".
 	 *    		 Si el vehículo se encuentra en la lista de vehículos alquilados, no se realizará la actualización y el método devolverá false.
	 * @param placa La placa del vehículo que se desea actualizar.
	 * @return Un valor booleano que indica si la actualización fue exitosa o no.
	 * @throws Exception Si ocurre un error en la lectura o escritura del archivo de inventario general.
	 */
    public boolean devolverDeLimpiezaOMantenimientoVehiculo (String placa) throws Exception {
    	Inventario inventariogeneral = new Inventario("./src/datos/InventarioGENERAL.txt");
		ArrayList<String> inventalquilado = inventariogeneral.mostrarinventarioalquilado();
			BufferedReader brinventario = new BufferedReader(new FileReader("./src/datos/InventarioGENERAL.txt"));
			String linealect = null;
			linealect = brinventario.readLine();
			ArrayList<String> inventalleno = new ArrayList<>();
			String cambioinfo = null;
			String marca = null;
			boolean carroEncontrado = false;
			 while (linealect != null) {
			        String info[] = linealect.split(",");
			        String placacomp2 = info[0];
			        String estado = info[7];
			        if (placa.equals(placacomp2) && (estado.equals("limpieza") || estado.equals("mantenimiento"))) {
			            cambioinfo = info[0] + "," + info[1] + "," + info[2] + "," + info[3] + "," + info[4] + "," + info[5] + "," + info[6] + "," + "disponible\n";
			            carroEncontrado = true;
			        } else {
			            inventalleno.add(linealect);
			        }
			        linealect = brinventario.readLine();
			        
			    }
			 
			    brinventario.close();
			if (carroEncontrado) {
				FileWriter fw = new FileWriter("./src/datos/InventarioGENERAL.txt");
				for (int b=0;b<inventalleno.size();b++) {
					String dato = inventalleno.get(b);
					fw.write(dato+"\n");
				}
				fw.write(cambioinfo);
				fw.close();
			}
		
		return carroEncontrado;
    }
    
	/** 
	 * Si en la categoria seleccionada por el cliente en el proceso de reserva y alquiler, no se encuentran vehículos disponible, este método se encarga de encontrar un vehículo de una mejor categoría.
	 * precond: En el sistema ya existe un empleado, pues este maneja el sistema de reservas y alquiler.
	 * 			El catálogo ya fue inicializado.
	 * 			Hay vehículos en el sistema.
	 * 			Se tiene la información de la reserva de la cuál no se le pudo encontrar vehículo.
	 * postcond: El vehículo seleccionado como alternativa para el cliente.
	 * @param categoria La categoría del carro que se desea encontrar. En la aplicación de este método, dada una reserva
	 * 					es la categoría del carro que el cliente quiere alquilar.
	 * @param sede La sede del carro que se desea encontrar. En la aplicación de este método, dada una reserva, es la sede
	 * 			   del carro que el cliente quiere alquilar.
	 * @throws IOException Si hay algun error para escribir en el archivo. Algunos de estos errores pueden ser:
	 * 					   -file not found
	 * 					   -permission issues
	 * 					   -u otros errores tipo I/O.
	 */    
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
