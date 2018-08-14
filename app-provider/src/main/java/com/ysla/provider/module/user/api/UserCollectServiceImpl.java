package com.ysla.provider.module.user.api;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.ysla.api.auto.model.Article;
import com.ysla.api.auto.model.UserCollect;
import com.ysla.api.model.common.ErrorCode;
import com.ysla.api.model.exception.TxException;
import com.ysla.api.model.page.PageModel;
import com.ysla.api.module.user.IUserCollectService;
import com.ysla.provider.module.article.dao.IArticleCommentDao;
import com.ysla.provider.module.article.dao.IArticleDao;
import com.ysla.provider.module.user.dao.IUserCollectDao;
import com.ysla.utils.date.DateUtils;
import com.ysla.utils.string.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 收藏api实现类
 * @author konghang
 */
@Component
@Service(version = "${dubbo.service.version}")
public class UserCollectServiceImpl implements IUserCollectService {

    @Resource
    private IUserCollectDao collectDao;
    @Resource
    private IArticleDao articleDao;
    @Resource
    private IArticleCommentDao commentDao;

    /**
     * 收藏
     * @param userCollect
     * @return
     */
    @Override
    @Transactional(rollbackFor = TxException.class)
    public int collect(UserCollect userCollect) throws TxException {
        userCollect.setCreateDate(DateUtils.getUnixStamp());
        userCollect.setRefCollectId(StringUtils.getUUID());
        userCollect.setStatus((byte)0);
        if(collectDao.collect(userCollect) < 1){
            throw new TxException(ErrorCode.TRANSACTION_ERROR);
        }
        byte type = userCollect.getType();
        Article article = new Article();
        if(1 == type){
            //文章收藏数+1
            article.setCountCollection(1);
            article.setRefArticleId(userCollect.getLinkId());
            if(articleDao.updateStatistics(article) < 1){
                throw new TxException(ErrorCode.TRANSACTION_ERROR);
            }
        }else if (2 == type){
            //评论表的点赞数+1
            if(commentDao.updateApprove(userCollect.getLinkId(),1) < 1){
                throw new TxException(ErrorCode.TRANSACTION_ERROR);
            }
        }
        return 1;
    }

    /**
     * 删除收藏
     * @param collectId
     * @param type
     * @return
     * @throws TxException
     */
    @Override
    @Transactional(rollbackFor = TxException.class)
    public int deleteCollect(String collectId, byte type) throws TxException {
        UserCollect userCollect = collectDao.selectByRef(collectId);
        if(userCollect == null){
            return 0;
        }

        userCollect.setStatus((byte)1);
        if(collectDao.updateByPrimaryKeySelective(userCollect) < 1){
            throw new TxException(ErrorCode.TRANSACTION_ERROR);
        }
        if(1 == type){
            Article article = new Article();
            article.setCountCollection(-1);
            article.setRefArticleId(userCollect.getLinkId());
            //文章收藏数-1
            if(articleDao.updateStatistics(article) < 1){
                throw new TxException(ErrorCode.TRANSACTION_ERROR);
            }
        }else if (2 == type){
            //评论表的点赞数-1
            if(commentDao.updateApprove(userCollect.getLinkId(),-1) < 1){
                throw new TxException(ErrorCode.TRANSACTION_ERROR);
            }
        }
        return 1;
    }

    /**
     * 用户收藏的文章列表
     * @param username
     * @param pageModel
     * @return
     */
    @Override
    public PageModel articleCollects(String username, PageModel pageModel) {
        PageHelper.startPage(pageModel.getPageNum(), pageModel.getPageSize());
        List<JSONObject> list = collectDao.articleCollects(username);
        return pageModel.list(list);
    }
}
