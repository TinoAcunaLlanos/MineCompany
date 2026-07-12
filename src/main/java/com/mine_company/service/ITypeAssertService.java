package com.mine_company.service;

import com.mine_company.entity.Plant;
import com.mine_company.entity.TypeAssert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITypeAssertService extends IGenericService<TypeAssert, Integer> {

    Page<TypeAssert> search(TypeAssert filter, Pageable pageable);
}
