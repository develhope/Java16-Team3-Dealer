package com.develhope.spring.features.configurations;

import com.develhope.spring.features.acquirente.AcquirenteInterceptor;
import com.develhope.spring.features.amministratore.AmministratoreInterceptor;
import com.develhope.spring.features.venditore.VenditoreInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class SpringMVCConfiguration implements WebMvcConfigurer {
    @Autowired
    private AcquirenteInterceptor acquirenteInterceptor;
    @Autowired
    private AmministratoreInterceptor amministratoreInterceptor;
    @Autowired
    private VenditoreInterceptor venditoreInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(acquirenteInterceptor).addPathPatterns("/autosalone/cliente/**");
        registry.addInterceptor(amministratoreInterceptor).addPathPatterns("/autosalone/admin/**");
        registry.addInterceptor(venditoreInterceptor).addPathPatterns("/autosalone/venditore/**");
    }
}