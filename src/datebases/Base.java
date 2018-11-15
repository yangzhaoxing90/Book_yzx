package datebases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Book.Dbutil;

public class Base {
	Connection conn = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;

    
	public Base(){
		try {
			Class.forName(Dbutil.className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void initial(){
		try {
			conn = DriverManager.getConnection(Dbutil.url, Dbutil.name, Dbutil.password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void myClose(){
		try {
			if(rs != null){
				rs.close();
			}
			if(pstm != null){
				pstm.close();
			}
			if(conn != null){
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
