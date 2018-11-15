package datebases;

import Book.User;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

public class UserBase extends Base {
	
	public boolean add(User user){
		initial();
		String sql = "insert into userinfo(userName,passWord) values(?,?)";
		try {
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, user.getUserName());
			pstm.setString(2, user.getPassWord());
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
		String sql = "delete from userinfo where userName=?";
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
	public boolean update(User user){
		initial();
		String sql = "update userinfo set passWord=? where userName=?";
		try {
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, user.getPassWord());
			pstm.setString(2, user.getUserName());
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
	public User find(String name){
		initial();
		String sql = "select * from userinfo where userName=?";
		User user = null;
		try {
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, name);
			rs = pstm.executeQuery();
			while(rs.next()){
				user = new User();
				user.setUserName(rs.getString("userName"));
				user.setPassWord(rs.getString("passWord"));	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			myClose();
		}
		return user;
	}
	public int findCount(String name){
		initial();
		String sql = "select count(*) from userinfo where userName=?";
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
	public List<User> findAllList(){
		initial();
		List<User> list = new ArrayList<User>();
		String sql = "select * from userinfo";
		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()){
				User user = new User();
				user.setUserName(rs.getString("userName"));
				user.setPassWord(rs.getString("passWord"));
				list.add(user);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			myClose();
		}
		return list;
	}
}
