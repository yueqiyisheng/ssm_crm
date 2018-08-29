package dao;

import pojo.Customer;
import pojo.QueryVo;

import java.util.List;

public interface CustomerMapper {

    public List<Customer> findCustomerByDict(QueryVo vo);
    public Integer countCustomerByDict(QueryVo vo);

    public Customer findCustomerById(Long id);

    public void updateCustomerById(Customer customer);

    public void deleteCustomerById(Long id);
}
