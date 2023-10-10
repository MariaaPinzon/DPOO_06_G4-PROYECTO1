package uniandes.dpoo.proyecto1.modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.FileWriter;

import uniandes.dpoo.proyecto1.consola.Administrador;
import uniandes.dpoo.proyecto1.consola.Cliente;
import uniandes.dpoo.proyecto1.consola.Usuario;
import uniandes.dpoo.proyecto1.consola.Vehiculo;
import uniandes.dpoo.proyecto1.consola.Empleado;
import uniandes.dpoo.proyecto1.consola.Inventario;
import uniandes.dpoo.proyecto1.consola.InventarioSede;
import uniandes.dpoo.proyecto1.consola.Reserva;
import uniandes.dpoo.proyecto1.consola.Tarifa;

public class sistemaAlquiler {
	@SuppressWarnings("resource")
    public Usuario IniciarSesion() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("./src/datos/Usuarios.txt"));

        String linea = br.readLine();
        Boolean revisar = false;
        Usuario usuarioreal = null;

        while (revisar == false) {
            String usuario = input("ingrese su usuario: ");
            String contra = input("ingrese su contraseña: ");

            while (linea != null) {
                String[] info = linea.split(",");
                String cuenta = info[0];
                String password = info[1];
                String jerarquia = info[2];

                if (usuario.equals(cuenta) && contra.equals(password)) {
                    if (jerarquia.equals("AG")) {
                        usuarioreal = new Administrador(usuario, contra);
                        usuarioreal.getRol();
                    } 
                    else if (jerarquia.equals("EAL") || jerarquia.equals("E")) {
                        usuarioreal = new Empleado(usuario, contra);
                        usuarioreal.getRol();
                    } 
                    else if (jerarquia.equals("C")) {
                        usuarioreal = new Cliente(usuario, contra, info[3], info[4], info[5], info[6], info[7], info[8], info[9], info[10], info[11], Long.parseLong(info[12]), info[13]);
                        usuarioreal.getRol();
                    }

                    revisar = true;
                    break;
                }
                linea = br.readLine();
            }
            if (revisar == false) {
                System.out.println("No se encontró el usuario registrado, intente de nuevo");
            }
        }
        return usuarioreal;
    }
	public void CargarUsuarios() {
		
		
	}
	public Usuario CrearNuevoUsuario (String name) throws Exception {
		Usuario returnfinal = null;
		FileWriter output = new FileWriter(name, true);
		BufferedWriter br = new BufferedWriter(output);
		//br.newLine();
		String usuario = input("ingrese su usuario: ");
		//PARA HACER UN EMPLEADO SE TYPEA "secreto"
		//PARA HACER UN ADMINISTRADOR SE TYPEA "secretoA"
		if (!usuario.equals("secreto") && (!usuario.equals("secretoA"))) {
            String contra = input("ingrese su contraseña: ");
            String nombres = input("Ingrese sus nombres: ");
            String contacto = input("Ingrese su número de teléfono: ");
            String fechaNacimiento = input("Ingrese su fecha de nacimiento (dd/mm/yyyy): ");
            String nacionalidad = input("Ingrese su nacionalidad: ");
            String docIdentidad = input("Ingrese su documento de identidad: ");
            String numeroLicencia = input("Ingrese su número de licencia (12 dígitos): ");
            String paisExpedicionLicencia = "Colombia"; // Asumiendo que siempre es Colombia
            String fechaVencimientoLicencia = input("Ingrese la fecha de vencimiento de su licencia (dd/mm/yyyy): ");
            String tipoMedioDePago = input("Ingrese el tipo de medio de pago (ej. Tarjeta, Transferencia, etc.): ");
            int numeroMedioDePago = Integer.parseInt(input("Ingrese el número de su medio de pago: "));
            String fechaVencimientoMedioPago = input("Ingrese la fecha de vencimiento de su medio de pago (dd/mm/yyyy): ");

            br.write(usuario + "," + contra + ",C," + nombres + "," + contacto + "," + fechaNacimiento + "," + nacionalidad + "," + docIdentidad + "," + numeroLicencia + "," + paisExpedicionLicencia + "," + fechaVencimientoLicencia + "," + tipoMedioDePago + "," + numeroMedioDePago + "," + fechaVencimientoMedioPago);
            br.close();

            returnfinal = new Cliente(usuario, contra, nombres, contacto, fechaNacimiento, nacionalidad, docIdentidad, numeroLicencia, paisExpedicionLicencia, fechaVencimientoLicencia, tipoMedioDePago, numeroMedioDePago, fechaVencimientoMedioPago);
        } 
		else if(usuario.equals("secretoA")){
			System.out.println("Hola admin");
			String adminusuario = input("ingrese su usuario: ");
			String contra = input("ingrese su contraseña: ");		
			br.write("\n"+adminusuario+","+contra+",A");
			br.close();
			returnfinal = new Administrador (adminusuario, contra);
		}
		else { 
			System.out.println("Hola empleado");
			String empleusuario = input("ingrese su usuario: ");
			String contra = input("ingrese su contraseña: ");
			br.write("\n"+empleusuario+","+contra+",E");
			br.close();
			returnfinal = new Empleado (empleusuario, contra);
			}
		return returnfinal;
	}
		
		
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) throws Exception {

		sistemaAlquiler sistema = new sistemaAlquiler();
		System.out.println("1. Iniciar Sesión");
		System.out.println("2. Crear nuevo usuario");
		
		int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
		Usuario usuario = null;
		if (opcion_seleccionada == 1) {
			usuario = sistema.IniciarSesion();
		}
		else if (opcion_seleccionada == 2) {
			usuario = sistema.CrearNuevoUsuario("./src/datos/Usuarios.txt");
		}
		Inventario inventariogeneral = new Inventario("./src/datos/InventarioGENERAL.txt");
		
		/*System.out.println("\nMostrando todos los carros disponibles en todas las sedes\n");
		*inventariogeneral.mostrarinventariodisponible();
		
		*System.out.println("\nMostrando todos los carros de la empresa\n");
		*inventariogeneral.mostrarinventariototal();*/
		
		/*TOTAL DE OPCIONES:
		  	0.cerrar
			1. revisar los carros del inventario general 					check
			2. revisar todos los carros disponibles 						check
			3. revisar todos los carros disponibles de una sede. 			check
			4.Crear una reserva 											check
			5.Gestionar/eliminar reserva 									check
			6.Añadir carro al inventario									check
			7. Eliminar carro del inventario								check
			8. Crear seguro 												check
			9. crear empleado												
			10. eliminar empleado
			11. alquiler con reserva
			12. alquiler sin reserva
			13. reserva especial
			14. alquiler especial
			tarifa por reserva y 30%										check


		 */
		Boolean revision_opciones = true;
		while (revision_opciones == true){
			int opcion = usuario.mostrarOpciones();

			if (opcion == 1) {
				inventariogeneral.mostrarinventariototal();
			}
			if (opcion == 2) {
				inventariogeneral.mostrarinventariodisponible();
			}
			if (opcion == 3) {
				
				InventarioSede inventariosede = null;
				System.out.println("1. Sede 1");
				System.out.println("2. Sede 2");
				System.out.println("3. Sede 3");;
				String opcionsede = input("Seleccione una sede");
				System.out.println(opcionsede);
				if (opcionsede.equals("1")) {
					inventariosede = new InventarioSede("./src/datos/InventarioGENERAL.txt", "s1","./src/datos/inventarioSede1.txt");
				}
				else if (opcionsede.equals("2")) {
					inventariosede = new InventarioSede("./src/datos/InventarioGENERAL.txt", "s2","./src/datos/inventarioSede2.txt");
				}
				else if (opcionsede.equals("3")) {
					inventariosede = new InventarioSede("./src/datos/InventarioGENERAL.txt", "s3","./src/datos/inventarioSede3.txt");
				}
				inventariosede.mostrarinventariodisponible();
				
			}
			if (opcion == 4) {
				Cliente cliente = (Cliente)usuario;
				System.out.println(cliente.getNombre()); 
				
				System.out.println("Estos son los tipos de carros disponibles actualmente: \n"
						+ "1.economico\r\n"
						+ "2.estándar\r\n"
						+ "3.van\r\n"
						+ "4.SUV\r\n"
						+ "5.todoterreno\r\n"
						+ "6.lujo");
				int categoria = Integer.parseInt(input("Seleccione una categoría"));
				System.out.println("Nuestras sedes: \n"
						+ "Sede 1 (s1)\r\n"
						+ "Sede 2 (s2)\r\n"
						+ "Sede 3 (s3)\r\n");
				String sedeIn = input("Escriba el nombre de la sede de la cual quiere recoger el automóvil: ");
				String sedeFin = input("Escriba el nombre de la sede en la cual va a devolver el automóvil: ");
				String fechaini = input("Escriba qué día quiere recoger el vehículo (en formato DD/MM/AA): ");
				String horaini = input("Escriba qué a qué hora lo recogerá (en formato HH:MM): ");
				String fechafinal = input("Escriba qué día va a devolver el vehículo (en formato DD/MM/AA): ");
				String horafinal = input("Escriba qué a qué hora lo devolverá (en formato HH:MM): ");
				ArrayList<String> segurosSeleccionados = new ArrayList<>();
			    boolean seleccionandoSeguros = true;
			    while (seleccionandoSeguros) {
			        System.out.println("Seleccione un seguro por su ID, escriba 'terminar' para finalizar la selección:");
					System.out.println("Nuestros seguros: \n"
							+ "Protección completa contra accidentes (Seguro1)\r\n"
							+ "Protección básica contra accidentes (Seguro2)\r\n"
							+ "Protección contra daños a terceros (Seguro3)\r\n"
							+ "Protección en caso de accidentes en viajes largos (Seguro4)\r\n"
							+ "Protección en caso de robo del vehículo (Seguro5)\r\n");
			        String seguro = input("Seguro");
			        if (seguro.equalsIgnoreCase("terminar")) {
			            seleccionandoSeguros = false;
			        } else {
			            segurosSeleccionados.add(seguro);
			        }
			    }
			    Reserva reserva = new Reserva(cliente, fechaini, horaini, fechafinal, horafinal, sedeIn, sedeFin, categoria, segurosSeleccionados);
			    System.out.println(reserva.getinfo());
			    Tarifa precio = new Tarifa(reserva);
				long preciofinal = precio.calcularCostoFinal();
				System.out.println("El precio total de la reserva es de: "+precio+" mil pesos");
				long precio30 = (long) (preciofinal*0.3);
				System.out.println("Pague ahora mismo el 30% del precio, el cual es: "+precio30);
			    reserva.escribirTXT("./src/datos/ListaReserva.txt");
			    
				
			}
			if (opcion == 5) {
				System.out.println("\nOpciones para buscar la reserva\n");
				System.out.println("1. Por nombre de quien generó la reserva.");
				System.out.println("2. Por identificador de la reserva");
				int seleccion = Integer.parseInt(input("Seleccione una opción"));
				String comparador = "";
				if (seleccion ==1) {
					comparador = input("Escriba el nombre de quien generó la reserva");
				}
				if (seleccion ==2) {
					comparador = input("Escriba el identificador de su reserva");
				}
				BufferedReader br = new BufferedReader(new FileReader("./src/datos/ListaReserva.txt"));

				String linea = null;
				Reserva reserva = null;
				linea = br.readLine();
				if (linea==null) {
					linea = br.readLine();
				}
				Usuario usuarioreal = null;
				while (linea!=null) {
					boolean encontrar_reserva =linea.contains(comparador);
					if (encontrar_reserva==true) {

						String inforeserva[]= linea.split(",");
						String nombrecliente = inforeserva[1];
						int categoria = Integer.parseInt(inforeserva[2]);
						String fechaIni = inforeserva[4];
						String horaIni = inforeserva[5];
						String fechaFin = inforeserva[6];
						String horafin = inforeserva[7];
						String sedein = inforeserva[8];
						String sedeout = inforeserva[9];
						int ID = Integer.parseInt(inforeserva[0]);

						ArrayList<String> seguros = new ArrayList<>();
						String segurosgrande = inforeserva[10];
						String[]seguroslistagrande = segurosgrande.split(";");
						
						for (int i = 0; i<seguroslistagrande.length; i++) {
							seguros.add(seguroslistagrande[i]);
						}

						BufferedReader lect_usuario = new BufferedReader(new FileReader("./src/datos/Usuarios.txt"));
						String linea_usuarios = null;
						linea_usuarios= lect_usuario.readLine();
						while (linea_usuarios!=null) {
							String info[]= linea_usuarios.split(",");
							String usuariocomp = info[0];
							if (nombrecliente.equals(usuariocomp)) {

								String contraseña= info[1];
								usuarioreal = new Cliente(nombrecliente, contraseña, info[3], info[4], info[5], info[6], info[7], info[8], info[9], info[10], info[11], 1111, info[13]);
								break;
							}
							linea_usuarios= lect_usuario.readLine();
						}
						lect_usuario.close();
						Cliente cliente = (Cliente)usuarioreal;
						reserva = new Reserva(ID,cliente, fechaIni, horaIni, fechaFin, horafin, sedein, sedeout, categoria, seguros);
						System.out.println(reserva.getinfo());
						
						Tarifa precio = new Tarifa(reserva);
						long preciofinal = precio.calcularCostoFinal();
						System.out.println("El precio total de la reserva es de: "+preciofinal+" mil pesos");
						
						break;

					}
					linea=br.readLine();
				}
				br.close();
				
				
			}
			if (opcion == 6) {
				String placa = input("Escriba la placa del vehículo");
				String marca = input("Escriba la marca del vehículo");
				int modelo = Integer.parseInt(input("Escriba el modelo de vehículo"));
				System.out.println("Estos son los tipos de carros disponibles actualmente: \n"
						+ "1.economico\r\n"
						+ "2.estándar\r\n"
						+ "3.van\r\n"
						+ "4.SUV\r\n"
						+ "5.todoterreno\r\n"
						+ "6.lujo");
				int categoria = Integer.parseInt(input("Seleccione una categoría"));
				String categoriastr = findcategoria(categoria);
				String color = input("Escriba el color del vehículo");
				System.out.println("\n1. Automático");
				System.out.println("2. Manual");
				String resptrasmision = input("Escoja el tipo de transmisión que tiene el vehiculo");
				String trasmision= null;
				if (resptrasmision.equals("1")) {
					trasmision = "AUTOMATICO";
				}
				else if (resptrasmision.equals("2")) {
					trasmision = "MANUAL";
				}
				String sede = input("¿En qué sede se encontrará el vehículo?");
				FileWriter output = new FileWriter("./src/datos/InventarioGENERAL.txt", true);
				BufferedWriter br = new BufferedWriter(output);
				br.append(placa+","+marca+","+modelo+","+categoriastr+","+color+","+trasmision+","+sede+",disponible"+"\n");
				br.close();
				System.out.println("\nSe añadió el vehículo con éxito");
				
				
			}
			if (opcion == 7) {
				String placa = input("Escriba la placa del vehículo que desea retirar del inventario");
				BufferedReader br = new BufferedReader(new FileReader("./src/datos/InventarioGENERAL.txt"));
				String linea = null;
				linea = br.readLine();
				ArrayList<String> lista = new ArrayList<>();
				boolean encontrar_carro = false;
				while (linea!=null) {
					String info[]= linea.split(",");
					String placacomp = info[0];
					if (! placa.equals(placacomp)) {
						lista.add(linea+"\n");
					}
					else {
						encontrar_carro=true;
					}
					linea= br.readLine();
				}
				br.close();
				
				if (encontrar_carro==true) {
				FileWriter output = new FileWriter("./src/datos/InventarioGENERAL.txt");
				for (int i=0; i<lista.size(); i++) {
					String dato = lista.get(i);
					output.append(dato);
				}
				output.close();
				System.out.println("El vehículo con las placas "+placa+" ha sido eliminado éxitosamente");
				}
				else {
					System.out.println("No existe un vehículo con esa placa");
				}
				
			}
			if (opcion == 8) {
				break;
			}
			if (opcion == 9) {
				break;
			}
			if (opcion == 10) {
				break;
			}
			if (opcion == 11) {
				System.out.println("\nOpciones para buscar la reserva\n");
				System.out.println("1. Por nombre de quien generó la reserva.");
				System.out.println("2. Por identificador de la reserva");
				int seleccion = Integer.parseInt(input("Seleccione una opción"));
				String comparador = "";
				if (seleccion ==1) {
					comparador = input("Escriba el nombre de quien generó la reserva");
				}
				if (seleccion ==2) {
					comparador = input("Escriba el identificador de su reserva");
				}
				BufferedReader br = new BufferedReader(new FileReader("./src/datos/InventarioGENERAL.txt"));
				String linea = null;
				Reserva reserva = null;
				linea = br.readLine();
				if (linea==null) {
					linea = br.readLine();
				}
				Usuario usuarioreal = null;
				while (linea!=null) {
					boolean encontrar_reserva =linea.contains(comparador);
					if (encontrar_reserva==true) {

						String inforeserva[]= linea.split(",");
						String nombrecliente = inforeserva[1];
						int categoria = Integer.parseInt(inforeserva[2]);
						String fechaIni = inforeserva[4];
						String horaIni = inforeserva[5];
						String fechaFin = inforeserva[6];
						String horafin = inforeserva[7];
						String sedein = inforeserva[8];
						String sedeout = inforeserva[9];
						int ID = Integer.parseInt(inforeserva[0]);

						ArrayList<String> seguros = new ArrayList<>();
						String segurosgrande = inforeserva[10];
						String[]seguroslistagrande = segurosgrande.split(";");
						
						for (int i = 0; i<seguroslistagrande.length; i++) {
							seguros.add(seguroslistagrande[i]);
						}

						BufferedReader lect_usuario = new BufferedReader(new FileReader("./src/datos/Usuarios.txt"));
						String linea_usuarios = null;
						linea_usuarios= lect_usuario.readLine();
						while (linea_usuarios!=null) {
							String info[]= linea_usuarios.split(",");
							String usuariocomp = info[0];
							if (nombrecliente.equals(usuariocomp)) {

								String contraseña= info[1];
								usuarioreal = new Cliente(nombrecliente, contraseña, info[3], info[4], info[5], info[6], info[7], info[8], info[9], info[10], info[11], 1111, info[13]);
								break;
							}
							linea_usuarios= lect_usuario.readLine();
						}
						lect_usuario.close();
						Cliente cliente = (Cliente)usuarioreal;
						reserva = new Reserva(ID,cliente, fechaIni, horaIni, fechaFin, horafin, sedein, sedeout, categoria, seguros);
					}
				}
				br.close();
			}
			if (opcion == 12) {
				break;
			}
			if (opcion == 13) {
				System.out.println("\n"
						+ "1.economico\r\n"
						+ "2.estándar\r\n"
						+ "3.van\r\n"
						+ "4.SUV\r\n"
						+ "5.todoterreno\r\n"
						+ "6.lujo\r\n");
				int categoria = Integer.parseInt(input("Seleccione una categoría"));
				String fechaini = input("Escriba qué día va a desplazar el vehículo (en formato DD/MM/AA)");
				String sedein = input("Escriba de qué sede vendrá el vehiculo");
				String sedeout = input("Escriba a qué sede irá el vehiculo");
				Empleado empleado = (Empleado)usuario;
				Reserva reservaespecial = new Reserva (empleado,categoria,fechaini,sedein,sedeout);
				reservaespecial.escribirTXTespecial("./src/datos/ListaReserva.txt");
				System.out.println(reservaespecial.getinfoEspecial());
			}
			if (opcion == 14) {
				String identificador = input("Escriba el número de identificación de la reserva ESPECIAL");
				BufferedReader br = new BufferedReader(new FileReader("./src/datos/ListaReserva.txt"));
				String linea = null;
				String sedeorigen = "";
				String sedefin = "";
				String categoria = "";
				int numcategoria = 0;
				linea = br.readLine();
				ArrayList<String> lista = new ArrayList<>();
				boolean encontrar_reserva = false;
				while (linea!=null) {
					String info[]= linea.split(",");
					String IDcomp = info[0];
					String especial = info[8];
					if (! identificador.equals(IDcomp)) {
						lista.add(linea+"\n");
					}
					else if (identificador.equals(IDcomp) && especial.equals("ESPECIAL")){
						encontrar_reserva=true;
						sedeorigen = info[6];
						categoria=findcategoria(Integer.parseInt(info[2]));
						numcategoria = Integer.parseInt(info[2]);
						sedefin= info[7];
					}
					linea= br.readLine();
				}
				br.close();
				
				if (encontrar_reserva==true) {
				FileWriter output = new FileWriter("./src/datos/ListaReserva.txt");
				for (int i=0; i<lista.size(); i++) {
					String dato = lista.get(i);
					output.append(dato);
				}
				output.close();
				BufferedReader brinventario = new BufferedReader(new FileReader("./src/datos/InventarioGENERAL.txt"));
				String lineainv = null;
				lineainv=brinventario.readLine();
				ArrayList<String> listaInventario = new ArrayList<>();
				String marca = "";
				String placa = "";
				boolean encontrar_auto = false;
				String lineareescribir = null;
				while (lineainv!=null) {
					String info[]= lineainv.split(",");
					
					String categoriacomp = info[3];
					String sedecomp = info[6];
					String es_disponible = info[7];
					if ((!categoriacomp.equals(categoria))||(!sedecomp.equals(sedeorigen))||(encontrar_auto==true)){
						listaInventario.add(lineainv);	
					}
					else if (es_disponible.equals("disponible")){
						encontrar_auto=true;
						String informacion[]=lineainv.split(",");
						marca = informacion[1];
						placa= informacion[0];
						lineareescribir =informacion[0]+","+informacion[1]+informacion[2]+","+informacion[3]+","+informacion[4]+","+informacion[5]+","+sedefin+","+informacion[7];
					}
					else {
						listaInventario.add(lineainv);	
					}
					
					
					lineainv=brinventario.readLine();
					
					}
					brinventario.close();
					if (encontrar_auto==true) {
						FileWriter escritura = new FileWriter("./src/datos/InventarioGENERAL.txt");
						for (int i=0; i<listaInventario.size(); i++) {
							String dato = listaInventario.get(i);
							escritura.append(dato+"\n");
							}
					
						if (lineareescribir!=null) {
							escritura.append(lineareescribir+"\n");
							System.out.println("El vehiculo "+marca +" con las placas "+placa+" fue transferido con éxito");
						}
						escritura.close();
					}
					else {
						System.out.println("No se encontró el carro");
					}
				}
				
				else {
					System.out.println("No existe una reserva especial con esa identificación");
				}
				
			}
			if (opcion == 0) {
				revision_opciones = false;
			}
		}
		
	}
	
	
	
	
	
	public static String findcategoria(int categoria) {
		String resp = "";
		if (categoria==1) {
			resp = "economico";
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
	
	
	public static String input(String mensaje)
	{
		try
		{
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}
}
