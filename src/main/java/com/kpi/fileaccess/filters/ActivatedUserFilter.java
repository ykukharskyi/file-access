package com.kpi.fileaccess.filters;

import com.kpi.fileaccess.domain.User;
import com.kpi.fileaccess.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ActivatedUserFilter implements Filter {

    @Autowired
    private UserService userService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        if (
                httpRequest.getServletPath().equals("/login") ||
                httpRequest.getServletPath().equals("/users/activate") ||
                httpRequest.getServletPath().equals("/") ||
                httpRequest.getServletPath().equals("/users/token/send") ||
                httpRequest.getServletPath().equals("/users/token/activate")

        ) {
            chain.doFilter(request, response);
            return;
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        User user = userService.readByEmail(userEmail);
        if (!user.isActivated()) {
            httpResponse.sendRedirect("/users/activate");
        }
        chain.doFilter(request, response);
    }
}
