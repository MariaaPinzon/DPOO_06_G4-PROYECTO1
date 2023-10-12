package uniandes.dpoo.proyecto1.consola;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class Conductor {
		private String licenciaClienteAlquiler;
		private String nombres;
	    private String numeroLicencia;
	    private String paisExpedicionLicencia;
	    private String fechaVencimientoLicencia;

		public Conductor( String licenciaClienteAlquiler, String nombres, String numeroLicencia, String paisExpedicionLicencia, String fechaVencimientoLicencia) {
			this.licenciaClienteAlquiler = licenciaClienteAlquiler;
			this.nombres = nombres;
	        this.numeroLicencia = numeroLicencia;
	        this.paisExpedicionLicencia = paisExpedicionLicencia;
	        this.fechaVencimientoLicencia = fechaVencimientoLicencia;
		}
		
		public void escribirTXT(String enlace) throws Exception {
		    FileWriter output = new FileWriter(enlace, true);
		    BufferedWriter br = new BufferedWriter(output);
		    br.write(licenciaClienteAlquiler + "," + nombres + "," + numeroLicencia + "," + paisExpedicionLicencia + "," + fechaVencimientoLicencia + "\n");
		    br.close();
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

		public String getLicenciaClienteAlquiler() {
			return licenciaClienteAlquiler;
		}



	}