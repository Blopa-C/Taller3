package sistemastarkon.dominio;

public class Encomienda extends Entrega {
	private int largo;
	private int ancho;
	private int profundidad;
	
	public Encomienda(String codigo, int peso, int largo, int ancho, int profundidad) {
		super(codigo, peso);
		this.largo = largo;
		this.ancho = ancho;
		this.profundidad = profundidad;
		setValor(calcularValor());
	}
	
	public int calcularValor() {
		return (int)((getPeso()/1000.0) * (largo/100.0) * (ancho/100.0) 
				* (profundidad/100.0) * 50);
	}
	
	public boolean verificarLimites() {
		if (getPeso() <= 0 || getPeso() > 50000) return false;
		else if (largo <= 0 || largo > 120) return false;
		else if (ancho <= 0 || ancho > 80) return false;
		else if (profundidad <= 0 || profundidad > 80) return false;
		return true;
	}

	public int getLargo() {
		return largo;
	}

	public void setLargo(int largo) {
		this.largo = largo;
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public int getProfundidad() {
		return profundidad;
	}

	public void setProfundidad(int profundidad) {
		this.profundidad = profundidad;
	}

	@Override
	public String toString() {
		return super.toString() + ", largo: " + largo + " cm, ancho: " + ancho
				+ " cm, profundidad: " + profundidad + " cm";
	}
}
