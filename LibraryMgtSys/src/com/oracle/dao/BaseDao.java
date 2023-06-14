package com.oracle.dao;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class BaseDao implements AutoCloseable{
    /*private static String driver;
    private static String url;
    private static String user;
    private static String password;*/
    private static DataSource dataSource;
    private static void init(){
        Properties properties = new Properties();
        String configFile = "druid.properties";
        InputStream is = BaseDao.class.getClassLoader().getResourceAsStream(configFile);
        try {
            properties.load(is);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        /*driver = properties.getProperty("driver");
        url = properties.getProperty("url");
        user = properties.getProperty("user");
        password = properties.getProperty("password");*/
    }
    static {
        init();
    }
    protected Connection conn;
    protected PreparedStatement ps;
    /**
     * 获得连接的方法
     * @return 连接对象
     */
    protected Connection getConnection(){
        try {
            if (conn == null || conn.isClosed())
                conn = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    /**
     * 通用的增删改方法
     * @param sql 增删改的sql语句
     * @param objects 可变参数，代表占位符对应的值
     * @return 影响行数
     */
    protected int executeUpdate(String sql,Object...objects){
        int n = -1;
        conn = getConnection();
        try {
            ps = conn.prepareStatement(sql);
            if (objects != null)
                for (int i = 0; i < objects.length; i++)
                    ps.setObject(i+1,objects[i]);
            n = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeAll(null,ps,conn);
        }
        return n;
    }
    /**
     * 关闭所有资源
     * @param rs 结果集对象
     * @param sm 执行器对象
     * @param con 连接对象
     */
    protected void closeAll(ResultSet rs, Statement sm, Connection con){
        try {
            if (rs!=null) rs.close();
            if (sm!=null) sm.close();
            if (con!=null) con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    protected ResultSet executeQuery(String sql,Object...objects){
        conn = getConnection();
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            if (objects != null)
                for (int i = 0; i < objects.length; i++)
                    ps.setObject(i+1,objects[i]);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }

    @Override
    public void close() throws Exception {
        try {
            if (this.ps!=null) ps.close();
            if (conn!=null) conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
