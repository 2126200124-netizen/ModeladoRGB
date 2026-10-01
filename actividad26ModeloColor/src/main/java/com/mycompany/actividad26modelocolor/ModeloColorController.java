// Morones Torres Jorge Arnulfo
package com.mycompany.actividad26modelocolor;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author jorgi
 */
public class ModeloColorController implements Initializable {
    
    @FXML private TextField txtRojo, txtVerde, txtAzul, txtHex;
    @FXML private Spinner<Integer> spinRojo, spinVerde, spinAzul;
    @FXML private Slider sliderRojo, sliderVerde, sliderAzul;
    @FXML private CheckBox chkRojo, chkVerde, chkAzul;
    @FXML private RadioButton rbRojo, rbVerde, rbAzul;
    @FXML private ToggleGroup tgColores; 
    @FXML private ToggleButton tbEstado;    
    @FXML private ColorPicker colorPicker;
    @FXML private TextArea txtInfo;
    @FXML private Pane pane1, pane2, pane3, pane4, pane5, pane6, pane7;
        

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Configuracion de los spinners
        spinRojo.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,255,0));
        spinVerde.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,255,0));
        spinAzul.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,255,0));    
        
        //seccion 1
        ChangeListener<String> textListener = (observable, oldValue, newValue) ->{
            int r = validarRango(txtRojo.getText());
            int g = validarRango(txtVerde.getText());
            int b = validarRango(txtAzul.getText());
            aplicarColorHex(pane1, r, g, b);
            txtInfo.appendText("TextFields actualizados: RGB(" + r + ", " + g + ", " + b + ")\n");
        };
        txtRojo.textProperty().addListener(textListener);
        txtVerde.textProperty().addListener(textListener);    
        txtAzul.textProperty().addListener(textListener);    
        
       //seccion 2 
        ChangeListener<Integer> spinListener = (observable, oldValue, newValue) -> {
            int r = spinRojo.getValue();
            int g = spinVerde.getValue();
            int b = spinAzul.getValue();
            aplicarColorHex(pane2, r, g, b);
            txtInfo.appendText("Spinners actualizados: RGB (" + r + "," + g + "," + b + ")\n");
        };
        spinRojo.valueProperty().addListener(spinListener);
        spinVerde.valueProperty().addListener(spinListener);
        spinAzul.valueProperty().addListener(spinListener);
    
    
        //seccion 3
        ChangeListener<Number> sliderListener = (observable, oldValue, newValue) -> {
            int r = (int) sliderRojo.getValue();
            int g = (int) sliderVerde.getValue();
            int b = (int) sliderAzul.getValue();
            aplicarColorHex(pane3, r, g, b);
            txtInfo.appendText("Sliders actualizados: RGB ("+ r + "," + g + ","+ b +")\n");
        };    
        sliderRojo.valueProperty().addListener(sliderListener);
        sliderVerde.valueProperty().addListener(sliderListener);
        sliderAzul.valueProperty().addListener(sliderListener);
    
       // seccion 4 
        ChangeListener<Boolean> checkListener =(observable, oldValue, newValue) -> {
           int r = chkRojo.isSelected()? 255:0;
           int g = chkVerde.isSelected()? 255:0;
           int b = chkAzul.isSelected()? 255:0;
           aplicarColorHex(pane4, r, g, b);
           txtInfo.appendText("CheckBoxes actualizados: RGB ("+ r +","+ g +","+ b +")\n");
        };
        chkRojo.selectedProperty().addListener(checkListener);
        chkVerde.selectedProperty().addListener(checkListener);
        chkAzul.selectedProperty().addListener(checkListener);
    
       // seccion 5
        tgColores.selectedToggleProperty().addListener((observable, oldToggle, newToggle) -> {
            if (newToggle == rbRojo) aplicarColorHex(pane5, 255, 0, 0);
            else if (newToggle == rbVerde) aplicarColorHex(pane5, 0, 255, 0);
            else if (newToggle == rbAzul) aplicarColorHex(pane5, 0, 0, 255);
            
            if (newToggle !=null){
                txtInfo.appendText("RadioButton seleccionado:" + ((RadioButton)newToggle).getText()+ "\n");
            }
        });

       // seccion 6
        tbEstado.selectedProperty().addListener((observable, oldValue, isSelected) ->{
            if (isSelected){
                tbEstado.setText("[ HEX ]");
                txtInfo.appendText("Estado cambiado a: HEX\n");
            }else {
                tbEstado.setText("[ RGB ]");
                txtInfo.appendText("Estado cambiado a: RGB\n");
            }
        });

       // seccion 7
        colorPicker.valueProperty().addListener((observable, oldColor, newColor) ->{
            int r = (int) (newColor.getRed()*255);
            int g = (int) (newColor.getGreen()*255);
            int b = (int) (newColor.getBlue()*255);
            aplicarColorHex(pane6, r, g, b); 
            txtInfo.appendText("ColorPicker seleccionado: "+ newColor + "\n");
        });

       // seccion 8
        txtHex.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("^#[0-9A-Fa-f]{6}$")){
                pane7.setStyle("-fx-background-color:"+ newValue +"; -fx-border-color: black;"); 
                txtInfo.appendText("Color hexadecimal valido: "+newValue+"\n");
            }
        });
    }


    private void aplicarColorHex(Pane pane, int r, int g, int b) {
        String hex = String.format("#%02X%02X%02X", r, g, b);
        pane.setStyle("-fx-background-color: " + hex + "; -fx-border-color: black;");
    }

    private int validarRango(String texto){
        try {
            int valor = Integer.parseInt(texto);
            if (valor < 0) return 0;
            if (valor > 255) return 255;
            return valor;
        } catch (NumberFormatException e){
            return 0;
        }
    }    
}
