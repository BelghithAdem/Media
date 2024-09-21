package com.example.Media.Security;

import com.example.Media.Repository.UtilisateurRespository;
import com.example.Media.Model.Utilisateur;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Service
@AllArgsConstructor
public class JwtService {
  private static final String ENCRIPTION_KEY = "608f36e92dc66d97d5933f0e6371493cb4fc05b1aa8f8de64014732472303a7c";
  private final UtilisateurRespository utilisateurRepository;

  public Map<String, String> generate(String username) {
    Optional<Utilisateur> utilisateur = utilisateurRepository.findByEmail(username);
    if (utilisateur.isPresent()) {
      return this.generateJwt(utilisateur.get());
    } else {
      throw new RuntimeException("User not found");
    }
  }

  public String extractUsername(String token) {
    return this.getClaim(token, Claims::getSubject);
  }

  public boolean isTokenExpired(String token) {
    Date expirationDate = getExpirationDateFromToken(token);
    return expirationDate.before(new Date());
  }

  private Date getExpirationDateFromToken(String token) {
    return this.getClaim(token, Claims::getExpiration);
  }

  private <T> T getClaim(String token, Function<Claims, T> function) {
    Claims claims = getAllClaims(token);
    return function.apply(claims);
  }

  private Claims getAllClaims(String token) {
    return Jwts.parser()
        .setSigningKey(this.getKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
  }

  private Map<String, String> generateJwt(Utilisateur utilisateur) {
    final long currentTime = System.currentTimeMillis();
    final long expirationTime = currentTime + 120 * 60 * 1000;

    final Map<String, Object> claims = Map.of(
        "firstName", utilisateur.getNom(),
        "userId", utilisateur.getId(),
        "email", utilisateur.getEmail(),
        "city", utilisateur.getCurrentCity(),
        "birth", utilisateur.getBirth(),
        "lastName", utilisateur.getPrenom());

    final String bearer = Jwts.builder()
        .setIssuedAt(new Date(currentTime))
        .setExpiration(new Date(expirationTime))
        .setSubject(utilisateur.getEmail())
        .addClaims(claims)
        .signWith(getKey(), SignatureAlgorithm.HS256)
        .compact();
    return Map.of("bearer", bearer);
  }

  private Key getKey() {
    final byte[] decoder = Decoders.BASE64.decode(ENCRIPTION_KEY);
    return Keys.hmacShaKeyFor(decoder);
  }

}
