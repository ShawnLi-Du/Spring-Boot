package com.du.reponsitory;

import java.util.List;

import com.du.model.RegisterBean;

public interface RegisterDao {

	List<RegisterBean> email(String mail);

	Integer register(String mail, String passwords);

	Integer search(String mail, String passwords);

}
