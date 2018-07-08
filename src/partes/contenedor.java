package partes;

import javafx.animation.FadeTransition;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javax.swing.JOptionPane;
/**
 * @author Elis Brayan Valdez Martinez 
 */
public class contenedor extends StackPane {
    public static int contenedorVacio = 9;
    public static int CantidadMovimiento = 0;
    int id ;
    piezas piesa;
    FadeTransition trans;
    public void movimiento(){
       if ( this.hasPieza()) {
            if (PuedoMoverme()) {
                piezas piesaz =this.getPieza();
                this.quitarPieza();
                games.principalController.añadePiezaoMovimiento(contenedorVacio -1, piesaz);
                contenedorVacio = this.getID();
                label.setText("");
                CantidadMovimiento+=1;
                
            }else{
                JOptionPane.showMessageDialog(null, "No te puede mover desde esta posicion");
            }
        }
    }
    private void añadeEfectos(){
        trans = new FadeTransition(Duration.seconds(0.5), this);
        trans.setFromValue(1);
        trans.setToValue(0.0);
        trans.setCycleCount(2);

        trans.setAutoReverse(true);
    }
    
    public contenedor(int id) {
        super();
         this.id = id;
         this.setPrefWidth(130);
         this.setPrefHeight(130);
          label = new Text();
        label.setFont(javafx.scene.text.Font.font(25));
        this.getChildren().add(0,label);
        
        this.setOnMouseClicked(((MouseEvent) -> {
            trans.play();
            movimiento();
             
        }));
        añadeEfectos();
                }
            
    
    public void setPieza(piezas pieza){
        this.piesa  =pieza;
        pieza.setContenedor(this);
        label.setText(String.valueOf(pieza.getValor()));
    }
    public piezas getPieza (){
        return piesa;
    }
   
    
    public  int getID()
    {
    return id;
    }
    
    public boolean hasPieza(){
        return piesa !=null;
    }
        
    public  void quitarPieza(){
        piesa = null;
        this.setStyle("-fx-background-color:red");
  // buscar una forma de que me quite la pieza visiblemente ( El plan es quitar la poner una pieza invisible )
    }
    public static int getContenedorVacio(){
        return contenedorVacio;
    }
    Text label;
    boolean PuedoMoverme(){
        boolean puedo = false;
        switch(id){
            case 1 :
                puedo = getContenedorVacio()==2 || getContenedorVacio()==4;
                break;
            case 2 :
                puedo = getContenedorVacio()==1 || getContenedorVacio()==3 || getContenedorVacio()==5;
                break;
            case 3 :
                puedo = getContenedorVacio()==2 || getContenedorVacio()==6;
                break;
            case 4 :
                puedo = getContenedorVacio()==5 || getContenedorVacio()==7 || getContenedorVacio()==1;
                break;
            case 5 :
                puedo = getContenedorVacio()==4 || getContenedorVacio()==6 || getContenedorVacio()==2||getContenedorVacio()==8;
                break;
            case 6 :
                puedo = getContenedorVacio()==5 || getContenedorVacio()==3|| getContenedorVacio()==9;
                break;
            case 7 :
                puedo = getContenedorVacio()==4 || getContenedorVacio()==8;
                break;
            case 8 :
                puedo = getContenedorVacio()==7 || getContenedorVacio()==9 || getContenedorVacio()==5;
                break;
            case 9 :
                puedo = getContenedorVacio()==8 || getContenedorVacio()==6;
                break;
        }
        return puedo;
    }
}
