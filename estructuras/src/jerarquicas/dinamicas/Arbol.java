package jerarquicas.dinamicas;

import lineales.dinamicas.Cola;
import lineales.dinamicas.Lista;

public class Arbol {

    private NodoArbol raiz;
    
    public Arbol() {
        this.raiz = null;
    }
    
    public boolean insertar(Object elem, Object padre) {
        boolean insertado = false;
        
        NodoArbol nodoPadre = null;
        if(raiz == null) {
            raiz = new NodoArbol(elem, null, null);
            insertado = true;
        }
        else {
            nodoPadre = encontrarNodo(raiz, padre);
        }
        
        // inseci�n v�lida solo si padre existe en este arbol
        if(nodoPadre != null) {
            // crear el nuevo nodo
            NodoArbol nuevoHijo = new NodoArbol(elem, null, null);
            
            // encontrar su posici�n (hijoIzquierdo o uno de los derechos)
            // e insertar
            NodoArbol hijo = nodoPadre.getHijoIzquierdo();
            if(hijo == null) {
                nodoPadre.setHijoIzquierdo(nuevoHijo);
            }
            else {
                while(hijo.getHermanoDerecho() != null) {
                    hijo = hijo.getHermanoDerecho();
                }
                // sale del while solo si hijo es el �ltimo hijo derecho
                hijo.setHermanoDerecho(nuevoHijo);
            }
            insertado = true;
        }
        return insertado;
    }
    
    private NodoArbol encontrarNodo(Object elem) {
        // retorna el nodo de este arbol que contiene a elem
        // o null si no se encuentra
        return encontrarNodo(raiz, elem);
    }
    
    private NodoArbol encontrarNodo(NodoArbol subarbol, Object elem) {
        // retorna el nodo de subarbol que contiene a elem
        // o null si no se encuentra
        // si hay varias instancias de elem retorna la primera
        // encontrada en recorrido preorden
        
        // caso base 1: subarbol es null, salta el if
        NodoArbol buscado = null;
        
        if(subarbol != null) {
            
            if(subarbol.getElem().equals(elem)){
                // caso base 2: encontrado!!!
                buscado = subarbol;
            }
            else {
                // caso recursivo, buscar en los hijos
                NodoArbol hijo = subarbol.getHijoIzquierdo();
            
                // mientras queden hijos, buscar hasta encontrar elem
                while(hijo != null && buscado == null) {
                    buscado = encontrarNodo(hijo, elem);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
        return buscado;
    }
    
    public boolean pertenece(Object elem) {
        return this.encontrarNodo(elem) != null;
    }

    public boolean esVacio() {
        return raiz == null;
    }
    
    public void vaciar() {
        raiz = null;
        // al setear raiz null, el garbage collector se lleva la ra�z.
        // hijoIzquirdo queda sin referencia, luego es reclamado dejando
        // sin referencia a su hijoIzquierdo y su hermanoDerecho.
        // cada hermanoDerecho al ser reclamado deja sin referencia a su
        // hermanoDerecho, eventualmente vaciando todo el primer nivel.
        // cada hermanoDerecho reclamado deja sin referencia su hijoIzuierdo
        // eventualmente todo el arbol es reclamado.
    }
    
    public Object padre(Object elem) {
        // retorna el elemento que es padre de elem o null
        // si elem no se encuentra
        NodoArbol padre = padre(raiz, elem);
        return padre != null? padre.getElem() : null;
    }
    
    private NodoArbol padre(NodoArbol subarbol, Object elem) {
        // retorna el nodo que es padre de elem, o null si elem
        // no pertenece a subarbol
        // recorre el arbol en preorden
        
        // caso base 1: subarbol null
        NodoArbol padre = null;
        
        if(subarbol != null) {
            NodoArbol hijo = subarbol.getHijoIzquierdo();
            while(hijo != null && padre == null) {
                if(hijo.getElem().equals(elem)) {
                    // caso base 2: encontrado
                    padre = subarbol;
                }
                else {
                    // caso recursivo, buscar en subarbol hijo
                    padre = padre(hijo, elem);
                }
                // avanzar al siguiente subarbol hijo
                hijo = hijo.getHermanoDerecho();
            }
        }
        
        return padre;
    }

    public int nivel(Object elem) {
        // retorna el nivel donde se encuentra elem dentro de este arbol
        return nivel(raiz, elem);
    }
    
    private int nivel(NodoArbol subarbol, Object elem) {
        // retorna el nivel del subarbol donde se encuentra elem
        
        // caso base 1: subarbol vac�o
        int nivel = -1;
        
        if(subarbol != null) {
            // caso base 2: encontrado
            if(subarbol.getElem().equals(elem)) {
                nivel++;
            }
            // caso recursivo: buscar en los hijos
            NodoArbol hijo = subarbol.getHijoIzquierdo();
            int nivelHijo = nivel(hijo, elem);
            while(nivelHijo == -1 && hijo != null) {
                hijo = hijo.getHermanoDerecho();
                nivelHijo = nivel(hijo, elem);
            }
            if(nivelHijo != -1) {
                nivel = nivelHijo + 1;
            }
        }
        
        return nivel;
    }
    
    public int altura() {
        // retorna la altura de este arbol
        return altura(raiz);
    }
    
    private int altura(NodoArbol subarbol) {
        // retorna la altura del subarbol
        
        // caso base, arbol vac�o tiene altura -1
        int altura = -1;
        
        if(subarbol != null) {
            NodoArbol hijo = subarbol.getHijoIzquierdo();
            while(hijo != null) {
                int alturaHijo = altura(hijo);
                if(alturaHijo > altura) {
                    altura = alturaHijo;
                }
                hijo = hijo.getHermanoDerecho();
            }
            altura++;
        }
        return altura;
    }
    
    public Lista obtenerAncestros(Object elem) {
        // retorna la lista de ancestros de elem
        Lista lista = obtenerAncestros(raiz, elem);
        if(lista == null) {
            lista = new Lista();
        }
        return lista;
    }
    
    private Lista obtenerAncestros(NodoArbol subarbol, Object elem) {
        // retorna la lista de ancestros desde subarbol hasta elem
        // si elem se encuentra en subarbol, null en caso contrario
        
        // caso base 1: subarbol null
        Lista lista = null;
        if(subarbol != null) {
            
            // caso base 2: encontrado
            if(subarbol.getElem().equals(elem)) {
                lista = new Lista();
                lista.insertar(subarbol.getElem(), 1);
            }
            else {
                NodoArbol hijo = subarbol.getHijoIzquierdo();
                lista = obtenerAncestros(hijo, elem);
                if(hijo != null) hijo = hijo.getHermanoDerecho();
                while(lista == null && hijo != null) {
                    lista = obtenerAncestros(hijo, elem);
                    hijo = hijo.getHermanoDerecho();
                }
                if(lista != null) {
                    lista.insertar(subarbol.getElem(), 1);
                }
            }
        }
        return lista;
    }
    
    public Arbol clone() {
        // retorna una copia superficial de este arbol
        Arbol clon = new Arbol();
        clon.raiz = clone(this.raiz);
        return clon;
    }
    
    private NodoArbol clone(NodoArbol subarbol) {
        // retorna una copia superficial de subarbol
        
        // caso base 1: subarbol null, nada que copiar
        NodoArbol copia = null;
        
        if(subarbol != null) {
            // copiar el elemento padre
            copia = new NodoArbol(subarbol.getElem(), null, null);
            
            // copiar hijo izquierdo
            NodoArbol hijo = subarbol.getHijoIzquierdo();
            NodoArbol copiaHijo = clone(hijo);
            copia.setHijoIzquierdo(copiaHijo);
            
            // copiar hijos derechos
            if(hijo != null) hijo = hijo.getHermanoDerecho();
            while(hijo != null) {
                
                // copiar el hijo
                copiaHijo.setHermanoDerecho(clone(hijo));
                
                // avanzar
                hijo = hijo.getHermanoDerecho();
                copiaHijo = copiaHijo.getHermanoDerecho();
            }
        }
        return copia;
    }
    
    // En los recorridos de arbol se usa insertar(1) en listas porque es
    // m�s eficiente que insertar() general, dada la implementaci�n de lista
    // con cabecera. Esto genera el inconveniente de que la lista tiene los
    // elementos en el orden inverso, por lo antes de retornar la lista 
    // se debe invertir. El orden de los recorridos es O(2n) = O(n).
    
    // Esto sigue siendo m�s eficiente que usar insetar() general, que da 
    // O(n^2) para los recorridos.
    
    public Lista porNiveles() {
        // retorna una lista conteniendo el recorrido por niveles de este arbol
        Lista lista = new Lista();
        Cola cola = new Cola();
        
        if(raiz != null) {
            cola.poner(raiz);
        }
        while(!cola.esVacia()) {
            // sacar el siguiente nodo y ponerlo en la lista
            NodoArbol siguiente = (NodoArbol) cola.obtenerFrente();
            cola.sacar();
            lista.insertar(siguiente.getElem(), 1);
            
            // encolar los hijos del nodo sacado
            NodoArbol hijo = siguiente.getHijoIzquierdo();
            while(hijo != null) {
                cola.poner(hijo);
                hijo = hijo.getHermanoDerecho();
            }
        }
        lista.invertirInPlace();
        return lista;
    }
    
    public Lista preorden() {
        // retorna una lista de los elementos en el arbol en recorrido preorden
        Lista lista = new Lista();
        preorden(raiz, lista);
        lista.invertirInPlace();
        return lista;
    }
    
    private void preorden(NodoArbol subarbol, Lista lista) {
        // inserta los elementos de subarbol en lista en recorrido preorden
        
        // caso base 1: no hay nodos que insertar
        
        if(subarbol != null) {
            // insertar padre
            lista.insertar(subarbol.getElem(), 1);
            
            // insertar los hijos
            NodoArbol hijo = subarbol.getHijoIzquierdo();
            preorden(hijo, lista);
            while(hijo != null) {
                hijo = hijo.getHermanoDerecho();
                preorden(hijo, lista);
            }
        }
    }
    
    public Lista inorden() {
        // retorna una lista de los elementos en este arbol en recorrido inorden
        Lista lista = new Lista();
        inorden(raiz, lista);
        lista.invertirInPlace();
        return lista;
    }
    
    private void inorden(NodoArbol subarbol, Lista lista) {
        // inserta los elementos de subarbol en lista en recorrido inorden
        
        // caso base 1: no hay nodos que insertar
        
        if(subarbol != null) {
            // insertar hijo izquierdo
            NodoArbol hijo = subarbol.getHijoIzquierdo();
            inorden(hijo, lista);
            
            // insertar padre
            lista.insertar(subarbol.getElem(), 1);
            
            // insertar los hijos derechos
            while(hijo != null) {
                hijo = hijo.getHermanoDerecho();
                inorden(hijo, lista);
            }
        }
    }
    
    public Lista posorden() {
        // retorna una lista de los elementos en este arbol en recorrido posorden
        Lista lista = new Lista();
        posorden(raiz, lista);
        lista.invertirInPlace();
        return lista;
    }
    
    private void posorden(NodoArbol subarbol, Lista lista) {
        // inserta los elementos de subarbol en lista en recorrido posorden
        
        // caos base 1: no hay nodos que insertar
        
        if(subarbol != null) {
            // insertar hijos
            NodoArbol hijo = subarbol.getHijoIzquierdo();
            posorden(hijo, lista);
            while(hijo != null) {
                hijo = hijo.getHermanoDerecho();
                posorden(hijo, lista);
            }
            
            // insertar padre
            lista.insertar(subarbol.getElem(), 1);
        }
    }
    
    public String toString() {
        // retorna una representaci�n gr�fica del arbol
        String rep;
        if(raiz == null) {
            rep = "(arbol vac�o)";
        }
        else {
            rep = toString(raiz, " ", "r");
        }
        return rep;
    }
    
    private String toString(NodoArbol subarbol, String prefijo, String pos) {
        String rep = "";
        if(subarbol != null) {
            // a�adir padre
            rep += prefijo + "-------" + pos + " " + subarbol.getElem().toString() + "\n";
            
            // a�adir hijo izquierdo
            NodoArbol hijo = subarbol.getHijoIzquierdo();
            rep += toString(hijo, prefijo + "\t|", "i0");
            
            // a�adir hijos derechos
            if(hijo != null) hijo = hijo.getHermanoDerecho();
            int i = 0;
            while(hijo != null) {
                i++;
                String spos = Integer.toString(i);
                // si no es el �ltimo hijo
                if(hijo.getHermanoDerecho() != null) {
                    rep += toString(hijo, prefijo + "\t|", "m" + spos);
                }
                else {
                    rep += toString(hijo, prefijo + "\t ", "d" + spos);
                }
                hijo = hijo.getHermanoDerecho();
            }
        }
        return rep;
    }


}
