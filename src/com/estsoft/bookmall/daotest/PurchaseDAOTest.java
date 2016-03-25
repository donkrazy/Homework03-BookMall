package com.estsoft.bookmall.daotest;

import java.util.List;

import com.estsoft.bookmal.vo.PurchaseVO;
import com.estsoft.bookmal.vo.Purchase_detailVO;
import com.estsoft.bookmall.dao.PurchaseDAO;

public class PurchaseDAOTest {

	public static void insertTest(){
		PurchaseVO purchaseVO = new PurchaseVO();
		PurchaseDAO purchaseDAO = new PurchaseDAO();
		purchaseVO.setUser_id(1);
		purchaseVO.setAddress("your home");
		purchaseVO.setDate("1999-3-3");
		purchaseVO.setPrice(10000);
		
		Purchase_detailVO purchase_detailVO = new Purchase_detailVO();
		purchase_detailVO.setPurchase_id(1);	
		purchase_detailVO.setBook_id(1);
		purchase_detailVO.setQuantity(2);
		
		purchaseDAO.insert( purchaseVO, purchase_detailVO );
	}
	
	public static void getListTest() {
		List<PurchaseVO> list = new PurchaseDAO().getList();
		for( PurchaseVO purchaseVO : list ) {
			System.out.println( purchaseVO );
		}
	}
	
	public static void getDetailListTest() {
		List<Purchase_detailVO> list = new PurchaseDAO().getDetailList();
		for( Purchase_detailVO purchase_detailVO : list ) {
			System.out.println( purchase_detailVO );
		}
	}
}
