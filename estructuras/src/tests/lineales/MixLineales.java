package tests.lineales;

import lineales.dinamicas.Cola;
import lineales.dinamicas.Pila;

public class MixLineales {

	public static void main(String[] args) {
		Cola[] cola = crearArregloCola(20);
		for(Cola c: cola) {
			System.out.println(cola2String(c));
			System.out.println(cola2String(crearOtraCola(c)));
			System.out.println();
		}
	}
	
	public static Cola crearOtraCola(Cola c) {
		c = c.clone();
		Cola otra = new Cola();
		Pila pila = new Pila();
		char elem = '0'; // incializar solo para tranquilizar al compilador
		while(!c.esVacia()) {
			// copiar elementos hasta vaciar la cola o encontrar '$'
			while(!c.esVacia() && (elem = (char) c.obtenerFrente()) != '$') {
				otra.poner(elem);
				pila.apilar(elem);
				c.sacar();
			}
			// vaciar pila sobre otra
			Object elemReverso;
			while(!pila.esVacia()) {
				elemReverso = (char) pila.obtenerTope();
				otra.poner(elemReverso);
				pila.desapilar();
			}
			if(elem == '$') {
				otra.poner(elem);
				c.sacar();
			}
		}
		return otra;
	}
	
	public static Cola[] crearArregloCola(int n) {
		Cola[] cola = new Cola[n];
		for(int i = 0; i < n; i++) {
			cola[i] = crearCola(i);
		}
		return cola;
	}
	
	public static Cola crearCola(int n) {
		// crea las colas a,     a$ab$a, a$ab&abc$ab$a, ...
		// para           n = 0, n = 1,  n = 2,         ...
		Cola cola = new Cola();
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < i + 1; j++) {
				cola.poner(numeroAChar(j));
			}
			cola.poner('$');
		}
		
		for(int j = 0; j < n + 1; j++) {
			cola.poner(numeroAChar(j));
		}
		
		for(int i = n - 1; i > -1; i--) {
			cola.poner('$');
			for(int j = 0; j < i + 1; j++) {
				cola.poner(numeroAChar(j));
			}
		}
		return cola;
	}
	
	public static char numeroAChar(int n) {
		// convierte un numero entre 0 a 25 en el correspondiente char a-z
		// y entre 26 - 51 a A-Z
		char c;
		if(0 <= n && n <= 25) {
			n += 97;
		}
		else if(26 <= n && n <= 51) {
			n += 65;
		}
		c = (char) n;
		return c;
	}
	
	public static String cola2String(Cola c) {
		c = c.clone();
		String rep = "";
		while(!c.esVacia()) {
			rep += (char) c.obtenerFrente();
			c.sacar();
		}
		return rep;
	}
}
