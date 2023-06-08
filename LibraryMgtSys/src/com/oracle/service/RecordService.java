package com.oracle.service;

import com.oracle.entity.Record;

import java.util.List;

public interface RecordService {
    /**
     * 根据用户id和图书ISBN生成借书记录
     * @param id 用户id
     * @param isbn 图书ISBN
     * @return 影响行数
     */
    int createRecord(int id, String isbn);

    /**
     * 根据用户id获取所有借书记录
     * @param id 用户id
     * @return 借书记录集合
     */
    List<Record> getRecordById(int id);

    /**
     * 根据id删除借书记录
     * @param recordId 记录id
     * @return 影响行数
     */
    int deleteRecordById(int recordId,int id);
}
