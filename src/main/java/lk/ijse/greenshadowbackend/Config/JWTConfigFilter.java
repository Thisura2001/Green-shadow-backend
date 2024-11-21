//package lk.ijse.greenshadowbackend.Config;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lk.ijse.greenshadowbackend.Service.JWTService;
//import lk.ijse.greenshadowbackend.Service.UserService;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//@Configuration
//public class JWTConfigFilter extends OncePerRequestFilter {
//    @Autowired
//    private JWTService jwtService;
//    @Autowired
//    private UserService userService;
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String initToken = request.getHeader("Authorization");
//        String userEmail;
//        String extractedJwtToken;
//        //Validate the token
//        if(StringUtils.isEmpty(initToken) || !initToken.startsWith("Bearer ")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//        extractedJwtToken = initToken.substring(7);
//        userEmail = jwtService.extractUserName(extractedJwtToken);
//        // user email
//        if(StringUtils.isNotEmpty(userEmail) &&
//                SecurityContextHolder.getContext().getAuthentication() == null) {
//            var userDetails = userService.userDetailsService().loadUserByUsername(userEmail);
//            if(jwtService.validateToken(extractedJwtToken, userDetails)) {
//                //add user to the securoty context
//                SecurityContext emptyContext =
//                        SecurityContextHolder.createEmptyContext();
//                var authToken =
//                        new UsernamePasswordAuthenticationToken(
//                                userDetails,null,userDetails.getAuthorities());
//                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                emptyContext.setAuthentication(authToken);
//                SecurityContextHolder.setContext(emptyContext);
//            }
//
//        }
//        filterChain.doFilter(request, response);
//    }
//}
