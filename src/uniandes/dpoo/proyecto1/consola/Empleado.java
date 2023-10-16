package uniandes.dpoo.proyecto1.consola;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileReader;
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
    
    public Empleado() {
        super("", ""); 
    }    
    
	public void escribirTXT() throws Exception {
		FileWriter output = new FileWriter("./src/datos/Usuarios.txt", true);
		BufferedWriter br = new BufferedWriter(output);

	    br.write(getNombre() + "," + getContraseña()+",E" + "," + nombres + "," + contacto + "," + fechaNacimiento + "," + nacionalidad + "," + docIdentidad+ "," + "NA"+ "," + "NA"+ "," + "NA"+ "," + "NA"+ "," + "NA"+ "," + "NA"+"\n");
	    br.close();
	}
	
	/**
	 * Elimina un empleado del sistema a partir de su nombre de usuario.
	 * precond: El archivo Usuarios.txt debe existir y ser accesible para lectura y escritura.
	 * 			Se espera que el nombre de usuario del empleado a eliminar sea válido y exista en el sistema.
	 * postcond: Se elimina exitosamente al empleado.
	 * @param nombreUsuarioEmpleado El String del nombre del usuario que se quiere eliminar del sistema.
	 * @return Un boolean que confirma si el empleado fue eliminado exitosamente.
	 * @throws IOException Si ocurre un error al escribir en el archivo "Usuarios.txt". Algunos de estos errores pueden ser:
	 * 					   -file not found
	 * 					   -permission issues
	 * 					   -u otros errores tipo I/O.
	 */
	public boolean eliminarEmpleado(String nombreUsuarioEmpleado) throws IOException {
	BufferedReader br = new BufferedReader(new FileReader("./src/datos/Usuarios.txt"));
	String linea = null;
	linea=br.readLine();
	ArrayList<String> usuarios= new ArrayList<>();
	boolean usuario_eliminado= false;
	
	while (linea!=null) {
		String informacion[]= linea.split(",");
		String usuariocomp = informacion[0];
		if (!usuariocomp.equals(nombreUsuarioEmpleado)) {
			usuarios.add(linea);
		}
		else if (informacion[2].equals("E")){
			usuario_eliminado=true;
		}
		linea=br.readLine();
	}
	br.close();
	if (usuario_eliminado) {
		FileWriter eliminarUsuario = new FileWriter("./src/datos/Usuarios.txt");
		for (int i=0;i<usuarios.size();i++) {
			eliminarUsuario.append(usuarios.get(i)+"\n");
		}
		
		eliminarUsuario.close();
	}
	return usuario_eliminado;
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
		System.out.println("8. Recibir un auto alquilado (mandarlo a lavar)");
		System.out.println("9. Enviar un vehículo a mantenimiento");
		System.out.println("10. Regresar un vehículo de mantenimiento o lavado");
		System.out.println("11. Salir.");
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
			resp =15;
		}
		if (opcion_seleccionada==(9)) {
			resp =16;
		}
		if (opcion_seleccionada==(10)) {
			resp =17;
		}
		if (opcion_seleccionada==(11)) {
			resp =0;
		}
		return resp;
	}

	@Override
	public String getFechaVencimientoMedioPago() {
		// TODO Auto-generated method stub
		return null;
	}
}
