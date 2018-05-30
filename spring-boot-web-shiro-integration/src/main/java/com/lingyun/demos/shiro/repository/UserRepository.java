package com.lingyun.demos.shiro.repository;

import com.lingyun.demos.shiro.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String>{
    void testSelectAll();
}
