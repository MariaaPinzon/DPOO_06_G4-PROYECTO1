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
import uniandes.dpoo.proyecto1.consola.Vehiculo;
import uniandes.dpoo.proyecto1.consola.Empleado;
import uniandes.dpoo.proyecto1.consola.AdministradorLocal;
import uniandes.dpoo.proyecto1.consola.AlquilerVehiculo;
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

		//Si un AG (Administrador general) quiere crear usuario, selecciona la opcion 2 Crear Usuario, y en ingrese su usuario:  se escribe "secretoAG"
		//Si un AL (Administrador Local) quiere crear usuario, selecciona la opcion 2 Crear Usuario, y en ingrese su usuario:  se escribe "secretoAL"

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
			1. revisar los carros del inventario general 					
			2. revisar todos los carros disponibles 						
			3. revisar todos los carros disponibles de una sede. 			
			4.Crear una reserva 											
			5.Gestiona reserva 									
			6.Añadir carro al inventario									
			7. Eliminar carro del inventario								
			8. Crear seguro 												
			9. crear empleado												
			10. eliminar empleado											
			11. alquiler con reserva										
			12. alquiler sin reserva										
			13. reserva especial											
			14. alquiler especial											
			15.recibir un automovil (ponerlo en limpieza)					
			16. mandar un automovil a mantenimiento							
			17.devolver auto de limpieza o mantenimiento					
			18. generar historial alquiler									
		 */
		Boolean revision_opciones = true;
		while (revision_opciones == true){
			int opcion = usuario.mostrarOpciones();

			//  1. revisar los carros del inventario general.  ----------------------------------------------------------------------------------------------------------------------------------------------------------------

			// La hace un Empleado
			if (opcion == 1) {
				inventariogeneral.mostrarinventariototal();
			}

			// 2. revisar todos los carros disponibles.  ----------------------------------------------------------------------------------------------------------------------------------------------------------------

			// La hace un Empleado
			if (opcion == 2) {
				inventariogeneral.mostrarinventariodisponible();
			}

			// 3. revisar todos los carros disponibles de una sede.  ----------------------------------------------------------------------------------------------------------------------------------------------------------------

			// La hace un Empleado
			if (opcion == 3) {
				System.out.println("1. Sede 1");
				System.out.println("2. Sede 2");
				System.out.println("3. Sede 3");;
				String opcionSede = input("Seleccione una sede");
				try {
					InventarioSede inventarioSede = InventarioSede.crearInventarioPorSede(opcionSede);
					inventarioSede.mostrarinventariodisponible();
				} catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
				}
			}

			// 4.Crear una reserva ----------------------------------------------------------------------------------------------------------------------------------------------------------------

			//La hace un Cliente
			if (opcion == 4) {
				Cliente cliente = (Cliente)usuario;
				System.out.println(cliente.getNombre()); 				
				int categoria = seleccionarCategoria();				
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
				ArrayList<String> segurosSeleccionados = seleccionarSeguros();
				if (segurosSeleccionados.isEmpty()) {
					segurosSeleccionados.add("NA");
					}
				Reserva reserva = new Reserva(cliente, fechaini, horaini, fechafinal, horafinal, sedeIn, sedeFin, categoria, segurosSeleccionados);
				System.out.println(reserva.getinfo());
				Tarifa tarifa = new Tarifa(reserva);
				long precioFinal = tarifa.calcularCostoFinal();
				String precioFin = Long.toString(precioFinal);
				System.out.println("El precio total de la reserva es de: "+precioFin+" mil pesos");
				long precioFinal30 = tarifa.calcularCosto30P();
				System.out.println("Pague ahora mismo el 30% del precio, el cual es: "+precioFinal30);
				reserva.escribirTXT();	
			}

			// 5.Gestionar reserva  ----------------------------------------------------------------------------------------------------------------------------------------------------------------

			//La hace un Cliente
			if (opcion == 5) {
				System.out.println("\nOpciones para buscar la reserva\n");
				System.out.println("1. Por nombre de quien generó la reserva.");
				System.out.println("2. Por identificador de la reserva");
				int seleccion = Integer.parseInt(input("Seleccione una opción"));
				Reserva reserva = null;

				if (seleccion ==1) {
					String nombreComparador = input("Escriba el nombre de quien generó la reserva");
					reserva = Reserva.encontrarReservaPorNombre(nombreComparador);
				}
				else if (seleccion ==2) {
					int idComparador = Integer.parseInt(input("Escriba el identificador de su reserva"));
					reserva = Reserva.encontrarReservaPorID(idComparador);
				}
				if (reserva != null) {
					System.out.println(reserva.getinfo());
					Tarifa precio = new Tarifa(reserva);
					long preciofinal = precio.calcularCostoFinal();
					System.out.println("El precio total de la reserva es de: " + preciofinal + " mil pesos");
				} else {
					System.out.println("No se encontró la reserva.");
				}
			}
			// 6.Añadir carro al inventario ----------------------------------------------------------------------------------------------------------------------------------------------------------------

			// La hace Administrador General
			if (opcion == 6) {
				String placa = input("Escriba la placa del vehículo");
				String marca = input("Escriba la marca del vehículo");
				int modelo = Integer.parseInt(input("Escriba el modelo de vehículo"));
				String categoria = Vehiculo.findcategoria(seleccionarCategoria());
				String color = input("Escriba el color del vehículo");
				String transmision = (input("Escoja el tipo de transmisión que tiene el vehiculo").equals("1")) ? "AUTOMATICO" : "MANUAL";
				String sede = input("¿En qué sede se encontrará el vehículo?");
				Vehiculo nuevoVehiculo = new Vehiculo(placa, marca, modelo, categoria, color, transmision, sede, "disponible");
				Inventario inventario = new Inventario();
				inventario.añadirVehiculoInventario(nuevoVehiculo);
				System.out.println("\nSe añadió el vehículo con éxito");

			}

			// 7. Eliminar carro del inventario ----------------------------------------------------------------------------------------------------------------------------------------------------------------

			// La hace Administrador General

			if (opcion == 7) {
				String placa = input("Escriba la placa del vehículo que desea retirar del inventario");
				Inventario inventario = new Inventario();
				boolean eliminado = inventario.eliminarVehiculoInventario(placa); // Asumiendo que 'inventario' es una instancia de la clase Inventario

				if (eliminado) {
					System.out.println("El vehículo con las placas " + placa + " ha sido eliminado éxitosamente");
				} else {
					System.out.println("No existe un vehículo con esa placa");
				}
			}

			// 8. Crear seguro ----------------------------------------------------------------------------------------------------------------------------------------------------------------			

			//Administrador general hace esto 
			if (opcion == 8) {

				String idSeguro = input("Escriba el id del seguro que va a crear (Seguro#)");
				String descripcionSeguro = input("Escriba una descripcion de este seguro ");
				String costoSeguro = input("Escriba el costo de este seguro ");
				Seguro seguro= new Seguro(idSeguro,descripcionSeguro,costoSeguro);
				seguro.escribirTXT();
			}

			// 9. crear empleado ----------------------------------------------------------------------------------------------------------------------------------------------------------------			

			//Administrador Local hace esto
			if (opcion == 9) {

				String nombreUsuario = input("Escriba el nombre de usuario del empleado");
				String contraseña = input("Escriba la constraseña del empleado");
				String tipoUsuario = "E";
				String nombres = input("Escriba el nombre y apellido del empleado");
				String datosContacto = input("Escriba el numero de contacto del empleado)");
				String fechaNacimiento = input("Escriba la fecha de nacimiento del empleado (en formato DD/MM/AAAA)");
				String nacionalidad = "Colombia";
				String docIdentidad = input("Escriba el documento de identidad del empleado");
				Empleado empleado = new Empleado(nombreUsuario,contraseña,tipoUsuario,nombres,datosContacto,fechaNacimiento,nacionalidad,docIdentidad);
				empleado.escribirTXT();
				System.out.println("Empleado creado");
			}

			// 10. eliminar/despedir empleado ----------------------------------------------------------------------------------------------------------------------------------------------------------------			

			//Administrador Local hace esto
			if (opcion == 10) {
				String nombreUsuarioEmpleado = input("Escriba el usuario del empleado que desea eliminar");
				Empleado empleado = new Empleado();
				boolean usuario_eliminado = empleado.eliminarEmpleado(nombreUsuarioEmpleado);

				if (usuario_eliminado) {
					System.out.println("El empleado "+nombreUsuarioEmpleado+" ha sido eliminado.");	
				}
				else {
					System.out.println("No se encontró el empleado");
				}

			}
			//11. alquiler con reserva  ----------------------------------------------------------------------------------------------------------------------------------------------------------------			

			// Empleado normal hace esto
			if (opcion == 11) {				
				System.out.println("\nOpciones para buscar la reserva\n");
				System.out.println("1. Por nombre de quien generó la reserva.");
				System.out.println("2. Por identificador de la reserva");
				int seleccion = Integer.parseInt(input("Seleccione una opción"));
				Reserva reserva = null;

				if (seleccion == 1) { 
					String nombreCliente = input("Escriba el nombre de quien generó la reserva");
					reserva = Reserva.encontrarReservaPorNombre(nombreCliente); // se encuentra la reserva
				} else if (seleccion == 2) {
					int idReserva = Integer.parseInt(input("Escriba el identificador de su reserva"));
					reserva = Reserva.encontrarReservaPorID(idReserva);
				}
				if (reserva != null) {       
					Reserva.eliminarReservaYActualizarArchivo(reserva.getID()); // se elimina la reserva de la lista de reservas
					Inventario inventario = new Inventario(); // se busca vehiculo por categoria y sede
					Vehiculo vehiculoEncontrado = inventario.encontrarVehiculoPorSedeYCateg(reserva.getSedeinicial(), reserva.getCategoria());

					vehiculoEncontrado = inventario.buscarVehiculoPorCategoriaMax(reserva.getSedeinicial(), reserva.getCategoria());
					if (vehiculoEncontrado != null) { //actualiza la disponibilidad vehiculo 
						Inventario.actualizarVehiculoAlquilado(vehiculoEncontrado, reserva.getSedefinal(),reserva.getCliente().getNombre(),reserva.getFechaFin());

						Tarifa tarifa = new Tarifa(reserva);
						long totalFinal = tarifa.calcularCosto70P();
						System.out.println("Pague el 70% restante del alquiler, el cual es " + totalFinal + " mil pesos");
						System.out.println("El vehículo " + vehiculoEncontrado.getMarca() + " con las placas " + vehiculoEncontrado.getPlaca() + " fue alquilado con éxito");

						Cliente clienteReserva = reserva.getCliente();
						agregarConductoresAdicionales(clienteReserva.getNumeroLicencia());

						long costoConductoresAdicionales = tarifa.calcularCostoConductoresAd();
						totalFinal += costoConductoresAdicionales;
						if (costoConductoresAdicionales > 0) {
							System.out.println("El nuevo total final, incluyendo el costo de los conductores adicionales, es: " + totalFinal + " mil pesos");
						}
						AlquilerVehiculo alquiler = new AlquilerVehiculo();
						alquiler.escribirLog(vehiculoEncontrado.getPlaca(), vehiculoEncontrado.getMarca(), vehiculoEncontrado.getCategoria(), reserva.getCliente().getNombre(), reserva.getFechaIni(), reserva.getHoraIni(), reserva.getFechaFin(), reserva.getHoraFin(), reserva.getSedeinicial(), reserva.getSedefinal(), totalFinal);
					} else {
						System.out.println("No se encontró ningún vehículo disponible en la categoría y sede especificadas.");
					}
				} else {
					System.out.println("No se encontró la reserva.");
				}
			}

			//12. alquiler sin reserva  ----------------------------------------------------------------------------------------------------------------------------------------------------------------			

			// Empleado normal hace esto
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
					System.out.println("Tiene que ser un numero (1) o (2) ");
				}
				if (usuariotemp !=null) {
					Cliente cliente =(Cliente)usuariotemp;
					int categoria = seleccionarCategoria();
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

					segurosSeleccionados = seleccionarSeguros();
					AlquilerVehiculo alquiler = new AlquilerVehiculo(cliente, fechaini, horaini, fechafinal, horafinal, sedeIn, sedeFin, categoria, segurosSeleccionados);

					// Busca un vehículo disponible
					Inventario inventario = new Inventario();
					Vehiculo vehiculoEncontrado = inventario.encontrarVehiculoPorSedeYCateg(alquiler.getSedeinicial(), alquiler.getCategoria()); // Modifica este método para aceptar un AlquilerVehiculo en lugar de una Reserva

					vehiculoEncontrado = inventario.buscarVehiculoPorCategoriaMax(alquiler.getSedeinicial(), alquiler.getCategoria());
					if (vehiculoEncontrado != null) { //actualiza la disponibilidad vehiculo 
						Inventario.actualizarVehiculoAlquilado(vehiculoEncontrado, alquiler.getSedefinal(),alquiler.getCliente().getNombre(),alquiler.getFechaFin());

						Tarifa tarifa = new Tarifa(alquiler);
						long totalFinal = tarifa.calcularCosto70P();
						System.out.println("Pague el 70% restante del alquiler, el cual es " + totalFinal + " mil pesos");
						System.out.println("El vehículo " + vehiculoEncontrado.getMarca() + " con las placas " + vehiculoEncontrado.getPlaca() + " fue alquilado con éxito");

						Cliente clienteReserva = alquiler.getCliente();
						agregarConductoresAdicionales(clienteReserva.getNumeroLicencia());

						long costoConductoresAdicionales = tarifa.calcularCostoConductoresAd();
						totalFinal += costoConductoresAdicionales;
						if (costoConductoresAdicionales > 0) {
							System.out.println("El nuevo total final, incluyendo el costo de los conductores adicionales, es: " + totalFinal + " mil pesos");
						}

						alquiler.escribirLog(vehiculoEncontrado.getPlaca(), vehiculoEncontrado.getMarca(), vehiculoEncontrado.getCategoria(), alquiler.getCliente().getNombre(), alquiler.getFechaIni(), alquiler.getHoraIni(), alquiler.getFechaFin(), alquiler.getHoraFin(), alquiler.getSedeinicial(), alquiler.getSedefinal(), totalFinal);
					} else {
						System.out.println("No se encontró ningún vehículo disponible en la categoría y sede especificadas.");
					}
				} 
			}

			//13. Generar reserva especial (trasaldar carro entre sedes) ----------------------------------------------------------------------------------------------------------------------------------------------------------------			

			if (opcion == 13) {
				// Lo hace Empleado
				int categoria = seleccionarCategoria() ;
				String fechaini = input("Escriba qué día va a desplazar el vehículo (en formato DD/MM/AA)");
				String sedein = input("Escriba de qué sede vendrá el vehiculo");
				String sedeout = input("Escriba a qué sede irá el vehiculo");
				Empleado empleado = (Empleado)usuario;
				Reserva reservaespecial = new Reserva (empleado,categoria,fechaini,sedein,sedeout);
				reservaespecial.escribirTXTespecial("./src/datos/ListaReserva.txt");
				System.out.println(reservaespecial.getinfoEspecial());
			}

			// 14. Generar alquiler especial (trasladar carro entre sedes)  ----------------------------------------------------------------------------------------------------------------------------------------------------------------			
			
			if (opcion == 14) {
			    // Lo hace empleado 
			    String identificador = input("Escriba el número de identificación de la reserva ESPECIAL");		    
			    Reserva reservaEspecial = Reserva.encontrarReservaPorID(Integer.parseInt(identificador));
			    if (reservaEspecial != null && reservaEspecial.isEsEspecial()) {  // mira si la reserva es o no especial

			        Reserva.eliminarReservaYActualizarArchivo(reservaEspecial.getID());
			        Inventario inventario = new Inventario("./src/datos/InventarioGENERAL.txt");
			        Vehiculo vehiculoEncontrado = inventario.encontrarVehiculoPorSedeYCateg(reservaEspecial.getSedeinicial(), reservaEspecial.getCategoria());

			        if (vehiculoEncontrado != null) {
			            Inventario.actualizarVehiculoAlquilado(vehiculoEncontrado, reservaEspecial.getSedefinal(), "", ""); // Actualizar el vehículo alquilado con su nueva sede
			            System.out.println("El vehiculo " + vehiculoEncontrado.getMarca() + " con las placas " + vehiculoEncontrado.getPlaca() + " fue transferido con éxito");
			        } else {
			            System.out.println("No se encontró el carro");
			        }
			    } else {
			        System.out.println("No existe una reserva especial con esa identificación");
			    }
			}

			//15.recibir un automovil (ponerlo en limpieza)	----------------------------------------------------------------------------------------------------------------------------------------------------------------			
			
			if (opcion == 15) {
				// Lo hace empleado
				String placa= input("¿cual vehículo fue entregado?: ");
			    Inventario inventario = new Inventario("./src/datos/InventarioGENERAL.txt");
				boolean carroalquilado = inventario.devolverDeLimpiezaVehiculo (placa);
				
				if (carroalquilado) {

					System.out.println("Se envió a lavar al vehiculo de placa "+placa);
				}
				else {
					System.out.println("No se encontró ningun vehiculo alquilado con esa placa");
				}
			}

			// 16. mandar un automovil a mantenimiento ----------------------------------------------------------------------------------------------------------------------------------------------------------------						

			if (opcion == 16) {
				// Lo hace empleado
				String placa = input("Ingrese la placa del vehículo que tiene que ser mandado a mantenimiento (tiene que estar disponible actualmente)");
			    Inventario inventario = new Inventario("./src/datos/InventarioGENERAL.txt");

				boolean revisar_carro = inventario.actualizarMantenimientoVehiculo(placa);
				if (revisar_carro) {
					System.out.println("Se envió al mecánico el vehiculo de placa "+placa);
				}
				else {
					System.out.println("No se encontró ningun vehiculo alquilado con esa placa");
				
			}
				}

			// 17.devolver auto de limpieza o mantenimiento ----------------------------------------------------------------------------------------------------------------------------------------------------------------				
			
			if (opcion == 17) {
				// Lo hace empleado
				String placa = input("Ingrese la placa del vehículo que desea regresar");
			    Inventario inventario = new Inventario("./src/datos/InventarioGENERAL.txt");
			    
				boolean carroalquilado = inventario.devolverDeLimpiezaVehiculo (placa);
				if (carroalquilado) {

					System.out.println("Se envió a lavar al vehiculo de placa "+placa);
				}
				else {
					System.out.println("No se encontró ningun vehiculo alquilado con esa placa");
				}
			}

			// 18. generar historial alquiler ----------------------------------------------------------------------------------------------------------------------------------------------------------------			
			
			if (opcion == 18) {
				// Lo hace Admin general
				String placa = input("Ingrese la placa del vehículo para el cual desea revisar su historial de alquiler ");
				AlquilerVehiculo alquiler = new AlquilerVehiculo();
				HashMap<String, ArrayList<AlquilerVehiculo>> historialesAlquileres = alquiler.cargarLogAlquileres();

				if (historialesAlquileres.containsKey(placa)) {
					System.out.println("Historial de alquileres para el vehículo con placa " + placa + ":");
					ArrayList<AlquilerVehiculo> alquileres = historialesAlquileres.get(placa);
					for (AlquilerVehiculo indiceAlquiler : alquileres) {
						System.out.println("Placa del vehiculo: " + indiceAlquiler.getPlaca());
						System.out.println("Marca del vehiculo: " + indiceAlquiler.getMarca());
						System.out.println("Nombre del cliente: " + indiceAlquiler.getClienteAlquiler());
						System.out.println("Fecha en la que el cliente recoge el vehiculo : " + indiceAlquiler.getFechaIni());
						System.out.println("Hora en la que el cliente recoge el vehiculo : " + indiceAlquiler.getHoraIni());
						System.out.println("Fecha en la que el cliente devuelve el vehiculo : " + indiceAlquiler.getFechaFin());
						System.out.println("Hora en la que el cliente devuelve el vehiculo: " + indiceAlquiler.getHoraFin());
						System.out.println("Costo total: " + indiceAlquiler.getCostoTotal());
						System.out.println("-------------------------------------------------------------");
					}
				} else {
					System.out.println("No se encontró historial de alquileres para el carro con la placa " + placa);
				}
			}

			if (opcion == 0) {
				revision_opciones = false;
			}
		}
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

	// codigo que se repetia mucho:	
	public static int seleccionarCategoria() {
		System.out.println("Estos son los tipos de carros disponibles actualmente: \n"
				+ "1.economico, costo: 200000 \r\n"
				+ "2.estándar, costo: 250000 \r\n"
				+ "3.van,costo: 280000 \r\n"
				+ "4.SUV, costo: 300000\r\n"
				+ "5.todoterreno, costo: 320000 \r\n"
				+ "6.lujo, costo: 400000 ");
		return Integer.parseInt(input("Seleccione una categoría de acuerdo a su numero, ej. (1)"));

	}
	public static ArrayList<String> seleccionarSeguros() throws IOException {
		ArrayList<String> segurosSeleccionados = new ArrayList<>();
		HashMap<String, ArrayList<String>> segurosDisponibles = Tarifa.cargarTarifasSeguros();
		boolean seleccionandoSeguros = true;

		while (seleccionandoSeguros) {
			System.out.println("Seleccione un seguro por su ID, ej. (Seguro1). Escriba 'terminar' para finalizar la selección.");

			int numero = 1;
			for (String idSeguro : segurosDisponibles.keySet()) {
				ArrayList<String> datosSeguro = segurosDisponibles.get(idSeguro);
				if (datosSeguro != null && datosSeguro.size() > 1) {
					String descripcion = datosSeguro.get(0);
					String costo = datosSeguro.get(1);
					System.out.println(numero + ". " + "(" + idSeguro + ")" + "  " + descripcion + ", Costo:  " + costo);
					numero++;
				}
			}
			String seguro = input("Seguro: ");
			if (seguro.equalsIgnoreCase("terminar")) {
				seleccionandoSeguros = false;
			} else if (segurosDisponibles.containsKey(seguro)) {
				segurosSeleccionados.add(seguro);
			} else {
				System.out.println("ID de seguro no válido. Intente nuevamente.");
			}
		}
		return segurosSeleccionados;
	}    	
	public static void agregarConductoresAdicionales(String licenciaClienteAlquiler) throws Exception {

		String decisionConductor = input("¿Desea agregar conductores adicionales? (si/no): ");
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
					Conductor conductor = new Conductor(licenciaClienteAlquiler, nombres, numeroLicencia, paisExpedicionLicencia, fechaVencimientoLicencia);
					licenciasAdicionales.add(conductor.getNumeroLicencia());
					conductor.escribirTXT();
					numeroConductor++;
					conductoresAdicionales.put(licenciaClienteAlquiler, licenciasAdicionales);

				} else if (decision.equalsIgnoreCase("terminarConductores")) {
					añadiendoConductor = false;
				}
			}
		}
	}	
}
