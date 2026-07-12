package com.mine_company.service.impl;

import com.mine_company.entity.SensorThreshold;
import com.mine_company.repository.IGenericDao;
import com.mine_company.repository.ISensorThresholdDao;
import com.mine_company.service.ISensorThresholdService;
import com.mine_company.specification.SensorThresholdSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class SensorThresholdServiceImpl extends GenericServiceImpl<SensorThreshold, Integer> implements ISensorThresholdService {

    @Autowired
    private ISensorThresholdDao repo;

    @Override
    protected IGenericDao<SensorThreshold, Integer> getRepo() {
        return repo;
    }

    @Override
    public Page<SensorThreshold> search(SensorThreshold filter, Pageable pageable) {
        Specification<SensorThreshold> specification = com.mine_company.specification.SensorThresholdSpecification.filter(filter);
        return repo.findAll(specification, pageable);
    }
}
