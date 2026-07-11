package com.mine_company.service.impl;

import com.mine_company.entity.Plant;
import com.mine_company.repository.IGenericDao;
import com.mine_company.repository.IMineDao;
import com.mine_company.repository.IPlantDao;
import com.mine_company.service.IPlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlantServiceImpl extends GenericServiceImpl<Plant, Integer> implements IPlantService {

    @Autowired
    private IPlantDao repo;

    @Override
    protected IGenericDao<Plant, Integer> getRepo() {
        return repo;
    }
}
