package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.vo.UserVO;

public class UserDAO {
	
	// DB環境取得
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/test1DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	

	// 查詢是否有同樣的username
	public boolean hasSameUsername(String username) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement("select * from userInfo where username = ?;");

			pstmt.setString(1,username);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				return true;
			}
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return false;
	}
	
	
	//------新增註冊資料-------------
	//	username : unique name
	//	pwd : 密碼
	public boolean insertNewUser(String username , String pwd) throws Exception{

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement("insert into userInfo (userId,username,pwd,signdate) values (?, ? , ? , ? );");
			
			Date date = new Date();
			pstmt.setString(1, username);
			pstmt.setString(2, username);
			pstmt.setString(3, pwd);
			pstmt.setTimestamp(4, new Timestamp(date.getTime()));

			pstmt.executeUpdate();
			
			return true;

		} catch (SQLException se) {
			se.printStackTrace();
			return false;
			// 關閉連線
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}
	
	
    
}
