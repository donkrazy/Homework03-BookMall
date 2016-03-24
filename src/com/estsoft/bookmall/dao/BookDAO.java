package com.estsoft.bookmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.estsoft.bookmal.vo.BookVO;

public class BookDAO {
	public BookVO get(  int id ) {
		BookVO bookVO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = Utils.getConnection();
			
			//3. Statement 준비
			String sql =
					"      SELECT a.id " +
					"       FROM books a" +
					" where a.id=?"		;
			pstmt = conn.prepareStatement( sql );
			
			//4. bind
			pstmt.setInt( 1, id );
			
			//5. SQL 실행
			rs = pstmt.executeQuery();
			if( rs.next() ) {
				int no = rs.getInt( 1 );
				String title = rs.getString( 2 );
				int seats_left = rs.getInt( 3 );
				bookVO = new BookVO();
				bookVO.setId( no );
				bookVO.setTitle( title );
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
		
		return bookVO;
	}
	
	
	public void insert( BookVO bookVO ) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = Utils.getConnection();
			
			//3. Statement 준비
			String sql = "insert into books values(  null, ?, ?, ? )";
			pstmt = conn.prepareStatement(sql);
			
			//4. bind
			pstmt.setInt( 1, bookVO.getCategory_id() );
			pstmt.setString( 2, bookVO.getTitle() );
			pstmt.setInt( 3, bookVO.getPrice() );
			
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
	
	public List<BookVO> getList() {
		List<BookVO> list = new ArrayList<BookVO>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = Utils.getConnection();
			stmt = conn.createStatement();
			String sql =
					"      SELECT * " +
					"       FROM books a" +
                    " ORDER BY a.id ASC";
			rs = stmt.executeQuery( sql );
			while( rs.next() ) {
				int no = rs.getInt( 1 );
				int category_id = rs.getInt( 2 );
				String title = rs.getString( 3 );
				int price = rs.getInt( 4 );
				
				BookVO bookVO = new BookVO();
				bookVO.setId(no);
				bookVO.setCategory_id(category_id);
				bookVO.setTitle(title);
				bookVO.setPrice(price);
				
				list.add( bookVO );
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
