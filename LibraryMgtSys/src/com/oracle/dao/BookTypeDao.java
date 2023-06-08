package com.oracle.dao;

import com.oracle.entity.BookType;

import java.util.List;

public interface BookTypeDao {
    /**
     * 查询所有图书分类
     * @return 图书分类对象集合
     */
    List<BookType> selectAllBookTypes();
}
