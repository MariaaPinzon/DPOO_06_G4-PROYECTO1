package uniandes.dpoo.proyecto1.consola;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Empleado extends Usuario {
	private String tipoUsuario;
	private String nombres;
    private String contacto;
    private String fechaNacimiento;
    private String nacionalidad;
    private String docIdentidad;

    public Empleado(String nombreUsuario, String contraseña, String tipoUsuario,String nombres, String datosContacto, String fechaNacimiento, String nacionalidad, String docIdentidad) {
        super(nombreUsuario, contraseña); // llamada al constructor de la clase padre Usuario
        this.tipoUsuario = tipoUsuario;
        this.nombres = nombres;
        this.contacto = datosContacto;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.docIdentidad = docIdentidad;
	}

	public void escribirTXT(String enlace) throws Exception {
		FileWriter output = new FileWriter(enlace, true);
		BufferedWriter br = new BufferedWriter(output);

	    br.write(getNombre() + "," + getContraseña() + "," + nombres + "," + contacto + "," + fechaNacimiento + "," + nacionalidad + "," + docIdentidad +"\n");
	    br.close();
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
	public void getRol() {
		System.out.println("Eres un empleado.");
	}
	@Override
	public int mostrarOpciones() {
		System.out.println("\nOpciones de empleado:\n");
		System.out.println("1. Generar alquiler (con reserva realizada previamente)");
		System.out.println("2. Generar alquiler (sin reserva)");
		System.out.println("3. Revisar todos los carros");
		System.out.println("4. Revisar todos los carros disponibles");
		System.out.println("5. Revisar todos los carros de una sede específica");
		System.out.println("6. Reserva especial (traslado de carro entre sedes)");
		System.out.println("7. Alquilar el carro (trasladarlo entre sede)");
		System.out.println("8. Salir.");
		int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
		int resp = 0;
		if (opcion_seleccionada==(1)) {
			resp =11;
		}
		if (opcion_seleccionada==(2)) {
			resp =12;
		}
		if (opcion_seleccionada==(3)) {
			resp =1;
		}
		if (opcion_seleccionada==(4)) {
			resp =2;
		}
		if (opcion_seleccionada==(5)) {
			resp =3;
		}
		if (opcion_seleccionada==(6)) {
			resp =13;
		}
		if (opcion_seleccionada==(7)) {
			resp =14;
		}
		if (opcion_seleccionada==(8)) {
			resp =0;
		}
		return resp;
	}
}
