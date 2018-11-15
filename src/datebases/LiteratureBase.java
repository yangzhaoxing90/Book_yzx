package datebases;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import Book.Literature;

public class LiteratureBase extends Base {
	
	public boolean add(Literature literature){
		initial();
		String sql = "insert into book(bookName,bookImg,bookPrice,bookCount) values(?,?,?,?)";
		try {
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, literature.getName());
			pstm.setString(2, literature.getImgpath());
			pstm.setDouble(3, literature.getPrice());
			pstm.setInt(4, literature.getNumber());
			pstm.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally{
			myClose();
		}
		return true;
	}

	public boolean delete(String name){
		initial();
		String sql = "delete from book where bookName=?";
		try {
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, name);
			if(pstm.executeUpdate() == 0){
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally{
			myClose();
		}
		return true;
	}
	public boolean update(Literature literature){
		initial();
		String sql = "update userinfo set bookImg=?,bookPrice=?,bookCount=? where bookName=?";
		try {
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, literature.getImgpath());
			pstm.setDouble(2, literature.getPrice());
			pstm.setInt(3, literature.getNumber());
			pstm.setString(4, literature.getName());
			if(pstm.executeUpdate() == 0){
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally{
			myClose();
		}
		return true;
	}
	public Literature find(String name){
		initial();
		String sql = "select * from book where bookName=?";
		Literature literature = null;
		try {
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, name);
			rs = pstm.executeQuery();
			while(rs.next()){
				literature = new Literature();
				literature.setName(rs.getString("bookName"));
				literature.setNumber(rs.getInt("bookCount"));	
				literature.setPrice(rs.getDouble("bookPrice"));	
				literature.setImgpath(rs.getString("bookImg"));	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			myClose();
		}
		return literature;
	}
	public int findCount(String name){
		initial();
		String sql = "select count(*) from book where bookName=?";
		int i = 0;
		try {
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, name);
			rs = pstm.executeQuery();
			while(rs.next()){
				i = rs.getInt(1);
			}
		}catch (SQLException e) {
			e.printStackTrace();
			return i;
		}finally{
			myClose();
		}
		return i;
	}
	public List<Literature> findAllList(){
		initial();
		List<Literature> list = new ArrayList<Literature>();
		String sql = "select * from book";
		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()){
				Literature literature = new Literature();
				literature.setName(rs.getString("bookName"));
				literature.setPrice(rs.getDouble("bookPrice"));
				literature.setNumber(rs.getInt("bookCount"));
				literature.setImgpath(rs.getString("bookImg"));
				list.add(literature);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			myClose();
		}
		return list;
	}
}
