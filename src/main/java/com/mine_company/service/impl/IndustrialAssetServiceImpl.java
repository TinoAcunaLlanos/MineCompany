package com.mine_company.service.impl;

import com.mine_company.dto.IndustrialAssetDTO;
import com.mine_company.entity.IndustrialAsset;
import com.mine_company.repository.IGenericDao;
import com.mine_company.repository.IIndustrialAssetDao;
import com.mine_company.service.IIndustrialAssetService;
import com.mine_company.specification.IndustrialAssetSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class IndustrialAssetServiceImpl extends GenericServiceImpl<IndustrialAsset, Integer> implements IIndustrialAssetService {

    @Autowired
    private IIndustrialAssetDao repo;

    @Override
    protected IGenericDao<IndustrialAsset, Integer> getRepo() {
        return repo;
    }

    @Override
    public Page<IndustrialAsset> search(IndustrialAssetDTO filter, Pageable pageable) {
        Specification<IndustrialAsset> specification = IndustrialAssetSpecification.filter(filter);
        return repo.findAll(specification, pageable);
    }
}
