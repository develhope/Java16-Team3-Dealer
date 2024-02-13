package com.develhope.spring.features.amministratore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autosalone/admin")
public class AmministratoreController {
    @Autowired
    private AmministratoreService service;
}
