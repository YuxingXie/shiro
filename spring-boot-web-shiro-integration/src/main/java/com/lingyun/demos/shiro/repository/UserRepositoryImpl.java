package com.lingyun.demos.shiro.repository;

import com.lingyun.demos.shiro.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class UserRepositoryImpl {
    @PersistenceContext
    private EntityManager em;

    public User findByUsername(String username){
        String jpql="select user from User user where user.username = ?1";
        TypedQuery<User> query=em.createQuery(jpql, User.class);
        query.setParameter(1,username);
        List<User> userList=query.getResultList();
        assert userList==null||userList.size()<=1;
        if(userList==null|userList.size()==0){
            System.out.println("no user find");
        }else {
            System.out.println("find "+userList.size()+" users");
        }
        return userList==null||userList.size()==0?null:userList.get(0);
    }
}
