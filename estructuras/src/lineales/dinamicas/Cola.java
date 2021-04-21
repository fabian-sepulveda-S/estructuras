package lineales.dinamicas;

public class Cola {

	private Nodo frente;
	private Nodo fin;
	
	public Cola() {
		frente = null;
		fin = null;
	}
	
	public boolean poner(Object elem) {
		Nodo nuevo = new Nodo(elem, null);
		// caso cola vacía
		if(frente == null) {
			frente = nuevo;
			fin = nuevo;
		}
		// caso cola con elementos
		else {
			fin.setEnlace(nuevo);
			fin = nuevo;
		}
		return true;
	}
	
	public boolean sacar() {
		boolean sacado;
		// caso cola vacía
		if(frente == null) {
			sacado = false;
		}
		else {
			frente = frente.getEnlace();
			// caso sacar el ultimo elemento
			if(frente == null) {
				fin = null;
			}
			sacado = true;
		}
		return sacado;
	}
	
	public Object obtenerFrente() {
		Object elFrente = null;
		if(frente != null) {
			elFrente = frente.getElem();
		}
		return elFrente;
	}
	
	public boolean esVacia() {
		return frente == null;
	}
	
	public void vaciar() {
		frente = null;
		fin = null;
	}
	
	public Cola clone() {
		Cola nueva = new Cola();
		// caso cola no vacía
		if(this.frente != null) {
			
			// copiar el primer elemento
			Nodo auxOriginal = this.frente;
			Nodo auxCopia = new Nodo(auxOriginal.getElem(), null);
			nueva.frente = auxCopia;
			auxOriginal = auxOriginal.getEnlace();
			
			// copiar el resto
			while(auxOriginal != null) {
				
				// copiar el siguiente nodo
				Nodo siguiente = new Nodo(auxOriginal.getElem(), null);
				auxCopia.setEnlace(siguiente);
				
				// avanzar
				auxOriginal = auxOriginal.getEnlace();
				auxCopia = auxCopia.getEnlace();
			}
			// enganchar a fin el último nodo copiado
			nueva.fin = auxCopia;
		}
		return nueva;
	}
	
	public String toString() {
		String rep = "[";
		Nodo aux = frente;
		while (aux != null) {
			rep += aux.getElem().toString();
			aux = aux.getEnlace();
			rep += (aux != null ?  ", ": "");
		}
		rep += "]";
		return rep;
	}
	
	public String toStringTransparente() {
		// muestra la estructura interna de la cola
		// en cola dinámica es indistinguible de toString()
		return this.toString();
	}
	
}
