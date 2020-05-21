/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.org.bcv.fts.util;

import java.io.Serializable;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import java.security.Key;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import javax.ws.rs.core.HttpHeaders;


/**
 *
 * @author aandrade
 */
public class Jwt implements Serializable {

    private String token;
    
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
    public Jwt() {
        
    }

    public Jwt(String token) {
        this.token = token;
    }
    
    public String createJWT(String id, String issuer, String subject, long ttlMillis) throws InterruptedException, ExpiredJwtException {
     
        String AUTHORIZATION_WORD = AlmacenPropiedades.getPropiedad("AUTHORIZATION_WORD");
        
        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
     
        long nowMillis = System.currentTimeMillis();
        System.out.println("Date: "+ new Date().toString());
        Date now = new Date(nowMillis);
     
        Key signingKey = this.getSigningKey();
     
        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(id)
                                    .setIssuedAt(now)
                                    .setSubject(subject)
                                    .setIssuer(issuer)
                                    .signWith(signatureAlgorithm, signingKey);
     
        //if it has been specified, let's add the expiration
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
     
        //Builds the JWT and serializes it to a compact, URL-safe string
        return (AUTHORIZATION_WORD+ " " +builder.compact());
    }
    
    //Sample method to validate and read the JWT
    public void parseJWT() throws InterruptedException, ExpiredJwtException {
     
        Key signingKey = this.getSigningKey();
        
        Claims claims = Jwts.parser()
           .setSigningKey(signingKey)
           .parseClaimsJws(this.getToken()).getBody();
        System.out.println("ID: " + claims.getId());
        System.out.println("Subject: " + claims.getSubject());
        System.out.println("Issuer: " + claims.getIssuer());
        System.out.println("Expiration: " + claims.getExpiration());
        
        String signature = Jwts.parser()         
        .setSigningKey(signingKey)
        .parseClaimsJws(this.getToken()).getSignature();
        
        System.out.println("Signature: " + signature);
    }
    
    public String getTokenServiceContext(HttpHeaders headers) throws Exception {
		
        String token = null;
        try {
                String AUTHORIZATION_WORD = AlmacenPropiedades.getPropiedad("AUTHORIZATION_WORD");
                
                token = headers.getRequestHeader(HttpHeaders.AUTHORIZATION).get(0);
                System.out.println("--------------------- "+token);
                token = token.substring((AUTHORIZATION_WORD.length()+1));
                System.out.println("--------------------- "+token);
                
                this.setToken(token);
                
        } catch (Exception e) {
                e.printStackTrace();
                throw new Exception(e.getMessage());
        } 
        return token;
    }
    
    public String getAccount() throws InterruptedException, ExpiredJwtException {
     
        Key signingKey = this.getSigningKey();
        
        Claims claims = Jwts.parser()
           .setSigningKey(signingKey)
           .parseClaimsJws(this.getToken()).getBody();
        System.out.println("ID: " + claims.getId());
        System.out.println("Subject: " + claims.getSubject());
        System.out.println("Issuer: " + claims.getIssuer());
        System.out.println("Expiration: " + claims.getExpiration());
        
        return claims.getId();
    }
    
    public String getSubject() throws InterruptedException, ExpiredJwtException {
        
        Key signingKey = this.getSigningKey();

        Claims claims = Jwts.parser()
           .setSigningKey(signingKey)
           .parseClaimsJws(this.getToken()).getBody();
        System.out.println("ID: " + claims.getId());
        System.out.println("Subject: " + claims.getSubject());
        System.out.println("Issuer: " + claims.getIssuer());
        System.out.println("Expiration: " + claims.getExpiration());
        
        return claims.getSubject();
    }
    
    public Date getExpiration() throws InterruptedException, ExpiredJwtException {
        
        Key signingKey = this.getSigningKey();

        Claims claims = Jwts.parser()
           .setSigningKey(signingKey)
           .parseClaimsJws(this.getToken()).getBody();
        System.out.println("ID: " + claims.getId());
        System.out.println("Subject: " + claims.getSubject());
        System.out.println("Issuer: " + claims.getIssuer());
        System.out.println("Expiration: " + claims.getExpiration());
        
        return claims.getExpiration();
    }
    
    private Key getSigningKey() throws InterruptedException, ExpiredJwtException {
     
        String SECRET_TOKEN_KEY = AlmacenPropiedades.getPropiedad("SECRET_TOKEN_KEY");
    	//The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
        //This line will throw an exception if it is not a signed JWS (as expected)
    	byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_TOKEN_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        return signingKey;
    }
    
}
