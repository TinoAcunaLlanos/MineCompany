package com.mine_company.service.impl;

import com.mine_company.entity.Mine;
import com.mine_company.entity.Plant;
import com.mine_company.repository.IGenericDao;
import com.mine_company.repository.IMineDao;
import com.mine_company.repository.IPlantDao;
import com.mine_company.service.IPlantService;
import com.mine_company.specification.MineSpecification;
import com.mine_company.specification.PlantSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class PlantServiceImpl extends GenericServiceImpl<Plant, Integer> implements IPlantService {

    @Autowired
    private IPlantDao repo;

    @Override
    protected IGenericDao<Plant, Integer> getRepo() {
        return repo;
    }

    @Override
    public Page<Plant> search(Plant filter, Pageable pageable) {
        Specification<Plant> specification = PlantSpecification.filter(filter);
        return repo.findAll(specification, pageable);
    }
}
