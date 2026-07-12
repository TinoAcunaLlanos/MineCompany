package com.mine_company.service;

import com.mine_company.entity.Plant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPlantService extends IGenericService<Plant, Integer> {

    Page<Plant> search(Plant filter, Pageable pageable);
}
