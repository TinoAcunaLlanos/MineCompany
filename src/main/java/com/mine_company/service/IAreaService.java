package com.mine_company.service;

import com.mine_company.entity.Area;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IAreaService extends IGenericService<Area, Integer> {

    Page<Area> search(Area filter, Pageable pageable);
}
