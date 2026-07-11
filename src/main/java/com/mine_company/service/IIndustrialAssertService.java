package com.mine_company.service;

import com.mine_company.dto.IndustrialAssertDTO;
import com.mine_company.entity.IndustrialAssert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IIndustrialAssertService extends IGenericService<IndustrialAssert, Integer> {

    Page<IndustrialAssert> search(IndustrialAssertDTO filter, Pageable pageable);
}
