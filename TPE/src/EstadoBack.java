
import java.util.ArrayList;


public class EstadoBack {
	private int posicion;
	private int km;
	private ArrayList<Arco<Integer>> solucionParcial;
	private UnionFind unionFind;

	public EstadoBack(int cantEstaciones) {
		this.posicion = 0;
		this.km = 0;
		this.solucionParcial = new ArrayList<>();
		this.unionFind = new UnionFind(cantEstaciones);
	}

	public UnionFind getUnion() {
		return unionFind;
	}

	public void setUnion(UnionFind union) {
		this.unionFind = union;
	}

	public ArrayList<Arco<Integer>> getSolucionParcial() {
		return new ArrayList<Arco<Integer>>(solucionParcial);
	}

	public void addArco(Arco<Integer> arco) {
		this.solucionParcial.add(arco);
	}

	public void removeArco(Arco<Integer> arco) {
		this.solucionParcial.remove(arco);
	}

	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int pos) {
		this.posicion = pos;
	}

	public int getKms() {
		return km;
	}

	public void setKms(int km) {
		this.km = km;
	}

}
