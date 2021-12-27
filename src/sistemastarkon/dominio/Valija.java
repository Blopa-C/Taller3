package sistemastarkon.dominio;

public class Valija extends Entrega {
	private String material;

	public Valija(String codigo, int peso, String material) {
		super(codigo, peso);
		this.material = material.toLowerCase();
		setValor(calcularValor());
	}
	
	public boolean verificarLimites() {
		if (getPeso() <= 0 || getPeso() > 2000) return false;
		if (!material.equalsIgnoreCase("cuero")
				&& !material.equalsIgnoreCase("plastico")
				&& !material.equalsIgnoreCase("tela")) return false;
		return true;
	}
	
	public int calcularValor() {
		int precioMaterial = 0;
		switch (material) {
		case "cuero":
			precioMaterial = 200;
		case "plastico":
			precioMaterial = 150;
		case "tela":
			precioMaterial = 100;
		}
		return (int)((getPeso()/1000.0) * precioMaterial * 150);
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
