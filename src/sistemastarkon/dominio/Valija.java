package sistemastarkon.dominio;

public class Valija extends Entrega {
	private String material;

	public Valija(String codigo, int peso, String material) {
		super(codigo, peso);
		this.material = material;
		switch (material) {
		case "Cuero":
			setValor(peso * 200 * 150);
		case "Plastico":
			setValor(peso * 150 * 150);
		case "Tela":
			setValor(peso * 100 * 150);
		}
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}
}
