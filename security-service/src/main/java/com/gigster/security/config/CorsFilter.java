package com.gigster.security.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

    @Value("#{'${api.request.origin}'.split(',')}")
    private List<String> requestOrigin;

    @Override
    public void init(FilterConfig fc) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpServletRequest request = (HttpServletRequest) req;
        response.setHeader("Access-Control-Allow-Methods", "OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with, authorization, Content-Type, Authorization, credential, X-XSRF-TOKEN");

        String originHeader = request.getHeader("origin");
        String clientHeader = null;

        if(requestOrigin!=null && requestOrigin.size()==1 && requestOrigin.get(0).equals("*")){
            clientHeader = requestOrigin.get(0);
        }else if(originHeader!=null){
            for (String reqOrigin : requestOrigin) {
                if (originHeader.equals(reqOrigin.trim())) {
                    clientHeader = originHeader;
                    break;
                }
            }
        }

        if(clientHeader!=null)
            response.setHeader("Access-Control-Allow-Origin", clientHeader);

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {
    }

}
