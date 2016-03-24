package com.estsoft.bookmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.estsoft.bookmal.vo.UserVO;

public class UserDAO {
	public UserVO get(  int id ) {
		UserVO userVO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = Utils.getConnection();
			
			//3. Statement 준비
			String sql =
					"      SELECT a.id " +
					"       FROM users a" +
					" where a.id=?"		;
			pstmt = conn.prepareStatement( sql );
			
			//4. bind
			pstmt.setInt( 1, id );
			
			//5. SQL 실행
			rs = pstmt.executeQuery();
			if( rs.next() ) {
				int no = rs.getInt( 1 );
				String name = rs.getString( 2 );
				String phone_num = rs.getString( 3 );
				String email = rs.getString( 4 );
				userVO = new UserVO();
				userVO.setId( no );
				userVO.setPhone_num( phone_num );
				userVO.setName(name);
				userVO.setEmail(email);
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
		
		return userVO;
	}
	
	
	public void insert( UserVO userVO ) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = Utils.getConnection();
			
			//3. Statement 준비
			String sql = "insert into users values(  null, ?, ?, ? )";
			pstmt = conn.prepareStatement(sql);
			
			//4. bind
			pstmt.setString( 1, userVO.getName() );
			pstmt.setString( 2, userVO.getPhone_num() );
			pstmt.setString( 3, userVO.getEmail() );
			
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
	
	public List<UserVO> getList() {
		List<UserVO> list = new ArrayList<UserVO>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = Utils.getConnection();
			stmt = conn.createStatement();
			String sql =
					"      SELECT * " +
					"       FROM Users a" +
                    " ORDER BY a.id ASC";
			rs = stmt.executeQuery( sql );
			while( rs.next() ) {
				int no = rs.getInt( 1 );
				String name = rs.getString( 2 );
				String phone_num = rs.getString( 3 );
				String email = rs.getString( 4 );
				UserVO userVO = new UserVO();
				userVO.setId( no );
				userVO.setPhone_num( phone_num );
				userVO.setName(name);
				userVO.setEmail(email);
				
				list.add( userVO );
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
