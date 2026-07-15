package com.mine_company.service.impl;

import com.mine_company.dto.AlertDTO;
import com.mine_company.entity.Alert;
import com.mine_company.repository.IGenericDao;
import com.mine_company.repository.IAlertDao;
import com.mine_company.service.IAlertService;
import com.mine_company.specification.AlertSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class AlertServiceImpl extends GenericServiceImpl<Alert, Integer> implements IAlertService {

    @Autowired
    private IAlertDao repo;

    @Override
    protected IGenericDao<Alert, Integer> getRepo() {
        return repo;
    }

    @Override
    public Page<Alert> search(AlertDTO filter, Pageable pageable) {
        Specification<Alert> specification = AlertSpecification.filter(filter);
        return repo.findAll(specification, pageable);
    }
}
