package com.estsoft.bookmall.daotest;

import java.util.List;

import com.estsoft.bookmal.vo.BookVO;
import com.estsoft.bookmall.dao.BookDAO;

public class BookDAOTest {

	public static void insertTest(){
		BookVO bookVO = new BookVO();
		BookDAO bookDAO = new BookDAO();
		bookVO.setCategory_id(1);
		bookVO.setTitle("총균쇠");
		bookVO.setPrice(12221340);
		bookDAO.insert( bookVO );
	}
	
	public static void getListTest() {
		List<BookVO> list = new BookDAO().getList();
		for( BookVO bookVO : list ) {
			System.out.println( bookVO );
		}
	}

}
