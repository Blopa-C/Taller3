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
		// TODO Auto-generated method stub

	}

	@Override
	public void ingresarCliente(String rut, String nombre, String apellido,
			int saldo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void ingresarDocumento(String codigo, int peso, int grosor) {
		// TODO Auto-generated method stub

	}

	@Override
	public void ingresarEncomienda(String codigo, int peso, int largo,
			int ancho, int profundidad) {
		// TODO Auto-generated method stub

	}

	@Override
	public void ingresarValija(String codigo, int peso, String material) {
		// TODO Auto-generated method stub

	}

	@Override
	public void asociarReciboCliente(String codigo, String rut) {
		// TODO Auto-generated method stub

	}

	@Override
	public void asociarEnvioCliente(String codigo, String rut) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean verificarCliente(String rut) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void recargarSaldo(String rut, int monto) {
		// TODO Auto-generated method stub

	}

	@Override
	public String obtenerEntregas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String obtenerDatosOficinas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String obtenerEntregasCliente() {
		// TODO Auto-generated method stub
		return null;
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
