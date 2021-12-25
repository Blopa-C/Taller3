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
		setValor((int)((peso/1000.0) * (largo/100.0) * (ancho/100.0) 
				* (profundidad/100.0) * 50));
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
