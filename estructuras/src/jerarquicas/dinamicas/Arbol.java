package jerarquicas.dinamicas;

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
        
        // inseción válida solo si padre existe en este arbol
        if(nodoPadre != null) {
            // crear el nuevo nodo
            NodoArbol nuevoHijo = new NodoArbol(elem, null, null);
            
            // encontrar su posición (hijoIzquierdo o uno de los derechos)
            // e insertar
            NodoArbol hijo = nodoPadre.getHijoIzquierdo();
            if(hijo == null) {
                nodoPadre.setHijoIzquierdo(nuevoHijo);
            }
            else {
                while(hijo.getHermanoDerecho() != null) {
                    hijo = hijo.getHermanoDerecho();
                }
                // sale del while solo si hijo es el último hijo derecho
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
    
    public Object padre(Object elem) {
        // retorna el elemento que es padre de elem o null
        // si elem no se encuentra
        NodoArbol padre = padre(raiz, elem);
        return padre != null? padre.getElem() : null;
    }
    
    public NodoArbol padre(NodoArbol subarbol, Object elem) {
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

    public String toString() {
        // retorna una representación gráfica del arbol
        return toString(raiz, " ", "r");
    }
    
    private String toString(NodoArbol subarbol, String prefijo, String pos) {
        String rep = "";
        if(subarbol != null) {
            // añadir padre
            rep += prefijo + "-------" + pos + " " + subarbol.getElem().toString() + "\n";
            
            // añadir hijo izquierdo
            NodoArbol hijo = subarbol.getHijoIzquierdo();
            rep += toString(hijo, prefijo + "\t|", "i0");
            
            // añadir hijos derechos
            if(hijo != null) hijo = hijo.getHermanoDerecho();
            int i = 0;
            while(hijo != null) {
                i++;
                String spos = Integer.toString(i);
                // si no es el último hijo
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
