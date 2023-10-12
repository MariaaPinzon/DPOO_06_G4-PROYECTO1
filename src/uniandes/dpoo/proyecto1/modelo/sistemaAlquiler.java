package uniandes.dpoo.proyecto1.modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.FileWriter;

import uniandes.dpoo.proyecto1.consola.Administrador;
import uniandes.dpoo.proyecto1.consola.Cliente;
import uniandes.dpoo.proyecto1.consola.Conductor;
import uniandes.dpoo.proyecto1.consola.Usuario;

import uniandes.dpoo.proyecto1.consola.Empleado;
import uniandes.dpoo.proyecto1.consola.AdministradorLocal;
import uniandes.dpoo.proyecto1.consola.Inventario;
import uniandes.dpoo.proyecto1.consola.InventarioSede;
import uniandes.dpoo.proyecto1.consola.Reserva;
import uniandes.dpoo.proyecto1.consola.Seguro;
import uniandes.dpoo.proyecto1.consola.Tarifa;

public class sistemaAlquiler {
	@SuppressWarnings("resource")
    public Usuario IniciarSesion() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("./src/datos/Usuarios.txt"));

        String linea = br.readLine();
        Boolean revisar = false;
        Usuario usuarioreal = null;

        while (revisar == false) {
            String usuario = input("ingrese su usuario");
            String contra = input("ingrese su contraseña");

            while (linea != null) {
                String[] info = linea.split(",");
                String cuenta = info[0];
                String password = info[1];
                String jerarquia = info[2];

                if (usuario.equals(cuenta) && contra.equals(password)) {
                    if (jerarquia.equals("AG")) {
                    	usuarioreal = new Administrador(usuario, contra, jerarquia, info[3], info[4], info[5], info[6], info[7]);
                        String nombrereal = info[3];
                        System.out.println("Hola "+nombrereal);
                        usuarioreal.getRol();
                    } 
                    else if (jerarquia.equals("E")) {
                        usuarioreal = new Empleado(usuario, contra, jerarquia, info[3], info[4], info[5], info[6], info[7]);
                        usuarioreal.getRol();
                    }
                    else if (jerarquia.equals("AL")) {
                        usuarioreal = new AdministradorLocal(usuario, contra, jerarquia, info[3], info[4], info[5], info[6], info[7], info[12]);
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
	
	public Usuario CrearNuevoUsuario (String name) throws Exception {
		Usuario returnfinal = null;
		FileWriter output = new FileWriter(name, true);
		BufferedWriter br = new BufferedWriter(output);
		//br.newLine();
		String usuario = input("ingrese su usuario: ");
		//PARA HACER UN AG SE TYPEA "secretoAG"
		//PARA HACER UN AL SE TYPEA "secretoAL"

				
		if (!usuario.equals("secretoAG") && (!usuario.equals("secretoAL"))) {
            String contra = input("ingrese su contraseña ");
            String nombres = input("Ingrese sus nombres ");
            String contacto = input("Ingrese su número de teléfono ");
            String fechaNacimiento = input("Ingrese su fecha de nacimiento (dd/mm/yyyy) ");
            String nacionalidad = input("Escriba su pais de nacionalidad ");
            String docIdentidad = input("Ingrese su documento de identidad ");
            String numeroLicencia = input("Ingrese su número de licencia (9 dígitos) ");
            String paisExpedicionLicencia = input("Escriba el pais de expedicion de su licencia "); 
            String fechaVencimientoLicencia = input("Ingrese la fecha de vencimiento de su licencia (dd/mm/yyyy) ");
            String tipoMedioDePago = input("Ingrese el tipo de medio de pago (ej. Tarjeta, Transferencia, etc.) ");
            long numeroMedioDePago = Long.parseLong(input("Ingrese el número de su medio de pago "));
            String fechaVencimientoMedioPago = input("Ingrese la fecha de vencimiento de su medio de pago (dd/mm/yyyy) ");

            br.write(usuario + "," + contra + ",C," + nombres + "," + contacto + "," + fechaNacimiento + "," + nacionalidad + "," + docIdentidad + "," + numeroLicencia + "," + paisExpedicionLicencia + "," + fechaVencimientoLicencia + "," + tipoMedioDePago + "," + numeroMedioDePago + "," + fechaVencimientoMedioPago+"\n");
            br.close();
            System.out.println("Hola "+nombres);
            returnfinal = new Cliente(usuario, contra, nombres, contacto, fechaNacimiento, nacionalidad, docIdentidad, numeroLicencia, paisExpedicionLicencia, fechaVencimientoLicencia, tipoMedioDePago, numeroMedioDePago, fechaVencimientoMedioPago);
        } 
		//EMPLEADO: ADMINISTRADOR GENERAL:

		else if (usuario.equals("secretoAG")) {
	        System.out.println("Hola admin general");
	        String adminusuario = input("ingrese su usuario");
	        String contra = input("ingrese su contraseña");
	        String nombres = input("Ingrese sus nombres");
	        String contacto = input("Ingrese su número de teléfono");
	        String fechaNacimiento = input("Ingrese su fecha de nacimiento (dd/mm/yyyy)");
	        String nacionalidad = input("Ingrese su nacionalidad");
	        String docIdentidad = input("Ingrese su documento de identidad");
	        br.write( adminusuario + "," + contra + ",AG," + nombres + "," + contacto + "," + fechaNacimiento + "," + nacionalidad + "," + docIdentidad + "," + "NA"+ "," + "NA"+ "," + "NA"+ "," + "NA"+ "," + "NA"+ "," + "NA"+"\n" );
	        br.close();
	        returnfinal = new Administrador(adminusuario, contra, "AG", nombres, contacto, fechaNacimiento, nacionalidad, docIdentidad);

	    //EMPLEADO: ADMINISTRADOR LOCAL

	    } else {
	        System.out.println("Hola admin local");
	        String adminLocalUsuario = input("ingrese su usuario ");
	        String contra = input("ingrese su contraseña ");
	        String nombres = input("Ingrese sus nombres ");
	        String contacto = input("Ingrese su número de teléfono ");
	        String fechaNacimiento = input("Ingrese su fecha de nacimiento (dd/mm/yyyy) ");
	        String nacionalidad = input("Ingrese su nacionalidad: ");
	        String docIdentidad = input("Ingrese su documento de identidad ");
	        String sede = input("Ingrese la sede, ej. (s1) ");
	        br.write( adminLocalUsuario + "," + contra + ",AL," + nombres + "," + contacto + "," + fechaNacimiento + "," + nacionalidad + "," + docIdentidad + "," + "NA"+ "," + "NA"+ "," + "NA"+ "," + "NA"+ "," + "NA"+ "," + "NA" +"," + sede+"\n");
	        br.close();
	        returnfinal = new AdministradorLocal(adminLocalUsuario, contra, "AL", nombres, contacto, fechaNacimiento, nacionalidad, docIdentidad, sede);
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
			1. revisar los carros del inventario general 					check	Revisado
			2. revisar todos los carros disponibles 						check	Revisado
			3. revisar todos los carros disponibles de una sede. 			check	Revisado
			4.Crear una reserva 											check	Revisado
			5.Gestionar/eliminar reserva 									check	Revisado
			6.Añadir carro al inventario									check	Revisado
			7. Eliminar carro del inventario								check	Revisado
			8. Crear seguro 												check   Revisado
			9. crear empleado												check	Revisado
			10. eliminar empleado											check	Revisado		
			11. alquiler con reserva										check	Revisado
			12. alquiler sin reserva										check	Revisado
			13. reserva especial											check	Revisado
			14. alquiler especial											check	Revisado
			15.recibir un automovil (ponerlo en limpieza)					check	Revisado
			16. mandar un automovil a mantenimiento							check	Revisado
			17.devolver auto de limpieza o mantenimiento					check	Revisado
			tarifa por reserva y 30%										check	Revisado
			+buscar mejor carro si no hay de la categoria en alq			check	Revisado								 
			+añadir conductores en alquiler									check   Revisado


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
						+ "1.economico, costo: 200000 \r\n"
						+ "2.estándar, costo: 250000 \r\n"
						+ "3.van,costo: 280000 \r\n"
						+ "4.SUV, costo: 300000\r\n"
						+ "5.todoterreno, costo: 320000 \r\n"
						+ "6.lujo, costo: 400000 ");
				int categoria = Integer.parseInt(input("Seleccione una categoría de acuerdo a su numero, ej. (1)"));
				System.out.println("Nuestras sedes: \n"
						+ "Sede 1 (s1)\r\n"
						+ "Sede 2 (s2)\r\n"
						+ "Sede 3 (s3)\r\n");
				String sedeIn = input("Escriba el nombre de la sede de la cual quiere recoger el automóvil, ej. (s1) ");
				String sedeFin = input("Escriba el nombre de la sede en la cual va a devolver el automóvil, ej. (s1) ");
				String fechaini = input("Escriba qué día quiere recoger el vehículo (en formato DD/MM/AA) ");
				String horaini = input("Escriba qué a qué hora lo recogerá (en formato HH:MM) ");
				String fechafinal = input("Escriba qué día va a devolver el vehículo (en formato DD/MM/AA) ");
				String horafinal = input("Escriba qué a qué hora lo devolverá (en formato HH:MM) ");
				ArrayList<String> segurosSeleccionados = new ArrayList<>();
			    boolean seleccionandoSeguros = true;
			    while (seleccionandoSeguros) {
			        System.out.println("Seleccione un seguro por su ID, escriba 'terminar' para finalizar la selección:");
					System.out.println("Nuestros seguros: \n"
							+ "Protección completa contra accidentes (Seguro1): $150000 por día\r\n"
							+ "Protección básica contra accidentes (Seguro2): $50000 por día\r\n"
							+ "Protección contra daños a terceros (Seguro3): $80000 por día\r\n"
							+ "Protección en caso de accidentes en viajes largos (Seguro4): $100000 por día\r\n"
							+ "Protección en caso de robo del vehículo (Seguro5): $120000 por día\r\n");
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
				String preciofin = Long.toString(preciofinal);
				System.out.println("El precio total de la reserva es de: "+preciofin+" mil pesos");
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
						+ "1.economico, costo: 200000 \r\n"
						+ "2.estándar, costo: 250000 \r\n"
						+ "3.van,costo: 280000 \r\n"
						+ "4.SUV, costo: 300000\r\n"
						+ "5.todoterreno, costo: 320000 \r\n"
						+ "6.lujo, costo: 400000 ");
				int categoria = Integer.parseInt(input("Seleccione una categoría de acuerdo a su numero, ej. (1)"));
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
				//Administrador general hace esto 
				
				String idSeguro = input("Escriba el id del seguro que va a crear (Seguro#)");
				String descripcionSeguro = input("Escriba una descripcion de este seguro ");
				String costoSeguro = input("Escriba el costo de este seguro ");
				Seguro seguro= new Seguro(idSeguro,descripcionSeguro,costoSeguro);
				seguro.escribirTXT("./src/datos/Seguros.txt");
			}
			if (opcion == 9) {
				//AdministradorLocal hace esto
				
				String nombreUsuario = input("Escriba el nombre de usuario del empleado");
				String contraseña = input("Escriba la constraseña del empleado");
				String tipoUsuario = "E";
				String nombres = input("Escriba el nombre y apellido del empleado");
				String datosContacto = input("Escriba el numero de contacto del empleado)");
				String fechaNacimiento = input("Escriba la fecha de nacimiento del empleado (en formato DD/MM/AAAA)");
				String nacionalidad = "Colombia";
				String docIdentidad = input("Escriba el documento de identidad del empleado");

			    Empleado empleado = new Empleado(nombreUsuario,contraseña,tipoUsuario,nombres,datosContacto,fechaNacimiento,nacionalidad,docIdentidad);
			    empleado.escribirTXT("./src/datos/Usuarios.txt");
			    System.out.println("Empleado creado");
			}
			if (opcion == 10) {
				String usuarioempleado = input("Escriba el usuario del empleado que desea eliminar");
				BufferedReader br = new BufferedReader(new FileReader("./src/datos/Usuarios.txt"));
				String linea = null;
				linea=br.readLine();
				ArrayList<String> usuarios= new ArrayList<>();
				boolean usuario_eliminado= false;
				while (linea!=null) {
					String informacion[]= linea.split(",");
					String usuariocomp = informacion[0];
					if (!usuariocomp.equals(usuarioempleado)) {
						usuarios.add(linea);
					}
					else if (informacion[2].equals("E")){
						usuario_eliminado=true;
					}
					linea=br.readLine();
				}
				br.close();
				if (usuario_eliminado==true) {
				FileWriter elimusuario = new FileWriter("./src/datos/Usuarios.txt");
				for (int i=0;i<usuarios.size();i++) {
					elimusuario.append(usuarios.get(i)+"\n");
					}
				System.out.println("El empleado "+usuarioempleado+" ha sido eliminado.");
				elimusuario.close();
				}
				else {
					System.out.println("No se encontró el empleado");
				}
				
				
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
				String nombrecliente = null;
				int categoria = 0;
				String fechaIni = null;
				String horaIni = null;
				String fechaFin = null;
				String horafin = null;
				String sedein = null;
				String sedeout = null;
				BufferedReader br = new BufferedReader(new FileReader("./src/datos/ListaReserva.txt"));
				String linea = null;
				Reserva reserva = null;
				Cliente cliente = null;
				ArrayList<String> seguros = new ArrayList<>();
				int ID = 0;
				linea = br.readLine();
				if (linea==null) {
					linea = br.readLine();
				}
				Usuario usuarioreal = null;
				while (linea!=null) {
					boolean encontrar_reserva =linea.contains(comparador);
					if (encontrar_reserva==true) {

						String inforeserva[]= linea.split(",");
						nombrecliente = inforeserva[1];
						categoria = Integer.parseInt(inforeserva[2]);
						fechaIni = inforeserva[4];
						horaIni = inforeserva[5];
						fechaFin = inforeserva[6];
						horafin = inforeserva[7];
						sedein = inforeserva[8];
						sedeout = inforeserva[9];
						ID = Integer.parseInt(inforeserva[0]);

						
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
						cliente = (Cliente)usuarioreal;
						reserva = new Reserva(ID,cliente, fechaIni, horaIni, fechaFin, horafin, sedein, sedeout, categoria, seguros);
					} 
					linea=br.readLine();
				}
				br.close();
				BufferedReader lect_usuario = new BufferedReader(new FileReader("./src/datos/ListaReserva.txt"));
				String IDorigin = String.valueOf(reserva.getID()) ;
				String linearev = null;
				linearev= lect_usuario.readLine();
				ArrayList<String> templista = new ArrayList<>();
				boolean encontro_reserva = false;
				while (linearev!=null) {
					String separar[]= linearev.split(",");
					String IDcomparar = separar[0];
					if (!IDorigin.equals(IDcomparar)) {
						templista.add(linearev);
					}
					else {
						encontro_reserva = true;
					}
					linearev= lect_usuario.readLine();
				}
				lect_usuario.close();
				if (encontro_reserva) {
					categoria = reserva.getCategoria();
					boolean buscar_de_nuevo = true;
					while (buscar_de_nuevo ==true && categoria<=6) {
					FileWriter eliminarreserva = new FileWriter("./src/datos/ListaReserva.txt");
					for (int i=0;i<templista.size();i++) {
						eliminarreserva.write(templista.get(i)+"\n");
					}
					eliminarreserva.close();
					BufferedReader brinventario = new BufferedReader(new FileReader("./src/datos/InventarioGENERAL.txt"));
					String lineainv = null;
					lineainv=brinventario.readLine();
					ArrayList<String> listaInventario = new ArrayList<>();
					String marca = "";
					String placa = "";
					String fechadev= "";
					String categoriastr = findcategoria(categoria);
					String sedeorigen = reserva.getSedeinicial();
					String sedefin = reserva.getSedefinal();
					String nombreusuario= (reserva.getCliente()).getNombre();
					boolean encontrar_auto = false;
					String lineareescribir = null;
					
					while (lineainv!=null) {
						String info[]= lineainv.split(",");
						
						String categoriacomp = info[3];
						
						String sedecomp = info[6];
						
						String es_disponible = info[7];
						if ((!categoriacomp.equals(categoriastr))||(!sedecomp.equals(sedeorigen))||(encontrar_auto==true)){
							listaInventario.add(lineainv);	
						}
						else if (es_disponible.equals("disponible")){
							
							encontrar_auto=true;
							String informacion[]=lineainv.split(",");
							marca = informacion[1];
							placa= informacion[0];
							fechadev= reserva.getFechaFin();
							lineareescribir =placa+","+marca+informacion[2]+","+informacion[3]
									+","+informacion[4]+","+informacion[5]+","+sedefin+","+nombreusuario+","+fechadev;
						}
						else {
							listaInventario.add(lineainv);	
						}
						
						
						lineainv=brinventario.readLine();
						
						}
						brinventario.close();
						if (encontrar_auto==true) {
							buscar_de_nuevo= false;
							FileWriter escritura = new FileWriter("./src/datos/InventarioGENERAL.txt");
							for (int i=0; i<listaInventario.size(); i++) {
								String dato = listaInventario.get(i);
								escritura.append(dato+"\n");
								}
						
							if (lineareescribir!=null) {
								escritura.append(lineareescribir+"\n");
								reserva = new Reserva(ID,cliente, fechaIni, horaIni, fechaFin, horafin, sedein, sedeout, categoria, seguros);
								Tarifa tarifa = new Tarifa(reserva);
								long total = tarifa.calcularCostoFinal();
								long total70= (long) (total*0.7);
								System.out.println("Pague el 70% restante del alquiler, el cual es "+total70+" mil pesos");
								System.out.println("El vehiculo "+marca +" con las placas "+placa+" fue alquilado a "+nombreusuario+" con éxito");
							}
							escritura.close();
						}
						else {
							categoria ++;
							String categorianueva = findcategoria(categoria);
							System.out.println("No se encontró ningún carro "+categoriastr+" en la sede "+sedeorigen+". se intentará buscar un vehículo de la clase "+categorianueva);
						}
					}
				}
				
		        String decisionConductor = input("¿Desea agregar conductores adicionales? (si/no)");
		        if (decisionConductor.equalsIgnoreCase("si")) {
		            HashMap<String, ArrayList<String>> conductoresAdicionales = new HashMap<>();
		            ArrayList<String> licenciasAdicionales = new ArrayList<>();
		            boolean añadiendoConductor = true;
		            int numeroConductor = 1;
		            while (añadiendoConductor) {
		                String decision = input("Escriba 'continuar' para agregar un conductor nuevo, de lo contrario escriba 'terminarConductores'");
		                if (decision.equalsIgnoreCase("continuar")) {
		                    System.out.println("Escriba la informacion del conductor adicional #" + numeroConductor);
		                    String nombres = input("Escriba los nombres del conductor adicional ");
		                    String numeroLicencia = input("Escriba el numero de licencia del conductor adicional ");
		                    String paisExpedicionLicencia = input("Escriba el pais de expedicion de la licencia del conductor adicional");
		                    String fechaVencimientoLicencia = input("Ecriba la fecha de vecimiento de la licencia del conductor adicional (en formato(DD/MM/AAAA))");
		                    Conductor conductor = new Conductor(cliente.getNumeroLicencia(), nombres, numeroLicencia, paisExpedicionLicencia, fechaVencimientoLicencia);
		                    licenciasAdicionales.add(conductor.getNumeroLicencia());
		                    conductor.escribirTXT("./src/datos/ConductoresAdicionales.txt");
		                    numeroConductor++;
		                } else if (decision.equalsIgnoreCase("terminarConductores")) {
		                    añadiendoConductor = false;
		                }
		            }
		            conductoresAdicionales.put(cliente.getNumeroLicencia(), licenciasAdicionales);
		            Tarifa tarifa = new Tarifa(reserva);
					long total = tarifa.calcularCostoFinal();
					long total70= (long) (total*0.7);
		            long costoCondAd = tarifa.calcularCostoConductoresAd();
		            long costoFinal = total70 + costoCondAd;
		            System.out.println("El costo total con los conductores adicionales es: " + costoFinal + " mil pesos.");
		        }
		        
				
				
				else {
					System.out.println("No se encontró la reserva.");
				}
				
				
				
				
			}
			
			
			
			
			if (opcion == 12) {
				System.out.println("1. El cliente ya tiene una cuenta en la página");
				System.out.println("2. El cliente tiene que crear su cuenta");
				String inpu = input("Seleccione");
				Usuario usuariotemp = null;
				String sedeIn = null;
				String sedeFin = null;
				String fechaini = null;
				String horaini = null;
				String fechafinal = null;
				String horafinal = null;
				ArrayList<String> segurosSeleccionados = null;
				if (inpu.equals("1")) {
					usuariotemp = sistema.IniciarSesion();
				}
				else if (inpu.equals("2")) {
					usuariotemp = sistema.CrearNuevoUsuario("./src/datos/Usuarios.txt");
				}
				else {
					System.out.println("escribe bien chique");
				}
				if (usuariotemp !=null) {
				Cliente cliente =(Cliente)usuariotemp;
				System.out.println("Estos son los tipos de carros disponibles actualmente: \n"
						+ "1.economico, costo: 200000 \r\n"
						+ "2.estándar, costo: 250000 \r\n"
						+ "3.van,costo: 280000 \r\n"
						+ "4.SUV, costo: 300000\r\n"
						+ "5.todoterreno, costo: 320000 \r\n"
						+ "6.lujo, costo: 400000 ");
				int categoria = Integer.parseInt(input("Seleccione una categoría de acuerdo a su numero, ej. (1)"));
				System.out.println("Nuestras sedes: \n"
						+ "Sede 1 (s1)\r\n"
						+ "Sede 2 (s2)\r\n"
						+ "Sede 3 (s3)\r\n");
				 sedeIn = input("Escriba el nombre de la sede de la cual quiere recoger el automóvil, ej. (s1) ");
				 sedeFin = input("Escriba el nombre de la sede en la cual va a devolver el automóvil, ej. (s1) ");
				 fechaini = input("Escriba qué día quiere recoger el vehículo (en formato DD/MM/AA) ");
				 horaini = input("Escriba qué a qué hora lo recogerá (en formato HH:MM): ");
				 fechafinal = input("Escriba qué día va a devolver el vehículo (en formato DD/MM/AA) ");
				 horafinal = input("Escriba qué a qué hora lo devolverá (en formato HH:MM) ");
				 
				segurosSeleccionados = new ArrayList<>();
			    HashMap<String, ArrayList<String>> segurosDisponibles = Tarifa.cargarTarifasSeguros("./src/datos/Seguros.txt");
			    boolean seleccionandoSeguros = true;
			    
			    while (seleccionandoSeguros) {
			        System.out.println("Seleccione un seguro por su ID, ej. (Seguto1) .Escriba 'terminar' para finalizar la selección");
			        
			        int numero = 1;
			        for (String idSeguro: segurosDisponibles.keySet()) {
			            ArrayList<String> datosSeguro = segurosDisponibles.get(idSeguro);
			            if (datosSeguro != null && datosSeguro.size() > 1) {
			                String descripcion = datosSeguro.get(0);
			                String costo = datosSeguro.get(1);
			                System.out.println(numero + ". " + "("+idSeguro +")"+"  " + descripcion + ",Costo:  " + costo );
			                numero++;
			            }
			        
					}
					String seguro = input("Seguro: ");
				    if (seguro.equalsIgnoreCase("terminar")) {
				    	   seleccionandoSeguros = false;
				    }
				    
				    else if (segurosDisponibles.containsKey(seguro)){
				    	segurosSeleccionados.add(seguro);
				    }
				    
				    else {
				        System.out.println("ID de seguro no válido. Intente nuevamente.");
				    	}
    
			    }
			    Reserva reserva = new Reserva(cliente, fechaini, horaini, fechafinal, horafinal, sedeIn, sedeFin, categoria, segurosSeleccionados);
			    reserva.escribirTXT("./src/datos/ListaReserva.txt");
			    
			    BufferedReader lect_usuario = new BufferedReader(new FileReader("./src/datos/ListaReserva.txt"));
			    int ID = reserva.getID();
			   
				String IDorigin = String.valueOf(reserva.getID()) ;
				String linearev = null;
				linearev= lect_usuario.readLine();
				ArrayList<String> templista = new ArrayList<>();
				boolean encontro_reserva = false;
				while (linearev!=null) {
					String separar[]= linearev.split(",");
					String IDcomparar = separar[0];
					if (!IDorigin.equals(IDcomparar)) {
						templista.add(linearev);
					}
					else {
						encontro_reserva = true;
					}
					linearev= lect_usuario.readLine();
				}
				lect_usuario.close();
				if (encontro_reserva) {
					categoria = reserva.getCategoria();
					boolean buscar_de_nuevo = true;
					while (buscar_de_nuevo ==true && categoria<=6) {
					FileWriter eliminarreserva = new FileWriter("./src/datos/ListaReserva.txt");
					for (int i=0;i<templista.size();i++) {
						eliminarreserva.write(templista.get(i)+"\n");
					}
					eliminarreserva.close();
					BufferedReader brinventario = new BufferedReader(new FileReader("./src/datos/InventarioGENERAL.txt"));
					String lineainv = null;
					lineainv=brinventario.readLine();
					ArrayList<String> listaInventario = new ArrayList<>();
					String marca = "";
					String placa = "";
					String fechadev= "";
					String categoriastr = findcategoria(categoria);
					String sedeorigen = reserva.getSedeinicial();
					String sedefin = reserva.getSedefinal();
					String nombreusuario= (reserva.getCliente()).getNombre();
					boolean encontrar_auto = false;
					String lineareescribir = null;
					
					while (lineainv!=null) {
						String info[]= lineainv.split(",");
						
						String categoriacomp = info[3];
						
						String sedecomp = info[6];
						
						String es_disponible = info[7];
						if ((!categoriacomp.equals(categoriastr))||(!sedecomp.equals(sedeorigen))||(encontrar_auto==true)){
							listaInventario.add(lineainv);	
						}
						else if (es_disponible.equals("disponible")){
							
							encontrar_auto=true;
							String informacion[]=lineainv.split(",");
							marca = informacion[1];
							placa= informacion[0];
							fechadev= reserva.getFechaFin();
							lineareescribir =placa+","+marca+","+informacion[2]+","+informacion[3]
									+","+informacion[4]+","+informacion[5]+","+sedefin+","+nombreusuario+","+fechadev;
						}
						else {
							listaInventario.add(lineainv);	
						}
						
						
						lineainv=brinventario.readLine();
						
						}
						brinventario.close();
						if (encontrar_auto==true) {
							buscar_de_nuevo= false;
							FileWriter escritura = new FileWriter("./src/datos/InventarioGENERAL.txt");
							for (int i=0; i<listaInventario.size(); i++) {
								String dato = listaInventario.get(i);
								escritura.append(dato+"\n");
								}
						
							if (lineareescribir!=null) {
								escritura.append(lineareescribir+"\n");
								reserva = new Reserva(ID,cliente, fechaini, horaini, fechafinal, horafinal, sedeIn, sedeFin, categoria, segurosSeleccionados);
								Tarifa tarifa = new Tarifa(reserva);
								long total = tarifa.calcularCostoFinal();
								System.out.println("Pague el alquiler, el cual tiene un costo de "+total+" mil pesos");
								System.out.println("El vehiculo "+marca +" con las placas "+placa+" fue alquilado a "+nombreusuario+" con éxito");
							}
							escritura.close();
						}
						else {
							categoria ++;
							String categorianueva = findcategoria(categoria);
							System.out.println("No se encontró ningún carro "+categoriastr+" en la sede "+sedeorigen+". se intentará buscar un vehículo de la clase "+categorianueva);
						}
					}
				}
				
		        String decisionConductor = input("¿Desea agregar conductores adicionales? (si/no)");
		        if (decisionConductor.equalsIgnoreCase("si")) {
		            HashMap<String, ArrayList<String>> conductoresAdicionales = new HashMap<>();
		            ArrayList<String> licenciasAdicionales = new ArrayList<>();
		            boolean añadiendoConductor = true;
		            int numeroConductor = 1;
		            while (añadiendoConductor) {
		                String decision = input("Escriba 'continuar' para agregar un conductor nuevo, de lo contrario escriba 'terminarConductores'");
		                if (decision.equalsIgnoreCase("continuar")) {
		                    System.out.println("Escriba la informacion del conductor adicional #" + numeroConductor);
		                    String nombres = input("Escriba los nombres del conductor adicional ");
		                    String numeroLicencia = input("Escriba el numero de licencia del conductor adicional ");
		                    String paisExpedicionLicencia = input("Escriba el pais de expedicion de la licencia del conductor adicional");
		                    String fechaVencimientoLicencia = input("Ecriba la fecha de vecimiento de la licencia del conductor adicional (en formato(DD/MM/AAAA))");
		                    Conductor conductor = new Conductor(cliente.getNumeroLicencia(), nombres, numeroLicencia, paisExpedicionLicencia, fechaVencimientoLicencia);
		                    licenciasAdicionales.add(conductor.getNumeroLicencia());
		                    conductor.escribirTXT("./src/datos/ConductoresAdicionales.txt");
		                    numeroConductor++;
		                } else if (decision.equalsIgnoreCase("terminarConductores")) {
		                    añadiendoConductor = false;
		                }
		            }
		            conductoresAdicionales.put(cliente.getNumeroLicencia(), licenciasAdicionales);
		            Tarifa tarifa = new Tarifa(reserva);
					long total = tarifa.calcularCostoFinal();
		            long costoCondAd = tarifa.calcularCostoConductoresAd();
		            long costoFinal = total + costoCondAd;
		            System.out.println("El costo total con los conductores adicionales es: " + costoFinal + " mil pesos.");
		        }
				
				else {
					System.out.println("No se encontró la reserva.");
				}
				
			}
				
				
			}
			
			
			
			
			if (opcion == 13) {
				System.out.println("Estos son los tipos de carros disponibles actualmente: \n"
						+ "1.economico, costo: 200000 \r\n"
						+ "2.estándar, costo: 250000 \r\n"
						+ "3.van,costo: 280000 \r\n"
						+ "4.SUV, costo: 300000\r\n"
						+ "5.todoterreno, costo: 320000 \r\n"
						+ "6.lujo, costo: 400000 ");
				int categoria = Integer.parseInt(input("Seleccione una categoría de acuerdo a su numero, ej. (1)"));
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
						lineareescribir =informacion[0]+","+informacion[1]+","+informacion[2]+","+informacion[3]+","+informacion[4]+","+informacion[5]+","+sedefin+","+informacion[7];
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
			
			

			
			if (opcion == 15) {
				ArrayList<String> inventalquilado = inventariogeneral.mostrarinventarioalquilado();
				String placa= input("¿cual vehículo fue entregado?");
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
				System.out.println("Se envió a lavar al "+marca+" de placa "+placa);
				}
				else {
					System.out.println("No se encontró ningun vehiculo alquilado con esa placa");
				}
				brinventario.close();
				}
			}
			
			
				
			
			if (opcion == 16) {
				String placa = input("Ingrese la placa del vehículo que tiene que ser mandado a mantenimiento (tiene que estar disponible actualmente)");
				
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
				System.out.println("Se envió al mecánico el "+marca+" de placa "+placa);
				}
				else {
					System.out.println("No se encontró ningun vehiculo alquilado con esa placa");
				}
				brinventario.close();
				}
			
			
			
			
			if (opcion == 17) {
				String placa = input("Ingrese la placa del vehículo que desea regresar");
				boolean revisar_carro = false;
				BufferedReader brinventario = new BufferedReader(new FileReader("./src/datos/InventarioGENERAL.txt"));
				String linealect = null;
				linealect = brinventario.readLine();
				ArrayList<String> inventalleno = new ArrayList<>();
				String cambioinfo = null;
				String marca = null;
				String sede = null;
				while (linealect!=null) {
					String info[] = linealect.split(",");
					String placacomp2 = info[0];
					String disponible = info[7];
					if (!placa.equals(placacomp2)) {
						inventalleno.add(linealect);
					}
					else if (disponible.equals("mantenimiento")||disponible.equals("limpieza")){
						revisar_carro= true;
						cambioinfo= info[0]+","+info[1]+","+info[2]+","+info[3]+","+info[4]+","+info[5]+","+info[6]+","+"disponible";
						marca = info[1];
						sede=info[6];
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
				System.out.println("Ya se regresó el "+marca+" de placa "+placa+" a la sede "+sede);
				}
				else {
					System.out.println("El vehículo no existe o ya está disponible");
				}
				brinventario.close();
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
