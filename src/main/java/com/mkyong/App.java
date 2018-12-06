package com.mkyong;

import com.mkyong.user.Product;
import com.mkyong.util.HibernateUtil;
import org.hibernate.Session;

public class App {
	public static void main(String[] args) {
        fillData();
    }

    private static void fillData() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.createQuery("DELETE FROM Product").executeUpdate();
        session.save(new Product(40, 50, 1, 1, "Save"));
        session.save(new Product(41, 50, 2, 2, "Save1"));
        session.save(new Product(42, 50, 3, 99999, "Save2"));
        session.getTransaction().commit();
    }
}
