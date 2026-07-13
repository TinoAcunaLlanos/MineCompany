package com.mine_company.service.impl;

import com.mine_company.dto.MeasurementDTO;
import com.mine_company.entity.Measurement;
import com.mine_company.repository.IGenericDao;
import com.mine_company.repository.IMeasurementDao;
import com.mine_company.service.IMeasurementService;
import com.mine_company.specification.MeasurementSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class MeasurementServiceImpl extends GenericServiceImpl<Measurement, Integer> implements IMeasurementService {

    @Autowired
    private IMeasurementDao repo;

    @Override
    protected IGenericDao<Measurement, Integer> getRepo() {
        return repo;
    }

    @Override
    public Page<Measurement> search(MeasurementDTO filter, Pageable pageable) {
        Specification<Measurement> specification = MeasurementSpecification.filter(filter);
        return repo.findAll(specification, pageable);
    }
}
