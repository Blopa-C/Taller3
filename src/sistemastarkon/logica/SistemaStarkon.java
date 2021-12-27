package sistemastarkon.logica;

/**
 * 
 * @author Pablo Cortes
 * @author Maria Luisa Rivera
 *
 */
public interface SistemaStarkon {
	
	/**
	 * Adds an office to the system.
	 * @param ciudad the city of the office.
	 */
	void ingresarOficina(String ciudad);
	
	/**
	 * Adds a client to the system.
	 * @param rut the RUT of the client.
	 * @param nombre the name of the client.
	 * @param apellido the last name of the client.
	 * @param saldo the balance of the client.
	 * @param ciudad the city of the client.
	 */
	void ingresarCliente(String rut, String nombre, String apellido, int saldo,
			String ciudad);
	
	/**
	 * Associates an office to a client, only if theirs cities are the same.
	 * @param ciudad the city of the client.
	 * @param rut the RUT of the client.
	 */
	void asociarClienteOficina(String ciudad, String rut);
	
	/**
	 * Adds a document to the system.
	 * @param codigo the code of the document.
	 * @param peso the weight of the document.
	 * @param grosor the thickness of the document.
	 */
	void ingresarDocumento(String codigo, int peso, int grosor);
	
	/**
	 * Adds an assignment to the system.
	 * @param codigo the code of the assignment.
	 * @param peso the weight of the assignment.
	 * @param largo the length of the assignment.
	 * @param ancho the width of the assignment.
	 * @param profundidad the depth of the assignment.
	 */
	void ingresarEncomienda(String codigo, int peso, int largo, int ancho,
			int profundidad);
	
	/**
	 * Adds a suitcase to the system.
	 * @param codigo the code of the suitcase.
	 * @param peso the weight of the suitcase.
	 * @param material the material of the suitcase.
	 */
	void ingresarValija(String codigo, int peso, String material);
	
	/**
	 * Associates a delivery with two clients, this is, adds the delivery to
	 * the list of shippings of the person who sends it and the list of shippings
	 * of the office of the city where he lives in, adds the delivery to the list 
	 * of receipts of the person who receives it and the list of receipts of the 
	 * office of the city where he lives in, and sets the two clients as the 
	 * shipper and receiver of the delivery.
	 * @param codigo the code of the delivery.
	 * @param rutRemitente the RUT of the shipper.
	 * @param rutDestinatario the RUT of the receiver.
	 */
	void asociarEntregas(String codigo, String rutRemitente, String rutDestinatario);
	
	/**
	 * Verifies if the client is registered in the system.
	 * @param rut the RUT of the client.
	 * @return {@code true} if the client is registered.
	 */
	boolean verificarCliente(String rut);
	
	/**
	 * Verifies if the city where the client lives has an office.
	 * @param rut the RUT of the client.
	 * @return {@code true} if the city where the client lives has an office.
	 */
	boolean comprobarOficinaCliente(String rut);
	
	/**
	 * Calculates the price of the delivery depending of its specifications.
	 * @param codigo the code of the delivery.
	 * @return the price of the delivery.
	 */
	int calcularValorEntrega(String codigo);
	
	/**
	 * Returns the balance of the client.
	 * @param rut the RUT of the client.
	 * @return the balance of the client.
	 */
	int getSaldoCliente(String rut);
	
	/**
	 * Return a random six-digit number.
	 * @return a random six-digit number.
	 */
	String getCodigoUnico();
	
	/**
	 * Verifies that the delivery specifications are within the limits.
	 * @param codigo the code of the delivery.
	 * @return {@code true} if the delivery specifications are whitin the limits.
	 */
	boolean comprobarLimitesEntrega(String codigo);
	
	/**
	 * Removes a delivery from the system.
	 * @param codigo the code of the delivery.
	 * @return {@code true} if the remove is succesuful.
	 */
	boolean eliminarEntrega(String codigo);
	
	/**
	 * Adds the given amount to the client balance. Note that a negative amount
	 * means that that amount will be sustracted from the client balance.
	 * @param rut the RUT of the client.
	 * @param monto the amount that will be added or sustracted.
	 */
	void recargarSaldo(String rut, int monto);
	
	/**
	 * Returns a string with all the deliverys sorted by type.
	 * @return a string with all the deliverys sorted by type.
	 */
	String obtenerEntregasTipo();
	
	/**
	 * Returns a string with all the offices and how many shippings and receipts
	 * they did.
	 * @return a string with all the offices and how many shippings and receipts
	 * they did.
	 */
	String obtenerDatosOficinas();
	
	/**
	 * Returns a string with all the deliverys shipped and received by a client.
	 * @param rut the rut of the client.
	 * @return a string with all the deliverys shipped and received by a client.
	 */
	String obtenerEntregasCliente(String rut);
	
	/**
	 * Returns a string with all the deliverys of all clients in the system.
	 * @return a string with all the deliverys of all clients in the system.
	 */
	String obtenerEntregasClientes();
	
	/**
	 * Returns a string with info on the money each office made.
	 * @return a string with info on the money each office made.
	 */
	String obtenerBalances();
	
	/**
	 * Returns a string with all the info of all clients in the system.
	 * @return a string with all the info of all clients in the system.
	 */
	String obtenerInfoClientes();
	
	/**
	 * Returns a string with all the info of all deliverys in the system.
	 * @return a string with all the info of all deliverys in the system.
	 */
	String obtenerInfoEntregas();
}
