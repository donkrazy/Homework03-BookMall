package com.estsoft.bookmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.estsoft.bookmal.vo.BookVO;
import com.estsoft.bookmal.vo.PurchaseVO;
import com.estsoft.bookmal.vo.Purchase_detailVO;

public class PurchaseDAO {
	public PurchaseVO get(int id) {
		PurchaseVO purchaseVO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = Utils.getConnection();

			// 3. Statement 준비
			String sql = "      SELECT a.id " + "       FROM purchases a" + " where a.id=?";
			pstmt = conn.prepareStatement(sql);

			// 4. bind
			pstmt.setInt(1, id);

			// 5. SQL 실행
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int no = rs.getInt(1);
				int user_id = rs.getInt(2);
				String address = rs.getString(3);
				String date = rs.getString(4);
				int price = rs.getInt(5);
				purchaseVO = new PurchaseVO();
				purchaseVO.setId(no);
				purchaseVO.setUser_id(user_id);
				purchaseVO.setAddress(address);
				purchaseVO.setDate(date);
				purchaseVO.setPrice(price);
			}
		} catch (SQLException ex) {
			System.out.println("SQL 오류:" + ex);
		} finally {
			// 6. 자원정리(clean-up)
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		return purchaseVO;
	}

	public void insert(PurchaseVO purchaseVO, Purchase_detailVO purchase_detailVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		try {
			conn = Utils.getConnection();

			// 3. Statement 준비
			String sql = "insert into purchases values( null, ?, ?, ?, ? )";
			pstmt = conn.prepareStatement(sql);

			// 4. bind
			pstmt.setInt(1, purchaseVO.getUser_id());
			pstmt.setString(2, purchaseVO.getAddress());
			pstmt.setString(3, purchaseVO.getDate());
			pstmt.setInt(4, purchaseVO.getPrice());
			// 5. SQL 실행
			pstmt.executeUpdate();
			
			//주문도서 테이블에도 insert
			String sql2 = "insert into purchase_detail values(?, ?, ?)";
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setInt(1, purchase_detailVO.getPurchase_id());
			pstmt2.setInt(2, purchase_detailVO.getBook_id());
			pstmt2.setInt(3, purchase_detailVO.getQuantity());
			pstmt2.executeUpdate();



		} catch (SQLException ex) {
			System.out.println("SQL 오류:" + ex);
		} finally {
			// 6. 자원정리(clean-up)
			try {
				if (pstmt != null && pstmt2 !=null ) {
					pstmt.close();
					pstmt2.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
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
	
	public List<Purchase_detailVO> getDetailList() {
		List<Purchase_detailVO> list = new ArrayList<Purchase_detailVO>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = Utils.getConnection();
			stmt = conn.createStatement();
			String sql =
					"      SELECT * " +
					"       FROM purchase_detail a, purchases b, books c" +
					"		WHERE b.id=a.purchase_id and c.id=a.book_id"+
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
