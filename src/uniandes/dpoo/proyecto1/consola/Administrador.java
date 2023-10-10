package uniandes.dpoo.proyecto1.consola;



public class Administrador extends Empleado{
	private String tipoUsuario;
	private String nombres;
    private String contacto;
    private String fechaNacimiento;
    private String nacionalidad;
    private String docIdentidad;
    
	public Administrador(String nombreUsuario, String contraseña, String tipoUsuario,String nombres, String datosContacto, String fechaNacimiento, String nacionalidad, String docIdentidad){
		super(nombreUsuario,contraseña,tipoUsuario,nombres,datosContacto,fechaNacimiento,nacionalidad,docIdentidad);
		
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