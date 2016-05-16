package com.conting.service;

import java.util.List;

import com.conting.dao.CustomerDAO;
import com.conting.vo.CustomerVO;

/**
 * 
 * @since 	2016. 2. 13.
 * @version	
 * @author 	Yoon JiSoo
 */
public class CustomerService {
	CustomerDAO dao = new CustomerDAO();
	
	public boolean create(CustomerVO vo) throws Exception {
		return dao.create(vo);
	}
	
	public boolean delete(String profileName) throws Exception {
		return dao.delete(profileName);
	}
	
	public boolean update(CustomerVO vo) throws Exception {
		return dao.update(vo);
	}
	
	public CustomerVO search(String profileName) throws Exception {
		return dao.search(profileName);
	}
	
	public List<CustomerVO> searchVisitor(String visit) throws Exception {
		return dao.searchVisitor(visit);
	}
}
