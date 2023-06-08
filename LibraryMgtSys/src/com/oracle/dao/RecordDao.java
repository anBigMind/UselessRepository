package com.oracle.dao;

import com.oracle.entity.Record;

import java.util.List;

public interface RecordDao {
    /**
     * 插入借书记录
     * @param record 借书记录对象
     * @return 影响行数
     */
    int insertRecord(Record record);

    /**
     * 根据用户id查询所有借书记录
     * @param id 用户id
     * @return 借书记录集合
     */
    List<Record> selectAllRecordById(int id);

    /**
     * 根据id删除借书记录
     * @param recordId
     * @return 影响行数
     */
    int deleteRecordById(int recordId,int id);
}
