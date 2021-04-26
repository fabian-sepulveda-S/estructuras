package tests.jerarquicas;

import jerarquicas.dinamicas.Arbol;

public class TestArbolGenerico {

    public static void main(String[] args) {
        testPertenece(10);
        testMostrarArbolCompleto(3,3);
    }
    
    public static void testMostrarArbolCompleto(int grado, int altura) {
        System.out.println("-------------------------------------------------");
        System.out.println("Test arbol completo");
        System.out.println(String.format(
                "crear un arbol de grado %d y altura %d",
                grado, altura));
        Arbol arbol = new Arbol();
        testCargarArbolCompletoPreorden(arbol, grado, altura);
        System.out.println("arbol:");
        System.out.println(arbol.toString());
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
    
    public static int testCargarArbolCompletoPreorden(Arbol arbol, int grado, int altura) {
        // carga un arbol completo con nodos de grado grado en arbol
        return cargarArbolCompletoPreorden(arbol, 0, 0, grado, altura);
    }
    
    public static int cargarArbolCompletoPreorden(Arbol arbol, int padre, int acumulado, int grado, int altura) {
        // carga un arbol completo
        // retorna la cantidad de elementos insertados en la llamada
        int insertados = 0;
        if(altura >= 0) {
            // insertar el padre
            acumulado++;
            int padreActual = acumulado;
            System.out.print("insertar " + acumulado + ", padre " + padre + ": ");
            System.out.println(arbol.insertar(acumulado, padre));
            insertados++;
            
            // insertar los arboles hijos
            for(int i = 0; i < grado; i++) {
                int c = cargarArbolCompletoPreorden(arbol, padreActual, acumulado, grado, altura - 1);
                insertados += c;
                acumulado += c;
            }
        }
        return insertados;
    }
}
