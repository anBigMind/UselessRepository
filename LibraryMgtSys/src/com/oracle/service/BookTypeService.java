package com.oracle.service;

import com.oracle.entity.BookType;

import java.util.List;

public interface BookTypeService {
    /**
     * 获取所有图书分类
     * @return 图书分类对象集合
     */
    List<BookType> getAllBookType();

    /**
     * 根据id验证图书分类是否存在
     * @param id
     * @return 存在返回true，不存在返回false
     */
    boolean isBookTypeExists(int id);
}
