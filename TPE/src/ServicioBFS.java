

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ServicioBFS {

	private Grafo<?> grafo;
	private List<Integer> fila;
	private HashMap<Integer, Boolean> visitados;
	
	public ServicioBFS(Grafo<?> grafo) {
		this.grafo = grafo;
		this.visitados = new HashMap<>();
	}
	
	public List<Integer> bfsForest() {
		// obtener vertices
		Iterator<Integer> verticesIterator = this.grafo.obtenerVertices();
		
		// recorrerlos marcandolos como no visitados (pseudocodigo es boolean)
		while(verticesIterator.hasNext()){
			Integer verticeId = verticesIterator.next();
			this.visitados.put(verticeId, false);
		}

		// instanciar fila
		this.fila = new LinkedList<>();
		List<Integer> recorrido = new ArrayList<>();
		verticesIterator = this.grafo.obtenerVertices();
		// volver a recorrerlos visitandolos si no han sido visitados
		while(verticesIterator.hasNext()){
			Integer verticeId = verticesIterator.next();
			if(this.visitados.get(verticeId) == false){
				recorrido.addAll(this.bfsVisit(verticeId));
			}
		}
		
		return recorrido;
	}

	private List<Integer> bfsVisit(Integer verticeActual){
		// marcar el vertice como visitado
		this.visitados.put(verticeActual, true);
		
		// agregarlo a la fila
		this.fila.add(verticeActual);
		List<Integer> recorrido = new ArrayList<>();
		
		// recorrer la fila hasta que este vacía
		while(fila.size() > 0){
			// quitar el vertice del principio
			Integer verticeRemovido = this.fila.remove(0);
			// al retirar el vertice de la fila 
			// agregarlo al recorrido
			recorrido.add(verticeRemovido);	
			// obtener los adyacentes al removido
			Iterator<Integer> adyacentesActualesIter = this.grafo.obtenerAdyacentes(verticeRemovido);
			
			// recorrer los vertices adyacentes al vertice del principio de la fila
			while(adyacentesActualesIter.hasNext()){
				Integer adyacente =  adyacentesActualesIter.next();
				if(this.visitados.get(adyacente) == false){
					this.visitados.put(adyacente, true);
					// si los vertices adyacentes no han sido recorridos 
					// se los agrega al final de la fila
					this.fila.add(adyacente);
				}
			}
		}
		return recorrido;
	}
	
}
