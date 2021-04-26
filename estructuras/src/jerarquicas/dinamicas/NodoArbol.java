package jerarquicas.dinamicas;

public class NodoArbol {
    
    private Object elem;
    private NodoArbol hijoIzquierdo;
    private NodoArbol hermanoDerecho;
    
    public NodoArbol(Object elem, NodoArbol hijo, NodoArbol hermano) {
        this.elem = elem;
        this.hijoIzquierdo = hijo;
        this.hermanoDerecho = hermano;
    }
    
    public Object getElem() {
        return elem;
    }
    
    public NodoArbol getHijoIzquierdo() {
        return hijoIzquierdo;
    }
    
    public NodoArbol getHermanoDerecho() {
        return hermanoDerecho;
    }
    
    public void setElem(Object elem) {
        this.elem = elem;
    }
    
    public void setHijoIzquierdo(NodoArbol izquierdo) {
        this.hijoIzquierdo = izquierdo;
    }
    
    public void setHermanoDerecho(NodoArbol derecho) {
        this.hermanoDerecho = derecho;
    }
}
