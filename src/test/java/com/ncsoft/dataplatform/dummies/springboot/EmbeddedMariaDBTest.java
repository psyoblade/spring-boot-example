package com.ncsoft.dataplatform.dummies.springboot;

import ch.vorburger.exec.ManagedProcessException;
import ch.vorburger.mariadb4j.DB;
import ch.vorburger.mariadb4j.DBConfigurationBuilder;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class EmbeddedMariaDBTest {

    private static DB db;
    private static DBConfigurationBuilder config;

    @BeforeClass
    public static void beforeAll() {
        config = DBConfigurationBuilder.newBuilder();
        config.setPort(0); // 0 => autom. detect free port
        try {
            db = DB.newEmbeddedDB(config.build());
            db.start();
        } catch (java.lang.Exception exception) {
            exception.printStackTrace();
        }
    }

    @AfterClass
    public static void afterAll() {
    }

    @Test
    public void testHelloWorld() {
        System.out.println("hello world");
    }

    @Test
    public void testCRUD() {
        String dbName = "mariaDB4jTest"; // or just "test"
        if (!dbName.equals("test")) {
            // mysqld out-of-the-box already has a DB named "test"
            // in case we need another DB, here's how to create it first
            try {
                db.createDB(dbName);
            } catch (ManagedProcessException e) {
                e.printStackTrace();
            }
        }

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(EmbeddedMariaDBTest.config.getURL(dbName), "root", "");
            QueryRunner qr = new QueryRunner();

            // Should be able to create a new table
            qr.update(conn, "CREATE TABLE hello(world VARCHAR(100))");

            // Should be able to insert into a table
            qr.update(conn, "INSERT INTO hello VALUES ('Hello, world')");

            // Should be able to select from a table
            List<String> results = qr.query(conn, "SELECT * FROM hello",
                    new ColumnListHandler<String>());
            Assert.assertEquals(1, results.size());
            Assert.assertEquals("Hello, world", results.get(0));

            // Should be able to source a SQL file
            db.source("testSourceFile.sql", "root", null, dbName);
            db.source("testSourceFile.sql", "root", "", dbName);
            results = qr.query(conn, "SELECT * FROM hello", new ColumnListHandler<String>());
            System.out.println(results);

            Assert.assertEquals(4, results.size());
            Assert.assertEquals("Hello, world", results.get(0));
            Assert.assertEquals("Bonjour, monde", results.get(1));
            Assert.assertEquals("Hola, mundo", results.get(2));
        } catch (java.lang.Exception exception) {
            exception.printStackTrace();
        } finally {
            DbUtils.closeQuietly(conn);
        }
    }

    @Test
    public void testHello() {
        String one = "1";
        String two = "2";
        Assert.assertNotSame(one, two);
    }
}
