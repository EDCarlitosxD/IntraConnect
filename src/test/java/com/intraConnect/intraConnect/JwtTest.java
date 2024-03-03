package com.intraConnect.intraConnect;

import com.intraConnect.intraConnect.service.JwtService;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.jsonwebtoken.JwtException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class JwtTest {


        @Autowired
        private JwtService jwtService;

        @Test
        public void testGetSubjectValidToken() {
            String token = jwtService.generateToken("testUser", 1L);
            String subject = jwtService.getSubject(token);

            assertEquals("testUser",subject);

        }

        @Test
        public void generateValidToken(){

            String token = jwtService.generateToken("Juan",43L);
            boolean validToken = jwtService.validToken(token);
            assertTrue(validToken);

        }
        @Test
        public void generateValidTokenWithClaims(){
        }


        @Test
        public void getClaimsTest(){

            Map<String, Object> claims = new HashMap<>();
            claims.put("Hola","Mundo");
            claims.put("Numero",23);
            claims.put("x","d");

            String token = jwtService.generateToken("Juanito",23L,claims);


            Claims claimsToken = jwtService.getClaims(token);

            assertEquals("Mundo", claimsToken.get("Hola"));
            assertEquals(23, claimsToken.get("Numero"));
            assertEquals("d", claimsToken.get("x"));
        }

        @Test
        public void invalidToken(){

            String falseToken = "ajajaaj";

            assertThrows(JwtException.class, () -> jwtService.getSubject(falseToken));
            assertThrows(JwtException.class, () -> jwtService.getClaims(falseToken));

        }

}
