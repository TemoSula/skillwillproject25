package com.example.skillwillproject25.Filters;


import com.example.skillwillproject25.Models.CustomAuthentication;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Locale;

@Component
@Qualifier("MyCustomFilterForCheckAuthenticationAndAuthorization")
public class CustomFilterToCheckUserIsAutenticated extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String username = (String)session.getAttribute("username");
        String role = (String)session.getAttribute("role");
        Boolean isAuthenticated = (Boolean) session.getAttribute("isAuthenticated");

        if(username == null || role == null || isAuthenticated == null)
        {
            filterChain.doFilter(request,response);
        }


        if(Boolean.TRUE.equals(isAuthenticated))
        {

            CustomAuthentication auth = new CustomAuthentication();
            auth.setAuthenticated(true);
            auth.setUsername(username);
            auth.setRole(role);
            SecurityContextHolder.getContext().setAuthentication(auth);
            filterChain.doFilter(request,response);
        }
    }
}
