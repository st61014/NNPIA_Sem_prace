package nnpia.st61014.NNPIA_SemPrace.security;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

public class SecurityConstants {
    public static final long JWT_EXPIRATION = 60000;
    public static final SecretKey JWT_SECRET =  Keys.secretKeyFor(SignatureAlgorithm.HS512);

}
