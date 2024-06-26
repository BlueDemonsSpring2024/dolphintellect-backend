package com.bluedemons2024.dolphintellect_backend.Account;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);

    @Override
    void deleteAllById(Iterable<? extends Long> longs);

    boolean existsByName(String name);

}
