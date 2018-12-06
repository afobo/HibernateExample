package com.github.afobo;

import org.hibernate.Session;

import java.util.List;

public class Main {
	public static void main(String[] args) {
        fillData();
        readDataByRID();
        readDataByOID();
    }

    private static void readDataByRID() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Product p1 = (Product) session.get(Product.class, 41);
        System.out.println("p1.getName() = " + p1.getName());
    }

    private static void readDataByOID() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List list = session.createQuery("from Product where oid = 50").list();
        for (Object o : list) {
            Product p = (Product) o;
            System.out.println("p.getName() = " + p.getName());
        }
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
