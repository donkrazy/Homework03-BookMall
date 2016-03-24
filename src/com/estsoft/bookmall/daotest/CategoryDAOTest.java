package com.estsoft.bookmall.daotest;

import java.util.List;

import com.estsoft.bookmal.vo.CategoryVO;
import com.estsoft.bookmall.dao.CategoryDAO;

public class CategoryDAOTest {

	public static void main(String[] args) {
		//1. insert test
		insertTest();
		getListTest();
	}
	
	public static void insertTest(){
		CategoryVO categoryVO = new CategoryVO();
		CategoryDAO categoryDAO = new CategoryDAO();
		categoryVO.setName("인문");
		categoryDAO.insert( categoryVO );
	}
	
	public static void getListTest() {
		List<CategoryVO> list = new CategoryDAO().getList();
		for( CategoryVO categoryVO : list ) {
			System.out.println( categoryVO );
		}
	}
}
