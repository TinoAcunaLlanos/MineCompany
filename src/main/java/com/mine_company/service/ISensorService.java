package com.mine_company.service;

import com.mine_company.dto.SensorDTO;
import com.mine_company.entity.Sensor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ISensorService extends IGenericService<Sensor, Integer> {

    Page<Sensor> search(SensorDTO filter, Pageable pageable);
}
