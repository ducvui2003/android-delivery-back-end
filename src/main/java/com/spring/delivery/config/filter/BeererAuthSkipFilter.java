package com.spring.delivery.config.filter;

import com.spring.delivery.config.properties.ApplicationProps;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component("beererAuthSkipFilter")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class BeererAuthSkipFilter extends OncePerRequestFilter {
    String BEERER_PREFIX = "Bearer ";
    ApplicationProps applicationProps;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");
        String requestURI = request.getRequestURI();

        // Check if the request URI matches any of the whitelist patterns
        boolean isWhitelisted = applicationProps.getWhitelist().stream()
                .anyMatch(pattern -> requestURI.matches(convertPatternToRegex(pattern)));

        // Skip authentication if it's a whitelisted path and Beerer token is found
        if (isWhitelisted && authorizationHeader != null && authorizationHeader.startsWith(BEERER_PREFIX)) {
            SecurityContextHolder.clearContext();  // Skip authentication
        }

        // Proceed with the filter chain
        filterChain.doFilter(request, response);
    }

    // Helper function to convert patterns like "/api/public/**" to regex
    private String convertPatternToRegex(String pattern) {
        return pattern.replace("/**", ".*").replace("*", ".*");
    }
}
