package com.estsoft.bookmall.daotest;

import java.util.List;

import com.estsoft.bookmal.vo.PurchaseVO;
import com.estsoft.bookmall.dao.PurchaseDAO;

public class PurchaseDAOTest {

	public static void main(String[] args) {
		//1. insert test
		insertTest();
		getListTest();
	}
	
	public static void insertTest(){
		PurchaseVO purchaseVO = new PurchaseVO();
		PurchaseDAO purchaseDAO = new PurchaseDAO();
		purchaseVO.setUser_id(1);
		purchaseVO.setAddress("your home");
		purchaseVO.setDate("1999-3-3");
		purchaseVO.setPrice(10000);
		purchaseDAO.insert( purchaseVO );
	}
	
	public static void getListTest() {
		List<PurchaseVO> list = new PurchaseDAO().getList();
		for( PurchaseVO purchaseVO : list ) {
			System.out.println( purchaseVO );
		}
	}
}
