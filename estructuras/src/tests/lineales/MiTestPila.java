package tests.lineales;
import lineales.estaticas.Pila;
// import lineales.dinamicas.Pila;

public class MiTestPila {
	
	public static void main (String[] args) {
		System.out.println("Iniciando tests...");
		System.out.println();
		
		testCapicua();
		testCopias();
		testPredefinido('i');
		testPredefinido('s');
		
	}
	
	public static void testCopias() {
		// crea un array de pilas de tamaño 0 a n,
		// luego las copia en copias[] y las compara
		
		System.out.println("Test clone");
		System.out.println();
		int n = 10;
		Pila[] original = new Pila[n];
		Pila[] copia = new Pila[n];
		
		// inicializar original
		System.out.println("Creando pilas originales...");
		for(int i = 0; i < n; i++) {
			Pila aux = new Pila();
			for(int j = 0; j < i; j++) {
				aux.apilar(j);
			}
			original[i] = aux;
		}
		System.out.println();
		
		// clonar en copia
		System.out.println("Clonando pilas...");
		for(int i = 0; i < n; i++) {
			copia[i] = original[i].clone();
		}
		System.out.println();
		
		// mostrar cada pareja de pilas
		System.out.println("parejas de pilas");
		System.out.println();
		for(int i = 0; i < n; i++) {
			System.out.println(original[i]);
			System.out.println(copia[i]);
			System.out.println();
		}
		
		// destruir colas originales
		System.out.println("Destuir colas originales...");
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				original[i].desapilar();
			}
		}
		
		// verificar que las copias todavía existen
		System.out.println("Mostrar que las copias aún existen");
		for(int i = 0; i < n; i++) {
			System.out.println(copia[i]);
		}
		
	}
	
	public static void testCapicua() {
		// prueba el metodo esCapicua
		// genera un lote de prueba y luego lo prueba
		
		System.out.println("Iniciando test capicúa\n");
		// test int
		int tam = 10;
		// generar las pilas [], [0], [0,1], [0,1,2],...
		Pila[] pilasNoCapicua = new Pila[tam / 2];
		for (int i = 0; i < tam / 2; i++) {
			pilasNoCapicua[i] = new Pila();
			for (int j = 0; j < i; j++) {
				// escribir 0, 1, 2, ... i en la pila
				pilasNoCapicua[i].apilar(j);
			}
		}
		 
		// generar las pilas 0, 00, 010, 0110, 01210, 012210,...
		Pila[] pilasCapicua = new Pila[tam];
		for (int i = 0; i < tam; i++) {
			Pila aux = new Pila();
			pilasCapicua[i] = aux;
			// escribir 0, 1, 2, ... (i + 1) / 2 en la pila
			for (int j = 0; j < (i + 1) / 2; j++) {
				aux.apilar(j);
			}
			// si i es par agregar el medio
			if (i % 2 == 0) {
				aux.apilar(i / 2);
			}
		}
		// escribir la mitad reversa para que sea capicúa
		for (int i = tam - 1; i >= 0; i--) {
			Pila aux = pilasCapicua[i];
			for (int j = (i + 1) / 2 - 1; j >= 0; j--) {
				aux.apilar(j);
			}
		}
		
		// testear la pila no capicúa
		// las pilas [] y [0] en realidad son capicúa
		System.out.println("Testeando pilas secuencia ascendente (no capicua excepto [] y [0])\n");
		for (int i = 0; i < tam / 2; i++) {
			Pila actual = pilasNoCapicua[i];
			System.out.println(actual.toString() + (esCapicua(actual) ? " capicúa" : " no capicúa"));
		}
		System.out.println();
		// testear las pilas capicúa
		System.out.println("Testeando pilas capicúa\n");
		for (int i = 0; i < tam; i++) {
			Pila actual = pilasCapicua[i];
			System.out.println(actual.toString() + (esCapicua(actual) ? " capicúa" : " no capicúa"));
		}
		System.out.println("\nTest capicúa terminado\n");
	}
	 
	public static boolean esCapicua(Pila p) {
		// verifica si los elementos de la pila p forman una cadena capicúa
		
		Pila copia = p.clone();
		Pila reversa = new Pila();
		
		// apilar copia sobre reversa. destruye copia
		// contar elementos de copia
		int contador = 0;
		while (!copia.esVacia()) {
			reversa.apilar(copia.obtenerTope());
			copia.desapilar();
			contador++;
		}
		
		int mitad = contador / 2;
		boolean esImpar = contador % 2 == 1;
		
		// apilar la mitad de reversa sobre copia
		for (int i = 0; i < mitad; i++) {
			copia.apilar(reversa.obtenerTope());
			reversa.desapilar();
		}
		
		if (esImpar) {
			// eliminar el elemento del medio
			reversa.desapilar();
		}
		
		// comparar copia y reversa
		boolean iguales = true;
		while (!reversa.esVacia() && iguales) {
			iguales = reversa.obtenerTope().equals(copia.obtenerTope());
			reversa.desapilar();
			copia.desapilar();
		}
		return iguales;
	}
	
	public static void cargarDatosInt(int n, Pila p) {
		// intenta rellenar la pila con n elementos int
		// imprime un mensaje indicando cuantos elementos fueron apilados
		System.out.println("Cargando " + n + " elementos: ");
		int i = 0;
		// mientras falte apilar y el apilado sea exitoso
		while (i < n && p.apilar(i)) {
			i++;
		}
		System.out.println("Se apilaron " + i + " elementos de " + n);
	}
	
	public static String generarString(int n) {
		// genera un String representado n alfabeticamente
		// 1:a, 2:b,... 26:z, 27:aa, 28:ab,... 
		// muy ineficiente pero deberÃ­a usarse para n pequeÃ±o
		String generado = "";
		char letra = (char) ('a' + (n % 26));
		n = n / 26;
		if (n > 0) {
			generado += generarString(n);
		}
		generado += Character.toString(letra);
		
		return generado;
	}
	
	public static void cargarDatosString(int n, Pila p) {
		// intenta rellenar la pila con n elementos String
		// imprime un mensaje indicando cuantos elementos fueron apilados
		System.out.println("Cargando " + n + " elementos: ");
		int i = 0;
		// mientras falte apilar y el apilado sea exitoso
		while (i < n && p.apilar(generarString(i))) {
			i++;
		}
		System.out.println("Se apilaron " + i + " elementos de " + n);
	}
	
	public static void testPredefinido(char cod) {
		// realiza un test de la clase Pila con el tipo de dato indicado por cod
		System.out.println("\nEjecutando test predefinido...\n");
		
		int tamanio = 20;
		Pila pila = new Pila();
		
		System.out.println("Metodo apilar");
		System.out.println("Rellenar la pila con "+ tamanio + " objetos");
		
		switch (cod) {
		case 'i':
			cargarDatosInt(tamanio, pila);
			break;
		case 's':
			cargarDatosString(tamanio, pila);
			break;
		default:
			System.out.println("TIPO DE DATO NO SOPORTADO POR EL TEST");
		}
		
		System.out.println("pila: " + pila);
		System.out.println();
		
		System.out.println("Metodo clone");
		System.out.println("Copiar pila...");
		System.out.println();
		Pila copia = pila.clone();
		
		System.out.println("Metodos desapilar y mostrarTope");
		System.out.println("Desapilar todos los elementos y mostrarlos");
		boolean quedan = true;
		while (quedan) {
			System.out.println(pila.obtenerTope());
			quedan = pila.desapilar();
		}
		System.out.println("pila: " + pila);
		System.out.println();
	
		System.out.println("Metodo esVacia: pila deberia estar vacia y copia no");
		System.out.println("pila " + (pila.esVacia() ? "esta vacia" : "no esta vacia"));
		System.out.println("pila: " + pila);
		System.out.println("copia " + (copia.esVacia() ? "esta vacia" : "no esta vacia"));
		System.out.println("copia: " + copia);
		System.out.println();

		System.out.println("Metodo vaciar: vaciando copia");
		copia.vaciar();
		System.out.println("copia" + (copia.esVacia() ? " esta vacia" : " no esta vacia"));
		System.out.println("copia:" + copia);
		System.out.println();
		
		System.out.println("Test finalizado");
	}
}