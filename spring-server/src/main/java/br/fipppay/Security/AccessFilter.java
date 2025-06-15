package br.fipppay.Security;


import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class AccessFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader("Authorization");
        System.out.println("TOKEN = "+token);
        if(token!=null && JWTTokenProvider.verifyToken(token)) {
            String level= JWTTokenProvider.getAllClaimsFromToken(token).get("level").toString();
            String DestinyRoute=(((HttpServletRequest) request).getRequestURI());
            if((DestinyRoute.contains("admin") && level.equals("1"))|| (DestinyRoute.contains("others") && level.equals("2")))
                chain.doFilter(request, response);
        }
        else {
            ((HttpServletResponse) response).setStatus(500);
            response.getOutputStream().write("NOT ALLOWED ".getBytes());
        }
    }
}

