package org.obsidian.scss.dao;

import org.apache.ibatis.annotations.Param;
import org.obsidian.scss.entity.CustomerService;
import org.obsidian.scss.entity.CustomerServiceExample;

import javax.swing.undo.CannotUndoException;
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

    /**
     * Create By cjn
     * @return
     */
    int selectAllServerNum();
    List<CustomerService> selectNotDimissionPerson();
<<<<<<< HEAD
    List<CustomerService>selectBySearchName(@Param("name") String name);
    int updateDimission(@Param("service_Id")int service_Id);
=======

    CustomerService verify(@Param("employeeId") String employeeId,@Param("password") String password);
>>>>>>> 93bd37076f16836c4de02dbd4cc21b8400a20fd6
}