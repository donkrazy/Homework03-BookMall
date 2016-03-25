package com.estsoft.bookmall.daotest;

import java.util.List;

import com.estsoft.bookmal.vo.UserVO;
import com.estsoft.bookmall.dao.UserDAO;

public class UserDAOTest {
	
	public static void insertTest(){
		UserVO userVO = new UserVO();
		UserDAO userDAO = new UserDAO();
		userVO.setName("이승현");
		userVO.setPhone_num("2342352523");
		userVO.setEmail("ddfdf@dfkj.com");
		userDAO.insert( userVO );
	}
	public static void getListTest() {
		List<UserVO> list = new UserDAO().getList();
		for( UserVO userVO : list ) {
			System.out.println( userVO );
		}
	}
}
