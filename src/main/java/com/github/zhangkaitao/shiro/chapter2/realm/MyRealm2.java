package com.github.zhangkaitao.shiro.chapter2.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.realm.Realm;

public class MyRealm2 implements Realm {
    @Override
    public String getName() {
        /**
         *todo
         * ini文件中myRealm2=com.github.zhangkaitao.shiro.chapter2.realm.MyRealm2的key:myRealm2并不代表realm的name，这个才是
         * ini中的key可以理解为shiro使用的Realm的bean名
         */
        return "myRealm2";
    }

    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        return false;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        return null;
    }
}
