package sistemastarkon.dominio;

import sistemastarkon.logica.ListaCircularDobleEnlace;

public class Sucursal {
	private String ciudad;
	private ListaCircularDobleEnlace<Entrega> envios;
	private ListaCircularDobleEnlace<Entrega> recibos;
	
	public Sucursal(String ciudad) {
		this.ciudad = ciudad;
		envios = new ListaCircularDobleEnlace<Entrega>();
		recibos = new ListaCircularDobleEnlace<Entrega>();
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public ListaCircularDobleEnlace<Entrega> getEnvios() {
		return envios;
	}

	public void setEnvios(ListaCircularDobleEnlace<Entrega> envios) {
		this.envios = envios;
	}

	public ListaCircularDobleEnlace<Entrega> getRecibos() {
		return recibos;
	}

	public void setRecibos(ListaCircularDobleEnlace<Entrega> recibos) {
		this.recibos = recibos;
	}
}
