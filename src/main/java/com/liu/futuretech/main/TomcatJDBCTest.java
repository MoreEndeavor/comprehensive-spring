package com.liu.futuretech.main;


import com.liu.futuretech.dao.TestDAO;
import org.apache.tomcat.jdbc.pool.DataSource;

import java.sql.SQLException;

public class TomcatJDBCTest {
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
    final static DataSource tomcatDataSource = getTomcatDataSource();
    final static int count = 100;


    /**
     * 获取Apache tomcat jdbc pool数据源
     * @return
     */
    public static DataSource getTomcatDataSource() {
        DataSource ds = new DataSource();
        ds.setUrl(jdbcUrl);
        ds.setUsername(user);
        ds.setPassword(password);
        ds.setDriverClassName(driver);
        ds.setInitialSize(initialSize);
        ds.setMaxIdle(minPoolSize);
        ds.setMaxActive(maxPoolSize);
        ds.setTestWhileIdle(false);
        ds.setTestOnBorrow(false);
        ds.setTestOnConnect(false);
        ds.setTestOnReturn(false);
        return ds;
    }

    public static void queryTomcatJDBC(TestDAO testDAO, DataSource ds, int count) throws SQLException {
        // 查询100次以初始化连接池
        for (int i = 0; i < 100; i++) {
            testDAO.query(ds.getConnection());
        }
        // 开始时间
        long startMillis = System.currentTimeMillis();
        // 循环查询
        for (int i = 0; i < count; i++) {
            testDAO.query(ds.getConnection());
        }
        // 结束时间
        long endMillis = System.currentTimeMillis();
        // 输出结束时间
        System.out.println("使用Tomcat JDBC，查询" + count + "次，" + "耗时" + (endMillis - startMillis));
    }

    public static void main(String[] args) throws SQLException {
        TestDAO testDAO = new TestDAO();
        System.out.println("==========================Tomcat Jdbc Pool 测试开始==========================");
        queryTomcatJDBC(testDAO, tomcatDataSource, count);
        System.out.println("==========================Tomcat Jdbc Pool 测试结束==========================");
    }
}
