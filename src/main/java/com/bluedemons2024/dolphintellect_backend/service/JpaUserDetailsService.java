//package com.bluedemons2024.dolphintellect_backend.service;
//
//import com.bluedemons2024.dolphintellect_backend.Account.UserRepistory;
//import com.bluedemons2024.dolphintellect_backend.Account.SecurityUser;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class JpaUserDetailsService implements UserDetailsService {
//
//    private final UserRepistory userRepistory;
//
//
//    public JpaUserDetailsService(UserRepistory userRepistory) {
//        this.userRepistory = userRepistory;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return this.userRepistory
//                .findByUsername(username)
//                .map(SecurityUser::new)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
//
//    }
//}
