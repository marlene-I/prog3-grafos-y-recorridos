

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ServicioCaminos {

	private Grafo<?> grafo;
	private int origen;
	private int destino;
	private int lim;
	private List<Integer> visitados;
	private List<List<Integer>> caminos;
	
	// Servicio caminos
	public ServicioCaminos(Grafo<?> grafo, int origen, int destino, int lim) {
		this.grafo = grafo;
		this.origen = origen;
		this.destino = destino;
		this.lim = lim; // limite de arcos que puede atravesar el camino
		this.caminos = new ArrayList<>();
	}

	public List<List<Integer>> caminos() {
		this.visitados = new ArrayList<>();
		List<Integer> subCamino = new ArrayList<Integer>();
		subCamino.add(origen);

		this.subCamino(this.origen, this.destino, subCamino);

		return this.caminos;
	}
	
	private void subCamino(Integer origenActual, Integer destino, List<Integer> caminoParcial){
		// como el numero de arcos siempre excedera por uno a la cantidad de vertices
		// comparo por el numero de vertices en el camino +1 
		if(caminoParcial.size() <= this.lim + 1 ){
			if(origenActual == destino){ 
				List<Integer> copyCamino = new ArrayList<>(caminoParcial);
				this.caminos.add(copyCamino);
			} else {
				Iterator<Integer> adyacentesAlOrigenIter = this.grafo.obtenerAdyacentes(origenActual);
				while(adyacentesAlOrigenIter.hasNext()){
					Integer adyacente = adyacentesAlOrigenIter.next();
					if(!this.visitados.contains(adyacente)){
						caminoParcial.add(adyacente);
						this.visitados.add(adyacente);
						this.subCamino(adyacente, destino, caminoParcial);	
						caminoParcial.remove(adyacente);
						this.visitados.remove(adyacente);
					}
				}
			}
		}
	}
}
