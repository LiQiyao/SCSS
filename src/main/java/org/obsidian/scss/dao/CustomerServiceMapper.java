package org.obsidian.scss.dao;

import org.apache.ibatis.annotations.Param;
import org.obsidian.scss.entity.CustomerService;
import org.obsidian.scss.entity.CustomerServiceExample;

import java.util.List;

public interface CustomerServiceMapper {
    long countByExample(CustomerServiceExample example);

    int deleteByExample(CustomerServiceExample example);

    int insert(CustomerService record);

    int insertSelective(CustomerService record);

    List<CustomerService> selectByExample(CustomerServiceExample example);

    int updateByExampleSelective(@Param("record") CustomerService record, @Param("example") CustomerServiceExample example);

    int updateByExample(@Param("record") CustomerService record, @Param("example") CustomerServiceExample example);

    int deleteCustomerService(int serviceId);
    
    int selectAllServerNum();
    List<CustomerService> selectNotDimssionPerson();
}