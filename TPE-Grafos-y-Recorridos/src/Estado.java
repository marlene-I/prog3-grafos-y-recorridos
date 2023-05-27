

public enum Estado {
    BLANCO("B"),
    NEGRO("N"),
    AMARILLO("A");

    public final String estado;

    private Estado(String estado){
        this.estado = estado;
    }

    @Override
    public String toString(){
        return estado;
    }
    
}
