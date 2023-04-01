package kz.iitu.libraryapp.core.jwt;

import java.io.Serializable;
import java.time.Instant;

public class JWTToken implements Serializable {

    private Instant issuedAt;
    private Instant expiresAt;
    private String username;

    public JWTToken() {
    }

    public JWTToken(Instant issuedAt, Instant expiresAt, String username) {
        this.issuedAt = issuedAt;
        this.expiresAt = expiresAt;
        this.username = username;
    }

    public Instant getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(Instant issuedAt) {
        this.issuedAt = issuedAt;
    }

    public Instant getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Instant expiresAt) {
        this.expiresAt = expiresAt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
