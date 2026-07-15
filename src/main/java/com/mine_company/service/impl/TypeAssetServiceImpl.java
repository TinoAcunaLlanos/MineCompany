package com.mine_company.service.impl;

import com.mine_company.entity.TypeAsset;
import com.mine_company.repository.ITypeAssetDao;
import com.mine_company.repository.IGenericDao;
import com.mine_company.service.ITypeAssetService;
import com.mine_company.specification.TypeAssetSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class TypeAssetServiceImpl extends GenericServiceImpl<TypeAsset, Integer> implements ITypeAssetService {

    @Autowired
    private ITypeAssetDao repo;

    @Override
    protected IGenericDao<TypeAsset, Integer> getRepo() {
        return repo;
    }

    @Override
    public Page<TypeAsset> search(TypeAsset filter, Pageable pageable) {
        Specification<TypeAsset> specification = TypeAssetSpecification.filter(filter);
        return repo.findAll(specification, pageable);
    }
}
