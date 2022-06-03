package com.takip.sist;

import java.net.URL;
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
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MusteriIslemleriController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Hyperlink getDepo;

    @FXML
    private Button mustEkle;

    @FXML
    private Button mustGuncelle;

    @FXML
    private TextField mustID;

    @FXML
    private TextField mustMiktar;

    @FXML
    private TextField mustName;

    @FXML
    private TextField mustOdeme;

    @FXML
    private TextField mustSatisMiktar;

    @FXML
    private Button mustSil;

    @FXML
    private TableColumn<MusteriIslemleri, Integer> mustTableId;

    @FXML
    private TableColumn<MusteriIslemleri, String> mustTableMiktar;

    @FXML
    private TableColumn<MusteriIslemleri, String> mustTableName;

    @FXML
    private TableColumn<MusteriIslemleri, String> mustTableOdeme;

    @FXML
    private TableColumn<MusteriIslemleri, String> mustTableSatisMiktari;

    @FXML
    private TableColumn<MusteriIslemleri, Integer> mustTableTaksit;

    @FXML
    private TableColumn<MusteriIslemleri, Date> mustTableTarih;

    @FXML
    private TableColumn<MusteriIslemleri, String> mustTableUrun;

    @FXML
    private TextField mustTaksit;

    @FXML
    private DatePicker mustTarih;

    @FXML
    private Button mustTemizle;

    @FXML
    private TextField mustUrun;

    @FXML
    private TableView<MusteriIslemleri> must_table;

    @FXML
    void getDepoAction(ActionEvent event) {

    	if(event.getSource() == getDepo) {
    		
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

    @FXML
    void mustEkleAction(ActionEvent event) {

    	if(event.getSource() == mustEkle){
            ekleAction();  
            
    }
    	
    }

    @FXML
    void mustGuncelleAction(ActionEvent event) {

    	if(event.getSource() == mustGuncelle){
           	updateRecord();  
            
    }
    	
    }

    @FXML
    void mustSilAction(ActionEvent event) {

    	if(event.getSource() == mustSil){
            silAction();  
            
    }
    	
    }

    @FXML
    void mustTablePressed(MouseEvent event) {

    	MusteriIslemleri sec = must_table.getSelectionModel().getSelectedItem();
    	
    	mustID.setText("" + sec.getMusteri_id());
    	mustName.setText("" + sec.getMusteri_ismi());
    	mustUrun.setText("" + sec.getUrun_ismi());
    	mustMiktar.setText("" + sec.getUrun_miktari());
    	mustSatisMiktar.setText("" + sec.getSatis_miktari());
    	mustTaksit.setText("" + sec.getTaksit_sayisi());
    	mustTarih.setValue(((java.sql.Date) sec.getSon_odeme()).toLocalDate());
    	mustOdeme.setText("" + sec.getOdeme_yapildi());
    	
    }

    @FXML
    void mustTemizleAction(ActionEvent event) {

    	temizle();
    	
    }
    private void temizle() {
    	mustID.setText("");
    	mustName.setText("");
    	mustUrun.setText("");
    	mustMiktar.setText("");
    	mustSatisMiktar.setText("");
    	mustTaksit.setText("");
    	mustTarih.getEditor().clear();
    	mustOdeme.setText("");
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
    public ObservableList<MusteriIslemleri>getmusteriBilgileri(){
    	ObservableList<MusteriIslemleri>MusteriIslemleriList = FXCollections.observableArrayList();
    	Connection conn = getConnection();
    	String sorgu = "SELECT * FROM musteri_bilgileri";
    	Statement statement;
    	ResultSet resultSet;
    	try {
    		statement = conn.createStatement();
    		resultSet = statement.executeQuery(sorgu);
			MusteriIslemleri depoIslemleri;
			while(resultSet.next()) {
				depoIslemleri = new MusteriIslemleri(resultSet.getInt("musteri_id"), resultSet.getString("musteri_ismi"), resultSet.getString("urun_ismi"), resultSet.getString("urun_miktari"), resultSet.getString("satis_miktari"),  resultSet.getInt("taksit_sayisi"), resultSet.getDate("son_odeme"),resultSet.getString("odeme_yapildi"));
				MusteriIslemleriList.add(depoIslemleri);				
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
    	return MusteriIslemleriList;
    }
    
    public void musteriBilgileriniGoster() {
    	ObservableList<MusteriIslemleri>list = getmusteriBilgileri();
    	
    	mustTableId.setCellValueFactory(new PropertyValueFactory<MusteriIslemleri, Integer>("musteri_id"));
    	mustTableName.setCellValueFactory(new PropertyValueFactory<MusteriIslemleri, String>("musteri_ismi"));
    	mustTableUrun.setCellValueFactory(new PropertyValueFactory<MusteriIslemleri, String>("urun_ismi"));
    	mustTableMiktar.setCellValueFactory(new PropertyValueFactory<MusteriIslemleri, String>("urun_miktari"));
    	mustTableSatisMiktari.setCellValueFactory(new PropertyValueFactory<MusteriIslemleri, String>("satis_miktari"));
    	mustTableTaksit.setCellValueFactory(new PropertyValueFactory<MusteriIslemleri, Integer>("taksit_sayisi"));
    	mustTableTarih.setCellValueFactory(new PropertyValueFactory<MusteriIslemleri, Date>("son_odeme"));
    	mustTableOdeme.setCellValueFactory(new PropertyValueFactory<MusteriIslemleri, String>("odeme_yapildi"));
    	
    	
    	must_table.setItems(list);
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
    	
    	String sorgu = "INSERT INTO musteri_bilgileri VALUES(" 
    	+ mustID.getText()
    	+ ",'" + mustName.getText() + "','"
    	+ mustUrun.getText() + "','"
    	+ mustMiktar.getText() + "','"
    	+ mustSatisMiktar.getText() + "',"
    	+ mustTaksit.getText() + ",'"
    	+ mustTarih.getValue()
    	+ "','" + mustOdeme.getText()+
    	"')";
    	
    	executeQuery(sorgu);
    	musteriBilgileriniGoster();
    }

private void updateRecord(){
	String sorgu = "UPDATE  musteri_bilgileri SET musteri_ismi  = '" + mustName.getText()
	+ "', urun_ismi = '" + mustUrun.getText() 
	+ "', urun_miktari = '" + mustMiktar.getText() 
	+ "', satis_miktari = '" + mustSatisMiktar.getText() 
	+ "', taksit_sayisi = " + mustTaksit.getText() 
	+ ", son_odeme = '" + mustTarih.getValue()
	+ "', odeme_yapildi = '" + mustOdeme.getText() + "' WHERE musteri_id = " + mustID.getText()+ "";
   		    	
   executeQuery(sorgu);
   musteriBilgileriniGoster();
}

	private void silAction(){
    String sorgu = "DELETE FROM musteri_bilgileri WHERE musteri_id =" + mustID.getText() + "";
    executeQuery(sorgu);
    musteriBilgileriniGoster();
}
    @FXML
    void initialize() {
       musteriBilgileriniGoster();
    }
}
