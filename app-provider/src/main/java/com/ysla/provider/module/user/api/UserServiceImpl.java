package com.ysla.provider.module.user.api;

import com.alibaba.dubbo.config.annotation.Service;
import com.ysla.api.auto.model.User;
import com.ysla.api.model.common.ErrorCode;
import com.ysla.api.model.exception.BaseException;
import com.ysla.api.model.exception.TxException;
import com.ysla.api.module.user.IUserService;
import com.ysla.provider.module.user.dao.IUserDao;
import com.ysla.utils.crypto.CryptoUtils;
import com.ysla.utils.date.DateUtils;
import com.ysla.utils.string.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 用户api接口
 * @author konghang
 */
@Component
@Service(version = "${dubbo.service.version}")
public class UserServiceImpl implements IUserService {

    @Resource
    private IUserDao userDao;

    /**
     * 查询用户
     * @param user
     * @return
     */
    @Override
    public User selectUserBy(User user) {
        return userDao.selectUserBy(user);
    }

    /**
     * 注册用户
     * @param user
     * @return
     * @throws TxException
     */
    @Transactional(rollbackFor = TxException.class)
    @Override
    public Integer saveUser(User user) throws TxException {
        User search = new User();
        //判断是否重名
        search.setUsername(user.getUsername());
        if(!StringUtils.isEmpty(userDao.selectUserBy(search))){
            throw new BaseException(ErrorCode.USER_NAME_EXIST);
        }
        search.setUsername(null);
        search.setEmail(user.getEmail());
        if(!StringUtils.isEmpty(userDao.selectUserBy(search))){
            throw new BaseException(ErrorCode.USER_EMAIL_EXIST);
        }
        //取到加密种子
        String salt = CryptoUtils.getSalt();
        user.setUsername(user.getUsername().trim());
        user.setPassword(user.getPassword().trim());
        //生成新的加密密码
        String hashPassword = CryptoUtils.passwordMD5(user.getPassword() + salt + user.getUsername());
        user.setSalt(salt);
        user.setPassword(hashPassword);
        user.setRefUserId(StringUtils.getUUID());
        user.setCreateDate(DateUtils.getUnixStamp());
        user.setSex((byte)1);

        if(userDao.insertSelective(user) < 1){
            throw new TxException(ErrorCode.USER_CREATE_ERROR);
        }
        /*UserRole userRole = new UserRole();
        userRole.setRefUserId(user.getRefUserId());
        userRole.setRoleId(1);
        if(userRoleDao.insertSelective(userRole) < 1){
            throw new TxException(ErrorCode.USER_ROLE_CREATE_ERROR);
        }*/
        return 1;
    }
}
