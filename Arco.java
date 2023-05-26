

/*
 * La clase arco representa un arco del grafo. Contiene un vertice origen, un vertice destino y una etiqueta.
 * Nota: Para poder exponer los arcos fuera del grafo y que nadie los modifique se hizo esta clase inmutable
 * (Inmutable: una vez creado el arco no es posible cambiarle los valores).
 */
public class Arco<T> {

	private int verticeOrigen;
	private int verticeDestino;
	private T etiqueta;

	public Arco(int verticeOrigen, int verticeDestino, T etiqueta) {
		this.verticeOrigen = verticeOrigen;
		this.verticeDestino = verticeDestino;
		this.etiqueta = etiqueta;
	}

	public int getVerticeOrigen() {
		return verticeOrigen;
	}

	public int getVerticeDestino() {
		return verticeDestino;
	}

	public T getEtiqueta() {
		return etiqueta;
	}

	@Override
	public boolean equals(Object obj) {
		Arco<T> otroArco = (Arco<T>) obj;
		// No comparo por etiqueta porque dada la interfaz del método borrarArco del grafo
		// interpreto que la etiqueta no forma parte de la identidad del arco.
		return otroArco.verticeOrigen == this.verticeOrigen && otroArco.verticeDestino == this.verticeDestino;
	}

	@Override
	public String toString(){
		return this.getVerticeOrigen() + "--"+this.etiqueta+ "-->" + this.getVerticeDestino() ; 
	}
}
