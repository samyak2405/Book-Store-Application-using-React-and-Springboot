package com.javahunter.application.config;

import com.javahunter.application.service.JWTService;
import com.javahunter.application.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final UserService userService;

    /**
     * Same contract as for {@code doFilter}, but guaranteed to be
     * just invoked once per request within a single request thread.
     * See {@link #shouldNotFilterAsyncDispatch()} for details.
     * <p>Provides HttpServletRequest and HttpServletResponse arguments instead of the
     * default ServletRequest and ServletResponse ones.
     *
     * @param request
     * @param response
     * @param filterChain
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("Inside do filter Internal Method");
        log.info("Get Header Authorization: {}",request.getHeader("Authorization"));
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userName;

        if(request.getRequestURI().startsWith("/swagger")){
            filterChain.doFilter(request,response);
            return;
        }

        if(StringUtils.isEmpty(authHeader) || !org.apache.commons.lang3.StringUtils.startsWith(authHeader,"Bearer ")){
            log.info("auth header empty: {}",authHeader);
            log.info("auth Header empty");
            filterChain.doFilter(request,response);
            return;
        }
        log.info("AuthHeader: {}",authHeader);
        jwt = authHeader.substring(7);
        userName = jwtService.extractUserName(jwt);

        log.info("UserName Requested: {}",userName);
        if(org.apache.commons.lang3.StringUtils.isNotEmpty(userName) && SecurityContextHolder.getContext().getAuthentication()==null)
        {
            UserDetails userDetails = userService.userDetailsService().loadUserByUsername(userName);
            if(jwtService.isTokenValid(jwt,userDetails)){
                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                        userDetails,null,userDetails.getAuthorities()
                );
                token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                securityContext.setAuthentication(token);
                SecurityContextHolder.setContext(securityContext);
            }
        }
        filterChain.doFilter(request,response);
    }
}
