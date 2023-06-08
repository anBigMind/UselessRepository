package com.oracle.service.impl;

import com.oracle.dao.BookTypeDao;
import com.oracle.dao.impl.BookTypeDaoImpl;
import com.oracle.entity.BookType;
import com.oracle.service.BookTypeService;

import java.util.List;

public class BookTypeServiceImpl implements BookTypeService {
    BookTypeDao bookTypeDao = new BookTypeDaoImpl();
    /**
     * 获取所有图书分类
     * @return 图书分类对象集合
     */
    @Override
    public List<BookType> getAllBookType() {
        return bookTypeDao.selectAllBookTypes();
    }

    /**
     * 根据id验证图书分类是否存在
     * @param id
     * @return 存在返回true，不存在返回false
     */
    @Override
    public boolean isBookTypeExists(int id) {
        List<BookType> allBookType = getAllBookType();
        for (BookType bookType: allBookType) {
            if (bookType.getId()==id){
                return true;
            }
        }
        return false;
    }
}
