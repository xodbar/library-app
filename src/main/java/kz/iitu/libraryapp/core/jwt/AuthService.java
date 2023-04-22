package kz.iitu.libraryapp.core.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import kz.iitu.libraryapp.core.exception.auth.AuthenticationException;
import kz.iitu.libraryapp.core.user.User;
import kz.iitu.libraryapp.core.user.UserRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class AuthService {

    private static AuthService INSTANCE;

    private final UserRepository userRepository;
    private final String secret;
    private final int expirationMinutes;

    private AuthService() {
        userRepository = UserRepository.getInstance();
        expirationMinutes = 30;
        secret = "c2FsYW1BbGVpa3VtNzc3JElzSGVyZUFuZEdvbm5hRnVja0VtQWxsJSUl";
    }

    public static AuthService getInstance() {
        if (INSTANCE == null)
            INSTANCE = new AuthService();

        return INSTANCE;
    }

    public String issueToken(String username, String password) throws AuthenticationException {
        if (!isCredentialCorrect(username, password))
            throw new AuthenticationException("Incorrect credentials");

        Claims claims = Jwts.claims().setSubject(username);

        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, secret.getBytes(StandardCharsets.UTF_8))
                .setClaims(claims)
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plusSeconds(expirationMinutes * 60L)))
                .compact();
    }

    public String getUsername(String token) throws Exception {
        return Jwts.parser()
                .setSigningKey(secret.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public void ensureHasAccess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Cookie[] cookies = req.getCookies();
            List<Cookie> filtered = Arrays.stream(cookies).filter(cookie -> cookie.getName().equals("jwt")).collect(Collectors.toList());

            Cookie cookie = filtered.stream().findFirst().orElseThrow();

            String username = getUsername(cookie.getValue());

            if (userRepository.getByUsername(username) == null)
                throw new IOException("User does not exists");
        } catch (Exception e) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
            req.setAttribute("errorMessage", e.getMessage());
            dispatcher.forward(req, resp);
        }
    }

    private boolean isCredentialCorrect(String username, String password) {
        User user = userRepository.getByUsername(username);

        if (user == null)
            return false;

        return user.getPassword().equals(password);
    }

    public int getExpirationMinutes() {
        return expirationMinutes;
    }
}
