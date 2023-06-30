import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        // test servicio caminos
        Grafo<Integer> grafo = new GrafoDirigido<>();

        grafo.agregarArco(1, 1, null);
        grafo.agregarArco(1, 2, null);
        grafo.agregarArco(1, 4, null);
        grafo.agregarArco(1, 5, null);
        
        grafo.agregarArco(2, 3, null);
        grafo.agregarArco(2, 4, null);
        grafo.agregarArco(2, 6, null);
        
        grafo.agregarArco(3, 6, null);

        grafo.agregarArco(5, 1, null);
        
        grafo.agregarArco(6, 5, null);
        
        ServicioCaminos service = new ServicioCaminos(grafo, 1, 5, 100);
        List<List<Integer>> caminos = service.caminos();
        System.out.println(caminos);
    }
}
