package lineales.dinamicas;

public class Lista {

	private Nodo cabecera;
	private int tamanio;
	
	public Lista() {
		this.cabecera = null;
		this.tamanio = 0;
	}
	
	public int getTamanio() {
		return tamanio;
	}
	
	public boolean insertar(Object elem, int pos) {
		boolean exito;
		// caso insertar en cabecera
		if(pos == 1) {
			cabecera = new Nodo(elem, cabecera);
			tamanio++;
			exito = true;
		}
		// caso general
		else if(1 <= pos && pos <= tamanio + 1) {
			// encontrar el nodo anterior a donde hay que insertar
			Nodo aux = encontrarNodo(pos - 1);
			// enganchar el nodo nuevo a los nodos delante
			Nodo nuevo = new Nodo(elem, aux.getEnlace());
			// enganchar el nodo nuevo a los nodos detras
			aux.setEnlace(nuevo);
			tamanio++;
			exito = true;
		}
		else {
			exito = false;
		}
		return exito;
	}
	
	public boolean eliminar(int pos) {
		boolean exito;
		// verificar operación válida
		if(tamanio == 0 || !(1 <= pos && pos <= tamanio)) {
			exito = false;
		}
		else {
			// caso eliminar cabecera
			if(pos == 1) {
				cabecera = cabecera.getEnlace();
			}
			// caso eliminar general
			else {
				// encontrar el nodo anterior
				Nodo aux = encontrarNodo(pos - 1);
				// enganchar anterior con siguiente
				aux.setEnlace(aux.getEnlace().getEnlace());
				// aux.getEnlace() es el elemento a eliminar.
				// aux.getEnlace().getEnlace() es el siguiente, puede ser
				// otro nodo o null
				// de cualquier manera, el nodo aux.getEnlace() ya no es apuntado
				// por aux, y será recolectado por el garbage collector
			}
			tamanio--;
			exito = true;
		}
		return exito;
	}
	
	public int contar(Object elem) {
		// cuenta la cantidad de apariciones de elem en la lista
		Nodo aux = cabecera;
		int cuenta = 0;
		
		// recorrer toda la lista
		while(aux != null) {
			// aumentar cuenta si encontramos elem
			if(aux.getElem().equals(elem)) {
				cuenta++;
			}
			aux = aux.getEnlace();
		}
		
		return cuenta;
	}
	
	public Lista concatenar(Lista otra) {
		// retorna la concatenación de this con otra
		
		Lista concat = new Lista();
		
		// copiar this en concat y obtener el ultimo nodo copiado
		Nodo ultimo = this.clonarYDevolverUltimo(concat);
		
		// copiar la segunda lista
		Lista temp = otra.clone();
		
		concat.tamanio += temp.tamanio;
		
		// enganchar las dos listas
		if(ultimo != null) {
			ultimo.setEnlace(temp.cabecera);
		}
		else {
			// en este caso esta lista es vacía, luego 
			// la concatenación solo incluye a otra
			concat = temp;
		}
		return concat;
		
	}
	
	public Lista intercalar(Lista otra) {
		// retorna una lista con los elementos de esta lista
		// y de otra intercalados
		
		Lista intercalada = new Lista();
		intercalada.tamanio = this.tamanio + otra.tamanio;
				
		intercalada.cabecera = new Nodo(null, null);
		
		Nodo auxThis = this.cabecera;
		Nodo auxOtra = otra.cabecera;
		Nodo auxInter = intercalada.cabecera;
		
		// mientras ambas tengan elementos, intercalar
		while(auxThis != null && auxOtra != null) {
			
			// extraer elementos
			Nodo copiaThis = new Nodo(auxThis.getElem(), null);
			Nodo copiaOtra = new Nodo(auxOtra.getElem(), null);
			
			// copiar
			auxInter.setEnlace(copiaThis);
			auxInter = auxInter.getEnlace();
			auxInter.setEnlace(copiaOtra);
			
			// avanzar
			auxThis = auxThis.getEnlace();
			auxOtra = auxOtra.getEnlace();
			auxInter = auxInter.getEnlace();
		}
		
		// copiar el resto de los nodos
		copiar(auxThis, auxInter);
		copiar(auxOtra, auxInter);
		
		// quitar el nodo extra
		intercalada.cabecera = intercalada.cabecera.getEnlace();
		
		return intercalada;
	}
	
	private Nodo copiar(Nodo fuente, Nodo destino) {
		// copia la lista de nodos en fuente a la lista de nodos
		// en destino, y retorna el último nodo copiado
		while(fuente != null) {
			
			// copiar
			Nodo copia = new Nodo(fuente.getElem(), null);
			destino.setEnlace(copia);
			
			// avanzar
			fuente = fuente.getEnlace();
			destino = destino.getEnlace();
		}
		return destino;
	}
	
	private Nodo clonarYDevolverUltimo(Lista otra) {
		// una adaptación de clone que retorna el último nodo
		// copiado, o null si esta lista es vacía
		
		// la motivación para crear este módulo es facilitar la concatenación de 
		// listas. Para concatenar listas basta enganchar el último nodo de
		// la primera con el primero de la segunda. Pero la implementación
		// actual solo da acceso inmediato al primer nodo de una lista.
		// Ya que de todas maneras la concatenación requiere clonar las dos listas
		// a concatenar, este método aprovecha el recorrido de la primera lista
		// para recuperar ese último nodo.
		
		otra.tamanio = this.tamanio;
		
		// agregar un nodo extra para eliminar los casos
		// especiales de lista vacía y de un elemento
		otra.cabecera = new Nodo(null, null);
		
		// punteros auxiliares
		Nodo auxOriginal = this.cabecera;
		Nodo auxCopia = otra.cabecera;
		
		// si la lista es vacía no hay último nodo
		if(auxOriginal == null) {
			auxCopia = null;
		}
		
		auxCopia = copiar(auxOriginal, auxCopia);
		
		// quitar el nodo extra
		otra.cabecera = otra.cabecera.getEnlace();
		
		// auxCopia contiene el último nodo copiado
		return auxCopia;
	}
	
	public void eliminarApariciones(Object elem) {
		// elimina todas las apariciones de elem en la lista
		
		// agregar un nodo extra permite no preocuparnos por casos especiales,
		// como eliminar la cabecera varias veces seguidas
		cabecera = new Nodo(null, cabecera);
		
		Nodo aux = cabecera;
		Nodo visitado;
		while(aux != null && (visitado = aux.getEnlace()) != null) {
			if(visitado.getElem().equals(elem)) {
				// saltar el nodo para quitarlo de la lista
				aux.setEnlace(visitado.getEnlace());
				tamanio--;
			}
			else {
				aux = aux.getEnlace();
			}
		}
		
		// finalmente quitar el nodo extra
		cabecera = cabecera.getEnlace();
	}
	
	public Object recuperar(int pos) {
		// retorna el elemento en posición pos si pos es válido
		// en caso contrario retorna null
		Object elem = null;
		if(1 <= pos && pos <= tamanio) {
			elem = encontrarNodo(pos).getElem();
		}
		return elem;
	}
	
	private Nodo encontrarNodo(int posicion) {
		// retorna el nodo en la posición dada
		// precondición: el nodo se encuentra en la lista
		Nodo aux = cabecera;
		for(int i = 1; i < posicion; i++) {
			aux = aux.getEnlace();
		}
		return aux;
	}
	
	public int localizar(Object elem) {
		int pos;
		if(this.esVacia()) {
			pos = -1;
		}
		else {
			Nodo aux = cabecera;
			boolean encontrado = false;
			pos = 0;
			// termina al alcanzar final de la lista o encontrar el elemento
			while(aux != null && !encontrado) {
				pos++;
				// comparar con elemento actual
				encontrado = aux.getElem().equals(elem);
				// avanzar
				aux = aux.getEnlace();
			}
			// !encontrado significa salir del while porque
			// se recorrió toda la lista sin encontrar elem
			if(!encontrado) pos = -1;
		}
		return pos;
	}
	
	public Lista invertir() {
		// retorna la inversa de esta lista
		Nodo aux = cabecera;
		Lista invertida = new Lista();
		invertida.tamanio = this.tamanio;
		while(aux != null) {
			// agregar el siguiente elemento
			invertida.cabecera = new Nodo(aux.getElem(), invertida.cabecera);
			
			// avanzar
			aux = aux.getEnlace();
		}
		return invertida;
	}
	
	public Lista clone() {
		Lista clon = new Lista();
		clon.tamanio = this.tamanio;
		
		// punteros auxiliares
		Nodo auxOriginal = null; // seteado para saltar el while si lista vacía
		Nodo auxCopia = null; // inicializar solo pora calmar al compilador
		// entramos al while solo si antes se entró al if, donde
		// auxCopia es inicializada
		
		// si lista vacía, no entra en if y while
		if(cabecera != null) {
			// copiar el primer elemento
			clon.cabecera = new Nodo(cabecera.getElem(), null);
			auxOriginal = cabecera.getEnlace();
			auxCopia = clon.cabecera;
		}
		
		while(auxOriginal != null) {
			// copiar el elemento actual
			Nodo siguiente = new Nodo(auxOriginal.getElem(), null);
			auxCopia.setEnlace(siguiente);
			
			// avanzar
			auxOriginal = auxOriginal.getEnlace();
			auxCopia = auxCopia.getEnlace();
		}
		
		return clon;
	}
	
	public int longitud() {
		int cuenta = 0;
		Nodo aux = cabecera;
		while(aux != null) {
			aux = aux.getEnlace();
			cuenta++;
		}
		return cuenta;
	}
	
	public void vaciar() {
		// vacía la lista
		cabecera = null;
		// el garbage collector se encargará de
		// eliminar cada elemento recursivamente
	}
	
	public boolean esVacia() {
		return cabecera == null;
	}
	
	public String toString() {
		String rep = "[";
		Nodo aux = cabecera;
		for(int i = 1; i <= tamanio - 1; i++) {
			rep += aux.getElem().toString() + ", ";
			aux = aux.getEnlace();
		}
		if(tamanio != 0) {
			rep += aux.getElem().toString();
		}
		rep += "]";
		return rep;
	}	
}
