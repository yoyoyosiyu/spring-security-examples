package com.huayutech.springbootsecurityjwt.web;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Key;

@RestController
public class GenerateTokenController {

    @GetMapping("/generate_token")
    protected String doGet() {

        // We need a signing key, so we'll create one just for this example. Usually
        // the key would be read from your application configuration instead.
        Key key = MacProvider.generateKey();

        String compactJws = Jwts.builder()
                .setSubject("Joe")
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();

        return compactJws;

    }

}
