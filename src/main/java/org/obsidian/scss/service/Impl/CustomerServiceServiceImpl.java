package org.obsidian.scss.service.Impl;

import org.obsidian.scss.dao.CustomerServiceMapper;
import org.obsidian.scss.entity.CustomerService;
import org.obsidian.scss.entity.CustomerServiceExample;
import org.obsidian.scss.service.CustomerServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Created by Administrator on 2017/7/10.
 */
@Service
public class CustomerServiceServiceImpl implements CustomerServiceService {

    @Autowired
    private CustomerServiceMapper customerServiceMapper;

    /**
     * 新增客服
     * @param name
     * @param groupId
     * @param nickName
     * @param employeeId
     * @param autoMessage
     * @return
     */
    @Transactional
    public int insertCustomerService(String name, int groupId, String nickName, String employeeId, String autoMessage) {
        CustomerService customerService = new CustomerService();
        customerService.setName(name);
        customerService.setGroupId(groupId);
        customerService.setNickname(nickName);
        customerService.setEmployeeId(employeeId);
        customerService.setAutoMessage(autoMessage);
        int insertSum = customerServiceMapper.insertSelective(customerService);
        return insertSum;
    }

    /**
     * 删除客服
     * @param serviceId
     * @return
     */
    @Transactional
    public int deleteCustomerService(int serviceId) {
        int deleteSum = customerServiceMapper.deleteCustomerService(serviceId);
        return deleteSum;
    }

    /**
     * 更新客服
     * @param serviceId
     * @param name
     * @param groupId
     * @param nickName
     * @param employeeId
     * @param autoMessage
     * @param isDimission
     * @return
     */
    @Transactional
    public int updateCustomerService(int serviceId,String name,int groupId,String nickName,
                                     String employeeId,String autoMessage,int isDimission){
        CustomerService customerService = new CustomerService();
        customerService.setName(name);
        customerService.setGroupId(groupId);
        customerService.setNickname(nickName);
        customerService.setEmployeeId(employeeId);
        customerService.setAutoMessage(autoMessage);
        customerService.setIsDimission(isDimission);
        CustomerServiceExample example = new CustomerServiceExample();
        example.or().andServiceIdEqualTo(serviceId);
        int updateSum = customerServiceMapper.updateByExampleSelective(customerService,example);
        return updateSum;

    }


    /**
     * 查询所有客服
     * @return
     */
    @Transactional
    public List<CustomerService> selectAllCustomerService(){
        List<CustomerService> list;
        CustomerServiceExample example = new CustomerServiceExample();
        list = customerServiceMapper.selectByExample(example);
        if(list.size() == 0 || list == null){
            return null;
        }
        return list;
    }

    /**
     * 按组查询客服
     * @param groupId
     * @return
     */
    public List<CustomerService> selectCustomerServiceByGroup(int groupId) {
        List<CustomerService> list;
        CustomerServiceExample example = new CustomerServiceExample();
        example.or().andGroupIdEqualTo(groupId);
        list = customerServiceMapper.selectByExample(example);
        if(list.size() == 0 || list == null){
            return null;
        }
        return list;
    }

    /**
     * 按客服号查询客服
     * @param serviceId
     * @return
     */
    @Transactional
    public CustomerService selectCustomerServiceByServiceId(int serviceId) {
        List<CustomerService> list;
        CustomerServiceExample example = new CustomerServiceExample();
        example.or().andServiceIdEqualTo(serviceId);
        list = customerServiceMapper.selectByExample(example);
        if(list.size() == 0 || list == null){
            return null;
        }
        return list.get(0);
    }

    /**
     * 按工号查询客服
     * @param employeeId
     * @return
     */
    public CustomerService selectCustomerServiceByEmployeeId(String employeeId) {
        List<CustomerService> list;
        CustomerServiceExample example = new CustomerServiceExample();
        example.or().andEmployeeIdEqualTo(employeeId);
        list = customerServiceMapper.selectByExample(example);
        if(list.size() == 0 || list == null){
            return null;
        }
        return list.get(0);
    }

    /**
     * Create By cjn
     * @return
     */
    @Transactional
    public int selectTotalServer() {
        return customerServiceMapper.selectAllServerNum();
    }

    public List<CustomerService> selectNotDimissionPerson() {
        List<CustomerService> res =  customerServiceMapper.selectNotDimissionPerson();
        return res;
    }
}
