package uniandes.dpoo.proyecto1.consola;

public class Administrador extends Empleado{
	public Administrador(String nombre, String contraseña) {
		super(nombre, contraseña);
	}
	public void getRol() {
		System.out.println("Eres un administrador.");
	} 

}
