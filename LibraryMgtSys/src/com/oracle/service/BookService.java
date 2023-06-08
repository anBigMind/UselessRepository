package com.oracle.service;

import com.oracle.entity.Book;

import java.util.List;

public interface BookService {
    /**
     * 获得所有图书对象
     * @return 图书对象集合
     */
    List<Book> getAllBooks();

    /**
     * 根据分类查找图书
     * @param id 要查找的分类id
     * @return 图书对象集合
     */
    List<Book> getBooksByTypeId(int id);

    /**
     * 根据名称查找图书（模糊查询）
     * @param name 要查询的模糊字
     * @return 图书对象集合
     */
    List<Book> getBooksByName(String name);

    /**
     * 根据ISBN删除图书
     * @param isbn 图书ISBN码
     * @return 影响行数
     */
    int deleteBookByIsbn(String isbn);

    /**
     * 验证isbn码是否存在
     * @param isbn
     * @return 存在返回true，不存在返回false
     */
    boolean isIsbnExists(String isbn);

    /**
     * 添加图书
     * @param book
     * @return 影响行数
     */
    int addBook(Book book);
}
