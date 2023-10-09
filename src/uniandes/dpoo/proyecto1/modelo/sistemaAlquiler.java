package uniandes.dpoo.proyecto1.modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.FileWriter;

import uniandes.dpoo.proyecto1.consola.Administrador;
import uniandes.dpoo.proyecto1.consola.Cliente;
import uniandes.dpoo.proyecto1.consola.Usuario;
import uniandes.dpoo.proyecto1.consola.Empleado;
import uniandes.dpoo.proyecto1.consola.Inventario;
import uniandes.dpoo.proyecto1.consola.InventarioSede;
import uniandes.dpoo.proyecto1.consola.Reserva;

public class sistemaAlquiler {
	@SuppressWarnings("resource")
	public Usuario IniciarSesion() throws IOException {
		BufferedReader br = null;
		br = new BufferedReader(new FileReader("./src/datos/Usuarios.txt"));
		
		String linea = null;
		Boolean revisar = false;
		Usuario usuarioreal = null;
		linea = br.readLine();
		while (revisar == false) {
			String usuario = input("ingrese su usuario: ");
			String contra = input("ingrese su contraseña: ");
			while (linea!=null) 
			{
				
				String[] info = linea.split(",");
				String cuenta = info[0];
				String password = info[1];
				String jerarquia = info[2];
				if (usuario.equals(cuenta) && contra.equals(password)) {
					if (jerarquia.equals("A") ){
						usuarioreal = new Administrador(usuario, contra);
						usuarioreal.getRol();
						
					}
					else if (jerarquia.equals("E") ){
						usuarioreal = new Empleado(usuario, contra);
						usuarioreal.getRol();
						
					}
					else if (jerarquia.equals("C") ){
						usuarioreal = new Cliente(usuario, contra);
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
			br.write(usuario+","+contra+",C");
			br.close();
			returnfinal = new Cliente (usuario, contra);
		}
		else if(usuario.equals("secretoA")){
			System.out.println("Hola admin");
			String adminusuario = input("ingrese su usuario: ");
			String contra = input("ingrese su contraseña: ");		
			br.write(adminusuario+","+contra+",A");
			br.close();
			returnfinal = new Administrador (adminusuario, contra);
		}
		else { 
			System.out.println("Hola empleado");
			String empleusuario = input("ingrese su usuario: ");
			String contra = input("ingrese su contraseña: ");
			br.write(empleusuario+","+contra+",E");
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
			1. revisar los carros del inventario general
			2. revisar todos los carros disponibles
			3. revisar todos los carros disponibles de una sede.
			4.Crear una reserva
			5.Gestionar/eliminar reserva
			6.Añadir carro al inventario
			7. Eliminar carro del inventario
			8. Crear seguro
			9. crear empleado
			10. eliminar empleado
			11. alquiler con reserva
			12. alquiler sin reserva


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
				String fechaini = input("Escriba qué día quiere recoger el vehículo (en formato DD/MM/AA): ");
				String horaini = input("Escriba qué a qué hora lo recogerá (en formato HH:MM): ");
				String fechafinal = input("Escriba qué día va a devolver el vehículo (en formato DD/MM/AA): ");
				String horafinal = input("Escriba qué a qué hora lo devolverá (en formato HH:MM): ");
				System.out.println("Nuestras sedes: \n"
						+ "Sede 1 (s1)\r\n"
						+ "Sede 2 (s2)\r\n"
						+ "Sede 3 (s3)\r\n");
				String sedeIn = input("Escriba el nombre de la sede de la cual quiere recoger el automóvil: ");
				String sedeFin = input("Escriba el nombre de la sede de la cual quiere recoger el automóvil: ");
				Reserva reserva = new Reserva (cliente,fechaini, horaini, fechafinal, horafinal,  sedeIn, sedeFin, categoria);
				System.out.println(reserva.getinfo()); 
				//falta añadirlo a un txt de reservas
			}
			if (opcion == 5) {
				break;
			}
			if (opcion == 6) {
				break;
			}
			if (opcion == 7) {
				break;
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
				break;
			}
			if (opcion == 12) {
				break;
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
}
