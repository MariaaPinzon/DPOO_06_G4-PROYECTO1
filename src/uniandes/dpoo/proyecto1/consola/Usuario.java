package uniandes.dpoo.proyecto1.consola;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class Usuario {
	private String nombre;
	private String contraseña;
	

	public Usuario(String nombre, String contraseña) {
		this.nombre=nombre;
		this.contraseña=contraseña;
	}
	public abstract void getRol();
	
	public abstract int mostrarOpciones();
	
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
	public String getContraseña() {
		return contraseña;
	}
	public String getNombre() {
		return nombre;
	}
	public abstract String getFechaVencimientoMedioPago();
}
