package com.codegym.config.sercurity;

import com.codegym.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("do filter");
        try{
            String token = getJwt(request);
            if(token!=null && jwtTokenUtil.validateToken(token)){
                String email = jwtTokenUtil.getEmailFromToken(token);
                UserDetails accountPrinciple = userDetailsService.loadUserByUsername(email);
                Collection<GrantedAuthority> roleList = (Collection<GrantedAuthority>) accountPrinciple.getAuthorities();
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        accountPrinciple,null,roleList
                );
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        filterChain.doFilter(request,response);
    }
    private String getJwt(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        if(header!=null && header.startsWith("Bearer")){
            String token = header.replace("Bearer","");
            return token;
        }
        return null;
    }
}
