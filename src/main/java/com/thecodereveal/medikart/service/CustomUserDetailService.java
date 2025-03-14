package com.thecodereveal.medikart.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.thecodereveal.medikart.model.User;
import com.thecodereveal.medikart.repository.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserDetailRepository userDetailRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDetailRepository.findByEmail(username);

        if(null == user){
            //throw exception
            throw  new UsernameNotFoundException("User not found with username "+username);
        }
        return user;
    }


    public User getUserByEmail(String email){
        return userDetailRepository.findByEmail(email);
    }

    public User createUser(Map<String, Object> data, GoogleIdToken.Payload payload){
        String email = payload.getEmail();
        String name = (String) payload.get("name");
        String photo = (String) payload.get("picture");
        User user = User.builder()
                .name(name)
                .email(email)
                .image(photo)
                .roles(List.of(User.UserRole.USER))
                .build();
        return userDetailRepository.save(user);

    }
}
