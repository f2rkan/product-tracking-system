package com.takip.sist;

import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import VeritabaniBaglantisi.dbBag;

public class LoginController {

	  @FXML
	   private Label label_donus;

	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonGiris;

    @FXML
    private Button buttonUnuttum;

    @FXML
    private Button buttonYeniKayit;

    @FXML
    private PasswordField passwordLog;

    @FXML
    private TextField usernameLog;

    @FXML
    void buttonGirisAction(ActionEvent event) {

    	if(event.getSource() == buttonGiris) {
    		if(GirisFonksiyonu().equals("basarili")) {
    		
    			try {
					Node node = (Node) event.getSource();
					Stage stage = (Stage) node.getScene().getWindow();
					stage.close();
					
					Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Sample.fxml")));
					stage.setScene(scene);
					stage.show();
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}    				
    		}
    	}
    	
    }
    
    public LoginController() {
		con = dbBag.dbBag2();
	}
	Connection con = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    @FXML
    void buttonUnuttumAction(ActionEvent event) {

    	if(event.getSource() == buttonUnuttum) {
    		
			try {
				Node node = (Node) event.getSource();
				Stage stage = (Stage) node.getScene().getWindow();
				stage.close();
				
				Scene scene = new Scene(FXMLLoader.load(getClass().getResource("YeniKayit.fxml")));
				stage.setScene(scene);
				stage.show();
			} catch (Exception e) {
				System.err.println(e.getMessage());
		}
	}
    	
    }

    @FXML
    void buttonYeniKayitAction(ActionEvent event) {

if(event.getSource() == buttonYeniKayit) {
    		
			try {
				Node node = (Node) event.getSource();
				Stage stage = (Stage) node.getScene().getWindow();
				stage.close();
				
				Scene scene = new Scene(FXMLLoader.load(getClass().getResource("YeniKayit.fxml")));
				stage.setScene(scene);
				stage.show();
			} catch (Exception e) {
				System.err.println(e.getMessage());
		}
	}
    	
    }
    
    public static String sifreleme_algorithm(String pass) {
		try {
			
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] encrypted = md5.digest(pass.getBytes());
			BigInteger no = new BigInteger(1, encrypted);
			String hashPass = no.toString(16);
			while(hashPass.length() < 32) {
				hashPass = "0" + hashPass;
			}
			return hashPass;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
    
    private String GirisFonksiyonu() {
    	String kullanici_adi = usernameLog.getText().toString();
    	String sifre = passwordLog.getText().toString();
    	
    	//login sorgusu
    	String sorgu = "SELECT * FROM users WHERE username = ? and password = ?";
    	try {
			preparedStatement = con.prepareStatement(sorgu);			
			preparedStatement.setString(1, kullanici_adi);
			preparedStatement.setString(2, sifre.valueOf(sifreleme_algorithm(sifre)));
			
			resultSet = preparedStatement.executeQuery();
			if(!resultSet.next()) {
				label_donus.setTextFill(Color.TOMATO);
				label_donus.setText("Hatali Giris");
				return "error";
			}
			else {
				label_donus.setTextFill(Color.GREEN);
				label_donus.setText("Login İslemi Basarıyla Sonuclandı");
				return "basarili";
			}			
		} catch (SQLException e) {
			
			System.err.println(e.getMessage());
			return "exception";
		}
    }

    @FXML
    void initialize() {
        
    }

}
