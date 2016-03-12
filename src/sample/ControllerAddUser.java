package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.h2.table.Table;



/**
 * Created by Хускар on 15.02.2016.
 */
public class ControllerAddUser {
@FXML
    private TextField loginsearch;
    @FXML
    private Button search;
    @FXML
    private ProgressIndicator prgind;

    @FXML
    private void initialize(){}
    @FXML
    private void searchuser(){
        String t=loginsearch.getText ();
        if(!t.equals (Client.mylogin)){Client client = new Client (uu,tb);
        Client.str=t;

        Boolean z=client.runs (303);//Поиск клиента и добавление в друзья
        Osnova os= new Osnova ();
        os.zareg (z);
        prim.close ();}
        else{ Osnova os= new Osnova ();
        os.zareg (false);
        prim.close();
        }
    }
    Stage prim;
    public void setDialogStage(Stage g){
        this.prim=g;
    }
    public void setBro(ObservableList<User>uu,TableView tb){
        this.uu=uu;
        this.tb=tb;
    }
    public  TableView tb;
    ObservableList<User> uu;
}
