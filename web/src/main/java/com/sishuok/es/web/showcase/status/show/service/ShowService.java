/**
 * Copyright (c) 2005-2012 https://github.com/zhangkaitao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.sishuok.es.web.showcase.status.show.service;

import com.sishuok.es.common.service.BaseService;
import com.sishuok.es.web.showcase.status.show.entity.Show;
import com.sishuok.es.web.showcase.status.show.repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 13-2-4 下午3:01
 * <p>Version: 1.0
 */
@Service
public class ShowService extends BaseService<Show, Long> {

    private ShowRepository sampleRepository;

    @Autowired
    public void setShowRepository(ShowRepository sampleRepository) {
        setBaseRepository(sampleRepository);
        this.sampleRepository = sampleRepository;
    }

}
