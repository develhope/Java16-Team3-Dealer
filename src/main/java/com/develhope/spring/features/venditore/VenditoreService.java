package com.develhope.spring.features.venditore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VenditoreService {
    @Autowired
    private VenditoreRepository repository;
}
