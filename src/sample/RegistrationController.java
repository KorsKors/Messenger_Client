package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by Хускар on 20.01.2016.
 */
public class RegistrationController {
    @FXML
    private Label label;
    @FXML
    private TextField NameUser;
    @FXML
    private TextField FirstPassword;
    @FXML
    private TextField SecondPassword;
    private boolean okClicked = false;
    private Stage dialogStage;

    @FXML
    private void initialize() {
    }

    User user;
    Main mainApp;
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    public boolean isOkClicked() {
        return okClicked;
    }
    public void setUser(User user){
    this.user=user;
    NameUser.setText(user.getLogin());
    FirstPassword.setText(user.getPassword());
}
    @FXML
    private void handleOk() {
        if (isInputValid()) {

            user.setLogin(NameUser.getText());
            user.setPassword(FirstPassword.getText());
            okClicked = true;
            dialogStage.close();
            //Client client=new Client(user);
            //Thread myThready = new Thread(client);
            //myThready.start();
        }

    }

    //public void setMainApp(Main main) {
    //    this.mainApp = main;
  //  }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (NameUser.getText() == null || NameUser.getText().length() == 0) {
            errorMessage += "Неверный логин!\n";
        }
        if (FirstPassword.getText() == null || FirstPassword.getText().length() == 0) {
            errorMessage += "Неверно введен пароль!\n";
        }
        if (SecondPassword.getText() == null || SecondPassword.getText().length() == 0) {
            errorMessage += "Неверно введен пароль!\n";
        }
        String s1=FirstPassword.getText();
        String s2=SecondPassword.getText();

        if(!(s1==null)&&!(s1.equals(s2))){
           errorMessage += "Пароли не совпадают!\n";
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Неверные данные");
            alert.setHeaderText("Введите корректные данные");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }

    }
}