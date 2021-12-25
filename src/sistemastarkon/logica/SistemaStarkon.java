package sistemastarkon.logica;

public interface SistemaStarkon {

	void ingresarSucursal(String ciudad);
	
	void ingresarCliente(String rut, String nombre, String apellido, int saldo);
	
	void asociarClienteSucursal(String ciudad, String rut);
	
	void ingresarDocumento(String codigo, int peso, int grosor);
	
	void ingresarEncomienda(String codigo, int peso, int largo, int ancho,
			int profundidad);
	
	void ingresarValija(String codigo, int peso, String material);
	
	void asociarEntregas(String codigo, String rutRemitente, String rutDestinatario);
	
	boolean verificarCliente(String rut);
	
	void recargarSaldo(String rut, int monto);
	
	String obtenerEntregas();
	
	String obtenerDatosOficinas();
	
	String obtenerEntregasCliente();
	
	String obtenerBalances();
	
	String obtenerTxtClientes();
	
	String obtenerTxtEntregas();
}
