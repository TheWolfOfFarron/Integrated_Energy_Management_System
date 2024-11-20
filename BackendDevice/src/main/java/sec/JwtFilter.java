package ro.tuc.ds2020.sec;


import antlr.StringUtils;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ro.tuc.ds2020.services.PersonService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JWTUtils jwtUtils;
    private final PersonService personService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        final String authHeader= httpServletRequest.getHeader("Authorization");
         String jwt;
        String userName;

        if(authHeader==null||!authHeader.startsWith("Bearer ")){

            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }
        jwt = authHeader.substring(7);

        System.out.println(jwt);
        var c = jwtUtils.auth(jwt);
        userName=(String) c.get("user");
        String rol= (String)c.get("rol");

        if(userName==null||rol==null){
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        if(!userName.isEmpty()&& SecurityContextHolder.getContext().getAuthentication()==null){

            if(jwtUtils.isTokenValid(jwt)){


                List<GrantedAuthority> authorities = new ArrayList<>();

                if (rol.equals("ADMIN")) {
                    authorities.add(new SimpleGrantedAuthority("ADMIN"));
                    System.out.println("tccfgvhbj");
                } else {
                    authorities.add(new SimpleGrantedAuthority("USER"));
                }

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken= new UsernamePasswordAuthenticationToken(
                        userName,  null,authorities
                                );

                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                System.out.println("trece");
            }else {
                System.out.println("token expired");
            }
        }else {
            System.out.println("invalid");
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
