package sistemastarkon.dominio;

import sistemastarkon.logica.ListaCircularDobleEnlace;

public class Cliente {
	private String rut;
	private String nombre;
	private String apellido;
	private int saldo;
	private Sucursal sucursal;
	private ListaCircularDobleEnlace<Entrega> envios;
	private ListaCircularDobleEnlace<Entrega> recibos;
	
	public Cliente(String rut, String nombre, String apellido, int saldo) {
		this.rut = rut;
		this.nombre = nombre;
		this.apellido = apellido;
		this.saldo = saldo;
		envios = new ListaCircularDobleEnlace<Entrega>();
		recibos = new ListaCircularDobleEnlace<Entrega>();
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getSaldo() {
		return saldo;
	}

	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
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
