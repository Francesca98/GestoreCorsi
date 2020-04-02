package it.polito.tdp.corsi;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import it.polito.tdp.corsi.model.Corso;
import it.polito.tdp.corsi.model.Model;
import it.polito.tdp.corsi.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	Model model ;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtPeriodo;

    @FXML
    private TextField txtCorso;

    @FXML
    private Button btnCorsiPerPeriodo;

    @FXML
    private Button btnNumeroStudenti;

    @FXML
    private Button btnStudenti;

    @FXML
    private Button btnDivisioneStudenti;

    @FXML
    private TextArea txtRisultato;

    @FXML
    void corsiPerPeriodo(ActionEvent event) {
    	
    	//txtRisultato.clear();
    	String pdString = txtPeriodo.getText();
    	int pd;
    	try {
    		pd = Integer.parseInt(pdString);
    	}catch(NumberFormatException e)
    	{
    		txtRisultato.setText("Devi inserire un numero! 1 o 2!");
    		return;
    	}
    	if(pd != 1 && pd!= 2)
    		{
    		txtRisultato.setText("Devi inserire un numero! 1 o 2!");
    		return;}
    		
    	//l'input è coretto
    
    	List<Corso> corsi = this.model.getCorsiByPeriodo(pd);
    	for (Corso c : corsi )
    	{
    		txtRisultato.appendText(c.toString()+"\n");
    	}
    		
    	txtPeriodo.clear();

    }

    @FXML
    void numeroStudenti(ActionEvent event) {

      	//txtRisultato.clear();
    	String pdString = txtPeriodo.getText();
    	int pd;
    	try {
    		pd = Integer.parseInt(pdString);
    	}catch(NumberFormatException e)
    	{
    		txtRisultato.setText("Devi inserire un numero! 1 o 2!");
    		return;
    	}
    	if(pd != 1 && pd!= 2)
    		{
    		txtRisultato.setText("Devi inserire un numero! 1 o 2!");
    		return;}
    		
    	//l'input è coretto
     Map<Corso, Integer> map = this.model.getIscrittiByPeriodo(pd);
     for (Corso c : map.keySet())
     {
    	 txtRisultato.appendText(c.getNome()+ ":   " + map.get(c)+ " \n");
     }
     
     txtPeriodo.clear();
    }

    @FXML
    void stampaDivisione(ActionEvent event) {
    	txtRisultato.clear();
    	String codins = txtCorso.getText();
    	if(codins == null)
    	{
    		txtRisultato.setText("Inserire un codice corso!\n");
    		return;
    	}
    	
    	//CONTROLLARE CHE IL CORSO ESISTE NEL DATABASE
   
    	Corso corso = new Corso(codins, null, null, null);
     	if(!this.model.esisteCorso(corso))
     	{
     		txtRisultato.setText("Corso inesistente nel database!\n");
    		return;
     	}
    	Map <String, Integer> map = this.model.getDivisioneByeCorso(corso);
    	for(String s : map.keySet())
    	
    	{
//    		if(s.equals(""))
//    		{		txtRisultato.appendText("Studenti non associati a nessun corso "+ map.get(s)+" \n");}
//    		else
//    		txtRisultato.appendText(s +" "+ map.get(s)+" \n");
    		
    		if(!s.equals(""))
    		
    		txtRisultato.appendText(s +" "+ map.get(s)+" \n");
    	}

    }

    @FXML
    void stampaStudenti(ActionEvent event) {
    	txtRisultato.clear();
    	String codins = txtCorso.getText();
    	if(codins == null)
    	{
    		txtRisultato.setText("Inserire un codice corso!\n");
    		return;
    	}
    	
    	//CONTROLLARE CHE IL CORSO ESISTE NEL DATABASE
   
    	Corso corso = new Corso(codins, null, null, null);
     	if(!this.model.esisteCorso(corso))
     	{
     		txtRisultato.setText("Corso inesistente nel database!\n");
    		return;
     	}

    	List <Studente> stud =  this.model.getStudentiByeCorso(corso);
    	if(stud.size()==0)
    	{
    		txtRisultato.setText("Il corso non ha studenti iscritti!\n");
    		
    	return;
    	}
    	for(Studente s : stud )
    	{
    		txtRisultato.appendText(s.toString()+"\n");
    	}
    }

    @FXML
    void initialize() {
        assert txtPeriodo != null : "fx:id=\"txtPeriodo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCorso != null : "fx:id=\"txtCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCorsiPerPeriodo != null : "fx:id=\"btnCorsiPerPeriodo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnNumeroStudenti != null : "fx:id=\"btnNumeroStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnStudenti != null : "fx:id=\"btnStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnDivisioneStudenti != null : "fx:id=\"btnDivisioneStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model m)
    {
    	this.model=m;
    }
}
