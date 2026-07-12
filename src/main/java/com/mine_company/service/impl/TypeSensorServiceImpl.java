package com.mine_company.service.impl;

import com.mine_company.entity.TypeSensor;
import com.mine_company.repository.IGenericDao;
import com.mine_company.repository.ITypeSensorDao;
import com.mine_company.service.ITypeSensorService;
import com.mine_company.specification.TypeSensorSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class TypeSensorServiceImpl extends GenericServiceImpl<TypeSensor, Integer> implements ITypeSensorService {

    @Autowired
    private ITypeSensorDao repo;

    @Override
    protected IGenericDao<TypeSensor, Integer> getRepo() {
        return repo;
    }

    @Override
    public Page<TypeSensor> search(TypeSensor filter, Pageable pageable) {
        Specification<TypeSensor> specification = TypeSensorSpecification.filter(filter);
        return repo.findAll(specification, pageable);
    }
}
