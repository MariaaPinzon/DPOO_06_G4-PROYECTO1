package uniandes.dpoo.proyecto1.consola;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Administrador extends Empleado{
	public Administrador(String nombre, String contraseña) {
		super(nombre, contraseña);
	}
	public void getRol() {
		System.out.println("Eres un administrador.");
	} 
	@Override
	public int mostrarOpciones() {
		System.out.println("\nOpciones del admin:\n");
		System.out.println("1. Añadir un carro a una sede/inventario");
		System.out.println("2. Eliminar un carro del inventario");
		System.out.println("3. Crear nuevos seguros");
		System.out.println("4. Revisar todos los carros");
		System.out.println("5. Salir.");
		int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
		int resp = 0;
		if (opcion_seleccionada==(1)) {
			resp =6;
		}
		if (opcion_seleccionada==(2)) {
			resp =7;
		}
		if (opcion_seleccionada==(3)) {
			resp =8;
		}
		if (opcion_seleccionada==(4)) {
			resp =1;
		}
		if (opcion_seleccionada==(5)) {
			resp =0;
		}
		return resp;
	}
}