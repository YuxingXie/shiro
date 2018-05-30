package com.lingyun.demos.shiro.repository;

import com.lingyun.demos.shiro.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class UserRepositoryImpl {
    @PersistenceContext
    private EntityManager em;

    public void testSelectAll(){
        String jpql="select user from User user";
        List<User> userList=em.createQuery(jpql, User.class).getResultList();
        if(userList==null){
            System.out.println("no user find");

        }else {
            System.out.println("find "+userList.size()+" users");
        }
    }
}
