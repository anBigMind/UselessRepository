package com.oracle.service.impl;

import com.oracle.dao.RecordDao;
import com.oracle.dao.impl.RecordDaoImpl;
import com.oracle.entity.Record;
import com.oracle.service.RecordService;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RecordServiceImpl implements RecordService {
    RecordDao recordDao = new RecordDaoImpl();
    /**
     * 根据用户id和图书ISBN生成借书记录
     * @param id 用户id
     * @param isbn 图书ISBN
     * @return 影响行数
     */
    @Override
    public int createRecord(int id, String isbn) {
        Record record = new Record();
        Date now = new Date();
        Calendar returnTime = Calendar.getInstance();
        returnTime.setTime(now);
        returnTime.add(Calendar.DATE,30);
        record.setUserId(id);
        record.setISBN(isbn);
        record.setBorrowTime(now);
        record.setReturnTime(returnTime.getTime());
        return recordDao.insertRecord(record);
    }

    /**
     * 根据用户id获取所有借书记录
     * @param id 用户id
     * @return 借书记录集合
     */
    @Override
    public List<Record> getRecordById(int id) {
        return recordDao.selectAllRecordById(id);
    }

    /**
     * 根据id删除借书记录
     * @param recordId 记录id
     * @return 影响行数
     */
    @Override
    public int deleteRecordById(int recordId,int id) {
        return recordDao.deleteRecordById(recordId,id);
    }
}
