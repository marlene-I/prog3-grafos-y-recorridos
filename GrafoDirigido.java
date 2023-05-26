

import java.util.Iterator;
import java.util.LinkedList;
import java.util.HashMap;

public class GrafoDirigido<T> implements Grafo<T> {

	private HashMap<Integer, Vertice<T>> grafo; 

	public GrafoDirigido() {
		this.grafo = new HashMap<>();
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
			this.grafo.put(verticeId, new Vertice<T>(verticeId, null));
		}	
	}

	/**
	* Complejidad: O(1).
	* Chequeo de que la clave exista en el HashMap: O(1).
	* Remover un elemento del HashMap : O(1).
	*/
	@Override
	public void borrarVertice(int verticeId) {
		if(this.grafo.containsKey(verticeId)){
			this.grafo.remove(verticeId);
		}	
	}

	/**
	* Complejidad: O(1).
	* Chequeo de que la clave exista en el HashMap: O(1).
	* Obtener un elemento del HashMap: O(1).
	* Metodo de GrafoDirigido agregarVertice: O(1).
	*/
	@Override
	public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
		if(!this.grafo.containsKey(verticeId1)){
			this.agregarVertice(verticeId1);
		}
		
		if(!this.grafo.containsKey(verticeId2)){
			this.agregarVertice(verticeId2);
		}
		// Asumo que vertice1 es el origen y vertice 2 es el destino
		this.grafo.get(verticeId1).agregarArco(verticeId1, verticeId2, etiqueta);
	}

	
	/**
	 * Complejidad: O(n) donde n es la cantidad de Arcos del Vértice.
	 *	Obtener un elemento del HashMap: O(1).
	 *	El método remove de la LinkedList debe iterar hasta encontrar el
	 * 	arco a borrar: O(n). 
	 */
	@Override
	public void borrarArco(int verticeId1, int verticeId2) {
		this.grafo.get(verticeId1).borrarArco(verticeId1, verticeId2);
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
	 *	El método contains de la LinkedList debe iterar hasta encontrar el
	 * 	arco a deseado: O(n). 
	 */
	@Override
	public boolean existeArco(int verticeId1, int verticeId2) {
		return this.grafo.get(verticeId1).existeArco(verticeId1, verticeId2);
	}

	
	/**
	 * Complejidad: O(n) donde n es la cantidad de Arcos del Vértice.
	 *	Obtener un elemento del HashMap: O(1).
	 *	El método indexOf de la LinkedList debe iterar hasta encontrar el
	 * 	arco a obtener: O(n). 
	 */
	@Override
	public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
		return this.grafo.get(verticeId1).obtenerArco(verticeId1, verticeId2);
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
	 * Complejidad O(n) donde n es la cantidad de vértices del 
	 * grafo dado que se debe preguntar a cada vértice la cantidad
	 * de arcos que almacena.
	 */
	@Override
	public int cantidadArcos() {
		int total = 0;
		for(Vertice<T> vertice : this.grafo.values()){
			total += vertice.obtenerCantidadArcos();
		}
		return total;
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
		return this.grafo.get(verticeId).obtenerAdyacentes();
	}

	/**
	 * Complejidad O(n) donde n es el número de Vértices en el Grafo
	 * dado que para obtener todos los Arcos del Grafo se debe iterar 
	 * por todos los vértices pidiendo los arcos asociados.
	 */
	@Override
	public Iterator<Arco<T>> obtenerArcos() {
		LinkedList<Arco<T>> arcos = new LinkedList<Arco<T>>();
		for (Vertice<T> vertice : this.grafo.values()){
			arcos.addAll(vertice.obtenerArcos());
		}
		return arcos.iterator();
	}

	/**
	 * Complejidad O(1) dado que HashMap tiene complejidad O(1) para
	 * el acceso a los datos almacenados.
	 */
	@Override
	public Iterator<Arco<T>> obtenerArcos(int verticeId) {
		return this.grafo.get(verticeId).obtenerArcos().iterator();
	}
}