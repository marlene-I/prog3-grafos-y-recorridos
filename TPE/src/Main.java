import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {

		
		String path = "./TPE/datasets/dataset1.txt";
		CSVReader reader = new CSVReader(path);
		GrafoDirigido<Integer> grafo = reader.read();
		

		Backtracking back = new Backtracking(grafo.obtenerListArcos(), grafo.obtenerListVertices());
		List<Arco<Integer>> solBack = back.backtracking();
		
		System.out.println("Backtracking");
		System.out.println("Solucion:");

		List<String> printableArcos = new ArrayList<>();

		for (Arco<Integer> arco : solBack) {
			int origen = arco.getVerticeOrigen();
			int destino = arco.getVerticeDestino();
			printableArcos.add("E" + origen + "-E" + destino);
		}

		System.out.println(String.join(", ", printableArcos));
		System.out.println("Kilometros Totales: " + back.getKms());
		System.out.println("Metrica: " + back.getMetrica());
		System.out.println("");



		Greedy greedy = new Greedy(grafo.obtenerListVertices());

		List<Arco<Integer>> solGreedy = greedy.greedy(grafo.obtenerListArcos());

		  
		System.out.println("Greedy");
		System.out.println("Solucion:");

		printableArcos = new ArrayList<>();

		for (Arco<Integer> arco : solGreedy) {
			int origen = arco.getVerticeOrigen();
			int destino = arco.getVerticeDestino();
			printableArcos.add("E" + origen + "-E" + destino);
		}

		System.out.println(String.join(", ", printableArcos));
		System.out.println("Kilometros Totales: " + greedy.getKms());
		System.out.println("Metrica: " + greedy.getMetrica());
		System.out.println("");

	}

	
}
 