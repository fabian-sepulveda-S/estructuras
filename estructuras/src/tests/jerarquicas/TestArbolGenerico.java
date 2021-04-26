package tests.jerarquicas;

import jerarquicas.dinamicas.Arbol;

public class TestArbolGenerico {

    public static void main(String[] args) {
        // testPertenece(4,2);
        // testMostrarArbolCompleto(3,3);
        testPadre(4, 2);
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
    
    public static void testPadre(int grado, int altura) {
        System.out.println("-------------------------------------------------");
        System.out.println("Test padre");
        System.out.println("Crear un arbol completo de grado " + grado + " y altura " + altura + ":");
        Arbol arbol = new Arbol();
        int cantidad = testCargarArbolCompletoPorNivel(arbol, grado, altura);
        System.out.println(arbol.toString());
        System.out.println();
        
        System.out.println("Por cada elemento mostrar su padre: ");
        for(int i = 1; i <= cantidad; i++) {
            Object padre = arbol.padre(i);
            System.out.println("padre de " + i + ": " + (padre == null ? "null" : padre.toString()));
        }
        System.out.println();
        
        System.out.println("para elementos que no pertenecen al arbol retorna null: ");
        for(int i = cantidad + 1; i <= 2 * cantidad; i++) {
            Object padre = arbol.padre(i);
            System.out.println("padre de " + i + ": " + (padre == null ? "null" : padre.toString()));
        }
        System.out.println();
    }
    
    public static void testPertenece(int grado, int altura) {
        System.out.println("-------------------------------------------------");
        System.out.println("Test pertenece");
        
        System.out.println("Crear arbol vacío");
        Arbol arbol = new Arbol();
        System.out.println();
        
        System.out.println("pertenece en arbol vacío da false");
        for(int i = 0; i < 10; i++) {
            System.out.println("pertenece " + i + ": " + arbol.pertenece(i));
        }
        System.out.println();
        
        System.out.println("Poblar el arbol");
        int cantidad = testCargarArbolCompletoPreorden(arbol, grado, altura);
        int cantidadElementos = 1 + grado * ((int) Math.pow(grado, altura) - 1) / (grado - 1);
        System.out.println(arbol.toString());
        System.out.println("cantidad de elementos: " + cantidad + " " + cantidadElementos);
        System.out.println();
        
        System.out.println("por cada elemento de 1 a " + cantidad + " pertenece da true:");
        for(int i = 1; i <= cantidad; i++) {
            System.out.println("pertenece " + i + ": " + arbol.pertenece(i));
        }
        System.out.println();
        
        System.out.println("elementos fuera de rango da false: ");
        for(int i = -1; i >= -cantidad; i--) {
            System.out.println("pertenece " + i + ": " + arbol.pertenece(i));
        }
        for(int i = cantidad + 1; i <= 2 * cantidad; i++) {
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

    public static int testCargarArbolCompletoPorNivel(Arbol arbol, int grado, int altura) {
        
        // formula para padre deducida de mirar arboles completos de grado 2, 3,
        // 4 y altura 3
        // después de probar con grados y alturas mayores, parece ser que
        // se cumple en general :)
        // TODO: buscar demostración de que la formula se cumple en general
        
        // formula de la progresión geométrica
        int cantidadElementos = 1 + grado * ((int) Math.pow(grado, altura) - 1) / (grado - 1);
        
        for(int i = 1; i <= cantidadElementos; i++) {
            int padre = (i + grado - 2) / grado;
            System.out.print("insertar " + i + ", padre " + padre + ": ");
            System.out.println(arbol.insertar(i, padre));
        }
        return cantidadElementos;
    }
}
