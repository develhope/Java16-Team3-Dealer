package com.develhope.spring.features.venditore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autosalone/venditore")
public class VenditoreController {
    @Autowired
    private VenditoreService service;
}
