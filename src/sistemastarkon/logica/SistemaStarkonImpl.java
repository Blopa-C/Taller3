package sistemastarkon.logica;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import sistemastarkon.dominio.*;

public class SistemaStarkonImpl implements SistemaStarkon {
	private List<Cliente> clientes;
	private List<Sucursal> sucursales;
	private ListaCircularDobleEnlace<Entrega> entregas;
	
	public SistemaStarkonImpl() {
		clientes = new LinkedList<Cliente>();
		sucursales = new ArrayList<Sucursal>();
		entregas = new ListaCircularDobleEnlace<Entrega>();
	}
	
	@Override
	public void ingresarSucursal(String ciudad) {
		Sucursal suc = new Sucursal(ciudad);
		sucursales.add(suc);
	}

	@Override
	public void ingresarCliente(String rut, String nombre, String apellido,
			int saldo) {
		Cliente cl = new Cliente(rut, nombre, apellido, saldo);
		clientes.add(cl);
	}
	
	private Cliente buscarCliente(String rut) {
		for (Cliente c : clientes) {
			if (c.getRut().equals(rut)) {
				return c;
			}
		}
		return null;
	}
	
	private Sucursal buscarSucursal(String ciudad) {
		for (Sucursal s : sucursales) {
			if (s.getCiudad().equals(ciudad)) {
				return s;
			}
		}
		return null;
	}
	
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
	public void asociarClienteSucursal(String ciudad, String rut) {
		Cliente cliente = buscarCliente(rut);
		Sucursal sucursal = buscarSucursal(ciudad);
		if (sucursal != null) {
			cliente.setSucursal(sucursal);
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
			remitente.getSucursal().getEnvios().add(entrega);
			destinatario.getRecibos().add(entrega);
			destinatario.getSucursal().getRecibos().add(entrega);
		}
	}

	@Override
	public boolean verificarCliente(String rut) {
		Cliente c = buscarCliente(rut);
		if (c != null) return true;
		else return false;
	}
	
	@Override
	public int getSaldoCliente(String rut) {
		Cliente c = buscarCliente(rut);
		return c.getSaldo();
	}

	@Override
	public void recargarSaldo(String rut, int monto) {
		Cliente c = buscarCliente(rut);
		c.setSaldo(c.getSaldo() + monto);
	}

	@Override
	public String obtenerEntregasTipo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String obtenerDatosOficinas() {
		// TODO Auto-generated method stub
		return null;
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
		text += "[Recibos]\n";
		for (int i = 0; i < recibos.size(); i++) {
			Entrega e = recibos.get(i);
			text += "- " + e.toString() + "\n";
		}
		return text.trim();
	}

	@Override
	public String obtenerBalances() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String obtenerTxtClientes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String obtenerTxtEntregas() {
		// TODO Auto-generated method stub
		return null;
	}

}
