package tests.lineales;

import lineales.dinamicas.Lista;

public class MiTestLista {

	public static void main(String[] args) {
		testLongitud(10);
	}
	
	public static void testContar() {
		// TODO
	}
	
	public static void testLongitud(int n) {
		
		System.out.println("Crear lista nueva...");
		Lista lista = new Lista();
		System.out.println("Longitud de lista vacía es 0: " + lista.longitud());
		
		for(int i = 1; i <= n; i++) {
			System.out.println();
			System.out.print("Agregar elemento... ");
			lista.insertar(i, 1);
			System.out.println("Longitud " + i + ": " + lista.longitud());
			System.out.println(lista.toString());
		}
	}
	
	public static void testEliminarApariciones() {
		Lista cadenas = new Lista();
		cadenas.insertar(StringAListaChar("a"), 1);
		cadenas.insertar(StringAListaChar("aa"), 1);
		cadenas.insertar(StringAListaChar("aaa"), 1);
		cadenas.insertar(StringAListaChar(""), 1);
		cadenas.insertar(StringAListaChar("b"), 1);
		cadenas.insertar(StringAListaChar("bb"), 1);
		cadenas.insertar(StringAListaChar("abab"), 1);
		cadenas.insertar(StringAListaChar("aaaababababab"), 1);
		while(!cadenas.esVacia()) {
			Lista lista = (Lista) cadenas.recuperar(1);
			System.out.println("original  : " + lista.toString());
			lista.eliminarApariciones('a');
			System.out.println("modificada: " + lista.toString());
			System.out.println();
			cadenas.eliminar(1);
		}
	}
	
	public static void testIntercalar(int n) {
		// crea n + 1 pares de listas cuyo tamaño suma n, y las intercala
		
		Lista[] primera = new Lista[n + 1];
		Lista[] segunda = new Lista[n + 1];
		
		System.out.println("Test intercalar");
		
		// crear pares de listas [], [a.. a] ; [b], [a.. a] ; [b, b], [a.. a] ; ... 
		for(int i = 0; i < n + 1; i++) {
			primera[i] = new Lista();
			segunda[i] = new Lista();
			for(int j = 0; j < i; j++) {
				segunda[i].insertar('b', 1);
			}
			for(int j = i; j < n; j++) {
				primera[i].insertar('a', 1);
			}
		}
		
		for(int i = 0; i < n + 1; i++) {
			System.out.println("lista 1: " + primera[i].toString());
			System.out.println("lista 2: " + segunda[i].toString());
			System.out.println("inter  : " + primera[i].intercalar(segunda[i]).toString());
			System.out.println();
		}
	}
	
	public static void testConcatenar(int n) {
		// crea n + 1 pares de listas cuyo tamaño suma n, y las concatena
		
		Lista[] primera = new Lista[n + 1];
		Lista[] segunda = new Lista[n + 1];
		
		System.out.println("Test concatenar");
		
		// crear pares de listas [], [1.. n] ; [1], [2.. n] ; [1, 2], [3.. n] ; ... 
		for(int i = 0; i < n + 1; i++) {
			primera[i] = new Lista();
			segunda[i] = new Lista();
			for(int j = 0; j < i; j++) {
				segunda[i].insertar(j, 1);
			}
			for(int j = i; j < n; j++) {
				primera[i].insertar(j, 1);
			}
		}
		
		for(int i = 0; i < n + 1; i++) {
			System.out.println("lista 1: " + primera[i].toString());
			System.out.println("lista 2: " + segunda[i].toString());
			System.out.println("concat : " + primera[i].concatenar(segunda[i]).toString());
			System.out.println();
		}
		
	}
	
	public static void testInvertir(int n) {
		System.out.println("test invertir");
		
		Lista listas = new Lista();
		// crear una lista de listas de tamaño 0 a n - 1
		System.out.println("Crear una lista de listas con elementos de 0 a " + (n - 1));
		for(int i = 0; i < n; i++) {
			Lista lista = new Lista();
			for(int j = 0; j <= i; j++) {
				lista.insertar(j, 1);
			}
			listas.insertar(lista, 1);
		}
		
		// imprimir cada lista con su inversa
		
		System.out.println("imprimir cada lista con su inversa");
		while(!listas.esVacia()) {
			Lista lista = (Lista) listas.recuperar(1);
			listas.eliminar(1);
			System.out.println(lista.toString());
			System.out.println(lista.invertir().toString());
			System.out.println();
		}
	}

	public static void testClone(int n) {
		System.out.println("Test clone");
		System.out.println();
		
		System.out.println("Crear una lista nueva");
		Lista lista = new Lista();
		System.out.println(lista.toString());
		System.out.println();
		
		System.out.println("Clonar lista");
		Lista clon = lista.clone();
		System.out.println("original: " + lista.toString());
		System.out.println("clon    : " + clon.toString());
		System.out.println();
		
		System.out.println("Agregar elementos a lista original");
		testInsertarFinal(lista, n);
		System.out.println("original modificada, clon sin cambios: ");
		System.out.println("original: " + lista.toString());
		System.out.println("clon    : " + clon.toString());
		System.out.println();
		
		System.out.println("Crear dos clones de original");
		clon = lista.clone();
		Lista clon2 = clon.clone();
		System.out.println("original: " + lista.toString());
		System.out.println("clon    : " + clon.toString());
		System.out.println("clon2   : " + clon2.toString());
		System.out.println();
	
		System.out.println("Modificar original y clon 2...");
		testEliminarDesdeCabecera(lista, n / 2);
		testEliminarDesdeFinal(clon2, n / 2);
		
		System.out.println("original y clon2 modificadas, no afecta a clon");
		System.out.println("original: " + lista.toString());
		System.out.println("clon    : " + clon.toString());
		System.out.println("clon2   : " + clon2.toString());
		System.out.println();
		
		
	}
	
	public static void testLocalizar(int n) {
		System.out.println("Crear lista nueva");
		Lista lista = new Lista();
		
		System.out.println("Localizar elementos siempre da -1 en lista vacía");
		for(int i = 0; i < n; i++) {
			System.out.println("localizar " + i + ": " + lista.localizar(i));
		}
		
		System.out.println("Poblar con elementos de 1 a " + n + ":");
		for(int i = 1; i <= n; i++) {
			lista.insertar(i, i);
		}
		System.out.println(lista.toString());
		
		System.out.println("Localizar cada elemento, imprime i, i");
		for(int i = 1; i <= n; i++) {
			System.out.println(i + ", " + lista.localizar(i));
		}
		System.out.println("Localizar elementos que no están en la lista");
		for(int i = n + 1; i <= 2 * n; i++) {
			System.out.println(i + ", " + lista.localizar(i));
		}
	}
	
	public static void testInsertar(int n) {
		Lista lista = new Lista();
		testInsertarCabecera(lista, n);
		testInsertarPosicionesValidas(lista, n);
		testInsertarPosicionesInvalidas(lista, n);
		testInsertarFinal(lista, n);
	}
	
	public static void testEliminar(int n) {
		Lista lista = new Lista();
		System.out.println("Eliminar en lista vacía, imprime false");
		testEliminarDesdeCabecera(lista, n);
		testEliminarDesdeFinal(lista, n);
		System.out.println("insertar elementos en la lista...");
		testInsertarCabecera(lista, n);
		testInsertarFinal(lista, n);
		System.out.println("Eliminar por el final...");
		testEliminarDesdeFinal(lista, n);
		System.out.println("Eliminar desde cabecera...");
		testEliminarDesdeCabecera(lista, n);
		System.out.println("Lista vaciada, agregar elemenotos...");
		testInsertarFinal(lista, 2 * n);
		System.out.println("Eliminar elementos por el medio...");
		testEliminarPosiciones(lista, n);
	}
	
	public static void testRecuperar(int n) {
		System.out.println("Test recuperar");
		System.out.println("Crear una lista vacía");
		Lista lista = new Lista();
		System.out.println("Recuperar en lista vacía siempre devuelve null");
		for(int i = 0; i < n; i++) {
			System.out.print("recuperar " + i + ": ");
			Object recuperado = lista.recuperar(i);
			System.out.println(recuperado == null ? "null" : recuperado.toString());
		}
		System.out.println("Llenar la lista con " + n + " elementos en orden ascendente");
		testInsertarFinal(lista, n);
		System.out.println("recuperar en orden");
		for(int i = 1; i <= n; i++) {
			Object elem = lista.recuperar(i);
			System.out.println("recuperar " + i + ": " 
			+ (elem == null ? "null" : elem.toString()));
		}
	}
	
	public static void testVaciar(Lista lista) {
		System.out.print("vaciar la lista... ");
		lista.vaciar();
		System.out.println("lista vaciada");
		System.out.println(lista.toString());
		System.out.println();
	}
	
	public static void testInsertarCabecera(Lista lista, int n) {
		System.out.println("test: insertar " + n + " elementos en la cabecera");
		for(int i = 1; i <= n; i++) {
			System.out.println(i + " " + lista.insertar(i, 1));
			System.out.println(lista.toString());
		}
		System.out.println();
	}
	
	public static void testInsertarFinal(Lista lista, int n) {
		int tamanio = lista.getTamanio();
		System.out.println("test: insertar elementos en el final");
		for(int i = tamanio + 1; i <= tamanio + n; i++) {
			System.out.println(i + " " + lista.insertar(i, i));
			System.out.println(lista.toString());
		}
		System.out.println();
	}
	
	public static void testInsertarPosicionesValidas(Lista lista, int n) {
		System.out.println("test: insertar elementos en posiciones válidas, desde 1 hasta " + n);
		for(int i = 1; i <= n; i++) {
			System.out.println(i + " " + lista.insertar(i, i));
			System.out.println(lista.toString());
		}
		System.out.println();
	}
	
	public static void testInsertarPosicionesInvalidas(Lista lista, int n) {
		System.out.println("test: insertar elementos en posiciones inválidas");
		int tamanio = lista.getTamanio();
		System.out.println("0 y negativos");
		for(int i = 0; i > -n; i--) {
			System.out.println(i + " " + lista.insertar(i, i));
			System.out.println(lista.toString());
		}
		System.out.println("posiciones mayores a tamaño + 1");
		for(int i = tamanio + 2; i < tamanio + n + 2; i++) {
			System.out.println(i + " " + lista.insertar(i, i));
			System.out.println(lista.toString());
		}
		System.out.println();
	}

	public static void testEliminarDesdeCabecera(Lista lista, int n) {
		// elimina elementos de la lista uno a uno desde la cabecera
		System.out.println("Test eliminar desde cabecera");
		for(int i = 1; i <= n; i++) {
			System.out.println(lista.eliminar(1) + " " + lista.toString());
		}
		System.out.println();
	}
	
	public static void testEliminarDesdeFinal(Lista lista, int n) {
		// vacía la lista eliminando elementos uno a uno desde el final
		System.out.println("Test eliminar desde final");
		int tamanio = lista.getTamanio();
		for(int i = 0; i < n; i++) {
			System.out.println(lista.eliminar(tamanio) + " " + lista.toString());
			tamanio--;
		}
		System.out.println();
	}
	
	public static void testEliminarPosiciones(Lista lista, int n) {
		// elimina elementos desde n hasta 0
		// precondición: la lista tiene al menos n elementos
		for(int i = n; i > 0; i--) {
			System.out.println(i + " " + lista.eliminar(i));
			System.out.println(lista.toString());
		}
	}
	
	public static Lista StringAListaChar(String s) {
		// retorna una Lista de los caracteres en s
		int len = s.length();
		Lista lista = new Lista();
		for(int i = len - 1; i >= 0; i--) {
			// insertar(1) es más eficiente (O(1)) pero invierte el orden,
			// como apilar en una pila. Por lo tanto el String se recorre
			// de atrás a adelante
			lista.insertar(s.charAt(i), 1);
		}
		return lista;
	}
}
