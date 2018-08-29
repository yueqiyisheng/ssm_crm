package controller;

import cn.itcast.utils.Page;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.BaseDict;
import pojo.Customer;
import pojo.QueryVo;
import service.CustomerService;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Resource(name = "customerService")
    private CustomerService customerService;

    @Value("${cust.source.typecode}")
    private String custSource;
    @Value("${cust.industry.typecode}")
    private String custIndustry;
    @Value("${cust.level.typecode}")
    private String custLevel;


    @RequestMapping("/list")
    public String BaseDict(QueryVo vo,Model model) throws UnsupportedEncodingException {
        // 实现下拉列表
        List<BaseDict> custSourcelist = customerService.findDictByCode(custSource);
        List<BaseDict> custIndustrylist = customerService.findDictByCode(custIndustry);
        List<BaseDict> custLevellist = customerService.findDictByCode(custLevel);

        // 高级查询数据回显
        if(vo.getCustName() != null){
            vo.setCustName(new String(vo.getCustName().getBytes("iso8859-1"),"utf-8"));
        }
        if(vo.getPage() == null){
            vo.setPage(1);
        }
        vo.setStart((vo.getPage()-1)*vo.getSize());
        // 高级查询，返回数据
        Integer total = customerService.countCustomerByDict(vo);
        Page<Customer> page = new Page<>();
        page.setTotal(total);
        page.setRows(customerService.findCustomerByDict(vo));
        page.setSize(vo.getSize());
        page.setPage(vo.getPage());

        model.addAttribute("fromType",custSourcelist);
        model.addAttribute("industryType",custIndustrylist);
        model.addAttribute("levelType",custLevellist);
        model.addAttribute("custName",vo.getCustName());
        model.addAttribute("custSource",vo.getCustSource());
        model.addAttribute("custIndustry",vo.getCustIndustry());
        model.addAttribute("custLevel",vo.getCustLevel());
        model.addAttribute("page",page);

        return "customer";
    }

    @RequestMapping("/edit")
    @ResponseBody   //要返回 pojo 对象给页面 ajax 函数，必须使用 responseBody注解
    public Customer edit(Long id){
        Customer customer = customerService.findCustomerById(id);
        return customer;
    }

    @RequestMapping("/update")
    @ResponseBody
    public String update(Customer customer){
        customerService.updateCustomerById(customer);
        return "ok";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(Long id){
        customerService.deleteCustomerById(id);
        return "ok";
    }

}
