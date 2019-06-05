package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class NameDAO {
	
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
	

	// 查詢是否有同樣的usename並取其使用次數
	public int getUsetimeOfName(String name) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int usetime = 0;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement("select usetime from nameUse where name = ?;");

			pstmt.setString(1,name);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				usetime = rs.getInt("usetime");
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
		
		return usetime;
	}
	
	
	
	
		//------新增名稱-------------
		public void insertNewName(String name) throws Exception{

			Connection con = null;
			PreparedStatement pstmt = null;

			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement("insert into nameUse (name,usetime) values (?, ?);");
				
				pstmt.setString(1, name);
				pstmt.setInt(2, 1);

				pstmt.executeUpdate();
				
			} catch (SQLException se) {
				se.printStackTrace();
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
		
		
		
		//修改同樣名稱的次數
		public void updateTimes(String name , int count) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {
				
				con = ds.getConnection();
				pstmt = con.prepareStatement("update nameUse set usetime = ? where name = ? ;");

				pstmt.setInt(1, count);
				pstmt.setString(2, name);

				pstmt.executeUpdate();
				
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. " + se.getMessage());
				
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
