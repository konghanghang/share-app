package com.ysla.web.config.shiro.realm;

import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.ysla.api.model.common.ErrorCode;
import com.ysla.web.config.shiro.JwtStatelessToken;
import com.ysla.web.config.shiro.JwtUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

/**
 * 自定义认证类
 * @author konghang
 */
public class JwtStatelessRealm extends AuthorizingRealm {
    
    private final Logger logger = LoggerFactory.getLogger(getClass().getName());

    /**
     * 必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token != null && token instanceof JwtStatelessToken;
    }
    
    /**
     * 为当前登录的Subject授予角色和权限
     * -经测试:本例中该方法的调用时机为需授权资源被访问时
     * -经测试:并且每次访问需授权资源时都会执行该方法中的逻辑,这表明本例中默认并未启用AuthorizationCache
     * -个人感觉若使用了Spring3.1开始提供的ConcurrentMapCache支持,则可灵活决定是否启用AuthorizationCache
     * -比如说这里从数据库获取权限信息时,先去访问Spring3.1提供的缓存,而不使用Shiro提供的AuthorizationCache
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals){
        logger.trace("doGetAuthorizationInfo:"+principals.toString());
        String username = principals.toString();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        /*HashSet<String> roles = userRoleService.userRole(username);
        simpleAuthorizationInfo.addRoles(roles);
        HashSet<String> permissions = rolePermissionService.rolePermissions(roles);
        simpleAuthorizationInfo.addStringPermissions(permissions);*/
        return simpleAuthorizationInfo;
    }


    /**
     * 验证当前登录的Subject
     * -经测试:本例中该方法的调用时机为UserController.login()方法中执行Subject.login()时
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        JwtStatelessToken jwtStatelessToken = (JwtStatelessToken)authenticationToken;
        String token = (String) jwtStatelessToken.getCredentials();
        String username = (String) jwtStatelessToken.getPrincipal();
        try {
            /*JwtUtil.verifyToken(token,username,user.getSalt());*/
            JwtUtil.verifyToken(token,username,"123456");
        } catch (UnsupportedEncodingException e) {
            throw new IncorrectCredentialsException();
        } catch (SignatureVerificationException e) {
            throw new IncorrectCredentialsException(ErrorCode.TOKEN_SIGNATURE_ERROR.getErrorMsg());
        } catch (TokenExpiredException e) {
            throw new IncorrectCredentialsException(ErrorCode.TOKEN_EXPIRED.getErrorMsg());
        } catch (InvalidClaimException e) {
            throw new IncorrectCredentialsException(ErrorCode.TOKEN_INVALID_CLAIM.getErrorMsg());
        } catch (JWTVerificationException e) {
            throw new IncorrectCredentialsException(ErrorCode.TOKEN_ERROR.getErrorMsg());
        } catch (Exception e){
            throw new IncorrectCredentialsException(ErrorCode.TOKEN_ERROR.getErrorMsg());
        }
        /*if(null != user){*/
            if(true){
                AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, token, getName());//getName():realm name
                return authenticationInfo;
            }else{
                //没有返回登录用户名对应的SimpleAuthenticationInfo对象时,就会在LoginController中抛出UnknownAccountException异常
                return null;
            }
        /*}*/
    }

}
