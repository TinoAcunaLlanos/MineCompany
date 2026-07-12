package com.mine_company.service;

import com.mine_company.entity.SensorThreshold;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ISensorThresholdService extends IGenericService<SensorThreshold, Integer> {

    Page<SensorThreshold> search(SensorThreshold filter, Pageable pageable);
}
