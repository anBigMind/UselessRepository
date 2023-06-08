package com.oracle.dao.impl;

import com.oracle.dao.BaseDao;
import com.oracle.dao.RecordDao;
import com.oracle.dao.UserDao;
import com.oracle.entity.Book;
import com.oracle.entity.Record;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecordDaoImpl extends BaseDao implements RecordDao {
    UserDao userDao = new UserDaoImpl();
    /**
     * 插入借书记录
     * @param record 借书记录对象
     * @return 影响行数
     */
    @Override
    public int insertRecord(Record record) {
        String sql = "INSERT INTO tb_record(`borrow_time`,`return_time`,`ISBN`,`user_id`)VALUES(?,?,?,?);";
        userDao.updateUserRecordNum(1,record.getUserId());
        return executeUpdate(sql,record.getBorrowTime(),record.getReturnTime(),record.getISBN(),record.getUserId());
    }

    /**
     * 根据用户id查询所有借书记录
     * @param id 用户id
     * @return 借书记录对象集合
     */
    @Override
    public List<Record> selectAllRecordById(int id) {
        List<Record> recordList = new ArrayList<>();
        String sql = "SELECT * FROM tb_record WHERE user_id = ?;";
        ResultSet rs = this.executeQuery(sql,id);
        Record record = null;
        try {
            while (rs.next()){
                record = new Record();
                record.setISBN(rs.getString("ISBN"));
                record.setUserId(rs.getInt("user_id"));
                record.setBorrowTime(rs.getDate("borrow_time"));
                record.setReturnTime(rs.getDate("return_time"));
                record.setId(rs.getInt("id"));
                recordList.add(record);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeAll(rs,ps,conn);
        }
        return recordList;
    }
    /**
     * 根据id删除借书记录
     * @param recordId
     * @return 影响行数
     */
    @Override
    public int deleteRecordById(int recordId,int id) {
        String sql = "DELETE FROM tb_record WHERE id = ?;";
        userDao.updateUserRecordNum(0,id);
        return executeUpdate(sql,recordId);
    }
}
