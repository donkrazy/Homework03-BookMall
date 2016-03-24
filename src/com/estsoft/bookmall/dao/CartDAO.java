package com.estsoft.bookmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.estsoft.bookmal.vo.CartVO;

public class CartDAO {
	public CartVO get(  int id ) {
		CartVO cartVO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = Utils.getConnection();
			
			//3. Statement 준비
			String sql =
					"      SELECT a.id " +
					"       FROM carts a" +
					" where a.id=?"		;
			pstmt = conn.prepareStatement( sql );
			
			//4. bind
			pstmt.setInt( 1, id );
			
			//5. SQL 실행
			rs = pstmt.executeQuery();
			if( rs.next() ) {
				int no = rs.getInt( 1 );
				int quantity = rs.getInt( 2 );
				int user_id = rs.getInt( 3 );
				cartVO = new CartVO();
				cartVO.setBook_id(no);
				cartVO.setQuantity(quantity);
				cartVO.setUser_id(user_id);
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
		
		return cartVO;
	}
	
	
	public void insert( CartVO cartVO ) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = Utils.getConnection();
			
			//3. Statement 준비
			String sql = "insert into carts values( ?, ?, ? )";
			pstmt = conn.prepareStatement(sql);
			
			//4. bind
			pstmt.setInt( 1, cartVO.getQuantity() );
			pstmt.setInt( 2, cartVO.getBook_id() );
			pstmt.setInt( 3, cartVO.getUser_id());
			
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
	
	public List<CartVO> getList() {
		List<CartVO> list = new ArrayList<CartVO>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = Utils.getConnection();
			stmt = conn.createStatement();
			String sql =
					"      SELECT * " +
					"       FROM carts a" +
                    " ORDER BY a.book_id ASC";
			rs = stmt.executeQuery( sql );
			while( rs.next() ) {
				int quantity = rs.getInt( 1 );
				int book_id = rs.getInt( 2 );
				int user_id = rs.getInt( 3 );
				
				CartVO cartVO = new CartVO();
				cartVO.setQuantity(quantity);
				cartVO.setBook_id(book_id);
				cartVO.setUser_id(user_id);
				
				list.add( cartVO );
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
