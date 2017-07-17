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

    List<CustomerService>selectBySearchName(@Param("name") String name);
    int updateDimission(@Param("service_Id")int service_Id);



    CustomerService selectByEIdAndPwd(@Param("employeeId") String employeeId,@Param("password") String password);

    CustomerService verify(@Param("employeeId") String employeeId,@Param("password") String password);


}