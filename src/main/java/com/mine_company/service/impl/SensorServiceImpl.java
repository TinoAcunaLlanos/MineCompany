package com.mine_company.service.impl;

import com.mine_company.dto.SensorDTO;
import com.mine_company.entity.Sensor;
import com.mine_company.repository.IGenericDao;
import com.mine_company.repository.ISensorDao;
import com.mine_company.service.ISensorService;
import com.mine_company.specification.SensorSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class SensorServiceImpl extends GenericServiceImpl<Sensor, Integer> implements ISensorService {

    @Autowired
    private ISensorDao repo;

    @Override
    protected IGenericDao<Sensor, Integer> getRepo() {
        return repo;
    }

    @Override
    public Page<Sensor> search(SensorDTO filter, Pageable pageable) {
        Specification<Sensor> specification = SensorSpecification.filter(filter);
        return repo.findAll(specification, pageable);
    }
}
