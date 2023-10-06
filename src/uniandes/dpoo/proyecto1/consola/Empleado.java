package uniandes.dpoo.proyecto1.consola;

public class Empleado extends Usuario {
	public Empleado(String nombre, String contraseña) {
		super(nombre, contraseña);
		// TODO Auto-generated constructor stub
	}
	public void getRol() {
		System.out.println("Eres un empleado.");
	}
	@Override
	public int mostrarOpciones() {
		// TODO Auto-generated method stub
		return 0;
	}

}
