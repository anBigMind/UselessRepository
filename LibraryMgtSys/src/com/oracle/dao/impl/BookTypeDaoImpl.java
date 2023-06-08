package com.oracle.dao.impl;

import com.oracle.dao.BaseDao;
import com.oracle.dao.BookTypeDao;
import com.oracle.entity.BookType;
import com.oracle.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookTypeDaoImpl extends BaseDao implements BookTypeDao {
    /**
     * 查询所有图书分类
     * @return 图书分类对象集合
     */
    @Override
    public List<BookType> selectAllBookTypes() {
        List<BookType> bookTypeList = new ArrayList<>();
        String sql = "select * from tb_book_type";
        ResultSet rs = this.executeQuery(sql);
        BookType bookType = null;
        try {
            while (rs.next()){
                bookType = new BookType();
                bookType.setId(rs.getInt("id"));
                bookType.setTypeName(rs.getString("type_name"));
                bookTypeList.add(bookType);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeAll(rs,ps,conn);
        }
        return bookTypeList;
    }
}
