package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.ConnectionPool;
import entity.User;

public class UserDao {
	ConnectionPool pool=null;
    Connection con =null;
	public UserDao(){
		try{   
            pool = ConnectionPool.getInstance();
            con = pool.getConnection();
        }catch(Exception se ){   
            System.out.println("���ݿ�����ʧ�ܣ�");   
            se.printStackTrace() ;   
             }   
	}
	public boolean verifyUser(User user){
        try{   
        	String sql = "select password from user where user.user = ? ";
        	PreparedStatement ps= con.prepareStatement(sql);
        	ps.setString(1, user.getUserName());
        	ResultSet rs = ps.executeQuery();
        	while(rs.next())
        	{
        		String passwd=rs.getString(1);
        		String psdCmp=user.getPassword();
        		System.out.println("result: "+passwd+" "+psdCmp);
        		if(passwd.equals(psdCmp)){
        			return true;}
        		else {
        			return false;}
        	}
        }catch(SQLException se ){   
            System.out.println("�û�ʶ��ʧ�ܣ�");   
            se.printStackTrace() ;   
             }   
		return false;
} 
}