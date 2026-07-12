package com.mine_company.service;

import com.mine_company.entity.TypeSensor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITypeSensorService extends IGenericService<TypeSensor, Integer> {

    Page<TypeSensor> search(TypeSensor filter, Pageable pageable);
}
