package at.fh.ima.swengs.moviedbv3;

import org.springframework.beans.factory.annotation.Value;

public class JwtConfig {

    //JWT wird bei /auth/ benoetigt
    @Value("${security.jwt.uri:/auth/**}")
    private String Uri;

    //Jeder JWT Token hat einen Authorization-Header gesetzt
    //Dieser Beinhaltet Bearer <JWTToken>
    @Value("${security.jwt.header:Authorization}")
    private String header;

    @Value("${security.jwt.prefix:Bearer }")
    private String prefix;

    //Dauer wie lange der JWT gültig ist (1 Tag: 24h*60Min*60Sec)
    @Value("${security.jwt.expiration:#{24*60*60}}")
    private int expiration;

    //JwTsecrectKey representiert die Signatur
    @Value("${security.jwt.secret:JwtSecretKey}")
    private String secret;

    public String getUri() {
        return Uri;
    }

    public String getHeader() {
        return header;
    }

    public String getPrefix() {
        return prefix;
    }

    public int getExpiration() {
        return expiration;
    }

    public String getSecret() {
        return secret;
    }

}