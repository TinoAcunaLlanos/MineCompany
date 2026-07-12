package com.mine_company.service.impl;

import com.mine_company.entity.Mine;
import com.mine_company.repository.IGenericDao;
import com.mine_company.repository.IMineDao;
import com.mine_company.service.IMineService;
import com.mine_company.specification.MineSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MineServiceImpl extends GenericServiceImpl<Mine, Integer> implements IMineService {

    @Autowired
    private IMineDao repo;

    @Override
    protected IGenericDao<Mine, Integer> getRepo() {
        return repo;
    }

    @Override
    public Page<Mine> search(Mine filter, Pageable pageable) {
        Specification<Mine> specification = MineSpecification.filter(filter);
        return repo.findAll(specification, pageable);
    }
}
