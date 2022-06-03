package com.takip.sist;

import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class YeniKayitController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonGuncelle;

    @FXML
    private Button buttonKaydet;

    @FXML
    private Button buttonSil;

    @FXML
    private Button buttonTemizle;

    @FXML
    private PasswordField textfieldPassword;
    
    @FXML
    private TableColumn<YeniKayit, String> kayitTable_sifre;

    @FXML
    private TableColumn<YeniKayit, Date> kayitTable_tarih;

    @FXML
    private TableColumn<YeniKayit, String> kayitTable_username;

    @FXML
    private TableView<YeniKayit> tableKayit;

    @FXML
    private TableColumn<YeniKayit, Integer> kayitTable_id;
    
    @FXML
    private TextField textfieldID;

    @FXML
    private DatePicker textfieldKayitTarihi;


    @FXML
    private TextField textfieldUsername;

    @FXML
    void buttonGuncelleAction(ActionEvent event) {

    	if(event.getSource() == buttonGuncelle){
            guncelleFonksiyonu();  
            
    	}
    	
    }

    @FXML
    void buttonKaydetAction(ActionEvent event) {

    	if(event.getSource() == buttonKaydet){
            ekleFonksiyonu();  
            
    	}
    	
    }

    @FXML
    void buttonSilAction(ActionEvent event) {

    	if(event.getSource() == buttonSil){
            silFonksiyonu(); 	
    	}
    	
    }

    @FXML
    void buttonTemizleAction(ActionEvent event) {

    	temizle();
    	
    }
    
    private void temizle() {
    	
    	textfieldID.setText("");
    	textfieldUsername.setText("");
    	textfieldPassword.setText("");
    	textfieldKayitTarihi.getEditor().clear();    	
    }

    @FXML
    void tableKayitPressed(KeyEvent event) {

    	
    }
    @FXML
    void tableKayitClicked(MouseEvent event) {
    	YeniKayit sec = tableKayit.getSelectionModel().getSelectedItem();
    	
    	textfieldID.setText("" + sec.getUser_id());
    	textfieldUsername.setText("" + sec.getUsername());
    	textfieldPassword.setText("" + sec.getPassword());
    	textfieldKayitTarihi.setValue(((java.sql.Date) sec.getKayit_tarihi()).toLocalDate());
    	
    }

    public Connection getConnection() {
    	Connection conn;
    	try {
    		
    		conn = DriverManager.getConnection("jdbc:mysql://localhost/uruntakip", "root", "mysql");
    		return conn;
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
			return null;
		}
    }
    
  //veri tabanındaki kullanicilar tablosuna ulaşma fonksiyonu
    public ObservableList<YeniKayit>getUserList(){
    	ObservableList<YeniKayit>kayitList = FXCollections.observableArrayList();
    	Connection conn = getConnection();
    	String sorgu = "SELECT * FROM users";
    	Statement st;
    	ResultSet rs;
    	try {
			st = conn.createStatement();
			rs = st.executeQuery(sorgu);
			YeniKayit yeniKayit;
			while(rs.next()) {
				yeniKayit = new YeniKayit(rs.getInt("user_id"), rs.getString("username"), rs.getString("password"), rs.getDate("kayit_tarihi"));
				kayitList.add(yeniKayit);				
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
    	return kayitList;
    }
    
    //veri tabanındaki kayıtları çeken fonksiyon
    public void kayitGoster() {
    	ObservableList<YeniKayit>list = getUserList();
    	
    	kayitTable_id.setCellValueFactory(new PropertyValueFactory<YeniKayit, Integer>("user_id"));
    	kayitTable_username.setCellValueFactory(new PropertyValueFactory<YeniKayit, String>("username"));
    	kayitTable_sifre.setCellValueFactory(new PropertyValueFactory<YeniKayit, String>("password"));
    	kayitTable_tarih.setCellValueFactory(new PropertyValueFactory<YeniKayit, Date>("kayit_tarihi"));
    	tableKayit.setItems(list);
    }

    //sorgu çalıştırma fonksiyonu
    private void executeQuery(String sorgu) {
        Connection conn = getConnection();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(sorgu);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    //ekle butonu action sorgusu
    	private void ekleFonksiyonu() {
    	
    	String sorgu = "INSERT INTO users VALUES(" 
    	+ textfieldID.getText()
    	+ ",'" + textfieldUsername.getText() + "','"
    	+ textfieldPassword.getText().valueOf(sifreleme_algorithm(textfieldPassword.getText()))
    	+ "','" + textfieldKayitTarihi.getValue() +
    	"')";
    	
    	//sorguyu çalıştır
    	executeQuery(sorgu);
    	//çalışmış sorguyla güncellenen tabloyu kayitGoster() ile getir
    	kayitGoster();
    }
    
    	//güncelleme butonu ve md5 şifreleme
    	private void guncelleFonksiyonu(){
        	String sorgu = "UPDATE  users SET username  = '" + textfieldUsername.getText()
        	+ "', password = '" + textfieldPassword.getText().valueOf(sifreleme_algorithm(textfieldPassword.getText()))
        	+ "', kayit_tarihi = '" + textfieldKayitTarihi.getValue() 
        	+ "' WHERE user_id = " + textfieldID.getText() + "";
           		    	
           executeQuery(sorgu);
           kayitGoster();
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
    	//sil butonu sorgusu
    	private void silFonksiyonu(){
            String sorgu = "DELETE FROM users WHERE user_id =" + textfieldID.getText() + "";
            executeQuery(sorgu);
            kayitGoster();
        }
    @FXML
    void initialize() {
    	kayitGoster();
    }

}
