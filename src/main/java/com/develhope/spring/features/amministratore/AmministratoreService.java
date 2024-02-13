package com.develhope.spring.features.amministratore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AmministratoreService {
    @Autowired
    private AmministratoreRepository repository;
}
