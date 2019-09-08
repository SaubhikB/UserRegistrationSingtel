package com.saubhik.registration.repository;

import org.springframework.data.repository.CrudRepository;

import com.saubhik.registration.domain.Role;

/**
 * Created by nydiarra on 06/05/17.
 */
public interface RoleRepository extends CrudRepository<Role, Long> {
}
