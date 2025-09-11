package com.staj.CarCommerceApp.filters;

import com.staj.CarCommerceApp.services.JWTService;
import com.staj.CarCommerceApp.services.LoggingService;
import com.staj.CarCommerceApp.services.MyUserDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final LoggingService loggingService;
    private final ApplicationContext applicationContext;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        loggingService.logInfo("Using JWTFilter");

        String authorizationHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;
        UserDetails userDetails;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
            username = jwtService.extractUsername(token);
        }


        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            try {
                userDetails = applicationContext.getBean(MyUserDetailService.class).loadUserByUsername(username);
            } catch (UsernameNotFoundException | BeansException e) {
                throw new RuntimeException(e);
            }

            if (jwtService.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken newToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                loggingService.logInfo("Authorities: " + userDetails.getAuthorities());
                newToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(newToken); // add the new token to the chain for UsernamePasswordAuthenticationFilter to use
            }
        }
        filterChain.doFilter(request, response);
    }
}
