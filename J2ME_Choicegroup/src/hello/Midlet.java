/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextBox;
import javax.microedition.midlet.*;

/**
 * @author lexs
 */
public class Midlet extends MIDlet implements CommandListener{

    Display display;
    Form choiceForm;
    Command exitCommand = new Command("EXIT", null, Command.EXIT, 0);
    Command tampilkanCommand = new Command("SHOW", null, Command.OK, 0);
    ChoiceGroup choiceExclusive, choiceMultiple, choicePopup;
    
    public Midlet(){
        choiceForm = new Form("Choice Group Type");
        choiceForm.addCommand(exitCommand);
        choiceForm.addCommand(tampilkanCommand);
        choiceForm.setCommandListener(this);
        
        choiceExclusive = new ChoiceGroup("Exclusive", Choice.EXCLUSIVE);
        choiceExclusive.append("Male", null);
        choiceExclusive.append("Female", null);
        choiceForm.append(choiceExclusive);
        
        choiceMultiple = new ChoiceGroup("Multiple", Choice.MULTIPLE);
        choiceMultiple.append("Apple", null);
        choiceMultiple.append("Orange", null);
        choiceMultiple.append("Grape", null);
        choiceForm.append(choiceMultiple);
        
        choicePopup = new ChoiceGroup("Popup", Choice.POPUP);
        choicePopup.append("Asia", null);
        choicePopup.append("Europe", null);
        choicePopup.append("America", null);
        choiceForm.append(choicePopup);
    }
    
    public void startApp() {
        if(display == null){
            display = Display.getDisplay(this);
            display.setCurrent(choiceForm);
        }
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {
        if(c==exitCommand){
            destroyApp(true);
            notifyDestroyed();
        }
        if(c==tampilkanCommand){
            String daftarMulti = "";
            boolean[] daftar = new boolean[choiceMultiple.size()];
            choiceMultiple.getSelectedFlags(daftar);
            for(int i=0;i<choiceMultiple.size();i++){
                if(daftar[i]==true){
                    daftarMulti += ("# "+choiceMultiple.getString(i)+"\n");
                }
            }
            String textTemp = "===GENDER===\n"
                    +"# "+choiceExclusive.getString(choiceExclusive.getSelectedIndex())+"\n"
                    +"===BUAH=====\n"+daftarMulti
                    +"===OCEAN====\n"+"# "+choicePopup.getString(choicePopup.getSelectedIndex());
            Alert tempAlert = new Alert("Data Dipilih", textTemp, null, AlertType.INFO);
            tempAlert.setTimeout(Alert.FOREVER);
            display.setCurrent(tempAlert);
        }
    }
}
