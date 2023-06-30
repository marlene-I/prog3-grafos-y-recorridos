

import java.util.ArrayList;
import java.util.List;


public class Backtracking {

	private ArrayList<Arco<Integer>> mejorSolucion = new ArrayList<Arco<Integer>>();
	private ArrayList<Integer> estaciones;
    private List<Arco<Integer>> tunelesCandidatos;
	private int metrica;
	private int kms;

	public Backtracking(List<Arco<Integer>> tunelesCandidatos, List<Integer> estaciones) {
		this.estaciones = new ArrayList<>(estaciones);
		this.kms = Integer.MIN_VALUE;
		this.metrica = 0;
        this.tunelesCandidatos = tunelesCandidatos;
	}


	public ArrayList<Arco<Integer>> backtracking() {
		this.setKms(Integer.MAX_VALUE);
		EstadoBack estado = new EstadoBack(estaciones.size());
		this.back(tunelesCandidatos, estado);
		return this.mejorSolucion;
	}

	private void back(List<Arco<Integer>> tunelesCandidatos, EstadoBack e) {
		this.metrica++;
		if (e.getPosicion() == tunelesCandidatos.size()) {
			if (e.getUnion().numberOfSets() == 1) {
				if (this.mejorSolucion.isEmpty()) {
					this.setKms(e.getKms());
					this.mejorSolucion.addAll(e.getSolucionParcial());
				} else {
					if (e.getKms() <= this.getKms()) {
						this.setKms(e.getKms());
						this.mejorSolucion.clear();
						this.mejorSolucion.addAll(e.getSolucionParcial());
					}
				}
			}
		} else {
			int posicionActual = e.getPosicion();
			int kmActual = e.getKms();
			Arco<Integer> arco = tunelesCandidatos.get(posicionActual);

			int origenEstacion = this.estaciones.indexOf(arco.getVerticeOrigen());
			int destinoEstacion = this.estaciones.indexOf(arco.getVerticeDestino());

			if (this.esArcoRedundante(arco, e) && kmActual + arco.getEtiqueta() < this.getKms()) {
				UnionFind unionAux = e.getUnion().clone();
				e.getUnion().union(origenEstacion, destinoEstacion);
				e.addArco(arco);
				e.setKms(kmActual + arco.getEtiqueta());
				e.setPosicion(posicionActual + 1);
				this.back(tunelesCandidatos, e);
				e.setUnion(unionAux);
				e.removeArco(arco);
				e.setPosicion(posicionActual);
				e.setKms(kmActual);

			}
			e.setPosicion(posicionActual + 1);
			this.back(tunelesCandidatos, e);
			e.setPosicion(posicionActual);
		}
	}

	private boolean esArcoRedundante(Arco<Integer> arco, EstadoBack e) {
		int destino = e.getUnion().find(this.estaciones.indexOf(arco.getVerticeDestino()));
		int origen = e.getUnion().find(this.estaciones.indexOf(arco.getVerticeOrigen()));

        return destino != origen;
	}

	public int getMetrica() {
		return this.metrica;
	}


	public int getKms() {
		return this.kms;
	}

	public void setKms(int kms) {
		this.kms = kms;
	}

}
