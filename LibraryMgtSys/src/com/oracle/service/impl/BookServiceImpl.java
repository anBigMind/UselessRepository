package com.oracle.service.impl;

import com.oracle.dao.BookDao;
import com.oracle.dao.impl.BookDaoImpl;
import com.oracle.entity.Book;
import com.oracle.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    BookDao bookDao = new BookDaoImpl();

    /**
     * 获得所有图书对象
     * @return 图书对象集合
     */
    @Override
    public List<Book> getAllBooks() {
        return bookDao.selectAllBooks();
    }

    /**
     * 根据分类编号查找图书
     * @param id 要查找的分类id
     * @return 图书对象集合
     */
    @Override
    public List<Book> getBooksByTypeId(int id) {
        return bookDao.selectBookByTypeId(id);
    }

    /**
     * 根据名称查找图书（模糊查询）
     * @param name 要查询的模糊字
     * @return 图书对象集合
     */
    @Override
    public List<Book> getBooksByName(String name) {
        return bookDao.selectBookByName(name);
    }

    /**
     * 根据ISBN删除图书
     * @param isbn 图书ISBN码
     * @return 影响行数
     */
    @Override
    public int deleteBookByIsbn(String isbn) {
        return bookDao.deleteBookByIsbn(isbn);
    }

    /**
     * 验证isbn码是否存在
     * @param isbn
     * @return 存在返回true，不存在返回false
     */
    @Override
    public boolean isIsbnExists(String isbn) {
        List<Book> allBooks = getAllBooks();
        for (Book book:allBooks) {
            if (book.getISBN().equals(isbn)){
                return true;
            }
        }
        return false;
    }

    @Override
    public int addBook(Book book) {
        return bookDao.insertBook(book);
    }
}
