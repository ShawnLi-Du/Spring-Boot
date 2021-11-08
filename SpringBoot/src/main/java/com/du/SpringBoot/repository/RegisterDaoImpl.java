package com.du.SpringBoot.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.du.SpringBoot.model.RegisterBean;

@Repository("RegisterDao")
public class RegisterDaoImpl implements RegisterDao {

//	private String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
//	private String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=jdbc";
//	private String USER = "sa";
//	private String PASSWORD = "";

	@Autowired
	SessionFactory sessionFactory;

//	@Autowired
	EntityManager entityManager;

//	Connection conn;

	RegisterBean registerBean;

//	public RegisterDaoImpl() {
//		try {
//			Class.forName(JDBC_DRIVER);
//			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}
//	}

	// 查詢帳號
//	@Override
//	public List<Register> email(String mail) {
//
//		ArrayList<Register> list = new ArrayList<>();
//		final String SQL = "SELECT * FROM REGISTER E WHERE E.MAIL=?";
//		try (PreparedStatement ps = conn.prepareStatement(SQL);) {
//			ps.setString(1, mail);
//			ResultSet rs = ps.executeQuery();
//
//			Register bss = new Register();
//			while (rs.next()) {
//				bss.setEmail(rs.getString("mail"));
//				bss.setPassword(rs.getString("password"));
//				list.add(bss);
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//		}
//		return list;
//	}

	@Override
	public List<RegisterBean> email(String mail) {
		String hql = "FROM Register WHERE EMAIL=:EMAIL";
		List query = sessionFactory.getCurrentSession().createQuery(hql).setParameter("EMAIL", mail).list();
		return query;

	}

//-------------------------------------------------------------
	// 註冊資料
	@Override
//	public Integer register(String mail, String password) {
//		String SQL = "INSERT INTO REGISTER VALUES (?, ?)";
//		int count = 0;
//		try (PreparedStatement pstmt = conn.prepareStatement(SQL);) {
//			pstmt.setString(1, mail);
//			pstmt.setString(2, password);
//			count = pstmt.executeUpdate();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//		}
//		return count;
//	}
	public Integer register(String mail, String password) {
		String hql = "INSERT INTO Register (EMAIL, PASSWORD) VALUES (:EMAIL, :PASSWORD)";
		Query query = sessionFactory.getCurrentSession().createNativeQuery(hql).setParameter("EMAIL", mail).setParameter("PASSWORD", password);
		int result = query.executeUpdate();
		System.out.println("result " + result);
		return result;

	}

//	--------------------------------------------------
	// 密碼更新
//	@Override
//	public Integer search(String mail, String passwords) {
//		String SQL = "UPDATE REGISTER SET PASSWORD = ? WHERE MAIL = ?";
//		int count = 0;
//		try (PreparedStatement pstmt = conn.prepareStatement(SQL);) {
//			pstmt.setString(1, passwords);
//			pstmt.setString(2, mail);
//			count = pstmt.executeUpdate();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//		}
//		System.out.println("count = " + count);
//		return count;
//	}

	@Override
	public Integer search(String mail, String password) {
		String hql = "UPDATE Register SET PASSWORD = :password WHERE EMAIL = :email";
		Query query = sessionFactory.getCurrentSession().createNativeQuery(hql);
		int count = query.setParameter("password", password).setParameter("email", mail).executeUpdate();
		return count;
	}

}
