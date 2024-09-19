/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package novelsworld;

import java.sql.*;
//import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.net.URL;
import java.util.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author SMART-it
 */
public class FXMLDocumentController implements Initializable {
    
     @FXML
    private Button close;

    @FXML
    private Button close_signup;

    @FXML
    private Hyperlink create_account;

    @FXML
    private TextField email_signup;

    @FXML
    private Hyperlink have_account;

    @FXML
    private AnchorPane login_form;

    @FXML
    private Button loginbtn;

    @FXML
    private AnchorPane main_form;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField password_signup;

    @FXML
    private AnchorPane signup_form;

    @FXML
    private Button signupbtn;

    @FXML
    private TextField username;

    @FXML
    private TextField username_signup;

    
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet re;
            
public void login() {
    String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";
    conn = DataBase.ConDb();
    
    if (conn == null) {
        // If the connection fails, alert the user
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("Failed to connect to the database.");
        alert.showAndWait();
        return; // Exit the method
    }
    
    try {
        ps = conn.prepareStatement(sql);
        ps.setString(1, username.getText());
        ps.setString(2, password.getText());
        re = ps.executeQuery();
        Alert alert;
        if (username.getText().isEmpty() || password.getText().isEmpty()) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        } else {
            if (re.next()) {
                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Logged in!");
                alert.showAndWait();
                
                loginbtn.getScene().getWindow().hide();
                    Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));

                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
            } else {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Wrong  username or password ");
                alert.showAndWait();
            }
        }
    } catch (Exception e) {
        System.out.println(e);
    }
}

public void signup() {
        String sql = "insert into admin (email,username,password) values (?,?,?)";
        conn = DataBase.ConDb();
    
    if (conn == null) {
        // If the connection fails, alert the user
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("Failed to connect to the database.");
        alert.showAndWait();
        return; // Exit the method
    }

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, email_signup.getText());
            ps.setString(2, username_signup.getText());
            ps.setString(3, password_signup.getText());

            Alert alert;
            if (email_signup.getText().isEmpty() || password_signup.getText().isEmpty() || username_signup.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else if (password_signup.getText().length() < 5) {

                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Invaild password");
                alert.showAndWait();

            } else {

                ps.execute();
                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully create a new account!");
                alert.showAndWait();

                email_signup.setText("");
                username_signup.setText("");
                password_signup.setText("");
            }

        } catch (Exception e) {
            System.out.println(e);

        }
    }



    public void switchForm(ActionEvent ev) {
        if (ev.getSource() == create_account) {
            login_form.setVisible(  false);
            signup_form.setVisible(true);
        } else if (ev.getSource() == have_account) {
            login_form.setVisible(true);
            signup_form.setVisible(false);
        }
    }
    
    public void close(){
        System.exit(0);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}