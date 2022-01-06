package com.rafaelvieira.clientsecurityjwt.repository;

import com.rafaelvieira.clientsecurityjwt.entity.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleModel, Long> {

    Optional<RoleModel> findByName(String name);

}
