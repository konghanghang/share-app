package com.ysla.provider.module.article.api;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.ysla.api.auto.model.ArticleComment;
import com.ysla.api.model.common.NumEnum;
import com.ysla.api.model.page.PageModel;
import com.ysla.api.module.article.IArticleCommentService;
import com.ysla.provider.module.article.dao.IArticleCommentDao;
import com.ysla.provider.module.user.dao.IUserDao;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 文章评论api实现
 * @author konghang
 */
@Component
@Service(version = "${dubbo.service.version}")
public class ArticleCommentServiceImpl implements IArticleCommentService {

    @Resource
    private IArticleCommentDao commentDao;
    @Resource
    private IUserDao userDao;


    /**
     * 添加评论
     * @param comment
     * @return
     */
    @Override
    public int addComment(ArticleComment comment) {
        comment.setFloor(0);
        if(NumEnum.ZERO.getNum().toString().equals(comment.getReplayRefId())){
            int num = commentDao.getArticleCommentNum(comment.getRefArticleId());
            comment.setFloor(num + 1);
        }
        return commentDao.insertSelective(comment);
    }

    @Override
    public PageModel getOneArticleComment(String articleId, String username, PageModel pageModel) {
        //查出文章所有的一级回复
        PageHelper.startPage(pageModel.getPageNum(), pageModel.getPageSize());
        List<JSONObject> list = commentDao.getOneArticleComment(articleId);
        for(JSONObject comment : list){
            List<JSONObject> replays = new ArrayList<>();
            comment.put("replayComment", replays);
            String userId = comment.getString("replayUser");
            JSONObject userInfo = userDao.simpleInfo(userId);
            Integer status = comment.getInteger("status");
            if(status == 1){
                comment.put("content","该条评论已被删除");
            }
            //根据username查出当前登录用户是否点赞了该条评论 TODO:查询是否点赞
            comment.put("isApprove",false);
            /*if (status == 0){
                if(username != null){
                    String commentId = comment.getString("refCommentId");
                    int count = collectDao.selectByLinkIdAndUser(username,commentId);
                    if(count > 0){
                        comment.put("isApprove",true);
                    }
                }
            }*/
            comment.put("userInfo", userInfo);
            buildReplyComment(comment, replays);
        }
        return pageModel.list(list);
    }

    /**
     * 删除评论
     * @param commentId
     * @return
     */
    @Override
    public int delete(String commentId) {
        ArticleComment articleComment = new ArticleComment();
        articleComment.setRefCommentId(commentId);
        articleComment.setStatus((byte)1);
        return commentDao.updateByRefSelective(articleComment);
    }

    /**
     * 用户收到的评论
     * @param username
     * @param pageModel
     * @return
     */
    @Override
    public PageModel received(String username, PageModel pageModel) {
        PageHelper.startPage(pageModel.getPageNum(), pageModel.getPageSize());
        List<JSONObject> list =  commentDao.receive(username);
        return pageModel.list(list);
    }

    /**
     * 获取用户的评论历史
     * @param username
     * @param pageModel
     * @return
     */
    @Override
    public PageModel replayTo(String username, PageModel pageModel) {
        PageHelper.startPage(pageModel.getPageNum(), pageModel.getPageSize());
        List<JSONObject> list = commentDao.replayTo(username);
        return pageModel.list(list);
    }

    private void buildReplyComment(JSONObject comment, List<JSONObject> replays){
        List<JSONObject> replayComments = commentDao.getByReplayRefId(comment.getString("refCommentId"));
        replays.addAll(replayComments);
        for(JSONObject c : replayComments){
            //设置评论人的信息comment
            String userId = c.getString("replayUser");
            JSONObject userInfo = userDao.simpleInfo(userId);
            c.put("userInfo", userInfo);
            //设置回复人的信息c
            String replayUserId = c.getString("replayTo");
            JSONObject replayUserInfo = userDao.simpleInfo(replayUserId);
            c.put("replayUserInfo", replayUserInfo);
            Integer status = c.getInteger("status");
            if(status == 1){
                c.put("content","该条评论已被删除");
            }
            buildReplyComment(c, replays);
        }
    }
}
