package uniandes.dpoo.proyecto1.consola;



public class Cliente extends Usuario{
	private String nombres;
    private String contacto;
    private String fechaNacimiento;
    private String nacionalidad;
    private String docIdentidad;
    private String numeroLicencia;
    private String paisExpedicionLicencia;
    private String fechaVencimientoLicencia;
    private String imagenLicencia;
    private String tipoMedioDePago;
    private long numeroMedioDePago;
    private String fechaVencimientoMedioPago;
    
    public Cliente(String nombreUsuario, String contraseña, String nombres, String datosContacto, String fechaNacimiento, String nacionalidad, String docIdentidad, String numeroLicencia, String paisExpedicionLicencia, String fechaVencimientoLicencia, String tipoMedioDePago, long numeroMedioDePago, String fechaVencimientoMedioPago) {
        super(nombreUsuario, contraseña); // llamada al constructor de la clase padre Usuario
        this.setNombres(nombres);
        this.setContacto(datosContacto);
        this.setFechaNacimiento(fechaNacimiento);
        this.setNacionalidad(nacionalidad);
        this.setDocIdentidad(docIdentidad);
        this.setNumeroLicencia(numeroLicencia);
        this.setPaisExpedicionLicencia(paisExpedicionLicencia);
        this.setFechaVencimientoLicencia(fechaVencimientoLicencia);
        this.setTipoMedioDePago(tipoMedioDePago);
        this.setNumeroMedioDePago(numeroMedioDePago);
        this.setFechaVencimientoMedioPago(fechaVencimientoMedioPago);
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
	@Override
	public String getFechaVencimientoMedioPago() {
		return fechaVencimientoMedioPago;
	}

	public void setFechaVencimientoMedioPago(String fechaVencimientoMedioPago) {
		this.fechaVencimientoMedioPago = fechaVencimientoMedioPago;
	}

	public String getPaisExpedicionLicencia() {
		return paisExpedicionLicencia;
	}

	public void setPaisExpedicionLicencia(String paisExpedicionLicencia) {
		this.paisExpedicionLicencia = paisExpedicionLicencia;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getContacto() {
		return contacto;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getDocIdentidad() {
		return docIdentidad;
	}

	public void setDocIdentidad(String docIdentidad) {
		this.docIdentidad = docIdentidad;
	}

	public String getNumeroLicencia() {
		return numeroLicencia;
	}

	public void setNumeroLicencia(String numeroLicencia) {
		this.numeroLicencia = numeroLicencia;
	}

	public String getFechaVencimientoLicencia() {
		return fechaVencimientoLicencia;
	}

	public void setFechaVencimientoLicencia(String fechaVencimientoLicencia) {
		this.fechaVencimientoLicencia = fechaVencimientoLicencia;
	}

	public String getImagenLicencia() {
		return imagenLicencia;
	}

	public void setImagenLicencia(String imagenLicencia) {
		this.imagenLicencia = imagenLicencia;
	}

	public long getNumeroMedioDePago() {
		return numeroMedioDePago;
	}

	public void setNumeroMedioDePago(long numeroMedioDePago) {
		this.numeroMedioDePago = numeroMedioDePago;
	}

	public String getTipoMedioDePago() {
		return tipoMedioDePago;
	}

	public void setTipoMedioDePago(String tipoMedioDePago) {
		this.tipoMedioDePago = tipoMedioDePago;
	}
	
}