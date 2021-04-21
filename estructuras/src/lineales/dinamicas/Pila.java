package lineales.dinamicas;

public class Pila {
	
	private Nodo tope;
	
	public Pila() {
		this.tope = null;
	}
	
	public boolean apilar(Object nuevo) {
		// agrega el elemento nuevo a la pila y retorna true
		this.tope = new Nodo(nuevo, this.tope);
		return true;
	}
	
	public boolean desapilar() {
		// si quedan elememntos desapila el tope y retorna true,
		// si no retorna false
		boolean desapilado;
		if (this.tope == null) {
			desapilado = false;
		}
		else {
			// el garbage collector se lleva el elemento antes
			// referenciado por tope
			this.tope = this.tope.getEnlace();
			desapilado = true;
		}
		return desapilado;
	}
	
	public Object obtenerTope() {
		// retorna el objeto en el tope si lo hay y null en caso contrario
		Object top = null;
		if (this.tope != null) {
			top = this.tope.getElem();
		}
		return top;
	}
	
	public boolean esVacia() {
		return this.tope == null;
	}
	
	public void vaciar() {
		// vacía la pila
		// el garbage collector se encarga de eliminar
		// cada nodo recursivamente
		this.tope = null;
	}
	
	public Pila clone() {
		// devuelve una copia superficial de la pila
		
		Pila copia = new Pila();
		Nodo auxOriginal = this.tope;
		
		// agregar un nodo extra para no tener que preguntar si la pila está vacía
		Nodo auxCopia = new Nodo(null, null);
		copia.tope = auxCopia;
		
		while(auxOriginal != null) {
			Object elem = auxOriginal.getElem();
			
			// agregar el siguiente elemento a copia
			auxCopia.setEnlace(new Nodo(elem, null));
			
			// avanzar copia y original
			auxCopia = auxCopia.getEnlace();
			auxOriginal = auxOriginal.getEnlace();
		}
		copia.desapilar(); // quitar el nodo extra
		return copia;
	}
	
	public Pila cloneRecursivo() {
		// devuelve una copia superficial de la pila
		Pila nueva = new Pila();
		cloneAux(nueva, this.tope);
		return nueva;
	}
	
	private void cloneAux(Pila clon, Nodo actual) {
		// recorre recursivamente todos los nodos de la pila original,
		// y a la vuelta apila los elementos en la pila clon	

		// caso recursivo
		if (actual != null) {
			// antes de apilar este nodo apilar todos los siguientes
			cloneAux(clon, actual.getEnlace());
			
			// apilar el nodo actual
			clon.tope = new Nodo(actual.getElem(), clon.tope);
		}
		// else caso base, no quedan nodos para apilar
	}
	
	public String toString() {
		// devuelve una representación String de la pila
		String rep = "]";
		Nodo aux = this.tope;
		while (aux != null) {
			rep = aux.getElem().toString() + rep;
			aux = aux.getEnlace();
			rep = (aux != null ?  ",": "") + rep;
		}
		rep = "[" + rep;
		return rep;
	}
}
