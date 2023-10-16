package uniandes.dpoo.proyecto1.consola;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Seguro {
	private String idSeguro;
	private String descripcionSeguro;
	private String costoSeguro;

	public Seguro(String idSeguro, String descripcionSeguro, String costoSeguro) {
		this.idSeguro = idSeguro;
		this.descripcionSeguro = descripcionSeguro;
		this.costoSeguro = costoSeguro;
	}

	public String getCostoSeguro() {
		return costoSeguro;
	}

	public String getIdSeguro() {
		return idSeguro;
	}

	public String getDescripcionSeguro() {
		return descripcionSeguro;
	}

	public void escribirTXT() throws Exception {
	    FileWriter output = new FileWriter("./src/datos/Seguros.txt", true);
	    BufferedWriter br = new BufferedWriter(output);
	    br.write(idSeguro + "," + descripcionSeguro + "," + costoSeguro + "\n");
	    br.close();
	}
	
}
