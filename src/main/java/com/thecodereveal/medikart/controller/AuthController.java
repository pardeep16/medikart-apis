package com.thecodereveal.medikart.controller;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.thecodereveal.medikart.config.JWTTokenHelper;
import com.thecodereveal.medikart.dto.AuthResponse;
import com.thecodereveal.medikart.dto.SignInRequest;
import com.thecodereveal.medikart.model.User;
import com.thecodereveal.medikart.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    @Value("${google.client.id}")
    private String clientId;

    @Autowired
    private CustomUserDetailService userDetailsService;

    @Autowired
    private JWTTokenHelper jwtTokenHelper;


    @PostMapping("/google")
    public ResponseEntity<?> authenticate(@RequestBody Map<String, Object> request){

        try {
            JsonFactory jsonFactory = new GsonFactory();
           String idToken = (String) request.get("idToken");
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(),
                    jsonFactory).setAudience(Collections.singletonList(clientId)).build();

            GoogleIdToken idToken1 = verifier.verify(idToken);
            if(idToken1 != null){
                GoogleIdToken.Payload payload = idToken1.getPayload();

                if(!payload.getAudience().equals(clientId)){

                }
                String email = payload.getEmail();
                String name = payload.getEmail();
                User user = userDetailsService.getUserByEmail(email);
                if(null!= user && user.getEmail().equals(email)){
                    //create jwt toekn and return it
                    String token = jwtTokenHelper.generateToken(email);
                    AuthResponse authResponse = AuthResponse.builder()
                            .token(token).build();
                    return ResponseEntity.ok().body(authResponse);
                }
                else{
                    //register new User
                    User userObj = userDetailsService.createUser(request,payload);
                    String token = jwtTokenHelper.generateToken(email);
                    AuthResponse authResponse = AuthResponse.builder()
                            .token(token).build();
                    return ResponseEntity.ok().body(authResponse);
                }
            }
            return ResponseEntity.badRequest().body("Invalid Request");
        }
        catch (Exception e){
            System.out.println("Error "+e);
            return ResponseEntity.internalServerError().body("Something went wrong");
        }


    }

    @PostMapping("/test")
    public ResponseEntity<?> test(){
        return ResponseEntity.ok().body("hello");
    }
}
