package com.mine_company.service;

import com.mine_company.entity.TypeAsset;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITypeAssetService extends IGenericService<TypeAsset, Integer> {

    Page<TypeAsset> search(TypeAsset filter, Pageable pageable);
}
