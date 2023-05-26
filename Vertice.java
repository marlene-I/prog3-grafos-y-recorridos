

import java.util.Iterator;
import java.util.LinkedList;

public class Vertice<T> {
    
    int id;
    T data;
    LinkedList<Arco<T>> arcos;
    
    public Vertice(Integer id, T data){
        this.id = id;
        this.data = data;
        this.arcos = new LinkedList<>();
    }

    public void agregarArco(int verticeId1, int verticeId2, T etiqueta){
        this.arcos.add(new Arco<T>(verticeId1, verticeId2, etiqueta));
    }

    public void borrarArco(int verticeId1, int verticeId2){
        // arco solo para comparar
        Arco<T> arcoABorrar = new Arco<T>(verticeId1, verticeId2, null);
        // la linked list va a buscar entre sus elementos un arco que cumpla: arcoABorrar.equals(arco)
        // y lo eliminará.
        this.arcos.remove(arcoABorrar);
    }

    public boolean existeArco(int verticeId1, int verticeId2){
        Arco<T> arcoABuscar = new Arco<T>(verticeId1, verticeId2, null);
        // retornará true si entre sus elementos encuentra un arco tal que: 
        // arco.equals(arcoABuscar) == true
        return this.arcos.contains(arcoABuscar);
    }

    public Arco<T> obtenerArco(int verticeId1, int verticeId2){
        
        Arco<T> arcoAObtener = new Arco<T>(verticeId1, verticeId2, null);
        int index = this.arcos.indexOf(arcoAObtener);

        if(index != -1){
            return this.arcos.get(index);
        }
        return null;
    }
    
    public int obtenerCantidadArcos(){
        return this.arcos.size();
    }

    public Iterator<Integer> obtenerAdyacentes(){
        LinkedList<Integer> adyacentes = new LinkedList<Integer>();

        for( Arco<T> arco : this.arcos ){
            adyacentes.add(arco.getVerticeDestino());
        }
        
        return adyacentes.iterator();
    }
    
    public LinkedList<Arco<T>> obtenerArcos(){
        return this.arcos;
    }
}
