package sistemastarkon.dominio;

public abstract class Entrega {
	private String codigo;
	private int peso;
	private int valor;
	private Cliente remitente;
	private Cliente destinatario;
	
	protected Entrega(String codigo, int peso) {
		this.codigo = codigo;
		this.peso = peso;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public Cliente getRemitente() {
		return remitente;
	}

	public void setRemitente(Cliente remitente) {
		this.remitente = remitente;
	}

	public Cliente getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(Cliente destinatario) {
		this.destinatario = destinatario;
	}
	
	@Override
	public String toString() {
		return "Codigo: " + codigo + ", peso: " + peso + " g, valor: $ "
				+ valor;
	}
}
