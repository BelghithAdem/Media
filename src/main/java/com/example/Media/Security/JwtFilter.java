package com.example.Media.Security;

import com.example.Media.Model.Utilisateur;
import com.example.Media.Repository.UtilisateurRespository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@Service
public class JwtFilter extends OncePerRequestFilter {

  private final JwtService jwtService;
  private final UtilisateurRespository utilisateurRepository;

  @Autowired
  public JwtFilter(
      JwtService jwtService, UtilisateurRespository utilisateurRepository) {
    this.jwtService = jwtService;
    this.utilisateurRepository = utilisateurRepository;
  }

  @Override
  protected void doFilterInternal(jakarta.servlet.http.HttpServletRequest request,
      jakarta.servlet.http.HttpServletResponse response, jakarta.servlet.FilterChain filterChain)
      throws jakarta.servlet.ServletException, IOException {
    String token = null;
    String username = null;
    boolean isTokenExpired = true;
    try {
      final String authorization = request.getHeader("Authorization");
      if (authorization != null && authorization.startsWith("Bearer ")) {
        token = authorization.substring(7);
        isTokenExpired = jwtService.isTokenExpired(token);
        username = jwtService.extractUsername(token);
      }

      if (!isTokenExpired && username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        Optional<Utilisateur> userDetailsOptional = utilisateurRepository.findByEmail(username);
        if (userDetailsOptional.isPresent()) {
          Utilisateur userDetails = userDetailsOptional.get();
          UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,
              null, userDetails.getAuthorities());
          SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        } else {
          throw new RuntimeException("User not found");
        }
      }

      filterChain.doFilter(request, response);
    } catch (Exception exception) {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      response.setContentType(MediaType.APPLICATION_JSON_VALUE);

      String errorMessage = "La connexion est invalide.";

      log.error("Erreur de validation du jeton JWT", exception);

      String errorResponse = String.format("{\"error\": \"%s\"}", errorMessage);

      response.getWriter().write(errorResponse);
      response.getWriter().flush();
      response.getWriter().close();
    }
  }
}
