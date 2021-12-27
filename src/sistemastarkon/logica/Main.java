package sistemastarkon.logica;

import java.util.*;

import sistemastarkon.dominio.*;

import java.io.*;

public class Main {

	public static void main(String[] args) {
		SistemaStarkon sistema = new SistemaStarkonImpl();
		Scanner scan = new Scanner(System.in);
		
		leerArchivoSucursales(sistema);
		leerArchivoClientes(sistema);
		leerArchivoEntregas(sistema);
		abrirMenuPrincipal(sistema, scan);
	}

	private static void abrirMenuPrincipal(SistemaStarkon sistema,
			Scanner scan) {
		System.out.println("\n>--- MENU PRINCIPAL ---<\n");
		System.out.print("RUT: ");
		String rut = scan.nextLine().trim();
		if (rut.equals("Admin")) {
			System.out.print("Contrasena: ");
			String contra = scan.nextLine().trim();
			if (contra.equals("choripan123")) {
				abrirMenuAdmin(sistema, scan);
			}
			else {
				System.out.println("\n* Contrasena incorrecta *");
			}
		}
		else {
			if (sistema.verificarCliente(rut)) {
				abrirMenuCliente(sistema, scan, rut);
			}
			else {
				while (true) {
					System.out.print("\n* Cliente no registrado. "
							+ "Desea registrarse: (y/n) ");
					String resp = scan.nextLine().trim();
					if (resp.equalsIgnoreCase("y")) {
						registrarUsuario(sistema, scan, rut);
						break;
					}
					else if (resp.equalsIgnoreCase("n")) {
						break;
					}
					else {
						continue;
					}
				}
			}
		}
		abrirMenuPrincipal(sistema, scan);
	}

	private static void registrarUsuario(SistemaStarkon sistema, Scanner scan,
			String rut) {
		System.out.println("\n>--- REGISTRAR USUARIO ---<\n");
		System.out.print("Nombre: ");
		String nombre = scan.nextLine();
		System.out.print("Apellido: ");
		String apellido = scan.nextLine();
		System.out.print("Saldo inicial: $ ");
		int saldo = Integer.parseInt(scan.nextLine());
		System.out.print("Ciudad: ");
		String ciudad = scan.nextLine();
		sistema.ingresarCliente(rut, nombre, apellido, saldo);
		sistema.asociarClienteSucursal(ciudad, rut);
		System.out.println("\n* Registro exitoso! *");
	}

	private static void abrirMenuCliente(SistemaStarkon sistema, Scanner scan,
			String rut) {
		System.out.println("\n>--- MENU CLIENTE ---<\n");
		System.out.println("[1] Realizar una entrega");
		System.out.println("[2] Recargar saldo");
		System.out.println("[3] Ver mis entregas");
		System.out.println("[4] Menu principal");
		System.out.print("\n>>> ");
		String opcion = scan.nextLine();
		switch (opcion) {
		case "1":
			if (sistema.comprobarSucursalCliente(rut)) {
				realizarEntrega(sistema, scan, rut);
			}
			else {
				System.out.println("\n* No existe una oficina Starkon en su ciudad *");
			}
			break;
		case "2":
			recargarSaldo(sistema, scan, rut);
			break;
		case "3":
			verEntregas(sistema, scan, rut);
			break;
		case "4":
			abrirMenuPrincipal(sistema, scan);
			break;
		}
		abrirMenuCliente(sistema, scan, rut);
	}

	private static void verEntregas(SistemaStarkon sistema, Scanner scan,
			String rut) {
		System.out.println("\n>--- MIS ENTREGAS ---<\n");
		System.out.println(sistema.obtenerEntregasCliente(rut));
	}

	private static void realizarEntrega(SistemaStarkon sistema, Scanner scan,
			String rut) {
		System.out.println("\n>--- REALIZAR ENTREGA ---<\n");
		System.out.println("[1] Documento");
		System.out.println("[2] Encomienda");
		System.out.println("[3] Valija");
		System.out.print("\n>>> ");
		String tipo = scan.nextLine();
		String codigo = "";
		int peso = 0;
		switch (tipo) {
		case "1":
			System.out.print("\nPeso en gramos: ");
			peso = Integer.parseInt(scan.nextLine());
			System.out.print("Grosor en mm: ");
			int grosor = Integer.parseInt(scan.nextLine());
			codigo = sistema.getCodigoUnico();
			sistema.ingresarDocumento(codigo, peso, grosor);
			break;
		case "2":
			System.out.print("\nPeso en gramos: ");
			peso = Integer.parseInt(scan.nextLine());
			System.out.print("Largo en cm: ");
			int largo = Integer.parseInt(scan.nextLine());
			System.out.print("Ancho en cm: ");
			int ancho = Integer.parseInt(scan.nextLine());
			System.out.print("Profundidad en cm: ");
			int profundidad = Integer.parseInt(scan.nextLine());
			codigo = sistema.getCodigoUnico();
			sistema.ingresarEncomienda(codigo, peso, largo, 
					ancho, profundidad);
			break;
		case "3":
			System.out.print("\nPeso en gramos: ");
			peso = Integer.parseInt(scan.nextLine());
			System.out.print("Material: (Cuero/Plastico/Tela) ");
			String material = scan.nextLine();
			codigo = sistema.getCodigoUnico();
			sistema.ingresarValija(codigo, peso, material);
			break;
		default:
			System.out.println("\n* Opcion invalida *");
			Main.realizarEntrega(sistema, scan, rut);
		}
		if (sistema.comprobarLimitesEntrega(codigo)) {
			int valor = sistema.calcularValorEntrega(codigo);
			if (sistema.getSaldoCliente(rut) >= valor) {
				System.out.print("\nRUT del destinatario: ");
				String rutDest = scan.nextLine();
				if (sistema.verificarCliente(rutDest)) {
					if (sistema.comprobarSucursalCliente(rutDest)) {
						System.out.println("\n* Entrega realizada! *");
						sistema.recargarSaldo(rut, -valor);
						sistema.asociarEntregas(codigo, rut, rutDest);
					}
					else {
						System.out.println("\n* El destinatario no puede recibir"
								+ " entregas *");
						sistema.eliminarEntrega(codigo);
					}
				}
				else {
					System.out.println("\n* El destinatario no esta registrado *");
					sistema.eliminarEntrega(codigo);
				}
			}
			else {
				System.out.println("\n* Saldo insuficiente *");
				sistema.eliminarEntrega(codigo);
			}
		}
		else {
			System.out.println("\n* Especificaciones de la entrega fuera de rango *");
			sistema.eliminarEntrega(codigo);
		}
	}
	
	private static void recargarSaldo(SistemaStarkon sistema, Scanner scan,
			String rut) {
		System.out.println("\n>--- RECARGAR SALDO <---\n");
		System.out.println("Saldo actual: $ " + sistema.getSaldoCliente(rut) + "\n");
		int monto;
		do {
			System.out.print("Monto: $ ");
			monto = Integer.parseInt(scan.nextLine());
		} while (monto <= 0);
		sistema.recargarSaldo(rut, monto);
		System.out.println("\n* Recarga exitosa! Nuevo saldo: $ "
				+ sistema.getSaldoCliente(rut) + " *");
	}

	private static void abrirMenuAdmin(SistemaStarkon sistema, Scanner scan) {
		// TODO Auto-generated method stub
		
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
				sistema.asociarClienteSucursal(ciudad, rut);
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
