package lineales.estaticas;

public class Pila {
	
	private static final int TAMANIO = 10;
	
	private Object[] arreglo;
	private int tope;
	
	public Pila() {
		this.arreglo = new Object[TAMANIO];
		this.tope = -1;
	}
	
	public boolean apilar(Object elemento) {
		// si queda espacio apila elemento y retorna true,
		// si no retorna false
		boolean apilado;
		if(tope + 1 >= TAMANIO) {
			apilado = false;
		}
		else {
			tope++;
			arreglo[tope] = elemento;
			apilado = true;
		}
		return apilado;
	}
	
	public boolean desapilar() {
		// si quedan elementos desapila y retorna true,
		// si no retorna false
		boolean desapilado;
		if(tope == -1) {
			desapilado = false;
		}
		else {
			arreglo[tope] = null;
			tope--;
			desapilado = true;
		}
		return desapilado;
	}
	
	public Object obtenerTope() {
		// retorna el tope si existe, si no null
		Object elTope = null;
		if(tope != -1) {
			elTope = arreglo[tope];
		}
		return elTope;
	}
	
	public boolean esVacia() {
		return tope == -1;
	}
	
	public void vaciar() {
		// vacía la pila eliminando todas las referencias
		// a los elementos apilados
		while(tope != -1) {
			arreglo[tope] = null;
			tope--;
		}
	}
	
	public Pila clone() {
		// retorna una copia superficial de la pila
		Pila pila = new Pila();
		pila.tope = this.tope;
		for(int i = 0; i <= tope; i++) {
			pila.arreglo[i] = this.arreglo[i];
		}
		return pila;
	}
	
	public String toString() {
		// retorna una representación String de la pila
		String rep = "[";
		for(int i = 0; i <= tope; i++) {	// puramente estetico
			rep += arreglo[i].toString() + (i < tope ? "," : "");
		}
		rep += "]";
		return rep;
	}
}
