package com.example.Media.Security;


import com.example.Media.Services.UtilisateurService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Slf4j
@Service
public class JwtFilter extends OncePerRequestFilter {

  private final UtilisateurService utilisateurService;
  private final JwtService jwtService;



  public JwtFilter(
   @Lazy UtilisateurService utilisateurService,
    JwtService jwtService
   ) {
    this.utilisateurService = utilisateurService;
    this.jwtService = jwtService;

  }

    @Override
    protected void doFilterInternal(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, jakarta.servlet.FilterChain filterChain) throws jakarta.servlet.ServletException, IOException {
      String token = null;
      String username = null;
      boolean isTokenExpired = true;
      try {
        // Bearer eyJhbGciOiJIUzI1NiJ9.eyJub20iOiJBY2hpbGxlIE1CT1VHVUVORyIsImVtYWlsIjoiYWNoaWxsZS5tYm91Z3VlbmdAY2hpbGxvLnRlY2gifQ.zDuRKmkonHdUez-CLWKIk5Jdq9vFSUgxtgdU1H2216U
        final String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.startsWith("Bearer ")) {
          token = authorization.substring(7);
          isTokenExpired = jwtService.isTokenExpired(token);
          username = jwtService.extractUsername(token);
        }

        if (!isTokenExpired && username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
          UserDetails userDetails = utilisateurService.loadUserByUsername(username);
          UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
          SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        filterChain.doFilter(request, response);
      } catch (Exception exception) {
        // Gérer l'exception ici
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        // Vous pouvez personnaliser le message d'erreur en fonction du type d'exception
        String errorMessage = "La connexion est invalide.";

        // Vous pouvez également logger l'exception si nécessaire
        log.error("Erreur de validation du jeton JWT", exception);

        // Construire le corps de la réponse JSON
        String errorResponse = String.format("{\"error\": \"%s\"}", errorMessage);

        // Écrire la réponse dans le corps de la réponse HTTP
        response.getWriter().write(errorResponse);
        response.getWriter().flush();
        response.getWriter().close();


      }
    }}
