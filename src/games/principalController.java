package games;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import partes.contenedor;
import partes.piezas;

/**
 *
 * @author ulises Guerrero
 */
public class principalController implements Initializable {
    
    @FXML   GridPane gridpane;
    @FXML private   static Text txtMovi = new Text();
    @FXML private AnchorPane panelP;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        añadePaneles();
        panelP.getChildren().add(txtMovi);
        txtMovi.setX(500);
        txtMovi.setY(70);
        randoms();
    }    

    public static boolean Gano(){
        int gano =0;
        if (panel[8].getPieza()==null) {
             for (int i = 0; i < 8; i++) {
            if (panel[i].getID()== panel[i].getPieza().getValor()){
                System.out.println("Contenedor : "+ panel[i].getId() +"Pieza : " + panel[i].getPieza().getValor());
                gano++;
            }
        }
        }
       
        return gano>=8;
    } 
    public static void reinicia(){
        
    }
    private  static  void añadePaneles1(){
            Object []arrayN =randoms().toArray();
           for (int i = 0; i < 9; i++) {
            
        
          if(i!=8){
              
             panel[i].setStyle("-fx-background-color:orange");
            pieza [i]= new piezas((int)arrayN[i]);
            pieza[i].setContenedor(panel[i]);
            panel[i].setPieza(pieza[i]);       

         }
           }
  }  
    private    void añadePaneles(){
            Object []arrayN =randoms().toArray();
          
          gridpane.setGridLinesVisible(true);
          int fila=0;
          int columna=0;
      for (int i = 0; i < 9; i++) {
          panel[i] = new contenedor(i+1);
          panel[i].setStyle("-fx-background-color:green");
          gridpane.add(panel[i],columna,fila);
       columna++;
       if (columna==3) {
              fila++;
              columna=0;
          }
          
          
          if(i!=8){
              
             panel[i].setStyle("-fx-background-color:orange");
            pieza [i]= new piezas((int)arrayN[i]);
            pieza[i].setContenedor(panel[i]);
            panel[i].setPieza(pieza[i]);       

         }
      }
  }
      
   private static RotateTransition rotate;
  public static void añadePiezaoMovimiento( int idContenedor, piezas pieza){
      rotate = new RotateTransition(Duration.seconds(0.5), panel[idContenedor]);
      rotate.setFromAngle(0);
      rotate.setToAngle(360);
      rotate.play();
      panel[idContenedor].setPieza(pieza);
      panel[idContenedor].setStyle("-fx-background-color:orange");
      txtMovi.setText(""+contenedor.CantidadMovimiento);
      if (Gano()){
          alertCreator("Felicitaciones", "Felicitaciones", "Usted haganado el juago... \n ¿Que decea hacer?");
           contenedor.CantidadMovimiento =0;
          añadePaneles1();
          
          
      }
      
      
  }
  
    public static partes.contenedor panel[]= new contenedor[9];
    public static partes.piezas pieza[] = new piezas[8];
 
    
    public static ArrayList randoms(){
        Integer   entero ;
         ArrayList <Integer> numerosNuevos= new ArrayList<Integer>();
        while (numerosNuevos.size()<=7) {
                  entero =(int) (Math.random()*8)+1;
                  System.out.println(entero);
              if (!hasThisNumbre(entero, numerosNuevos)) {
                   numerosNuevos.add(entero);
            }
        }
        return numerosNuevos;
    }
    
    private static boolean hasThisNumbre(int numero, ArrayList array){
         boolean is = false;
        for (Object object : array) {
            if((int)object == numero){ 
               is= true;
                break;}
        }
        return is;
    }
    
    private static void alertCreator(String title, String header, String content) {
        JFXDialogLayout dialogContent = new JFXDialogLayout();
        Text text =new Text(header);
        text.setFont(Font.font(18));
    
    dialogContent.setHeading(text);
    dialogContent.setBody(new Text(content));
    JFXButton close = new JFXButton("Volver a Jugar");
    JFXButton close2 = new JFXButton("Salir de Juego");
    close.getStyleClass().add("JFXButton");
    dialogContent.setActions(close,close2);
    JFXDialog dialog = new JFXDialog( (StackPane) GameS.getMyStage().getScene().getRoot(), dialogContent, JFXDialog.DialogTransition.CENTER);
    close.setOnAction((ActionEvent a)->{
        dialog.close();
    });
    close2.setOnAction((ActionEvent a)->{
        System.exit(0);
    });
    
    dialog.show();
}
}