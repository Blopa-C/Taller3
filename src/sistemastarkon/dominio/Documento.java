package sistemastarkon.dominio;

public class Documento extends Entrega {
	private int grosor;

	public Documento(String codigo, int peso, int grosor) {
		super(codigo, peso);
		this.grosor = grosor;
		setValor(calcularValor());
	}
	
	public int calcularValor() {
		return (int)((getPeso()/1000.0) * (grosor/10.0) * 100);
	}
	
	public boolean verificarLimites() {
		if (getPeso() <= 0 || getPeso() > 1500) return false;
		else if (grosor <= 0 || grosor > 50) return false;
		return true;
	}
	
	public int getGrosor() {
		return grosor;
	}

	public void setGrosor(int grosor) {
		this.grosor = grosor;
	}
	
	@Override
	public String toString() {
		return super.toString() + ", grosor: " + grosor + " mm";
	}
}
