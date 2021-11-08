package com.du.SpringBoot.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.du.SpringBoot.controller.SendEmail;
import com.du.SpringBoot.model.RegisterBean;
import com.du.SpringBoot.repository.RegisterDao;

@Transactional //有用到SQL 就要加
@Service
public class RegisterServiseImpl implements RegisterServise {
	
	@Autowired
	RegisterDao registerDao;

	// md5 加密建構子---------------------------------------------------------
	@Override
	public String registerServiseImpl(String password) {
		char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

		StringBuffer sb = null;
		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(password.getBytes("UTF-8"));
			// Hash計算, 產生128位的長整數
			byte[] bytes = messageDigest.digest();
			sb = new StringBuffer(bytes.length * 2);
			for (Byte b : bytes) {
				// 右移四位, 取字節中前四位轉換
				sb.append(hexDigits[(b >> 4) & 0x0f]);
				// 取字節中後四位轉換
				sb.append(hexDigits[b & 0x0f]);
			}
			// 輸出 602965cf9dd0e80ca28269257a6aba87
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	// email 驗證---------------------------------------------------------
	@Override
	public String email(String mail) {

		System.out.println("mail 2 =" + mail);
		List<RegisterBean> register = registerDao.email(mail); // SQL DB
		System.out.println("register = " + register);
		if (register.size() < 1) {
			return "0"; // 可新增
		} else {
			return "1"; // 重複
		}
	}

	// 註冊---------------------------------------------------------
	@Override
	public Integer register(String mail, String password) {

		String passwords = registerServiseImpl(password); // md5
		Integer str1 = registerDao.register(mail, passwords); // 存入sql
		return str1;
	}

	// 登入---------------------------------------------------------
	@Override
	public List<RegisterBean> input(String mail, String password) {

		String passwords = registerServiseImpl(password); // 輸入 password轉md5
		List<RegisterBean> mailIndex = registerDao.email(mail); // 跟sql比對是否存在, 不存在 return 0

		List<RegisterBean> listError = new ArrayList<>();
		RegisterBean bs = new RegisterBean();
		bs.setEmail("error");
		listError.add(bs);

		if (mailIndex.size() != 0) { // 如果帳號存在

			if (mailIndex.get(0).getEmail().equals(mail) && mailIndex.get(0).getPassword().equals(passwords)) {
				return mailIndex;
			} else {
				return listError;
			}
		} else {
			return listError;
		}
	}

	// 找回密碼---------------------------------------------------------
	@Override
	public Integer search(String mail) {

		String newPw = "";

		for (int j = 0; j < 4; j++) {
			Random random = new Random();
			String pw = String.valueOf(random.nextInt(4) + 1);
			newPw += pw;
		}

		String passwords = registerServiseImpl(newPw); // 轉md5
		SendEmail send = new SendEmail();
		send.SendMail(newPw);

		Integer count = registerDao.search(mail, passwords); // 修改 sql 密碼

		return count;

	}

}
