package com.example.skillwillproject25.Controllers;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import lombok.Getter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PostMapping("/login")
    public String UserLogin(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        if(username.equals("temo") && password.equals("temotemo123"))
        {
            session.setAttribute("username",username);
            session.setAttribute("isAuthenticated", Boolean.TRUE);
            session.setAttribute("role","User");
        }else if(username.equals("giorgi") && password.equals("giogoi123"))
        {
            session.setAttribute("username",username);
            session.setAttribute("isAuthenticated", Boolean.TRUE);
            session.setAttribute("role", "Admin");
        }else
        {
            return "user not found";
        }
        return "sucsess";

    }

    @GetMapping("/User")
    public String user()
    {
        return "hello User";
    }

    @GetMapping("/Admin")
    public String admin()
    {
        return "hello Admin";
    }

    @GetMapping("/Hello")
    public String hello()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }



}
