package com.saubhik.registration.repository;

import org.springframework.data.repository.CrudRepository;

import com.saubhik.registration.domain.UserDetails;

public interface UserDetailsRepository extends CrudRepository<UserDetails, Long> {

}
