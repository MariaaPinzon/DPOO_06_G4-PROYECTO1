package uniandes.dpoo.proyecto1.modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.FileWriter;

import uniandes.dpoo.proyecto1.consola.Administrador;
import uniandes.dpoo.proyecto1.consola.Cliente;
import uniandes.dpoo.proyecto1.consola.Usuario;
import uniandes.dpoo.proyecto1.consola.Empleado;
import uniandes.dpoo.proyecto1.consola.Inventario;

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
		System.out.println("\nMostrando todos los carros disponibles en todas las sedes\n");
		inventariogeneral.mostrarinventariodisponible();
		
		System.out.println("\nMostrando todos los carros de la empresa\n");
		inventariogeneral.mostrarinventariototal();
		usuario.mostrarOpciones();
		
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
