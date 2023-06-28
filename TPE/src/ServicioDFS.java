import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ServicioDFS {

	private Grafo<?> grafo;
	private HashMap<Integer, String> visitados;


	public ServicioDFS(Grafo<?> grafo) {
		this.grafo = grafo;
		this.visitados = new HashMap<>();
	}
		
	public List<Integer> dfsForest() {
		// obtener vertices
		Iterator<Integer> verticesIterator = this.grafo.obtenerVertices();

		// recorrer los vertices marcando cada uno de color blanco
		while(verticesIterator.hasNext()){
			Integer verticeId = verticesIterator.next();
			this.visitados.put(verticeId, Estado.BLANCO.toString());
		}
		verticesIterator = this.grafo.obtenerVertices();
		
		// volver a recorrer los vertices preguntando si su color es blanco
		// y en tal caso visitarlos 
		List<Integer> visitadosDfs = new ArrayList<Integer>();
		while(verticesIterator.hasNext()){
			Integer verticeId = verticesIterator.next();
			if(this.visitados.get(verticeId) == Estado.BLANCO.toString()){
				visitadosDfs.addAll(dfsVisit(verticeId));
			}
		}
		
		return visitadosDfs;
	}

	private List<Integer> dfsVisit(Integer verticeActual){
		List<Integer> verticesVisitados = new ArrayList<Integer>();
		// pintar el vertice de color amarillo 
		this.visitados.put(verticeActual, Estado.AMARILLO.toString());
		// construyo los visitados antes de la recursión, esto me permite 
		// mantener el orden de visita en el arreglo
		verticesVisitados.add(verticeActual);
		
		// obtener los adyacentes del verticeActual
		Iterator<Integer> adyacentesActuales = this.grafo.obtenerAdyacentes(verticeActual);

		// recorrer los adyacentes del verticeActual que sean de color blanco
		while(adyacentesActuales.hasNext()){
			Integer idVerticeAdyacente = adyacentesActuales.next();
			if(this.visitados.get(idVerticeAdyacente) == Estado.BLANCO.toString()){
				// como respuesta quiero obtener los vertices visitados a partir
				// del adyacente dado
				verticesVisitados.addAll(dfsVisit(idVerticeAdyacente));
			}
		}
		// luego de recorrer todos los vertices adyacentes este vertice esta "agotado" 
		this.visitados.put(verticeActual, Estado.NEGRO.toString());
		// (y lo agregamos al arrego de vertices visitados que va a tener los visitados
		// al visitar los adyacentes )

		
		return verticesVisitados;
	}
}
