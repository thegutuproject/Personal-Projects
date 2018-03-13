package com.thegutuproject.pizzashop.db.mapper;

import com.thegutuproject.pizzashop.db.model.OrderEntryDb;
import com.thegutuproject.pizzashop.db.model.OrderEntryDbExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface OrderEntryDbMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_entry
     *
     * @mbg.generated Tue Mar 13 14:55:23 EDT 2018
     */
    long countByExample(OrderEntryDbExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_entry
     *
     * @mbg.generated Tue Mar 13 14:55:23 EDT 2018
     */
    int deleteByExample(OrderEntryDbExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_entry
     *
     * @mbg.generated Tue Mar 13 14:55:23 EDT 2018
     */
    int deleteByPrimaryKey(Integer orderEntryId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_entry
     *
     * @mbg.generated Tue Mar 13 14:55:23 EDT 2018
     */
    int insert(OrderEntryDb record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_entry
     *
     * @mbg.generated Tue Mar 13 14:55:23 EDT 2018
     */
    int insertSelective(OrderEntryDb record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_entry
     *
     * @mbg.generated Tue Mar 13 14:55:23 EDT 2018
     */
    List<OrderEntryDb> selectByExampleWithRowbounds(OrderEntryDbExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_entry
     *
     * @mbg.generated Tue Mar 13 14:55:23 EDT 2018
     */
    List<OrderEntryDb> selectByExample(OrderEntryDbExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_entry
     *
     * @mbg.generated Tue Mar 13 14:55:23 EDT 2018
     */
    OrderEntryDb selectByPrimaryKey(Integer orderEntryId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_entry
     *
     * @mbg.generated Tue Mar 13 14:55:23 EDT 2018
     */
    int updateByExampleSelective(@Param("record") OrderEntryDb record, @Param("example") OrderEntryDbExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_entry
     *
     * @mbg.generated Tue Mar 13 14:55:23 EDT 2018
     */
    int updateByExample(@Param("record") OrderEntryDb record, @Param("example") OrderEntryDbExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_entry
     *
     * @mbg.generated Tue Mar 13 14:55:23 EDT 2018
     */
    int updateByPrimaryKeySelective(OrderEntryDb record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_entry
     *
     * @mbg.generated Tue Mar 13 14:55:23 EDT 2018
     */
    int updateByPrimaryKey(OrderEntryDb record);
}