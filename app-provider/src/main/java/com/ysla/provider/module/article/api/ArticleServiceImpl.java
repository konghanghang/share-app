package com.ysla.provider.module.article.api;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.ysla.api.auto.model.Article;
import com.ysla.api.model.page.PageModel;
import com.ysla.api.module.article.IArticleService;
import com.ysla.provider.module.article.dao.IArticleDao;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 文章api实现类
 * @author konghang
 */
@Component
@Service(version = "${dubbo.service.version}")
public class ArticleServiceImpl implements IArticleService {

    @Resource
    private IArticleDao articleDao;

    /**
     * 新增文章
     * @param article
     * @return
     */
    @Override
    public Integer insert(Article article) {
        return articleDao.insertSelective(article);
    }

    /**
     * 获取文章详细
     * @param articleId
     * @param username  用于判断登录用户是否收藏了文章 todo: 收藏
     * @return
     */
    @Override
    public JSONObject getArticleByRef(String articleId, String username) {
        JSONObject json = articleDao.getArticleByRef(articleId);
        json.put("isCollect",false);
        /*if(username != null){
            int count = collectDao.selectByLinkIdAndUser(username,json.getString("refArticleId"));
            if(count > 0){
                json.put("isCollect",true);
            }
        }*/
        return json;
    }

    /**
     * 获取所有文章,username不为空则获取某人的所有文章
     * @param username
     * @return
     */
    @Override
    public PageModel articles(String username, PageModel pageModel) {
        PageHelper.startPage(pageModel.getPageNum(), pageModel.getPageSize());
        List<JSONObject> list = articleDao.articles(username);
        return pageModel.list(list);
    }

    /**
     * 根据articleId更新文章
     * @param article
     * @return
     */
    @Override
    public int update(Article article) {
        return articleDao.updateByPrimaryKeySelective(article);
    }

    /**
     * 根据refArticleId更新文章
     * @param article
     * @return
     */
    @Override
    public int updateByRefSelective(Article article) {
        return articleDao.updateByRefSelective(article);
    }

    /**
     * 精选文章
     * @return
     */
    @Override
    public List<JSONObject> chosen() {
        return articleDao.chosen();
    }

    /**
     * 更新文章统计数据
     * @param article
     * @return
     */
    @Override
    public int updateStatistics(Article article) {
        return articleDao.updateStatistics(article);
    }
}
