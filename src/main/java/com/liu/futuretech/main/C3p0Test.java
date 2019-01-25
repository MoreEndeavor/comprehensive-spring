package com.liu.futuretech.main;

import com.liu.futuretech.dao.TestDAO;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.*;

public class C3p0Test {

    final static String driver = "com.mysql.jdbc.Driver";
    final static String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/db_shiro?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
    final static String user = "root";
    final static String password = "root";
    final static int initialSize = 5;
    final static int minPoolSize = 10;
    final static int maxPoolSize = 50;
    final static int maxIdleTime = 100000;
    final static int retryAttempts = 10;
    final static int acquireIncrement = 5;
    final static ComboPooledDataSource c3p0DataSource = getC3p0DataSource();

    final static int count = 100;

    /**
     * 获取数据源
     * @return
     */
    public static ComboPooledDataSource getC3p0DataSource() {

        ComboPooledDataSource cpds = new ComboPooledDataSource();

        try {
            cpds.setDriverClass(driver);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }

        cpds.setJdbcUrl(jdbcUrl);
        cpds.setUser(user);
        cpds.setPassword(password);
        cpds.setInitialPoolSize(initialSize);
        cpds.setMinPoolSize(minPoolSize);
        cpds.setMaxPoolSize(maxPoolSize);
        cpds.setMaxIdleTime(maxIdleTime);
        cpds.setAcquireRetryAttempts(retryAttempts);
        cpds.setTestConnectionOnCheckin(false);
        cpds.setTestConnectionOnCheckout(false);
        return cpds;
    }

    public static void queryC3p0(TestDAO testDao, ComboPooledDataSource ds, int count) throws SQLException {

        //查询100次以初始化连接池
        for (int i = 0; i < 100; i++) {
            testDao.query(ds.getConnection());
        }
        //开始时间
        long startMillis = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            testDao.query(ds.getConnection());
        }
        //结束时间
        long endMillis = System.currentTimeMillis();

        System.out.print("查询" + count + "次，" + "耗时：");
        System.out.println(endMillis - startMillis);
    }

    public static void main(String[] args) throws IOException, SQLException {
        TestDAO testDAO = new TestDAO();
        System.out.println("查询次数为：" + count);
        System.out.println();
        System.out.println("==========================c3p0 测试开始==========================");
        /*for (int i = 0; i < count; i++) {
            queryC3p0(testDAO, c3p0DataSource, count);
        }*/
        queryC3p0(testDAO, c3p0DataSource, count);
        System.out.println("==========================c3p0 测试结束==========================");


        //使用JDBC
        try {
            long start = System.currentTimeMillis();
            TestDAO testDao = new TestDAO();
            for (int i = 0; i < count; i++) {
                Class.forName(driver);
                Connection connection = DriverManager.getConnection(jdbcUrl, user, password);
                testDao.query(connection);
            }
            long end = System.currentTimeMillis();
            System.out.println("十次常规耗时：" + (end - start));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}