package uniandes.dpoo.proyecto1.consola;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AdministradorLocal extends Administrador{
	private String tipoUsuario;
	private String nombres;
    private String contacto;
    private String fechaNacimiento;
    private String nacionalidad;
    private String docIdentidad;
    private String sede;
    public AdministradorLocal(String nombreUsuario, String contraseña, String tipoUsuario,String nombres, String datosContacto, String fechaNacimiento, String nacionalidad, String docIdentidad, String sede) {
		super(nombreUsuario,contraseña,tipoUsuario,nombres,datosContacto,fechaNacimiento,nacionalidad,docIdentidad);
		this.sede=sede;
		// TODO Auto-generated constructor stub
	}
    public String getTipoUsuario() {
		return tipoUsuario;
	}

	public String getNombres() {
		return nombres;
	}

	public String getContacto() {
		return contacto;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public String getDocIdentidad() {
		return docIdentidad;
	}

	public String getSede() {
		return sede;
	}

	public int mostrarOpciones() {
		System.out.println("\nOpciones del admin de la sede "+getSede() +":\n");
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
	
	@Override
	public void getRol() {
		System.out.println("Eres un administrador Local.");
	} 
	
	
	

}