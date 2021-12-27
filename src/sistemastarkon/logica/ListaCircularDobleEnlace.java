package sistemastarkon.logica;

public class ListaCircularDobleEnlace<T> {
	private NodoDoble<T> header;
	private int size;
	
	/**
	 * Constructs an empty list.
	 */
	public ListaCircularDobleEnlace() {
		header = new NodoDoble<T>(null);
		header.setPrevious(header);
		header.setNext(header);
	}
	
	/**
	 * Returns the size of the list.
	 * @return the size of the list.
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Return true if the list has no elements.
	 * @return {@code true} if the list has no elements.
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Adds the specified element to the end of the list.
	 * @param info the element that will be added to the list.
	 */
	public void add(T info) {
		NodoDoble<T> nuevo = new NodoDoble<T>(info);
		NodoDoble<T> last = header.getPrevious();
		last.setNext(nuevo);
		nuevo.setPrevious(last);
		nuevo.setNext(header);
		header.setPrevious(nuevo);
		size++;
	}
	
	/**
	 * Removes from the list the first occurrence of the specified element.
	 * @param info the element that will be removed from the list.
	 * @return {@code true} if the remove was done.
	 */
	public boolean remove(T info) {
		NodoDoble<T> current = header.getNext();
		for (int i = 0; i < size; i++) {
			if (current.getInfo() == info) {
				current.getPrevious().setNext(current.getNext());
				current.getNext().setPrevious(current.getPrevious());
				size--;
				return true;
			}
			else {
				current = current.getNext();
			}
		}
		return false;
	}
	
	/**
	 * Returns the element at the specified index. The n-nth element in the list
	 * has a index of n-1. For example, the first element has an index of 0.
	 * @param i
	 * @throws IndexOutOfBoundsException if the index is lesser than 0 and greater
	 * than the size of the list.
	 * @return the element at the specified index.
	 */
	public T get(int i) {
		if (i >= 0 && i < size) {
			NodoDoble<T> current = header.getNext();
			for (int j = 0; j < i; j++) {
				current = current.getNext();
			}
			return current.getInfo();
		}
		else {
			throw new IndexOutOfBoundsException();
		}
	}
}
