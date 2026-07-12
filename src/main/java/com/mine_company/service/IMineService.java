package com.mine_company.service;

import com.mine_company.entity.Mine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IMineService extends IGenericService<Mine, Integer> {

    Page<Mine> search(Mine filter, Pageable pageable);
}
