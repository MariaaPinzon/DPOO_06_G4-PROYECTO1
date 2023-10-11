package uniandes.dpoo.proyecto1.consola;

public class Conductor {

	private String nombres;
    private String numeroLicencia;
    private String paisExpedicionLicencia;
    private String fechaVencimientoLicencia;
    
	public Conductor( String nombres, String numeroLicencia, String paisExpedicionLicencia, String fechaVencimientoLicencia) {
		this.nombres = nombres;
        this.numeroLicencia = numeroLicencia;
        this.paisExpedicionLicencia = paisExpedicionLicencia;
        this.fechaVencimientoLicencia = fechaVencimientoLicencia;
	}

	public String getNombres() {
		return nombres;
	}

	public String getNumeroLicencia() {
		return numeroLicencia;
	}

	public String getPaisExpedicionLicencia() {
		return paisExpedicionLicencia;
	}

	public String getFechaVencimientoLicencia() {
		return fechaVencimientoLicencia;
	}
	
	

}
