package com.github.afobo;

import com.github.afobo.model.Product;
import com.github.afobo.model.Service;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.github.afobo.HibernateUtil.getSessionFactory;
import static com.github.afobo.model.Constants.GID_FILTER;
import static com.github.afobo.model.Constants.GID_FILTER_PARAM;
import static com.github.afobo.model.Constants.MAX_VALUE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class MainTest {

    private Session session;

    @BeforeClass
    public static void setUp() {
        fillData();
    }

    @After
    public void tearDown() {
        if (session != null) {
            session.close();
        }
    }

    @Test
    public void testReadProductByGid_1() {
        Product p = readProductByGid(50, 1);
        assertEquals(40, p.getRecordId());
        assertEquals("Save", p.getName());
        checkServiceNames(p.getServices(), "Open", "Close");
        for (Service s : p.getServices()) {
            checkServiceToProductReference(s, p);
        }
    }

    @Test
    public void testReadProductByGid_2() {
        Product p = readProductByGid(50, 2);
        assertEquals(41, p.getRecordId());
        assertEquals("Save1", p.getName());
        checkServiceNames(p.getServices(), "Open1", "Close");
        for (Service s : p.getServices()) {
            checkServiceToProductReference(s, p);
        }
    }

    @Test
    public void testReadProductByGid_3() {
        Product p = readProductByGid(50, 3);
        assertEquals(42, p.getRecordId());
        assertEquals("Save2", p.getName());
        checkServiceNames(p.getServices(), "Open1", "Close");
        for (Service s : p.getServices()) {
            checkServiceToProductReference(s, p);
        }
    }

    @Test
    public void testReadProductByGid_4() {
        Product p = readProductByGid(50, 4);
        assertEquals(42, p.getRecordId());
        assertEquals("Save2", p.getName());
        checkServiceNames(p.getServices(), "Open1", "Close");
        for (Service s : p.getServices()) {
            checkServiceToProductReference(s, p);
        }
    }

    @Test
    public void testReadProductByGid_MAX_VALUE() {
        Product p = readProductByGid(50, MAX_VALUE);
        assertEquals(42, p.getRecordId());
        assertEquals("Save2", p.getName());
        checkServiceNames(p.getServices(), "Open1", "Close");
        for (Service s : p.getServices()) {
            checkServiceToProductReference(s, p);
        }
    }

    /////////////////
    // Test utilities
    /////////////////
    private Product readProductByGid(int objectId, int gid) {
        session = openSessionWithGidFilter(gid);
        Query query = session.createQuery("from Product where objectId = :objectId").setParameter("objectId", objectId);
        List productRecords = query.list();
        assertEquals(1, productRecords.size());
        return (Product) productRecords.get(0);
    }

    private void checkServiceNames(Set<Service> actualServices, String... expectedServiceNames) {
        Set<String> expected = new HashSet<>(Arrays.asList(expectedServiceNames));
        Set<String> actual = actualServices.stream().map(Service::getName).collect(Collectors.toSet());
        assertEquals(expected, actual);
    }

    private void checkServiceToProductReference(Service s, Product p) {
        assertEquals(1, s.getProducts().size());
        assertSame(p, s.getProduct());
    }

    private static void fillData() {
        Session session = getSessionFactory().openSession();
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


    private Session openSessionWithGidFilter(int gid) {
        Session session = getSessionFactory().openSession();
        session.enableFilter(GID_FILTER).setParameter(GID_FILTER_PARAM, gid);
        return session;
    }

}