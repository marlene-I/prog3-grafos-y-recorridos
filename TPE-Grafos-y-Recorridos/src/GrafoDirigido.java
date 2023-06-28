

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.HashMap;

public class GrafoDirigido<T> implements Grafo<T> {

	private HashMap<Integer, LinkedList<Arco<T>>> grafo; 
	private Integer numeroArcos;

	public GrafoDirigido() {
		this.grafo = new HashMap<>();
		this.numeroArcos = 0;
	}

	
	/**
	* Complejidad: O(1) debido a que la clase HashMap
	* consulta si existe la clave dada calculando el hash de
	* dicha clave y a partir de dicha clave el índice en el que
	* se encuentra, luego compara si la clave dada y la almacenada
	* son iguales, en dicho caso, la clave existe. El costo de esta
	* operación es constante O(1).
	* Luego para insertar un valor en HashMap se realiza el mismo 
	* chequeo para verificar si debe sobreescribir una clave existente 
	* o insertar un valor nuevo. Complejidad O(1).
	*/
	@Override
	public void agregarVertice(int verticeId) {
		if(!this.grafo.containsKey(verticeId)){
			this.grafo.put(verticeId, new LinkedList<Arco<T>>());
		}	
	}

	/*
	* Complejidad: O(a^2). Donde a son los arcos del grafo.
	* Chequeo de que la clave exista en el HashMap: O(1).
	* Iterar sobre los arcos del grafo: O(a).
	* Iterar sobre un arreglo de arcos a borrar: O(a).
	* Remover un elemento del HashMap : O(1).
	*/
	@Override
	public void borrarVertice(int verticeId) {
		
		if(this.grafo.containsKey(verticeId)){
			// borrar arco que tengan como destino el verticeId a borrar
			ArrayList<Arco<T>> arcosABorrar = new ArrayList<Arco<T>>();
			Iterator<Arco<T>> arcosDelGrafo = this.obtenerArcos(); 

			while (arcosDelGrafo.hasNext()){
				Arco<T> arco = arcosDelGrafo.next();
				if(arco.getVerticeDestino() == verticeId){
					arcosABorrar.add(arco);
				}
			}

			for(Arco<T> arco : arcosABorrar){
				this.borrarArco(arco.getVerticeOrigen(), verticeId);
			}

			this.grafo.remove(verticeId);
		}	
	}

	/**
	* Complejidad: O(n). Donde n es la cantidad de arcos que tiene el vertice 1.
	* Obtener el iterator de Arcos: O(1).
	* Iterar sobre los arcos del vertice: O(n).
	* Método add de LinkedList tiene complejidad O(1).
	*/
	@Override
	public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
		Iterator<Arco<T>> arcos = this.obtenerArcos(verticeId1);

		Boolean existeArco = false;
		while(arcos.hasNext() && !existeArco){
			Arco<T> arco = arcos.next();
			if(arco.getVerticeDestino() == verticeId2){
				existeArco = true;
			}
		}
		
		if(!existeArco){
			// Asumo que vertice1 es el origen y vertice 2 es el destino
			this.grafo.get(verticeId1).add(new Arco<T>(verticeId1, verticeId2, etiqueta));
			this.numeroArcos++;
		}

	}

	
	/**
	 * Complejidad: O(n) donde n es la cantidad de Arcos del Vértice.
	 *	Obtener un elemento del HashMap: O(1).
	 *	El método remove de la LinkedList debe iterar hasta encontrar el
	 * 	arco a borrar: O(n). 
	 */
	@Override
	public void borrarArco(int verticeId1, int verticeId2) {
		// NO MODIFIQUE ESTE METODO PORQUE LO CONSULTE EN CLASE
		Arco<T> arcoABorrar = new Arco<T>(verticeId1, verticeId2, null);
        // la linked list va a buscar entre sus elementos un arco que cumpla:
		//  arcoABorrar.equals(arco) y lo eliminará.
		this.grafo.get(verticeId1).remove(arcoABorrar);
	}

	/**
	 * Complejidad: O(1).
	 * El HashMap chequea si la clave existe con complejidad O(1).
	 */
	@Override
	public boolean contieneVertice(int verticeId) {
		return this.grafo.containsKey(verticeId);
	}

	
	/**
	 * Complejidad: O(n) donde n es la cantidad de Arcos del Vértice.
	 *	Obtener un elemento del HashMap: O(1).
	 *	Iterar sobre todos los arcos del vertice1: O(n).
	 */
	@Override
	public boolean existeArco(int verticeId1, int verticeId2) {
		LinkedList<Arco<T>> arcosDelVertice = this.grafo.get(verticeId1);

		for(Arco<T> arco : arcosDelVertice){
			if(arco.getVerticeDestino() == verticeId2){
				return true;
			}
		}

		 return false;
	}

	
	/**
	 * Complejidad: O(n) donde n es la cantidad de Arcos del Vértice.
	 *	Obtener un elemento del HashMap: O(1).
	 *	Iterar sobre los arcos del vertice: O(n).
	 */
	@Override
	public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
		LinkedList<Arco<T>> arcosDelVertice = this.grafo.get(verticeId1);
		
		for(Arco<T> arco : arcosDelVertice){
			if(arco.getVerticeDestino() == verticeId2){
				return arco;
			}
		}

		return null;
	}

	/**
	 * Complejidad: O(1).
	 * El HashMap obtiene su tamaño con complejidad O(1).
	 */
	@Override
	public int cantidadVertices() {
		return this.grafo.size();
	}

	/**
	 * Complejidad O(1). Dado que el numero de arcos se lleva
	 * en un atributo de clase.
	 */
	@Override
	public int cantidadArcos() {
		return this.numeroArcos;
	}

	/**
	 * Complejidad O(1) dado que la estructura HashMap retorna
	 * el keySet que tiene almacenado.
	 */
	@Override
	public Iterator<Integer> obtenerVertices() {
		return this.grafo.keySet().iterator();
	}

	/**
	 * O(n) donde n es la cantidad de Arcos que tenga el Vértice
	 * dado que para obtener los adyacentes debe recorrer todos los 
	 * Arcos pidiendo el vértice destino.
	 */
	@Override
	public Iterator<Integer> obtenerAdyacentes(int verticeId) {
		LinkedList<Integer> adyacentes = new LinkedList<Integer>();

        for( Arco<T> arco : this.grafo.get(verticeId) ){
            adyacentes.add(arco.getVerticeDestino());
        }
        
        return adyacentes.iterator();
	}

	/**
	 * Complejidad O(n) donde n es el número de Vértices en el Grafo
	 * dado que para obtener todos los Arcos del Grafo se debe iterar 
	 * por todos los vértices pidiendo los arcos asociados.
	 */
	@Override
	public Iterator<Arco<T>> obtenerArcos() {
		LinkedList<Arco<T>> arcos = new LinkedList<Arco<T>>();

		for (LinkedList<Arco<T>> arcosDelVertice : this.grafo.values()){
			arcos.addAll(arcosDelVertice);
		}
		return arcos.iterator();
	}

	/**
	 * Complejidad O(1) dado que HashMap tiene complejidad O(1) para
	 * el acceso a los datos almacenados.
	 */
	@Override
	public Iterator<Arco<T>> obtenerArcos(int verticeId) {
		return this.grafo.get(verticeId).iterator();
	}
}
