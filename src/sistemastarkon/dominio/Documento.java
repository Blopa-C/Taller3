package sistemastarkon.dominio;

public class Documento extends Entrega {
	private int grosor;

	public Documento(String codigo, int peso, int grosor) {
		super(codigo, peso);
		this.grosor = grosor;
		setValor(peso * grosor * 100);
	}
	
	public int getGrosor() {
		return grosor;
	}

	public void setGrosor(int grosor) {
		this.grosor = grosor;
	}	
}
