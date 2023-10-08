package uniandes.dpoo.proyecto1.consola;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class Usuario {

	

	public Usuario(String nombre, String contraseña) {
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
}
