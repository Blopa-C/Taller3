package sistemastarkon.dominio;

public class Valija extends Entrega {
	private String material;

	public Valija(String codigo, int peso, String material) {
		super(codigo, peso);
		this.material = material;
		switch (material) {
		case "Cuero":
			setValor((int)((peso/1000.0) * 200 * 150));
		case "Plastico":
			setValor((int)((peso/1000.0) * 150 * 150));
		case "Tela":
			setValor((int)((peso/1000.0) * 100 * 150));
		}
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}
	
	@Override
	public String toString() {
		return super.toString() + ", material: " + material.toLowerCase();
	}
}
