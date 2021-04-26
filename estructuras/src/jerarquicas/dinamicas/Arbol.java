package jerarquicas.dinamicas;

public class Arbol {

    private NodoArbol raiz;
    
    public Arbol() {
        this.raiz = null;
    }
    
    public boolean insertar(Object elem, Object padre) {
        boolean insertado = false;
        // TODO: implementar
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
}
