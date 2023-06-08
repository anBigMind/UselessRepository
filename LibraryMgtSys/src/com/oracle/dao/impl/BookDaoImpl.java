package com.oracle.dao.impl;

import com.oracle.dao.BaseDao;
import com.oracle.dao.BookDao;
import com.oracle.entity.Admin;
import com.oracle.entity.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl extends BaseDao implements BookDao {
    /**
     * 查询所有图书
     * @return 图书对象集合
     */
    @Override
    public List<Book> selectAllBooks() {
        List<Book> bookList = new ArrayList<>();
        String sql = "select * from tb_book,tb_book_type where book_type_id = id";
        ResultSet rs = this.executeQuery(sql);
        Book book = null;
        try {
            while (rs.next()){
                book = new Book();
                book.setISBN(rs.getString("ISBN"));
                book.setBookName(rs.getString("book_name"));
                book.setPublishingHoush(rs.getString("publishing_housh"));
                book.setBookTypeId(rs.getInt("book_type_id"));
                book.setAuthor(rs.getString("author"));
                book.setBookTypeName(rs.getString("type_name"));
                bookList.add(book);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeAll(rs,ps,conn);
        }
        return bookList;
    }

    /**
     * 根据分类编号查找图书
     * @param id 要查找的分类编号
     * @return 图书对象集合
     */
    @Override
    public List<Book> selectBookByTypeId(int id) {
        List<Book> bookList = new ArrayList<>();
        String sql = "SELECT * FROM tb_book,tb_book_type WHERE book_type_id = id AND id = ?;";
        ResultSet rs = this.executeQuery(sql,id);
        Book book = null;
        try {
            while (rs.next()){
                book = new Book();
                book.setISBN(rs.getString("ISBN"));
                book.setBookName(rs.getString("book_name"));
                book.setPublishingHoush(rs.getString("publishing_housh"));
                book.setBookTypeId(rs.getInt("book_type_id"));
                book.setAuthor(rs.getString("author"));
                book.setBookTypeName(rs.getString("type_name"));
                bookList.add(book);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeAll(rs,ps,conn);
        }
        return bookList;
    }

    /**
     * 根据名称查找图书（模糊查询）
     * @param name 要查询的模糊字
     * @return 图书对象集合
     */
    @Override
    public List<Book> selectBookByName(String name) {
        List<Book> bookList = new ArrayList<>();
        String sql = "SELECT * FROM tb_book,tb_book_type WHERE book_type_id = id AND book_name LIKE ?;";
        name = "%"+name+"%";
        ResultSet rs = this.executeQuery(sql,name);
        Book book = null;
        try {
            while (rs.next()){
                book = new Book();
                book.setISBN(rs.getString("ISBN"));
                book.setBookName(rs.getString("book_name"));
                book.setPublishingHoush(rs.getString("publishing_housh"));
                book.setBookTypeId(rs.getInt("book_type_id"));
                book.setAuthor(rs.getString("author"));
                book.setBookTypeName(rs.getString("type_name"));
                bookList.add(book);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeAll(rs,ps,conn);
        }
        return bookList;
    }

    @Override
    public int deleteBookByIsbn(String isbn) {
        String sql = "DELETE FROM tb_book WHERE ISBN = ?;";
        return executeUpdate(sql,isbn);
    }

    /**
     * 添加图书
     * @param book
     * @return 影响行数
     */
    @Override
    public int insertBook(Book book) {
        String sql = "INSERT INTO tb_book(ISBN,book_name,publishing_housh,`book_type_id`,`author`)\n" +
                "VALUES(?,?,?,?,?);";
        return executeUpdate(sql,book.getISBN(),book.getBookName(),book.getPublishingHoush(),book.getBookTypeId(),book.getAuthor());
    }
}
