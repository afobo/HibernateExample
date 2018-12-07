package com.github.afobo;

import com.github.afobo.model.Product;
import com.github.afobo.model.Service;
import org.hibernate.Session;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

    public static final int MAX_VALUE = 99999;

    public static void main(String[] args) {
        fillData();
//        readDataByRID();
//        readDataByOID();
        readDataByOID_WithGIDFilter(1);
        readDataByOID_WithGIDFilter(2);
        readDataByOID_WithGIDFilter(MAX_VALUE);

        System.exit(0);
    }

    private static void printProduct(Product p) {
        String serviceNames = p.getServices().stream()
                .map(Service::getName)
                .collect(Collectors.joining(",", "[", "]"));
        System.out.println("p.name = " + p.getName() + ", services = " + serviceNames);
    }

    private static void readDataByRID() {
        System.out.println("=== readDataByRID");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Product p1 = (Product) session.get(Product.class, 41);
        printProduct(p1);
    }

    private static void readDataByOID() {
        System.out.println("=== readDataByOID");
        Session session = HibernateUtil.getSessionFactory().openSession();
        List list = session.createQuery("from Product where oid = 50").list();
        for (Object o : list) {
            Product p = (Product) o;
            printProduct(p);
        }
    }

    private static void readDataByOID_WithGIDFilter(int gid) {
        System.out.println("=== readDataByOID_WithGIDFilter: " + gid);
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.enableFilter("gidFilter").setParameter("gid", gid);
        List list = session.createQuery("from Product where oid = 50").list();
        for (Object o : list) {
            Product p = (Product) o;
            printProduct(p);
            printServices(p.getServices());
        }
    }

    private static void printServices(Set<Service> services) {
        for (Service s : services) {
            String productNames = s.getProducts().stream()
                    .map(Product::getName)
                    .collect(Collectors.joining(",", "[", "]"));
            System.out.println("s.name = " + s.getName() + ", products = " + productNames);

        }
    }

    private static void fillData() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.createQuery("DELETE FROM Product").executeUpdate();
        session.createQuery("DELETE FROM Service").executeUpdate();


        Product p1 = new Product(40, 50, 1, 1, "Save");
        session.save(p1);
        Product p2 = new Product(41, 50, 2, 2, "Save1");
        session.save(p2);
        Product p3 = new Product(42, 50, 3, MAX_VALUE, "Save2");
        session.save(p3);

        Service s1 = new Service(80, 90, 1, 1, "Open", 50);
        session.save(s1);

        Service s2 = new Service(81, 91, 1, MAX_VALUE, "Close", 50);
        session.save(s2);

        Service s3 = new Service(82, 90, 2, MAX_VALUE, "Open1", 50);
        session.save(s3);

        session.getTransaction().commit();
    }
}
