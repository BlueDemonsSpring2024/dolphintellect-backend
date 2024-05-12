//package com.bluedemons2024.dolphintellect_backend.Account;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.List;
//
//public class SecurityUser implements UserDetails {
//
//
//    private UserEntity user;
//
//    public SecurityUser(UserEntity account) {
//        this.user = account;
//    }
//
//    @Override
//    public String getUsername() {
//        System.out.println("Getting username");
//        System.out.println(this.user.getUsername());
////        return this.account.getEmail();
//        return user.getUsername();
//    }
//
//    @Override
//    public String getPassword() {
//        System.out.println("Getting password");
//        return this.user.getPassword();
//    }
//
//
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        System.out.println("Getting Authority");
////        System.out.println(this.user.getRole());
//
//        return List.of();
////        return Arrays.stream(this.account
////                        .getRole()
////                        .split(","))
////                .map(SimpleGrantedAuthority::new)
////                .toList();
//    }
//
//
//
//
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
