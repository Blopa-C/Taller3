package sistemastarkon.logica;

import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) {
		SistemaStarkon sistema = new SistemaStarkonImpl();
		leerArchivoSucursales(sistema);
		leerArchivoClientes(sistema);
		leerArchivoEntregas(sistema);
	}

	private static void leerArchivoEntregas(SistemaStarkon sistema) {
		try {
			Scanner scan = new Scanner(new File("entregas.txt"));
			while (scan.hasNextLine()) {
				String[] partes = scan.nextLine().split(",");
				String codigo = partes[0];
				String tipo = partes[1];
				String remitente = partes[2];
				String destinatario = partes[3];
				int peso;
				switch (tipo) {
				case "D":
					peso = Integer.parseInt(partes[4]);
					int grosor = Integer.parseInt(partes[5]);
					sistema.ingresarDocumento(codigo, peso, grosor);
					break;
				case "E":
					peso = Integer.parseInt(partes[4]);
					int largo = Integer.parseInt(partes[5]);
					int ancho = Integer.parseInt(partes[6]);
					int profunidad = Integer.parseInt(partes[7]);
					sistema.ingresarEncomienda(codigo, peso, largo, ancho, profunidad);
					break;
				case "V":
					String material = partes[4];
					peso = Integer.parseInt(partes[5]);
					sistema.ingresarValija(codigo, peso, material);
					break;
				}
				try {
					sistema.asociarEntregas(codigo, remitente, destinatario);
				}
				catch (NullPointerException e) {
					System.out.println(e.getMessage());
				}
			}
			scan.close();
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void leerArchivoClientes(SistemaStarkon sistema) {
		try {
			Scanner scan = new Scanner(new File("clientes.txt"));
			while (scan.hasNextLine()) {
				String[] partes = scan.nextLine().split(",");
				String rut = partes[0];
				String nombre = partes[1];
				String apellido = partes[2];
				int saldo = Integer.parseInt(partes[3]);
				sistema.ingresarCliente(rut, nombre, apellido, saldo);
				String ciudad = partes[4];
				try {
					sistema.asociarClienteSucursal(ciudad, rut);
				}
				catch (NullPointerException e) {
					System.out.println(e.getMessage());
				}
			}
			scan.close();
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void leerArchivoSucursales(SistemaStarkon sistema) {
		try {
			Scanner scan = new Scanner(new File("localizaciones.txt"));
			while (scan.hasNextLine()) {
				String ciudad = scan.nextLine();
				sistema.ingresarSucursal(ciudad);
			}
			scan.close();
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
