package com.mine_company.service.impl;

import com.mine_company.repository.IGenericDao;
import com.mine_company.service.IGenericService;

import java.util.List;

public abstract class GenericServiceImpl<T, ID> implements IGenericService<T, ID> {

    protected abstract IGenericDao<T, ID> getRepo();

    @Override
    public T create(T t)throws Exception {
        return getRepo().save(t);
    }
    @Override
    public T update(T t)throws Exception{
        return getRepo().save(t);
    }

    @Override
    public T readById(ID id) throws Exception{
        return getRepo().findById(id).orElse(null);
    }

    @Override
    public T disabled(T t) throws Exception{
        return getRepo().save(t);
    }

    @Override
    public T enabled(T t) throws Exception{
        return getRepo().save(t);
    }



}
