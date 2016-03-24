package com.estsoft.bookmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.estsoft.bookmal.vo.PurchaseVO;

public class PurchaseDAO {
	public PurchaseVO get(  int id ) {
		PurchaseVO purchaseVO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = Utils.getConnection();
			
			//3. Statement 준비
			String sql =
					"      SELECT a.id " +
					"       FROM purchases a" +
					" where a.id=?"		;
			pstmt = conn.prepareStatement( sql );
			
			//4. bind
			pstmt.setInt( 1, id );
			
			//5. SQL 실행
			rs = pstmt.executeQuery();
			if( rs.next() ) {
				int no = rs.getInt( 1 );
				int user_id = rs.getInt( 2 );
				String address = rs.getString( 3 );
				String date = rs.getString( 4 );
				int price = rs.getInt( 5 );
				purchaseVO = new PurchaseVO();
				purchaseVO.setId(no);
				purchaseVO.setUser_id(user_id);
				purchaseVO.setAddress(address);
				purchaseVO.setDate(date);
				purchaseVO.setPrice(price);
			}
		} catch( SQLException ex ) {
			System.out.println( "SQL 오류:" + ex );
		} finally {
			//6. 자원정리(clean-up)
			try {
				if( rs != null ) {
					rs.close();
				}
				if( pstmt != null ) {
					pstmt.close();
				}
				if( conn != null ) {
					conn.close();
				}
			} catch( SQLException ex ) {
				ex.printStackTrace();
			}
		}
		
		return purchaseVO;
	}
	
	
	public void insert( PurchaseVO purchaseVO ) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = Utils.getConnection();
			
			//3. Statement 준비
			String sql = "insert into purchases values( null, ?, ?, ?, ? )";
			pstmt = conn.prepareStatement(sql);
			
			//4. bind
			pstmt.setInt( 1, purchaseVO.getUser_id() );
			pstmt.setString( 2, purchaseVO.getAddress());
			pstmt.setString( 3, purchaseVO.getDate());
			pstmt.setInt( 4, purchaseVO.getPrice());
			
			//5. SQL 실행
			pstmt.executeUpdate();
			
		} catch( SQLException ex ) {
			System.out.println( "SQL 오류:" + ex );
		} finally {
			//6. 자원정리(clean-up)
			try {
				if( pstmt != null ) {
					pstmt.close();
				}
				if( conn != null ) {
					conn.close();
				}
			} catch( SQLException ex ) {
				ex.printStackTrace();
			}
		}		
	}
	
	public List<PurchaseVO> getList() {
		List<PurchaseVO> list = new ArrayList<PurchaseVO>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = Utils.getConnection();
			stmt = conn.createStatement();
			String sql =
					"      SELECT * " +
					"       FROM purchases a" +
                    " ORDER BY a.id ASC";
			rs = stmt.executeQuery( sql );
			while( rs.next() ) {
				int id = rs.getInt( 1 );
				int user_id = rs.getInt( 2 );
				String address = rs.getString( 3 );
				String date = rs.getString( 4 );
				int price = rs.getInt( 5 );
				
				PurchaseVO PurchaseVO = new PurchaseVO();
				PurchaseVO.setId(id);
				PurchaseVO.setUser_id(user_id);
				PurchaseVO.setAddress(address);
				PurchaseVO.setDate(date);
				PurchaseVO.setPrice(price);
				
				list.add( PurchaseVO );
			}
			
		} catch( SQLException ex ) {
			System.out.println( "error : " + ex );
		} finally {
			try {
				if( rs != null ) {
					rs.close();
				}
				if( stmt != null ) {
					stmt.close();
				}
				if( conn != null ) {
					conn.close();
				}
			} catch( SQLException ex ) {
				ex.printStackTrace();
			}
		}
		return list;
	}
}
