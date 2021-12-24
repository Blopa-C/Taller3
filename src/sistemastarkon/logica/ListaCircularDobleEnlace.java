package sistemastarkon.logica;

public class ListaCircularDobleEnlace<T> {
	private NodoDoble<T> header;
	private int size;
	
	public ListaCircularDobleEnlace() {
		header = new NodoDoble<T>(null);
		header.setPrevious(header);
		header.setNext(header);
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void ingresarLast(T info) {
		NodoDoble<T> nuevo = new NodoDoble<T>(info);
		NodoDoble<T> last = header.getPrevious();
		last.setNext(nuevo);
		nuevo.setPrevious(last);
		nuevo.setNext(header);
		header.setPrevious(nuevo);
		size++;
	}
}
