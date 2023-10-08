package uniandes.dpoo.proyecto1.consola;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Cliente extends Usuario{

	public Cliente(String nombre, String contraseña) {
		super(nombre, contraseña);
	}

	@Override
	public void getRol() {
		System.out.println("Eres un cliente");
		
	}

	@Override
	public int mostrarOpciones() {
		System.out.println("\nOpciones del cliente:\n");
		System.out.println("1. Crear una reserva");
		System.out.println("2. Revisar/eliminar una reserva");
		System.out.println("3. Revisar todos los carros disponibles");
		System.out.println("4. Revisar todos los carros disponibles de una sede específica");
		System.out.println("5. Salir.");
		int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
		int resp = 0;
		if (opcion_seleccionada==(1)) {
			resp =4;
		}
		if (opcion_seleccionada==(2)) {
			resp =5;
		}
		if (opcion_seleccionada==(3)) {
			resp =2;
		}
		if (opcion_seleccionada==(4)) {
			resp =3;
		}
		if (opcion_seleccionada==(5)) {
			resp =0;
		}
		return resp;
	}
	
}