package nc.opt.colisnc.config;

import nc.opt.colisnc.app.authentification.GeodeUser;
import nc.opt.colisnc.app.authentification.service.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private final JwtService jwtManager;

    JWTAuthorizationFilter(AuthenticationManager authManager, JwtService jwtManager) {
        super(authManager);
        this.jwtManager = jwtManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null) {

            token = jwtManager.extractBearerToken(token);

            GeodeUser geodeuser = jwtManager.parseUserFromToken(token);

            if (geodeuser != null) {
                return new UsernamePasswordAuthenticationToken(geodeuser, null, new ArrayList<>());
            }
            return null;
        }
        return null;
    }
}