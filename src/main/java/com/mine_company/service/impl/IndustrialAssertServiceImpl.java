package com.mine_company.service.impl;

import com.mine_company.dto.IndustrialAssertDTO;
import com.mine_company.entity.IndustrialAssert;
import com.mine_company.repository.IGenericDao;
import com.mine_company.repository.IIndustrialAssertDao;
import com.mine_company.service.IIndustrialAssertService;
import com.mine_company.specification.IndustrialAssertSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class IndustrialAssertServiceImpl extends GenericServiceImpl<IndustrialAssert, Integer> implements IIndustrialAssertService {

    @Autowired
    private IIndustrialAssertDao repo;

    @Override
    protected IGenericDao<IndustrialAssert, Integer> getRepo() {
        return repo;
    }

    @Override
    public Page<IndustrialAssert> search(IndustrialAssertDTO filter, Pageable pageable) {
        Specification<IndustrialAssert> specification = IndustrialAssertSpecification.filter(filter);
        return repo.findAll(specification, pageable);
    }
}
