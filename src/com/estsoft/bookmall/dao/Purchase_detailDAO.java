package com.estsoft.bookmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.estsoft.bookmal.vo.Purchase_detailVO;

public class Purchase_detailDAO {
	public Purchase_detailVO get(  int id ) {
		Purchase_detailVO purchase_detailVO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = Utils.getConnection();
			
			//3. Statement 준비
			String sql =
					"      SELECT a.id " +
					"       FROM purchase_detail a" +
					" where a.id=?"		;
			pstmt = conn.prepareStatement( sql );
			
			//4. bind
			pstmt.setInt( 1, id );
			
			//5. SQL 실행
			rs = pstmt.executeQuery();
			if( rs.next() ) {
				int purchase_id = rs.getInt( 1 );
				int book_id = rs.getInt( 2 );
				int quantity = rs.getInt( 3 );
				purchase_detailVO = new Purchase_detailVO();
				purchase_detailVO.setBook_id(book_id);
				purchase_detailVO.setQuantity(quantity);
				purchase_detailVO.setPurchase_id(purchase_id);
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
		
		return purchase_detailVO;
	}
	
	
	public void insert( Purchase_detailVO purchase_detailVO ) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = Utils.getConnection();
			
			//3. Statement 준비
			String sql = "insert into purchase_detail values( ?, ?, ? )";
			pstmt = conn.prepareStatement(sql);
			
			//4. bind
			pstmt.setInt( 2, purchase_detailVO.getBook_id() );
			pstmt.setInt( 1, purchase_detailVO.getPurchase_id() );
			pstmt.setInt( 3, purchase_detailVO.getQuantity());
			
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
	
	public List<Purchase_detailVO> getList() {
		List<Purchase_detailVO> list = new ArrayList<Purchase_detailVO>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = Utils.getConnection();
			stmt = conn.createStatement();
			String sql =
					"      SELECT * " +
					"       FROM purchase_detail a" +
                    " ORDER BY a.book_id ASC";
			rs = stmt.executeQuery( sql );
			while( rs.next() ) {
				int purchase_id = rs.getInt( 1 );
				int book_id = rs.getInt( 2 );
				int quantity = rs.getInt( 3 );
				Purchase_detailVO purchase_detailVO = new Purchase_detailVO();
				purchase_detailVO.setBook_id(book_id);
				purchase_detailVO.setQuantity(quantity);
				purchase_detailVO.setPurchase_id(purchase_id);
				
				list.add( purchase_detailVO );
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
