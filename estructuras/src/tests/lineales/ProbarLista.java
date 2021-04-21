package tests.lineales;

import lineales.dinamicas.Lista;
import lineales.dinamicas.Pila;
import lineales.estaticas.Cola;

public class ProbarLista {
	
	public static void main(String[] args) {
		probarEsCapicua();
	}
	
	public static void probarContar() {
		
	}
	
	public static void probarEsCapicua() {
		Lista listas = new Lista();
		
		// cadenas true
		listas.insertar(stringALista(""), 1);
		listas.insertar(stringALista("1"), 1);
		listas.insertar(stringALista("11"), 1);
		listas.insertar(stringALista("111"), 1);
		listas.insertar(stringALista("121"), 1);
		listas.insertar(stringALista("1221"), 1);
		listas.insertar(stringALista("23455432"), 1);
		listas.insertar(stringALista("234565432"), 1);
		
		// cadenas false
		listas.insertar(stringALista("2354235"), 1);
		listas.insertar(stringALista("2354"), 1);
		listas.insertar(stringALista("345095"), 1);
		listas.insertar(stringALista("222224"), 1);
		
		while(!listas.esVacia()) {
			Lista lista = (Lista) listas.recuperar(1);
			listas.eliminar(1);
			System.out.println("cadena    : " + lista.toString());
			System.out.println("es capicua: " + esCapicua(lista));
			System.out.println();
		}
		
	}
	
	public static void probarInvertir() {
		
		Lista listas = new Lista();
		// crear una lista de listas de tamaño 0 a 9
		for(int i = 0; i < 10; i++) {
			Lista lista = new Lista();
			for(int j = 0; j < i; j++) {
				lista.insertar(j, 1);
			}
			listas.insertar(lista, 1);
		}
		
		// imprimir cada lista con su inversa
		while(!listas.esVacia()) {
			Lista lista = (Lista) listas.recuperar(1);
			listas.eliminar(1);
			System.out.println(lista.toString());
			System.out.println(invertir(lista).toString());
			System.out.println();
		}
	}
	
	public static void probarComprobar() {
		Lista cadenas = new Lista();
		
		// cadenas true
		cadenas.insertar(stringALista("00"), 1);
		cadenas.insertar(stringALista("10101"), 1);
		cadenas.insertar(stringALista("12012021"), 1);
		cadenas.insertar(stringALista("11101110111"), 1);
		cadenas.insertar(stringALista("12301230321"), 1);
		
		// cadenas false
		// sin 0
		cadenas.insertar(stringALista(""), 1);
		cadenas.insertar(stringALista("1"), 1);
		
		// con un 0
		cadenas.insertar(stringALista("0"), 1);
		cadenas.insertar(stringALista("1110"), 1);
		cadenas.insertar(stringALista("0111"), 1);
		
		// con dos 0
		cadenas.insertar(stringALista("110110"), 1);
		cadenas.insertar(stringALista("12012012"), 1);
		cadenas.insertar(stringALista("001"), 1);
		cadenas.insertar(stringALista("011011"), 1);
		cadenas.insertar(stringALista("12300123"), 1);
		
		
		while(!cadenas.esVacia()) {
			Lista temp = (Lista) cadenas.recuperar(1);
			cadenas.eliminar(1);
			System.out.println("cadena    : " + temp.toString());
			System.out.println("comprobada: " + comprobar(temp));
			System.out.println();
		}
	}
	
	public static void probarIntercalar() {
		Lista pares = new Lista();
		pares.insertar(stringALista(""), 1);
		pares.insertar(stringALista(""), 1);
		pares.insertar(stringALista("1"), 1);
		pares.insertar(stringALista("2"), 1);
		pares.insertar(stringALista("135"), 1);
		pares.insertar(stringALista("246"), 1);
		pares.insertar(stringALista("135"), 1);
		pares.insertar(stringALista("2468"), 1);
		pares.insertar(stringALista("1357"), 1);
		pares.insertar(stringALista("246"), 1);
		pares = invertir(pares);
		while(!pares.esVacia()) {
			Lista lista1 = (Lista) pares.recuperar(1);
			pares.eliminar(1);
			Lista lista2 = (Lista) pares.recuperar(1);
			pares.eliminar(1);
			System.out.println("lista1:      " + lista1.toString());
			System.out.println("lista2:      " + lista2.toString());
			System.out.println("intercalado: " + intercalar(lista1, lista2).toString());
			System.out.println();
		}
	}
	
	public static void probarConcatenar() {
		
		Lista lista1 = new Lista();
		for(int i = 10; i > 0; i--) {
			lista1.insertar(i, 1);
		}
		Lista lista2 = new Lista();
		for(int i = 15; i > 10; i--) {
			lista2.insertar(i, 1);
		}
		Lista concat = concatenar(lista1, lista2);
		System.out.println(lista1);
		System.out.println(lista2);
		System.out.println(concat);
	}

	public static Lista concatenar(Lista lista1, Lista lista2) {
		// clonar las listas para no alterar los originales
		lista1 = lista1.clone();
		lista2 = lista2.clone();
		
		Lista concat = new Lista();
		
		// recuperar(1) e insertar(1) son de O(1), así que nos gustaría
		// usarlos en la concatenación.
		// recuperar(1) se puede usar para obtener los elementos de las listas
		// a concatenar, pero si lo usamos junto con insertar(1), la lista
		// contatenada termina con orden inverso
		
		// para volver a invertir, primero vaciamos lista1 y lista2 en una pila
		Pila pila = apilarLista(lista2);
		desapilarSobreLista(pila, concat);
		pila = apilarLista(lista1);
		desapilarSobreLista(pila, concat);
		
		// tanto apilarLista como desapilarSobreLista son de O(n) ya que recorren
		// las estructuras completas una vez. Además, cada método copia las
		// estructuras que recibe por parámetro para no alterarlas.
		
		// En total, cada elemento de lista1 y de lista2 es recorrido 5 veces:
		// -una vez al inicio de concatenar, al ser clonadas
		// -dos veces en apilarLista: 1 clonar y 1 recorrer
		// -dos veces en desapilarSobreLista: 1 clonar y 1 recorrer
		
		// en total, el orden de concatenar es de O(5n) = O(n)
		return concat;
		
	}
		
	public static Pila apilarLista(Lista lista) {
		// agrega los elementos de lista en orden sobre una pila,
		// invirtiendo la lista original
		
		// clonamos para no alterar el parámetro
		lista = lista.clone();
		
		Pila pila = new Pila();
		while(!lista.esVacia()) {
			pila.apilar(lista.recuperar(1));
			lista.eliminar(1);
		}
		return pila;
	}
	
	public static Lista desapilarSobreLista(Pila pila, Lista lista) {
		// desapila la pila sobre una lista, insertando
		// cada elemento desapilado al inicio de la lista
		
		// clonar para no alterar la estructura original
		pila = pila.clone();
		while(!pila.esVacia()) {
			lista.insertar(pila.obtenerTope(), 1);
			pila.desapilar();
		}
		return lista;
	}

	public static boolean comprobar(Lista lista) {
		// comprueba que la cadena contenida en lista tiene forma
		// cadena0cadena0cadena', donde cadena' es el inverso de cadena
		
		// clonamos para trabajar con la lista sin alterar original
		lista = lista.clone();
		boolean comprobado = true;
		
		// sacar elementos de lista y poner en cola, hasta encontrar 0
		Cola cola = new Cola();
		int siguiente;
		while(!lista.esVacia() && (siguiente = (int) lista.recuperar(1)) != 0) {
			cola.poner(siguiente);
			lista.eliminar(1);
		}
		if(lista.esVacia()) comprobado = false;
		else lista.eliminar(1);
		
		// sacar elementos de la lista y apilar en pila
		// mientras se comparan con cola
		Pila pila = new Pila();
		while(comprobado && !lista.esVacia() && !cola.esVacia()
			&& (siguiente = (int) lista.recuperar(1)) != 0) {
			pila.apilar(siguiente);
			comprobado = (int) cola.obtenerFrente() == siguiente;
			cola.sacar();
			lista.eliminar(1);
		}
		if(lista.esVacia()) comprobado = false;
		else lista.eliminar(1);
		if(!cola.esVacia()) comprobado = false;
		
		// sacar los elementos restantes mientras se comparan con la pila
		while(comprobado && !lista.esVacia() && !pila.esVacia()) {
			siguiente = (int) lista.recuperar(1);
			comprobado = (int) pila.obtenerTope() == siguiente;
			pila.desapilar();
			lista.eliminar(1);
		}
		if(!pila.esVacia()) comprobado = false;
		if(!lista.esVacia()) comprobado = false;
		return comprobado;
	}
	
	public static Lista stringALista(String s) {
		// almacena los caracteres de s en una lista
		int len = s.length();
		Lista lista = new Lista();
		for(int i = len - 1; i >= 0; i--) {
			lista.insertar(Integer.parseInt(s.charAt(i) + ""), 1);
		}
		return lista;
	}
	
	public static Lista invertir(Lista lista) {
		// devuelve una lista invertida
		
		// clonar para no alterar la estructura original
		lista = lista.clone();
		
		Lista invertida = new Lista();
		
		while(!lista.esVacia()) {
			invertida.insertar(lista.recuperar(1), 1);
			lista.eliminar(1);
		}
		return invertida;
	}
	
	public static Lista intercalar(Lista lista1, Lista lista2) {
		// retorna una lista con los elementos de lista1 y lista2 intercalados
		
		// clonar para no alterar las estructuras originales
		lista1 = lista1.clone();
		lista2 = lista2.clone();

		// apilar los elementos ya que insertar(1) es más eficiente pero
		// invierte el orden
		Pila pila = new Pila();
		while(!lista1.esVacia() && !lista2.esVacia()) {
			pila.apilar(lista1.recuperar(1));
			pila.apilar(lista2.recuperar(1));
			lista1.eliminar(1);
			lista2.eliminar(1);
		}
		while(!lista1.esVacia()) {
			pila.apilar(lista1.recuperar(1));
			lista1.eliminar(1);
		}
		while(!lista2.esVacia()) {
			pila.apilar(lista2.recuperar(1));
			lista2.eliminar(1);
		}
		// finalmente desapilar
		Lista lista = new Lista();
		while(!pila.esVacia()) {
			lista.insertar(pila.obtenerTope(), 1);
			pila.desapilar();
		}
		return lista;
	}

	public static int contar(Lista lista, Object elem) {
		// cuenta las veces que aparece elem en lista
		
		// clonar para no alterar la estructura original
		lista = lista.clone();
		
		int count = 0;
		while(!lista.esVacia()) {
			if(lista.recuperar(1).equals(elem)) {
				count++;
			}
			lista.eliminar(1);
		}
		return count;
	}
	
	public static boolean esCapicua(Lista lista) {
		lista = lista.clone();
		
		int count = 0;
		Pila pila = new Pila();
		// apilar los elementos de la lista en una pila mientras se cuentan
		while(!lista.esVacia()) {
			pila.apilar(lista.recuperar(1));
			lista.eliminar(1);
			count++;
		}
		int mitad = count / 2;
		boolean esImpar = count % 2 == 1;
		
		Pila reversa = new Pila();
		// apilar la mitad de la pila en reversa
		for(int i = 0; i < mitad; i++) {
			reversa.apilar(pila.obtenerTope());
			pila.desapilar();
		}
		if(esImpar) {
			pila.desapilar();
		}

		// comparar pila y reversa
		boolean capicua = true;
		while(capicua && !pila.esVacia()) {
			Object elem1 = pila.obtenerTope();
			Object elem2 = reversa.obtenerTope();
			capicua = elem1.equals(elem2);
			pila.desapilar();
			reversa.desapilar();
		}
		
		return capicua;
	}
}
