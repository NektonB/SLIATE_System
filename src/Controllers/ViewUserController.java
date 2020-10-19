package Controllers;

import DataControllers.DataReader;
import DataControllers.DataWriter;
import Modules.AD_Status;
import Modules.User;
import Modules.UserType;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewUserController implements Initializable {

    DataWriter dataWriter;
    DataReader dataReader;

    Alerts alerts;

    User user;
    UserType userType;
    AD_Status ad_status;

    private void loadData() {

    }

    @FXML
    private AnchorPane contentPane;
    @FXML
    private JFXTextField txtSearch;
    @FXML
    private JFXButton btnSearch;
    @FXML
    private TableView<UserRow> tblUser;
    @FXML
    private TableColumn<UserRow, Integer> tcId;
    @FXML
    private TableColumn<UserRow, String> tcUserName;
    @FXML
    private TableColumn<UserRow, String> tcFullName;
    @FXML
    private TableColumn<UserRow, String> tcNIC;
    @FXML
    private TableColumn<UserRow, String> tcContactNumber;
    @FXML
    private TableColumn<UserRow, String> tcStatus;
    @FXML
    private JFXButton btnClose;
    @FXML
    private FontAwesomeIconView btnExit;
    private Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            dataReader = ObjectGenerator.getDataReader();
            dataWriter = ObjectGenerator.getDataWriter();
            user = ObjectGenerator.getUser();
            userType = ObjectGenerator.getUserType();
            ad_status = ObjectGenerator.getAd_status();
            alerts = ObjectGenerator.getAlerts();

            readyEmployeeTable();
            dataReader.fillUserTable(tblUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setLoadDataFunction(Void loadData) {
        //this.loadData = loadData;
    }

    public void selectUser() {
        try {
            if (!tblUser.getItems().isEmpty()) {
                UserRow userRow = tblUser.getSelectionModel().getSelectedItem();
                dataReader.getUserById(userRow.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void selectUser_Key(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            selectUser();
        }
    }

    public void closeMe() {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }

    private void readyEmployeeTable() {
        tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        tcFullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        tcNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        tcContactNumber.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        tcStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    public static class UserRow {
        SimpleIntegerProperty id;
        SimpleStringProperty userName;
        SimpleStringProperty fullName;
        SimpleStringProperty nic;
        SimpleStringProperty contactNumber;
        SimpleStringProperty status;

        public UserRow(int id, String userName, String fullName, String nic, String contactNumber, String status) {
            this.id = new SimpleIntegerProperty(id);
            this.userName = new SimpleStringProperty(userName);
            this.fullName = new SimpleStringProperty(fullName);
            this.nic = new SimpleStringProperty(nic);
            this.contactNumber = new SimpleStringProperty(contactNumber);
            this.status = new SimpleStringProperty(status);
        }

        public int getId() {
            return id.get();
        }

        public void setId(int id) {
            this.id.set(id);
        }

        public SimpleIntegerProperty idProperty() {
            return id;
        }

        public String getUserName() {
            return userName.get();
        }

        public void setUserName(String userName) {
            this.userName.set(userName);
        }

        public SimpleStringProperty userNameProperty() {
            return userName;
        }

        public String getFullName() {
            return fullName.get();
        }

        public void setFullName(String fullName) {
            this.fullName.set(fullName);
        }

        public SimpleStringProperty fullNameProperty() {
            return fullName;
        }

        public String getNic() {
            return nic.get();
        }

        public void setNic(String nic) {
            this.nic.set(nic);
        }

        public SimpleStringProperty nicProperty() {
            return nic;
        }

        public String getContactNumber() {
            return contactNumber.get();
        }

        public void setContactNumber(String contactNumber) {
            this.contactNumber.set(contactNumber);
        }

        public SimpleStringProperty contactNumberProperty() {
            return contactNumber;
        }

        public String getStatus() {
            return status.get();
        }

        public void setStatus(String status) {
            this.status.set(status);
        }

        public SimpleStringProperty statusProperty() {
            return status;
        }
    }
}
