package sistemastarkon.logica;

public class NodoDoble<T> {
	private T info;
	private NodoDoble<T> previous;
	private NodoDoble<T> next;
	
	public NodoDoble(T info) {
		this.info = info;
	}

	public NodoDoble<T> getPrevious() {
		return previous;
	}

	public void setPrevious(NodoDoble<T> previous) {
		this.previous = previous;
	}

	public NodoDoble<T> getNext() {
		return next;
	}

	public void setNext(NodoDoble<T> next) {
		this.next = next;
	}

	public T getInfo() {
		return info;
	}

}
