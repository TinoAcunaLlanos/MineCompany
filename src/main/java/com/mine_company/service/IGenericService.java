package com.mine_company.service;

import java.util.List;

public interface IGenericService <T, ID>{

    T create(T t)throws Exception;
    T update(T t)throws Exception;
    T readById(ID id) throws Exception;
    T disabled(T t) throws Exception;
    T enabled(T t) throws Exception;

}
