package jerarquicas.dinamicas;

public class NodoArbolBin {

	private Object elem;
	private NodoArbolBin izquierdo;
	private NodoArbolBin derecho;
	
	public NodoArbolBin(Object elem) {
		this.elem = elem;
		izquierdo = null;
		derecho = null;
	}
	
	public Object getElem() {
		return elem;
	}
	
	public NodoArbolBin getIzquierdo() {
		return izquierdo;
	}
	
	public NodoArbolBin getDerecho() {
		return derecho;
	}
	
	public void setElem(Object elem) {
		this.elem = elem;
	}
	
	public void setIzquierdo(NodoArbolBin izquierdo) {
		this.izquierdo = izquierdo;
	}
	
	public void setDerecho(NodoArbolBin derecho) {
		this.derecho = derecho;
	}
}
