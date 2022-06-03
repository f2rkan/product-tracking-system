package VeritabaniBaglantisi;

import java.sql.Connection;
import java.sql.DriverManager;

public class dbBag {

	Connection conn = null;
	public static Connection dbBag2() {
    	
    	try {
    		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/uruntakip", "root", "mysql");
    		return con;
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
			return null;
		}
    }
	
}
