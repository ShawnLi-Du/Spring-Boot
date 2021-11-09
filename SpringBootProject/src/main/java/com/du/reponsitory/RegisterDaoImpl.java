package com.du.reponsitory;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.du.model.RegisterBean;

@Repository
public class RegisterDaoImpl implements RegisterDao {

	@Autowired
	EntityManager entityManager;

	RegisterBean registerBean;

	// 查詢帳號
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<RegisterBean> email(String mail) {
		String hql = "FROM RegisterBean WHERE EMAIL=:EMAIL";
		List query = entityManager.createQuery(hql,RegisterBean.class).setParameter("EMAIL", mail).getResultList();
		return query;
	}

//-------------------------------------------------------------
	// 註冊資料
	@Override
	public Integer register(String mail, String password) {
		String hql = "INSERT INTO Register (EMAIL, PASSWORD) VALUES (:EMAIL, :PASSWORD)";
		Query query = entityManager.createNativeQuery(hql);
		int result = query.setParameter("EMAIL", mail).setParameter("PASSWORD", password).executeUpdate();
		return result;

	}

//	--------------------------------------------------
	// 密碼更新
	@Override
	public Integer search(String mail, String password) {
		String hql = "UPDATE Register SET PASSWORD = :password WHERE EMAIL = :email";
		Query query = entityManager.createNativeQuery(hql);
		int count = query.setParameter("password", password).setParameter("email", mail).executeUpdate();
		return count;
	}

}
