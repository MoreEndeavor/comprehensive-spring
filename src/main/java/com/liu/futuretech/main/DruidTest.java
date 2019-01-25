package com.liu.futuretech.main;

import com.alibaba.druid.pool.DruidDataSource;
import com.liu.futuretech.dao.TestDAO;

import java.sql.SQLException;

public class DruidTest {
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
    final static DruidDataSource druidDataSource = getDruidDataSource();
    final static int count = 100;

    public static DruidDataSource getDruidDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUsername(user);
        druidDataSource.setPassword(password);
        druidDataSource.setUrl(jdbcUrl);
        druidDataSource.setPassword(password);
        druidDataSource.setDriverClassName(driver);
        druidDataSource.setInitialSize(initialSize);
        druidDataSource.setMaxActive(maxPoolSize);
        druidDataSource.setMaxWait(maxPoolSize);
        druidDataSource.setTestWhileIdle(false);
        druidDataSource.setTestOnReturn(false);
        druidDataSource.setTestOnBorrow(false);
        return druidDataSource;
    }

    //
    public static void queryDruid(TestDAO testDAO, DruidDataSource druidDataSource, int count) throws SQLException {

        long startMillis = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            testDAO.query(druidDataSource.getConnection());
        }
        long endMillis = System.currentTimeMillis();
        System.out.println("druid耗时：" + (endMillis - startMillis));
    }

    public static void main(String[] args) throws SQLException {
        TestDAO testDAO = new TestDAO();
        System.out.println("==========================Druid 测试开始==========================");
        queryDruid(testDAO, druidDataSource, count);
        System.out.println("==========================Druid 测试结束==========================");
    }
}