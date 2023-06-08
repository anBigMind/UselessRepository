package com.oracle.dao;

import com.oracle.entity.Book;

import java.util.List;

public interface BookDao {
    /**
     * 查询所有图书
     * @return 图书对象集合
     */
    List<Book> selectAllBooks();

    /**
     * 根据分类编号查找图书
     * @param id 要查找的分类编号
     * @return 图书对象集合
     */
    List<Book> selectBookByTypeId(int id);

    /**
     * 根据名称查找图书（模糊查询）
     * @param name 要查询的模糊字
     * @return 图书对象集合
     */
    List<Book> selectBookByName(String name);

    /**
     * 根据ISBN删除图书
     * @param isbn
     * @return 影响行数
     */
    int deleteBookByIsbn(String isbn);

    /**
     * 添加图书
     * @param book
     * @return 影响行数
     */
    int insertBook(Book book);
}
