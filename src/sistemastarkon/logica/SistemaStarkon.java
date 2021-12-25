package sistemastarkon.logica;

public interface SistemaStarkon {

	void ingresarSucursal(String ciudad);
	
	void ingresarCliente(String rut, String nombre, String apellido, int saldo);
	
	void ingresarDocumento(String codigo, int peso, int grosor);
	
	void ingresarEncomienda(String codigo, int peso, int largo, int ancho,
			int profundidad);
	
	void ingresarValija(String codigo, int peso, String material);
	
	void asociarReciboCliente(String codigo, String rut);
	
	void asociarEnvioCliente(String codigo, String rut);
	
	boolean verificarCliente(String rut);
	
	void recargarSaldo(String rut, int monto);
	
	String obtenerEntregas();
	
	String obtenerDatosOficinas();
	
	String obtenerEntregasCliente();
	
	String obtenerBalances();
	
	String obtenerTxtClientes();
	
	String obtenerTxtEntregas();
}
