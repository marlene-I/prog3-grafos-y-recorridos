import java.util.ArrayList;
import java.util.List;

public class Greedy {

	private List<Integer> estaciones;
	private UnionFind unionFind;
	private int metrica;
	private int kms;

    public Greedy(List<Integer> estaciones){
        this.estaciones = estaciones;
    }

	public ArrayList<Arco<Integer>> greedy(List<Arco<Integer>> tunelesCandidatos) {
		ArrayList<Arco<Integer>> tuneles = new ArrayList<Arco<Integer>>();

		this.ordenarPorMenorKM(tunelesCandidatos);
		
		this.unionFind = new UnionFind(this.estaciones.size());

		while (!tunelesCandidatos.isEmpty() && tuneles.size() < this.estaciones.size() - 1) {
			Arco<Integer> arco = tunelesCandidatos.remove(0);
			this.metrica++;
			if (this.arcoFactible(arco)) {
				tuneles.add(arco);
				this.unionFind.union(this.estaciones.indexOf(arco.getVerticeOrigen()),
						this.estaciones.indexOf(arco.getVerticeDestino()));
				this.kms += arco.getEtiqueta();
			}

		}
		;
		return tuneles;
	}

	private void ordenarPorMenorKM(List<Arco<Integer>> tunelesCandidatos) {
		for (int i = 0; i < tunelesCandidatos.size(); i++) {
			for (int j = 0; j < tunelesCandidatos.size() - 1; j++) {
				Arco<Integer> arcoActual = tunelesCandidatos.get(j);
				Arco<Integer> arcoSiguiente = tunelesCandidatos.get(j + 1);
				if (arcoActual.getEtiqueta() > arcoSiguiente.getEtiqueta()) {
					tunelesCandidatos.set(j, arcoSiguiente);
					tunelesCandidatos.set(j + 1, arcoActual);
				}
			}
		}
	}

	private boolean arcoFactible(Arco<Integer> arco) {
		int destino = this.unionFind.find(this.estaciones.indexOf(arco.getVerticeDestino()));
		int origen = this.unionFind.find(this.estaciones.indexOf(arco.getVerticeOrigen()));

		return destino != origen;
	}

	public List<Integer> getEstaciones() {
		return estaciones;
	}

	public int getMetrica() {
		return metrica;
	}

	public int getKms() {
		return kms;
	}

}
