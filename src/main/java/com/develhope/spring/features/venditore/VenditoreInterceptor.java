package com.develhope.spring.features.venditore;

import com.develhope.spring.features.signUpSignIn.IDLogin;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class VenditoreInterceptor implements HandlerInterceptor {
    @Autowired
    private IDLogin idLogin;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(idLogin.getId()==null){
            response.sendError(512, "Effettua il login per avere accesso ai servizi dell'autosalone");
            return false;
        }

        if(!(idLogin.getTipoUtente().equals("VENDITORE"))){
            response.sendError(401, "Non autorizzato");
            return false;
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
