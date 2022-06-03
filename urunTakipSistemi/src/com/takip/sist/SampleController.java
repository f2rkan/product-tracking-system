package com.takip.sist;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import java.sql.*;
import javafx.scene.control.Hyperlink;

public class SampleController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField alisFiyati;

    @FXML
    private Button buttonEkle;

    @FXML
    private Button buttonGuncelle;

    @FXML
    private Button buttonTemizle;

    @FXML
    private Button butttonSil;

    @FXML
    private Hyperlink hyper;

    
    @FXML
    private DatePicker girisTarihi;

    @FXML
    private TextField odemeYapildi;

    @FXML
    private DatePicker sonOdeme;

    @FXML
    private TableColumn<DepoBilgileri, String> sutunAlis;

    @FXML
    private TableColumn<DepoBilgileri, String> sutunBorc;

    @FXML
    private TableColumn<DepoBilgileri, Date> sutunGiris;

    @FXML
    private TableColumn<DepoBilgileri, Integer> sutunId;

    @FXML
    private TableColumn<DepoBilgileri, String> sutunMiktar;

    @FXML
    private TableColumn<DepoBilgileri, String> sutunName;

    @FXML
    private TableColumn<DepoBilgileri, Date> sutunSonOdeme;

    @FXML
    private TableColumn<DepoBilgileri, Integer> sutunTaksit;

    @FXML
    private TableColumn<DepoBilgileri, String> sutunUrun;

    @FXML
    private TableView<DepoBilgileri> table_view;

    @FXML
    private TextField taksitFiyati;

    @FXML
    private TextField toptanciBilgisi;

    @FXML
    private TextField urunBilgisi;

    @FXML
    private TextField urunID;

    
    @FXML
    private TextField urunMiktari;

    @FXML
    void buttonEkleAction(ActionEvent event) {

    	if(event.getSource() == buttonEkle){
            ekleAction();  
            
    }
    	
    }

    @FXML
    void buttonGuncelleAction(ActionEvent event) {

    	if(event.getSource() == buttonGuncelle	){
           	updateRecord();  
            
    }
    	
    }
    
    @FXML
    void hyperAction(ActionEvent event) {

    	if(event.getSource() == hyper) {
    		
			try {
				Node node = (Node) event.getSource();
				Stage stage = (Stage) node.getScene().getWindow();
				stage.close();
				
				Scene scene = new Scene(FXMLLoader.load(getClass().getResource("MusteriIslemleri.fxml")));
				stage.setScene(scene);
				stage.show();
			} catch (Exception e) {
				System.err.println(e.getMessage());
		}
	}
    	
    }

    @FXML
    void buttonSilAction(ActionEvent event) {

    	if(event.getSource() == butttonSil){
            ekleAction();  
            
    }
    	
    }
    @FXML
    void buttonTemizleAction(ActionEvent event) {
			temizle();

    }
    @FXML
    void table_viewPressed(MouseEvent event) {

    	DepoBilgileri sec = table_view.getSelectionModel().getSelectedItem();
    	
    	urunID.setText("" + sec.getUrunID());
    	toptanciBilgisi.setText("" + sec.getToptanciBilgisi());
    	urunBilgisi.setText("" + sec.getUrunBilgisi());
    	urunMiktari.setText("" + sec.getUrunMiktari());
    	alisFiyati.setText("" + sec.getAlisFiyati());
    	taksitFiyati.setText("" + sec.getTaksitSayisi());
    	odemeYapildi.setText("" + sec.getOdemeYapildi());
    	girisTarihi.setValue(((java.sql.Date) sec.getGirisTarihi()).toLocalDate());
    	sonOdeme.setValue(((java.sql.Date) sec.getSonOdeme()).toLocalDate());
    	
    }
    
    private void temizle() {
    	urunID.setText("");
    	toptanciBilgisi.setText("");
    	urunBilgisi.setText("");
    	urunMiktari.setText("");
    	alisFiyati.setText("");
    	taksitFiyati.setText("");
    	odemeYapildi.setText("");
    	girisTarihi.getEditor().clear();   
    	sonOdeme.getEditor().clear();
    }
    
    public Connection getConnection() {
    	Connection conn;
    	try {
    		//jdbc:mysql://"veri tabanı konumu", "db user name", "db password"
    		conn = DriverManager.getConnection("jdbc:mysql://localhost/uruntakip", "root", "mysql");
    		return conn;
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
			return null;
		}
    }

    public ObservableList<DepoBilgileri>getDepoBilgileri(){
    	ObservableList<DepoBilgileri>depoBilgileriList = FXCollections.observableArrayList();
    	Connection conn = getConnection();
    	String sorgu = "SELECT * FROM depobilgileri";
    	Statement statement;
    	ResultSet resultSet;
    	try {
    		statement = conn.createStatement();
    		resultSet = statement.executeQuery(sorgu);
			DepoBilgileri depoIslemleri;
			while(resultSet.next()) {
				depoIslemleri = new DepoBilgileri(resultSet.getInt("urunID"), resultSet.getString("toptanciBilgisi"), resultSet.getString("urunBilgisi"), resultSet.getString("urunMiktari"), resultSet.getString("alisFiyati"),  resultSet.getInt("taksitSayisi"), resultSet.getString("odemeYapildi") , resultSet.getDate("girisTarihi"), resultSet.getDate("sonOdeme"));
				depoBilgileriList.add(depoIslemleri);				
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
    	return depoBilgileriList;
    }
    
    public void depoBilgileriniGoster() {
    	ObservableList<DepoBilgileri>list = getDepoBilgileri();
    	
    	sutunId.setCellValueFactory(new PropertyValueFactory<DepoBilgileri, Integer>("urunID"));
    	sutunName.setCellValueFactory(new PropertyValueFactory<DepoBilgileri, String>("toptanciBilgisi"));
    	sutunUrun.setCellValueFactory(new PropertyValueFactory<DepoBilgileri, String>("urunBilgisi"));
    	sutunMiktar.setCellValueFactory(new PropertyValueFactory<DepoBilgileri, String>("urunMiktari"));
    	sutunAlis.setCellValueFactory(new PropertyValueFactory<DepoBilgileri, String>("alisFiyati"));
    	sutunTaksit.setCellValueFactory(new PropertyValueFactory<DepoBilgileri, Integer>("taksitSayisi"));
    	sutunBorc.setCellValueFactory(new PropertyValueFactory<DepoBilgileri, String>("odemeYapildi"));
    	sutunGiris.setCellValueFactory(new PropertyValueFactory<DepoBilgileri, Date>("girisTarihi"));
    	sutunSonOdeme.setCellValueFactory(new PropertyValueFactory<DepoBilgileri, Date>("sonOdeme"));
    	
    	table_view.setItems(list);
    }
    
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
    
    private void ekleAction() {
    	
    	String sorgu = "INSERT INTO depobilgileri VALUES(" 
    	+ urunID.getText()
    	+ ",'" + toptanciBilgisi.getText() + "','"
    	+ urunBilgisi.getText() + "','"
    	+ urunMiktari.getText() + "','"
    	+ alisFiyati.getText() + "',"
    	+ taksitFiyati.getText() + ",'"
    	+ odemeYapildi.getText()
    	+ "','" + girisTarihi.getValue()
    	+ "','" + sonOdeme.getValue() +
    	"')";
    	
    	executeQuery(sorgu);
    	depoBilgileriniGoster();
    }
	private void updateRecord(){
    	String sorgu = "UPDATE  depobilgileri SET toptanciBilgisi  = '" + toptanciBilgisi.getText()
    	+ "', urunBilgisi = '" + urunBilgisi.getText() 
    	+ "', urunMiktari = '" + urunMiktari.getText() 
    	+ "', alisFiyati = '" + alisFiyati.getText() 
    	+ "', taksitSayisi = " + taksitFiyati.getText() 
    	+ ", odemeYapildi = '" + odemeYapildi.getText()
    	+ "', girisTarihi = '" + girisTarihi.getValue()
    	+ "', sonOdeme = '" + sonOdeme.getValue() + "' WHERE urunID = " + urunID.getText()+ "";
       		    	
       executeQuery(sorgu);
       depoBilgileriniGoster();
    }
	
	private void silAction(){
        String sorgu = "DELETE FROM depobilgileri WHERE id =" + urunID.getText() + "";
        executeQuery(sorgu);
        depoBilgileriniGoster();
    }
    @FXML
    void initialize() {
    	depoBilgileriniGoster();
    }
}
