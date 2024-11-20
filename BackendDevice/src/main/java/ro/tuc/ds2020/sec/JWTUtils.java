package ro.tuc.ds2020.sec;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.var;
import org.springframework.stereotype.Service;



import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Service
public class JWTUtils {




    private Key getKeys(){
        byte[] key= Decoders.BASE64.decode("5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437")   ;

        return Keys.hmacShaKeyFor(key);
    }


    public Claims auth(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getKeys())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    private Claims  extracttAllClaims(String token){
        System.out.println(Jwts.parserBuilder().setSigningKey(getKeys()).build().parseClaimsJws(token).getBody());
        return Jwts.parserBuilder().setSigningKey(getKeys()).build().parseClaimsJws(token).getBody();
    }

    private <T> T exctratClaims(String token,Function<Claims,T> claimsResolver){
        System.out.println(claimsResolver.apply(extracttAllClaims(token)));
        return claimsResolver.apply(extracttAllClaims(token));
    }


    public String extractSubjectFromJwt(String jwtToken) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getKeys()).build()
                .parseClaimsJws(jwtToken)
                .getBody();

        return claims.getSubject();
    }

    public boolean isTokenValid(String token){
        return exctratClaims(token,Claims::getExpiration).after(new Date(System.currentTimeMillis()));
    }



}
