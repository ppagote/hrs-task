package com.uk.hrstask.repository;

import com.uk.hrstask.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserDetails, Long> {

    @Query
    List<UserDetails> findByHasCheckedOutFalse();

    @Query
    List<UserDetails> findByHasCheckedOutTrue();
}
