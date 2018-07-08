package partes;
public class piezas  {
    int ubicacion;
    String imagen;
    int valor;
    contenedor miContenedor;
public piezas( int valor) {
        
         this.valor = valor;     
    }   
 public contenedor getContenedor(){
 
    return miContenedor;
 }
public boolean  hasContenedor(){
        return miContenedor != null;
}
public int getValor() {
        return valor;
    }
public void setContenedor(contenedor contenedors){
        miContenedor = contenedors;
    }
public void quitarContenedor(){
    miContenedor = null;
    }
}