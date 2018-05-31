package com.lingyun.demos.shiro.realm;

import com.lingyun.demos.shiro.entity.User;
import com.lingyun.demos.shiro.repository.UserRepository;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;


public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserRepository userRepository;


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        //获取用户的输入的账号.
        String username = ((UsernamePasswordToken) token).getUsername();
        String password = new String(((UsernamePasswordToken) token).getPassword());
        User user = userRepository.findByUsername(username);

        System.out.println(user+"dsdas");
        if(user == null){
            return null;
        }

        //ByteSource salt = ByteSource.Util.bytes(user.getId());
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(),this.getClass().getName());

        return authenticationInfo;
    }

    /**
     * 此方法调用  hasRole,hasPermission的时候才会进行回调.
     *
     * 权限信息.(授权):
     * 1、如果用户正常退出，缓存自动清空；
     * 2、如果用户非正常退出，缓存自动清空；
     * 3、如果我们修改了用户的权限，而用户不退出系统，修改的权限无法立即生效。
     * （需要手动编程进行实现；放在service进行调用）
     * 在权限修改后调用realm中的方法，realm已经由spring管理，所以从spring中获取realm实例，
     * 调用clearCached方法；
     * :Authorization 是授权访问控制，用于对用户进行的操作授权，证明该用户是否允许进行当前操作，如访问某个链接，某个资源文件等。
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        /*
         * 当没有使用缓存的时候，不断刷新页面的话，这个代码会不断执行，
         * 当其实没有必要每次都重新设置权限信息，所以我们需要放到缓存中进行管理；
         * 当放到缓存中时，这样的话，doGetAuthorizationInfo就只会执行一次了，
         * 缓存过期之后会再次执行。
         */
        //System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");

//        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
////        ManagerInfo managerInfo  = (ManagerInfo)principals.getPrimaryPrincipal();
////
////        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
//////     UserInfo userInfo = userInfoService.findByUsername(username)
////
////        //设置相应角色的权限信息
////        for(SysRole Role:managerInfo.getRoles()){
////            //设置角色
////            authorizationInfo.addRole(Role.getRole());
////            for(SysPermission p:Role.getPermissions()){
////                //设置权限
////                authorizationInfo.addStringPermission(p.getPermission());
////            }
////        }
////
////        return authorizationInfo;
        return null;
    }

}