package uniandes.dpoo.proyecto1.consola;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AdministradorLocal extends Administrador{
	private String usuario;
	private String contraseña;
	private String sede;
	public AdministradorLocal(String usuario, String contraseña, String sede) {
		super(usuario, contraseña);
		this.sede=sede;
		// TODO Auto-generated constructor stub
	}
	public int mostrarOpciones() {
		System.out.println("\nOpciones del admin de la sede tal:\n");
		System.out.println("1. Añadir un nuevo empleado de la sede");
		System.out.println("2. Despedir a un empleado");
		System.out.println("3. Revisar todos los carros de la sede");
		System.out.println("4. Revisar todos los carros");
		System.out.println("5. Salir.");
		int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
		int resp = 0;
		if (opcion_seleccionada==(1)) {
			resp =9;
		}
		if (opcion_seleccionada==(2)) {
			resp =10;
		}
		if (opcion_seleccionada==(3)) {
			resp =3;
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
