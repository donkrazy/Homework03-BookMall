package com.estsoft.bookmall.daotest;

import java.util.List;

import com.estsoft.bookmal.vo.CartVO;
import com.estsoft.bookmall.dao.CartDAO;

public class CartDAOTest {

	public static void insertTest(){
		CartVO cartVO = new CartVO();
		CartDAO cartDAO = new CartDAO();
		cartVO.setQuantity(1);
		cartVO.setBook_id(1);
		cartVO.setUser_id(1);
		cartDAO.insert( cartVO );
	}
	
	public static void getListTest() {
		List<CartVO> list = new CartDAO().getList();
		for( CartVO cartVO : list ) {
			System.out.println( cartVO );
		}
	}
}
