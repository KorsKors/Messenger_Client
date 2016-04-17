package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import sample.Priem.Priem;


/**
 * Created by Хускар on 15.02.2016.
 */
public class OsnovaController {
    @FXML
    private Button btn;
    @FXML
    private volatile TableView<User> table;
    @FXML
    private TableColumn<User,String> spisok;

    @FXML
    private void initialize() {
        spisok.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
    }
    @FXML
    private void addUser() throws Exception {
    new Osnova ().addUser (table);
    }
    public void setDialogStage(Stage dialogStage) {
        this.dialog = dialogStage;
    }
    public void setMainApp(Osnova main,ObservableList<User> uu) {

        this.osnova = main;
        this.uu=uu;
        table.setItems (osnova.getUser ());
        uu=table.getItems ();

        //Priem priem= new Priem();//пингуем сервер с целью проверки новых друзей и сообщений
       // Thread thread = new Thread (priem);//
       // thread.start ();//

        //table =new TableView<User>(uu);
        //uu.add(new User("Паша"));

        //uu.add(new User("Паша"));
    }
     ObservableList<User> uu;

    Osnova osnova;
    Stage dialog;
}
