package lineales.estaticas;

public class Cola {
	
	private Object[] arreglo;
	private int frente;
	private int fin;
	private static final int TAMANIO = 10;
	
	public Cola() {
		this.arreglo = new Object[Cola.TAMANIO];
		this.frente = 0;
		this.fin = 0;
	}
	
	public boolean poner(Object elem) {
		boolean exito;
		// verificar cola llena
		if((fin + 1) % TAMANIO == frente) {
			exito = false;
		}
		else {
			this.arreglo[fin] = elem;
			this.fin = (this.fin + 1) % TAMANIO;
			exito = true;
		}
		return exito;
	}
	
	public boolean sacar() {
		boolean exito;
		
		if(this.esVacia()) {
			exito = false;
		}
		else {
			this.arreglo[this.frente] = null;
			this.frente = (this.frente + 1) % Cola.TAMANIO;
			exito = true;
		}
		return exito;
	}
	
	public Object obtenerFrente() {
		Object elFrente = null;
		if(!this.esVacia()) {
			elFrente = this.arreglo[frente];
		}
		return elFrente;
	}
	
	public boolean esVacia() {
		return this.frente == this.fin;
	}
	
	public void vaciar() {
		int limit = fin;
		if(fin < frente) limit += TAMANIO;
		for(int i = frente; i < limit; i++) {
			arreglo[i % TAMANIO] = null;
		}
		frente = 0;
		fin = 0;
	}
	
	public Cola clone() {
		Cola clon = new Cola();
		clon.frente = this.frente;
		clon.fin = this.fin;
		for(int i = 0; i < TAMANIO; i++) {
			clon.arreglo[i] = this.arreglo[i];
		}
		return clon;
	}
	
	public String toString() {
		String rep = "[ ";
		int limit = fin;
		if (limit < frente) limit += TAMANIO;
		for(int i = frente; i < limit; i++) {
			rep += arreglo[i % TAMANIO].toString() + (i < limit - 1 ? ", ": "");
		}
		rep += "]";
		return rep;
	}

	public String toStringTransparente() {
		// muestra la estructura interna de la cola
		// en cola estática, muestra el arreglo
		String rep = "[ ";
		for(int i = 0; i < arreglo.length; i++) {
			if(arreglo[i] != null) {
				rep += arreglo[i].toString() + "  ";
			}
			else {
				rep += "_  ";
			}
		}
		rep += "] frente: " + frente + " fin: " + fin;
		return rep;
	}
}
