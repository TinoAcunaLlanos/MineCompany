package com.mine_company.service.impl;

import com.mine_company.entity.Area;
import com.mine_company.repository.IAreaDao;
import com.mine_company.repository.IGenericDao;
import com.mine_company.service.IAreaService;
import com.mine_company.specification.AreaSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class AreaServiceImpl extends GenericServiceImpl<Area, Integer> implements IAreaService {

    @Autowired
    private IAreaDao repo;

    @Override
    protected IGenericDao<Area, Integer> getRepo() {
        return repo;
    }

    @Override
    public Page<Area> search(Area filter, Pageable pageable) {
        Specification<Area> specification = AreaSpecification.filter(filter);
        return repo.findAll(specification, pageable);
    }
}
