package tests.lineales;

import lineales.dinamicas.Cola;

public class MiTestCola {

	public static void main(String[] args) {
		testClonar(20);
		testPonerSobreColaLlena(10);
		testSacarSobreColaVacia(10);
	}
	
	public static void testCircular(Cola cola, int n) {
		// agrega y quita n elementos a la cola
		// pensado para cola estática, pero dinámica
		// también debe pasar
		for(int i = 0; i < n; i++) {
			System.out.println("poner " + i + " y sacar frente");
			cola.poner(i);
			cola.sacar();
			verCola(cola);
		}
	}
	
	public static void verCola(Cola cola) {
		System.out.println("cola                     : " + cola.toString());
		System.out.println("cola (estructura interna): " + cola.toStringTransparente());
		System.out.println();
	}
	
	public static int llenarCola(Cola cola, int n) {
		// agrega elementos hasta llenar la cola
		// o poner n elementos
		// retorna la cantidad de elementos puestos
		System.out.println("poner " + n + " elementos...");
		int i = 0;
		while(i < n && cola.poner(i)) {
			i++;
		}
		System.out.println("puestos " + i + " elementos");
		return i;
	}
	
	public static void vaciarCola(Cola cola) {
		// saca elementos de la cola uno a uno hasta vaciar
		while(cola.sacar());
	}
	
	public static void testClonar(int n) {
		// crea una cola de n elementos, la clona,
		// altera sus elementos y verifica que el clon no cambia
		System.out.println("----------------------------------------------------");
		System.out.println("test clonar \n");
		System.out.println("Crear una cola de " + n + " elementos...");
	
		Cola cola = new Cola();
		llenarCola(cola, n);
		
		System.out.println("Clonar la cola...");
		
		Cola clon = cola.clone();
		
		System.out.println();
		System.out.println("Cola original");
		verCola(cola);
		System.out.println("Cola clon");
		verCola(clon);
		
		System.out.println("alterar cola original...");
		
		// sacar un elemento de la cola llena para iniciar
		// test circular
		System.out.println("sacar 1 elemento");
		Object elem = cola.obtenerFrente();
		cola.sacar();
		System.out.println("Sacado " + elem.toString());
		verCola(cola);
		System.out.println();
		
		testCircular(cola, 2 * n);
		
		System.out.println("Ver cola original");
		verCola(cola);
		
		System.out.println("Ver cola clon");
		verCola(clon);
		
		System.out.println("Vaciar cola original");
		cola.vaciar();
		
		System.out.println("Ver cola original");
		verCola(cola);
		
		System.out.println("Ver cola clon");
		verCola(clon);
	}
	
	public static void testPonerSobreColaLlena(int n) {
		// pone elementos en una cola hasta llenarla
		// o alcanzar n, luego agrega 10 elementos más.
		
		System.out.println("----------------------------------------------------");
		System.out.println("Test cola llena \n");
		
		Cola cola = new Cola();
		int i = llenarCola(cola, n);
		
		System.out.println();
		
		System.out.println("Poner otros 10 elementos en la cola");
		System.out.println("imprime true en dinámica, false en estática llena");
		
		for(i = 0; i < 10; i++) {
			System.out.println( (i + 1) + " " + cola.poner(0));
		}
		System.out.println();
	}
	
	public static void testSacarSobreColaVacia(int n) {
		// parte1: sacar n de cola vacía
		// parte2: poner elementos en una cola, sacarlos todos
		// y luego sacar n
		
		System.out.println("----------------------------------------------------");
		
		System.out.println("Test sacar");
		System.out.println("Crear una cola...");
		Cola cola = new Cola();
		System.out.println("esVacia (true): " + cola.esVacia());
		
		System.out.println("Sacar " + n + " de cola nueva (siempre false)...");
		for(int i = 0; i < n; i++) {
			System.out.println(i + " " + cola.sacar());
		}
		System.out.println("esVacia (true): " + cola.esVacia());
		System.out.println();
		
		System.out.println("Poner elementos en la cola");
		llenarCola(cola, 20);
		System.out.println("esVacia (false): " + cola.esVacia());
		System.out.println();
		
		System.out.print("Sacar todos los elementos uno a uno... ");
		vaciarCola(cola);
		System.out.println("vaciado");
		System.out.println("esVacia (true): " + cola.esVacia());
		System.out.println();
		
		System.out.println("Sacar " + n + " de cola (siempre false)...");
		for(int i = 0; i < n; i++) {
			System.out.println(i + " " + cola.sacar());
		}
		System.out.println("esVacia (true): " + cola.esVacia());
		System.out.println();
		
		System.out.println("Poner elementos en la cola");
		llenarCola(cola, 20);
		System.out.println("esVacia (false): " + cola.esVacia());
		System.out.println();
		
		System.out.print("Vaciar la cola con método vaciar... ");
		cola.vaciar();
		System.out.println("vaciado");
		System.out.println("esVacia (true): " + cola.esVacia());
		
		System.out.println("Sacar " + n + " de cola (siempre false)");
		for(int i = 0; i < n; i++) {
			System.out.println(i + " " + cola.sacar());
		}
		System.out.println("esVacia (true): " + cola.esVacia());
	}
}
