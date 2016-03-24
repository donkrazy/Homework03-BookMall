package com.estsoft.bookmall.daotest;

import java.util.List;

import com.estsoft.bookmal.vo.Purchase_detailVO;
import com.estsoft.bookmall.dao.Purchase_detailDAO;

public class Purchase_detailDAOTest {
	public static void main(String[] args) {
		//1. insert test
		insertTest();
		getListTest();
	}
	
	public static void insertTest(){
		Purchase_detailVO purchase_detailVO = new Purchase_detailVO();
		Purchase_detailDAO purchase_detailDAO = new Purchase_detailDAO();
		purchase_detailVO.setPurchase_id(1);
		purchase_detailVO.setBook_id(1);
		purchase_detailVO.setQuantity(1);
		purchase_detailDAO.insert( purchase_detailVO );
	}

	public static void getListTest() {
		List<Purchase_detailVO> list = new Purchase_detailDAO().getList();
		for( Purchase_detailVO purchaseVO : list ) {
			System.out.println( purchaseVO );
		}
	}
}
