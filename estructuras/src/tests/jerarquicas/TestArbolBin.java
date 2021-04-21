package tests.jerarquicas;
import jerarquicas.dinamicas.ArbolBin;
import lineales.dinamicas.Lista;

public class TestArbolBin {

	public static void main(String[] args) {
		// testRecorridosArbolBinario(3);
		// testClone(3);
		// testNivel(3);
		// testAltura(3);
		// testInsertar(3);
		// testPadre(3);
		// testRecorrerArbolLineas(10);
		// testRecorrerArbolHojas(9);
	}
	
	public static void testRecorridosArbolBinario(int n) {
		// crea un arbol binario completo de altura n e imprime
		// sus recorridos en preorden, inorden, posorden y por niveles
		
		System.out.println("----------------------------------------------------");
		System.out.println("Test recorridos");
		
		System.out.println("Crear un arbol binario completo de tamaño " + n + "...");
		ArbolBin arbol = new ArbolBin();
		testCargarArbolBinarioCompleto(arbol, n, false);
		System.out.println(arbol.toString());
		System.out.println();
		testMostrarRecorridosYFrontera(arbol);

	}
	
	public static void testMostrarRecorridosYFrontera(ArbolBin arbol) {
		System.out.println("recorridos: ");
		Lista lista;
		
		lista = arbol.listarPreorden();
		System.out.println("preorden: " + lista.toString());
		lista = arbol.listarInorden();
		System.out.println("inorden: " + lista.toString());
		lista = arbol.listarPosorden();
		System.out.println("posorden: " + lista.toString());
		lista = arbol.PorNivel();
		System.out.println("por nivel: " + lista.toString());
		lista = arbol.frontera();
		System.out.println("frontera: " + lista.toString());
	}
	
	public static void testClone(int n) {
		System.out.println("----------------------------------------------------");
		System.out.println("Test Clone");
		for(int i = -1; i < n; i++) {
			
			System.out.println("Crear un arbol de altura " + i + "...");
			ArbolBin arbol = new ArbolBin();
			testCargarArbolBinarioCompleto(arbol, i, false);
			System.out.println(arbol.toString());
			System.out.println();
			
			System.out.println("clonar el arbol...");
			ArbolBin clon = arbol.clone();
			System.out.println(clon.toString());
			System.out.println();
			
			System.out.println("Agregar un nivel al arbol original. clon sin cambios");
			testCargarArbolBinarioCompleto(arbol, i + 1, false);
			System.out.println();
			
			System.out.println("original");
			System.out.println(arbol.toString());
			System.out.println();
			System.out.println("clon");
			System.out.println(clon.toString());
			System.out.println();
			
			System.out.println("vaciar orignial. clon sin cambios");
			arbol.vaciar();
			System.out.println();
			
			System.out.println("original");
			System.out.println(arbol.toString());
			System.out.println();
			System.out.println("clon");
			System.out.println(clon.toString());
			System.out.println();
			System.out.println("------------------------------------------------");
		}
	}
	
	public static void testNivel(int n) {
		// crea un arbol binario completo de tamaño n, y por cada
		// elemento imprime su nivel
		
		System.out.println("----------------------------------------------------");
		System.out.println("Test Nivel");
		
		System.out.println("Creando arbol binario completo de altura " + n + "...");
		ArbolBin arbol = new ArbolBin();
		testCargarArbolBinarioCompleto(arbol, n, false);
		System.out.println(arbol.toString());
		System.out.println("por cada elemento del arbol imprimir su nivel...");
		
		int cantidadNodos = (int) Math.pow(2, n + 1) - 1;
		for(int i = 1; i <= cantidadNodos; i++) {
			System.out.println("nivel de " + i + ": " + arbol.nivel(i));
		}
	}
	
	public static void testAltura(int n) {
		System.out.println("-----------------------------------------------------");
		System.out.println("Test Altura");
		
		ArbolBin[] arbol = new ArbolBin[2 * n];
		
		System.out.println("Creando lote de prueba...");
		for(int i = 0; i < n; i++) {
			ArbolBin ar = new ArbolBin();
			testCargarArbolRamaUnica(ar, i, 'd');
			arbol[i] = ar;
		}
		for(int i = 0; i < n; i++) {
			ArbolBin ar = new ArbolBin();
			testCargarArbolBinarioCompleto(ar, i, false);
			arbol[n + i] = ar;
		}
		
		System.out.println("Iniciar test...");
		for(ArbolBin ar: arbol) {
			System.out.println("altura: " + ar.altura());
			System.out.println(ar.toString());
			System.out.println();
		}
		
	}
	
	public static void testInsertar(int n) {
		// crea un arbol binario completo de altura n y lo muestra
		System.out.println("-----------------------------------------------------");
		System.out.println("Test Insertar");
		System.out.println("Crear un árbol binario completo de altura " + n + "...");
		System.out.println();
		
		ArbolBin arbol = new ArbolBin();
		testCargarArbolBinarioCompleto(arbol, n, true);
		
		System.out.println(arbol.toString());
		
		System.out.println("Intentar volver a cargar el arbol, cada inserción imprime false:");
		System.out.println();
		
		testCargarArbolBinarioCompleto(arbol, n, true);
		
		System.out.println("La estructura del arbol no cambia: ");
		System.out.println();
		
		System.out.println(arbol.toString());
	}
	
	public static void testCargarArbolBinarioCompleto(ArbolBin arbol, int n, boolean print) {
		// carga un arbol binario completo en arbol e imprime el resultado
		// de cada inserción
		
		int cantidadNodos = (int) Math.pow(2, n + 1) - 1;
		
		for(int i = 1; i <= cantidadNodos; i++) {
			
			int elem = i;
			int padre = i / 2;
			char lado = i % 2 == 0 ? 'i' : 'd';
			
			boolean insertado = arbol.insertar(elem, padre, lado);
			
			if(print) {
				System.out.println(String.format(
						"insertar %d, padre %d, lado %c: %b", elem, padre, lado, insertado));
				System.out.println();
			}
		}
	}
	
	public static void testPadre(int n) {
		// crea un arbol binario completo de altura n y muestra
		// el padre de cada elemento
		
		System.out.println("-----------------------------------------------------");
		System.out.println("Test padre");
		
		System.out.println("Crear un arbol binario completo...");
		ArbolBin arbol = new ArbolBin();
		testCargarArbolBinarioCompleto(arbol, n, false);
		System.out.println(arbol.toString());
		
		System.out.println("Encontrar el padre de cada elemento en el arbol");
		int cantidadElementos = (int) Math.pow(2, n + 1) - 1;
		
		for(int i = 1; i <= cantidadElementos; i++) {
			Object padre = arbol.padre(i);
			System.out.println(String.format(
			"padre de %d: %s", i, padre != null ? padre.toString(): "null"));
		}
	}
	
	public static void testCargarArbolRamaUnica(ArbolBin arbol, int n, char lado) {
		// carga un arbol de profundidad n con una sola rama
		for(int i = 1; i <= n + 1; i++) {
			arbol.insertar(i, i - 1, lado);
		}
	}

	public static void testArbolHojaEnCadaNivel(ArbolBin arbol, int n) {
		// carga n nodos en el arbol, de forma que cada nivel despues de 2
		// tiene un nodo hoja
		
		/*
		 *    1
		 *    | \
		 *    2  3
		 *    	 | \
		 *       4  5
		 *          | \
		 *          6  7
		 *...
		 */
		
		int nodo = 1;
		int padre = -1;
		int lado = 1;
		
		while(nodo <= n) {
			arbol.insertar(nodo, padre, lado == 1 ? 'd' : 'i');
			nodo++;
			if(nodo % 2 == 0) padre = padre + 2;
			lado = (lado + 1) % 2;
		}
	}
	
	public static void testRecorrerArbolHojas(int n) {
		System.out.println("----------------------------------------------------");
		System.out.println("Test arbol hojas");
		ArbolBin arbol = new ArbolBin();
		testArbolHojaEnCadaNivel(arbol, n);
		System.out.println(arbol.toString());
		testMostrarRecorridosYFrontera(arbol);
	}
	
	public static void testRecorrerArbolLineas(int n) {
		System.out.println("----------------------------------------------------");
		System.out.println("Test arbol lineas");
		ArbolBin arbol = new ArbolBin();
		testCargarArbolLineas(arbol, n);
		System.out.println(arbol.toString());
		testMostrarRecorridosYFrontera(arbol);
	}
	
	public static void testCargarArbolLineas(ArbolBin arbol, int n) {
		// carga un arbol con la siguiente forma:
		
		/*
		 *   1
		 *   | \
		 *   2  3
		 *   |  | \
		 *   4  5  6
		 *   |  |  | \
		 *   7  8  9  10
		 *   ...
		 */
		int i = 1;
		int nivel = 1;
		int padre = 0;
		while(i <= n) {
			int j = 1;
			while(j <= nivel && i <= n) {
				char lado = j == nivel ? 'd' : 'i';
				boolean insertado = arbol.insertar(i, padre, lado);
				System.out.println(String.format(
				"i: %d, j: %d, nivel: %d, padre: %d, lado: %c, insertado: %b",
				i, j, nivel, padre, lado, insertado));
				j++;
				i++;
				if(j != nivel) {
					padre++;
				}
			}
			nivel++;
		}
	}
}
