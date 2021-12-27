package sistemastarkon.logica;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import sistemastarkon.dominio.*;

/**
 * 
 * @author Pablo Cortes
 * @author Maria Luisa Rivera
 *
 */
public class SistemaStarkonImpl implements SistemaStarkon {
	private List<Cliente> clientes;
	private List<Oficina> oficinas;
	private ListaCircularDobleEnlace<Entrega> entregas;
	
	public SistemaStarkonImpl() {
		clientes = new LinkedList<Cliente>();
		oficinas = new ArrayList<Oficina>();
		entregas = new ListaCircularDobleEnlace<Entrega>();
	}
	
	@Override
	public void ingresarOficina(String ciudad) {
		Oficina ofic = new Oficina(ciudad);
		oficinas.add(ofic);
	}

	@Override
	public void ingresarCliente(String rut, String nombre, String apellido,
			int saldo, String ciudad) {
		Cliente cl = new Cliente(rut, nombre, apellido, saldo, ciudad);
		clientes.add(cl);
	}
	
	/**
	 * Searchs for the client in the list of clients.
	 * @param rut the RUR of the client.
	 * @return the instance of {@code Cliente}.
	 */
	private Cliente buscarCliente(String rut) {
		for (Cliente c : clientes) {
			if (c.getRut().equalsIgnoreCase(rut)) {
				return c;
			}
		}
		return null;
	}
	
	/**
	 * Searchs for the office in the list of offices.
	 * @param ciudad the city where the office is located.
	 * @return the instance of {@code Oficina}.
	 */
	private Oficina buscarOficina(String ciudad) {
		for (Oficina o : oficinas) {
			if (o.getCiudad().equalsIgnoreCase(ciudad)) {
				return o;
			}
		}
		return null;
	}
	
	/**
	 * Searchs for the delivery in the list of deliveries.
	 * @param codigo the code of the delivery.
	 * @return the instance of {@code Entrega}.
	 */
	private Entrega buscarEntrega(String codigo) {
		for (int i = 0; i < entregas.size(); i++) {
			Entrega e = entregas.get(i);
			if (e.getCodigo().equals(codigo)) {
				return e;
			}
		}
		return null;
	}
	
	@Override
	public void asociarClienteOficina(String ciudad, String rut) {
		Cliente cliente = buscarCliente(rut);
		Oficina oficina = buscarOficina(ciudad);
		if (oficina != null) {
			cliente.setOficina(oficina);
		}
	}

	@Override
	public void ingresarDocumento(String codigo, int peso, int grosor) {
		Entrega documento = new Documento(codigo, peso, grosor);
		entregas.add(documento);
	}

	@Override
	public void ingresarEncomienda(String codigo, int peso, int largo,
			int ancho, int profundidad) {
		Entrega encomienda = new Encomienda(codigo, peso, largo, ancho, profundidad);
		entregas.add(encomienda);
	}

	@Override
	public void ingresarValija(String codigo, int peso, String material) {
		Entrega valija = new Valija(codigo, peso, material);
		entregas.add(valija);
	}

	@Override
	public void asociarEntregas(String codigo, String rutRemitente,
			String rutDestinatario) {
		Entrega entrega = buscarEntrega(codigo);
		Cliente remitente = buscarCliente(rutRemitente);
		Cliente destinatario = buscarCliente(rutDestinatario);
		if (remitente == null) {
			throw new NullPointerException("El remitente no existe");
		}
		else if (destinatario == null) {
			throw new NullPointerException("El destinatario no existe");
		}
		else {
			remitente.getEnvios().add(entrega);
			remitente.getOficina().getEnvios().add(entrega);
			destinatario.getRecibos().add(entrega);
			destinatario.getOficina().getRecibos().add(entrega);
			entrega.setRemitente(remitente);
			entrega.setDestinatario(destinatario);
		}
	}

	@Override
	public boolean verificarCliente(String rut) {
		Cliente c = buscarCliente(rut);
		if (c != null) return true;
		else return false;
	}
	
	@Override
	public boolean comprobarOficinaCliente(String rut) {
		Cliente c = buscarCliente(rut);
		if (c.getOficina() != null) {
			return true;
		}
		else return false;
	}
	
	@Override
	public int calcularValorEntrega(String codigo) {
		Entrega e = buscarEntrega(codigo);
		return e.calcularValor();
	}
	
	@Override
	public int getSaldoCliente(String rut) {
		Cliente c = buscarCliente(rut);
		return c.getSaldo();
	}
	
	/**
	 * Generates a random six-digit number.
	 * @return a random six-digit number.
	 */
	private String generateCode() {
		String code = "";
		for (int i = 0; i < 6; i++) {
			code += String.valueOf(ThreadLocalRandom.current().nextInt(0, 10));
		}
		return code;
	}
	
	@Override
	public String getCodigoUnico() {
		String code;
		do {
			code = generateCode();
		} while (buscarEntrega(code) != null);
		return code;
	}
	
	@Override
	public boolean comprobarLimitesEntrega(String codigo) {
		Entrega e = buscarEntrega(codigo);
		return e.verificarLimites();
	}
	
	@Override
	public boolean eliminarEntrega(String codigo) {
		Entrega e = buscarEntrega(codigo);
		return entregas.remove(e);
	}

	@Override
	public void recargarSaldo(String rut, int monto) {
		Cliente c = buscarCliente(rut);
		c.setSaldo(c.getSaldo() + monto);
	}

	@Override
	public String obtenerEntregasTipo() {
		String text = "[Documentos]\n";
		for (int i = 0; i < entregas.size(); i++) {
			Entrega e = entregas.get(i);
			if (e instanceof Documento) {
				text += "- " + e.getCodigo() + ": $ " + e.getValor() + "\n";
			}
		}
		text += "\n[Encomiendas]\n";
		for (int i = 0; i < entregas.size(); i++) {
			Entrega e = entregas.get(i);
			if (e instanceof Encomienda) {
				text += "- " + e.getCodigo() + ": $ " + e.getValor() + "\n";
			}
		}
		text += "\n[Valijas]\n";
		for (int i = 0; i < entregas.size(); i++) {
			Entrega e = entregas.get(i);
			if (e instanceof Valija) {
				text += "- " + e.getCodigo() + ": $ " + e.getValor() + "\n";
			}
		}
		return text.trim();
	}

	@Override
	public String obtenerDatosOficinas() {
		String text = "";
		for (Oficina o : oficinas) {
			int cantEnvios = o.getEnvios().size();
			int cantRecibos = o.getRecibos().size();
			text += "- " + o.getCiudad() + ": " + cantEnvios + " envios"
					+ " - " + cantRecibos + " recibos\n";
		}
		return text.trim();
	}

	@Override
	public String obtenerEntregasCliente(String rut) {
		Cliente c = buscarCliente(rut);
		ListaCircularDobleEnlace<Entrega> envios = c.getEnvios();
		ListaCircularDobleEnlace<Entrega> recibos = c.getRecibos();
		String text = "[Envios]\n";
		for (int i = 0; i < envios.size(); i++) {
			Entrega e = envios.get(i);
			text += "- " + e.toString() + "\n";
		}
		text += "\n[Recibos]\n";
		for (int i = 0; i < recibos.size(); i++) {
			Entrega e = recibos.get(i);
			text += "- " + e.toString() + "\n";
		}
		return text.trim();
	}
	
	@Override
	public String obtenerEntregasClientes() {
		String text = "";
		for (Cliente c : clientes) {
			text += "\n* " + c.getRut() + ":\n";
			ListaCircularDobleEnlace<Entrega> envios = c.getEnvios();
			ListaCircularDobleEnlace<Entrega> recibos = c.getRecibos();
			text += "\n[Envios]\n";
			for (int i = 0; i < envios.size(); i++) {
				Entrega e = envios.get(i);
				text += "- " + e.toString() + "\n";
			}
			text += "[Recibos]\n";
			for (int i = 0; i < recibos.size(); i++) {
				Entrega e = recibos.get(i);
				text += "- " + e.toString() + "\n";
			}
		}
		return text.trim();
	}

	@Override
	public String obtenerBalances() {
		String text = "";
		int total = 0;
		for (Oficina s : oficinas) {
			int suma = 0;
			ListaCircularDobleEnlace<Entrega> envios = s.getEnvios();
			ListaCircularDobleEnlace<Entrega> recibos = s.getRecibos();
			for (int i = 0; i < envios.size(); i++) {
				Entrega e = envios.get(i);
				suma += e.getValor();
			}
			for (int i = 0; i < recibos.size(); i++) {
				Entrega e = recibos.get(i);
				suma += e.getValor();
			}
			total += suma;
			text += "- " + s.getCiudad() + ": $ " + suma + "\n";
		}
		text += "- Ganancias totales: $ " + total;
		return text;
	}

	@Override
	public String obtenerInfoClientes() {
		String text = "";
		for (Cliente c : clientes) {
			String rut = c.getRut();
			String nombre = c.getNombre();
			String apellido = c.getApellido();
			int saldo = c.getSaldo();
			String ciudad = c.getCiudad();
			text += rut + "," + nombre + "," + apellido + "," + saldo
					+ "," + ciudad + "\n";
		}
		return text.trim();
	}

	@Override
	public String obtenerInfoEntregas() {
		String text = "";
		for (int i = 0; i < entregas.size(); i++) {
			Entrega e = entregas.get(i);
			String codigo = e.getCodigo();
			String rutRem = e.getRemitente().getRut();
			String rutDest = e.getDestinatario().getRut();
			int peso = e.getPeso();
			String tipo;
			if (e instanceof Documento) {
				tipo = "D";
				int grosor = ((Documento)e).getGrosor();
				text += codigo + "," + tipo + "," + rutRem + "," + rutDest
						+ "," + peso + "," + grosor + "\n";
			}
			else if (e instanceof Encomienda) {
				tipo = "E";
				int largo = ((Encomienda)e).getLargo();
				int ancho = ((Encomienda)e).getAncho();
				int prof = ((Encomienda)e).getProfundidad();
				text += codigo + "," + tipo + "," + rutRem + "," + rutDest
						+ "," + peso + "," + largo + "," + ancho + "," + prof + "\n";
			}
			else {
				tipo = "V";
				String material = ((Valija)e).getMaterial();
				text += codigo + "," + tipo + "," + rutRem + "," + rutDest
						+ "," + material + "," + peso + "\n"; 
			}
		}
		return text.trim();
	}

}
