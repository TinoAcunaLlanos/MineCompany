package com.mine_company.service.impl;

import com.mine_company.entity.Plant;
import com.mine_company.entity.TypeAssert;
import com.mine_company.repository.ITypeAssertDao;
import com.mine_company.repository.IGenericDao;
import com.mine_company.service.ITypeAssertService;
import com.mine_company.specification.TypeAssertSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class TypeAssertServiceImpl extends GenericServiceImpl<TypeAssert, Integer> implements ITypeAssertService {

    @Autowired
    private ITypeAssertDao repo;

    @Override
    protected IGenericDao<TypeAssert, Integer> getRepo() {
        return repo;
    }

    @Override
    public Page<TypeAssert> search(TypeAssert filter, Pageable pageable) {
        Specification<TypeAssert> specification = TypeAssertSpecification.filter(filter);
        return repo.findAll(specification, pageable);
    }
}
