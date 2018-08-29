package dao;

import pojo.BaseDict;
import pojo.Customer;
import pojo.QueryVo;

import java.util.List;

public interface BaseDictMapper {
    public List<BaseDict> findDictByCode(String code);

}
