package com.bluedemons2024.dolphintellect_backend.service;

import com.bluedemons2024.dolphintellect_backend.Account.AccountRepository;
import com.bluedemons2024.dolphintellect_backend.Account.SecurityUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;


    public JpaUserDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        this.accountRepository.findByUsername(username);

        return this.accountRepository
                .findByUsername(username)
                .map(SecurityUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

    }
}
