package service;

import dao.BaseDictMapper;
import dao.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.BaseDict;
import pojo.Customer;
import pojo.QueryVo;
import java.util.List;

@Service("customerService")
class CustomerServiceImpl implements CustomerService {

    @Autowired
    private BaseDictMapper baseDictMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public List<BaseDict> findDictByCode(String typeCode) {
        List<BaseDict> list = baseDictMapper.findDictByCode(typeCode);
        return list;
    }

    @Override
    public List<Customer> findCustomerByDict(QueryVo vo) {
        List<Customer> list = customerMapper.findCustomerByDict(vo);
        return list;
    }

    @Override
    public Integer countCustomerByDict(QueryVo vo) {
        Integer total = customerMapper.countCustomerByDict(vo);
        return total;
    }

    @Override
    public Customer findCustomerById(Long id) {
        Customer customer = customerMapper.findCustomerById(id);
        return customer;
    }

    @Override
    public void updateCustomerById(Customer customer) {
        customerMapper.updateCustomerById(customer);
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerMapper.deleteCustomerById(id);
    }
}
