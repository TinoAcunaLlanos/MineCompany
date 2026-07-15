package com.mine_company.service;

import com.mine_company.dto.MeasurementDTO;
import com.mine_company.entity.Measurement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IMeasurementService extends IGenericService<Measurement, Integer> {

    Page<Measurement> search(MeasurementDTO filter, Pageable pageable);
}
