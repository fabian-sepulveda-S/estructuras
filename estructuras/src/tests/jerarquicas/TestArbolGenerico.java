package tests.jerarquicas;

import jerarquicas.dinamicas.Arbol;

public class TestArbolGenerico {

    public static void main(String[] args) {
        testPertenece(10);
    }
    
    public static void testPertenece(int n) {
        System.out.println("-------------------------------------------------");
        System.out.println("Test pertenece");
        System.out.println("Crear arbol vacío");
        Arbol arbol = new Arbol();
        System.out.println("pertenece en arbol vacío da false");
        for(int i = 0; i < n; i++) {
            System.out.println("pertenece " + i + ": " + arbol.pertenece(i));
        }
    }
}
