package com.du.servise;

import java.util.List;

import com.du.model.RegisterBean;


public interface RegisterServise {

	Object email(String mail);

	Integer register(String mail, String password);

	List<RegisterBean> input(String mail, String password);

	String search(String email);

	String registerServiseImpl(String password);

}
