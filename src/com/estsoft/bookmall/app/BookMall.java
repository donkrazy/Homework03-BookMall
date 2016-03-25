package com.estsoft.bookmall.app;

import com.estsoft.bookmall.daotest.BookDAOTest;
import com.estsoft.bookmall.daotest.CartDAOTest;
import com.estsoft.bookmall.daotest.CategoryDAOTest;
import com.estsoft.bookmall.daotest.PurchaseDAOTest;
import com.estsoft.bookmall.daotest.UserDAOTest;

public class BookMall {

	public static void main(String[] args) {

		// 1. MemberDao의  회원 생성
		UserDAOTest.insertTest();
		// 2. MemberDao의  회원 리스트
		UserDAOTest.getListTest();

		// 3. CategoryDao의 카테고리 생성
		CategoryDAOTest.insertTest();
		// 4. CategoryDao의 카테고리 리스트
		CategoryDAOTest.getListTest();
		
		// 5. BookDao의 서적(상품) 생성
		BookDAOTest.insertTest();
		// 6. BookDao의  서적(상품) 리스트
		BookDAOTest.getListTest();

		// 7. CartDao의 장바구니 정보 생성
		CartDAOTest.insertTest();
		// 8. CartDao의  장바구니 내용 리스트
		CartDAOTest.getListTest();
		
		// 9. OrderDao의 주문 생성
		PurchaseDAOTest.insertTest();
		//10. OrderDao의  주문 리스트
		PurchaseDAOTest.getListTest();
		
		//11. OrderDao의 주문 서적 리스트  
		PurchaseDAOTest.getDetailListTest();
	}

}
