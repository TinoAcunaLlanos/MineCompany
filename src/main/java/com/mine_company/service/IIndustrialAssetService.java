package com.mine_company.service;

import com.mine_company.dto.IndustrialAssetDTO;
import com.mine_company.entity.IndustrialAsset;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IIndustrialAssetService extends IGenericService<IndustrialAsset, Integer> {

    Page<IndustrialAsset> search(IndustrialAssetDTO filter, Pageable pageable);
}
