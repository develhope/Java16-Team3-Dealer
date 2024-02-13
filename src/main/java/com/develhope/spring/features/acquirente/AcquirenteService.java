package com.develhope.spring.features.acquirente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AcquirenteService {
    @Autowired
    private AcquirenteRepository repository;
}
