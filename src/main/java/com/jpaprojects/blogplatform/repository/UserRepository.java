package com.jpaprojects.blogplatform.repository;

import com.jpaprojects.blogplatform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //spring works under the hood :)
}
