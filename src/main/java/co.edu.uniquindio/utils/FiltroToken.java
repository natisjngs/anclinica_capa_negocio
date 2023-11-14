package co.edu.uniquindio.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.io.IOException;
import static com.cloudinary.AccessControlRule.AccessType.token;

@Component
@RequiredArgsConstructor
public class FiltroToken implements Filter {
    private final JWTUtils jwtUtils;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;

        try{
            if (token != null) {
                Jws<Claims> jws = jwtUtils.parseJwt(String.valueOf(token)); // Revisar con el profe
                System.out.println(jws.getBody().getSubject());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        chain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest req) {
        String header = req.getHeader("Autorizacion");
        if (header != null && header.startsWith("Bearer "))
            return header.replace("Bearer", "");
        return null;
    }
}
