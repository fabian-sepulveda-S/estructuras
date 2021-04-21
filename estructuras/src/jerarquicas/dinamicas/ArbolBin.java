package jerarquicas.dinamicas;

import lineales.dinamicas.Lista;
import lineales.dinamicas.Cola;

public class ArbolBin {

	private NodoArbolBin raiz;
	
	public ArbolBin() {
		this.raiz = null;
	}
	
	public boolean esVacio() {
		return raiz == null;
	}

	public void vaciar() {
		// vacía el arbol, eliminando todos los nodos
		
		// una vez perdida la referencia a raíz el nodo es reclamado
		// por el garbage collector, dejando sin referencia a sus hijos
		// que también serán reclamados, y así recursivamente todos los
		// nodos del árbol son eliminados
		raiz = null;
	}
	
	public int altura() {
		return altura(raiz);
	}
	
	private int altura(NodoArbolBin nodo) {
		// retorna la altura del subarbol definido por nodo
		
		// caso base 1: nodo es null, salta el if
		int altura = -1;
		
		if(nodo != null) {
			// caso recursivo, sumar 1 a la mayor altura de los hijos
			altura = Math.max(altura(nodo.getIzquierdo()), altura(nodo.getDerecho())) + 1;
		}

		return altura;
		
	}
	
	public int nivel(Object elem) {
		// retorna el nivel donde se encuentra elem
		// si hay múltiples instancias de elem retorna la primera
		// encontrada en recorrido pre-orden
		return nivel(raiz, elem);
	}
	
	private int nivel(NodoArbolBin nodo, Object elem) {
		// retorna el nivel de elem en el subarbol definido por nodo
		
		// caso base 1, elem no se encuentra en este subarbol
		int nivel = -1;
		
		boolean encontrado = false;
		if(nodo != null) {
			// caso base 2: encontrado!!!
			if(nodo.getElem().equals(elem)) {
			nivel = 0;
			encontrado = true;
			}
			// caso recursivo: buscar en los hijos
			if(!encontrado) {
				int nivelIzquierdo = nivel(nodo.getIzquierdo(), elem);
				if(nivelIzquierdo != -1) {
					nivel = nivelIzquierdo + 1;
					encontrado = true;
				}
			}
			if(!encontrado) {
				int nivelDerecho = nivel(nodo.getDerecho(), elem);
				if(nivelDerecho != -1) {
					nivel = nivelDerecho + 1;
					encontrado = true;
				}
			}
		}
		return nivel;
	}
	
	public Object padre(Object elem) {
		// encuentra y retorna el padre de elem o null si no existe
		NodoArbolBin padre = padre(raiz, elem);
		return padre != null ? padre.getElem() : null;
	}
	
	private NodoArbolBin padre(NodoArbolBin nodo, Object elem) {
		// encuentra el nodo que es padre de elem
		
		NodoArbolBin padre = null;
		
		// caso base 1: nodo es null, no entra al if y retorna null
		
		if(nodo != null) {
			NodoArbolBin izquierdo = nodo.getIzquierdo();
			NodoArbolBin derecho = nodo.getDerecho();
			
			// caso base 2: nodo es padre de elem
			if((izquierdo != null && izquierdo.getElem() == elem) || 
			   (derecho != null && derecho.getElem() == elem)) {
				padre = nodo;
			}
			
			// caso recursivo: buscar en los hijos de nodo
			if(padre == null) {
				padre = padre(izquierdo, elem);
			}
			if(padre == null) {
				padre = padre(derecho, elem);
			}
		}
		
		return padre;
	}
	
	public boolean insertar(Object elem, Object padre, char lado) {
		// inserta elem como hijo (lado) del elemento padre
		// lado puede ser 'i' o 'd'
		
		boolean exito;
		
		// si arbol vacío ignorar padre y lado y crear la raíz con elem
		if(raiz == null) {
			exito = insertarRaiz(elem);
			
		}
		else {
			exito = insertarGeneral(elem, padre, lado);
		}
		return exito;
	}
	
	private boolean insertarRaiz(Object elem) {
		raiz = new NodoArbolBin(elem);
		return true;
	}
	
	private boolean insertarGeneral(Object elem, Object padre, char lado) {
		boolean exito = false;
		NodoArbolBin nodoPadre = encontrarElemento(raiz, padre);
		
		// si encontramos al padre
		if(nodoPadre != null) {
			// intentar insertar según lado
			switch(lado) {
			case 'i':
				if(nodoPadre.getIzquierdo() == null) {
					nodoPadre.setIzquierdo(new NodoArbolBin(elem));
					exito = true;
					break;
				}
			case 'd':
				if(nodoPadre.getDerecho() == null) {
					nodoPadre.setDerecho(new NodoArbolBin(elem));
					exito = true;
					break;
				}
			}
		}
		return exito;
	}	
	
	private NodoArbolBin encontrarElemento(NodoArbolBin subarbol, Object elem) {
		
		NodoArbolBin encontrado = null;
		
		// caso base 1: si subarbol == null no entra al if y retorna null
		
		if(subarbol != null) {
			// caso base 2: encontrado!!!
			if(subarbol.getElem().equals(elem)){
				encontrado = subarbol;
			}
			
			// caso recursivo: buscar en los hijos
			if(encontrado == null) {
				encontrado = encontrarElemento(subarbol.getIzquierdo(), elem);
			}
			if(encontrado == null) {
				encontrado = encontrarElemento(subarbol.getDerecho(), elem);
			}
		}
		return encontrado;
	}
	
	// en los cuatro métodos de recorridos se usa insertar(elem, 1) en listas
	// porque es más eficiente dada la implementación de lista con cabecera.
	// esta forma de insertar tiene el inconveniente de que el orden de la lista
	// es invertido, por lo que antes de retornar la lista debe ser invertida.
	// En total cada elemento es visitado 2 veces, la primera al recorrer el 
	// árbol y la segunda al invertir la lista, siendo los métodos de O(2n) = O(n)
	
	// Si en cambio se inserta por el final los métodos son de O(n^2) ya que 
	// se multiplica el recorrido O(n) del arbol por el O(n) de insertar general
	
	public Lista PorNivel() {
		// retorna una lista con los elemetos ordenados en recorrido por nivel
		
		Cola cola = new Cola();
		Lista lista = new Lista();
		
		// si raiz == null no hay elementos y retorna lista vacía
		if(raiz != null) {
			cola.poner(raiz);
			
			while(!cola.esVacia()) {
				
				// visitar siguiente nodo
				NodoArbolBin nodo = (NodoArbolBin) cola.obtenerFrente();
				cola.sacar();
				lista.insertar(nodo.getElem(), 1);
				
				// poner a los hijos del nodo en la cola
				NodoArbolBin izquierdo = nodo.getIzquierdo();
				NodoArbolBin derecho = nodo.getDerecho();
				if(izquierdo != null) cola.poner(izquierdo);
				if(derecho != null) cola.poner(derecho);
			}
		}
		
		lista = lista.invertir();
		return lista;
	}
	
	public Lista listarPosorden() {
		// retorna una lista con los elementos ordenados en posorden
		
		Lista lista = new Lista();
		
		listarPosorden(raiz, lista);
		
		lista.invertirInPlace();
		return lista;
	}
	
	private void listarPosorden(NodoArbolBin nodo, Lista lista) {
		// lista el subarbol en nodo en recorrido posorden
		
		// caso base: arbol vacío, salta el if
		
		if(nodo != null) {
			// caso recursivo, recorrer hijo izquierdo, recorrer hijo derecho,
			// visitar raíz
			listarPosorden(nodo.getIzquierdo(), lista);
			listarPosorden(nodo.getDerecho(), lista);
			lista.insertar(nodo.getElem(), 1);
		}
	}
	
	public Lista listarInorden() {
		// retorna una lista con los elementos ordenados en inorden
		
		Lista lista = new Lista();
		
		listarInorden(raiz, lista);
		
		lista.invertirInPlace();
		return lista;
	}
	
	private void listarInorden(NodoArbolBin nodo, Lista lista) {
		// lista nodos del arbol en nodo en inorden
		
		// caso base: arbol vacío, salta el if
		
		if(nodo != null) {
			// caso recursivo, recorrer hijo izquierdo, visitar raiz,
			// recorrer hijo derecho
			listarInorden(nodo.getIzquierdo(), lista);
			lista.insertar(nodo.getElem(), 1);
			listarInorden(nodo.getDerecho(), lista);
		}
	}
	
	public Lista listarPreorden() {
		// retorna una lista con los elemetos ordenados en preorden
		
		Lista lista = new Lista();
		
		listarPreorden(raiz, lista);
		
		lista.invertirInPlace();
		return lista;
	}
	
	private void listarPreorden(NodoArbolBin nodo, Lista lista) {
		// lista el arbol en nodo en recorrido preorden
		
		// caso base: arbol vacío, salta el if
		
		if(nodo != null) {
			// caso recursivo, apilar este nodo y luego sus hijos
			lista.insertar(nodo.getElem(), 1);
			listarPreorden(nodo.getIzquierdo(), lista);
			listarPreorden(nodo.getDerecho(), lista);
		}
	}
	
	public Lista frontera() {
		// retorna una lista con los nodos hoja del arbol
		
		Lista lista = new Lista();
		
		listarHojas(raiz, lista);
		lista.invertirInPlace();
		
		return lista;
	}
	
	private void listarHojas(NodoArbolBin nodo, Lista lista) {
		// agrega los nodos hoja del arbol en nodo a la lista
		
		// caso base 1: nodo es null
		
		if(nodo != null) {
			NodoArbolBin izquierdo = nodo.getIzquierdo();
			NodoArbolBin derecho = nodo.getDerecho();
			
			// caso base 2: nodo hoja encontrado!!!
			if(izquierdo == null && derecho == null) {
				lista.insertar(nodo.getElem(), 1);
			}
			else {
				listarHojas(izquierdo, lista);
				listarHojas(derecho, lista);
			}
		}
	}
	
	public ArbolBin clone() {
		// retorna una copia superficial de este arbol
		ArbolBin copia = new ArbolBin();
		
		copia.raiz = copiar(this.raiz);
		
		return copia;
	}
	
	private NodoArbolBin copiar(NodoArbolBin fuente) {
		// copia el subarbol en fuente y retorna la raiz del subarbol copiado
		
		NodoArbolBin copia = null;
		
		// caso base 1: fuente es null, salta el if
	
		if(fuente != null) {
			// copiar elemento raiz
			copia = new NodoArbolBin(fuente.getElem());
			
			// copiar subarboles
			copia.setIzquierdo(copiar(fuente.getIzquierdo()));
			copia.setDerecho(copiar(fuente.getDerecho()));
		}
		
		return copia;
	}
	
	public String toString() {
		String rep = raiz == null ? "(arbol vacio)" : toStringSubArbol(raiz, "", 'r');
		return rep;
	}
	
	private String toStringSubArbol(NodoArbolBin nodo, String prefijo, char lado) {
		// imprime el arbol en recorrido pre-orden, agregando espacios
		// según la profundidad
		String rep = "";
		
		if(nodo != null) {
			rep = prefijo;
			
			if(lado == 'd') {
				rep += "|";
				prefijo += " ";
			}
			// agregar el elemento del nodo actual
			rep += "------" + lado + " " + nodo.getElem().toString() + "\n";
		
			// agregar el resto del árbol
			rep += toStringSubArbol(nodo.getIzquierdo(), prefijo + "      |", 'i');
			rep += toStringSubArbol(nodo.getDerecho(), prefijo + "      ", 'd');
		}
		return rep;
	}
}
