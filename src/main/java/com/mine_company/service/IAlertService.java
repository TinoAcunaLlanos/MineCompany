package com.mine_company.service;

import com.mine_company.dto.AlertDTO;
import com.mine_company.entity.Alert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IAlertService extends IGenericService<Alert, Integer> {

    Page<Alert> search(AlertDTO filter, Pageable pageable);
}
