package com.ysla.api.model.page;

import com.github.pagehelper.Page;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * page工具类
 * @param <T>
 * @author konghang
 */
public class PageModel<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Setter @Getter private Integer pageSize;
    @Setter @Getter private Integer pageNum;
    @Setter @Getter private List<T> list;
    @Setter @Getter private Map<String,Object> pageModel;

    /**
     * 包装Page对象，因为直接返回Page对象，在JSON处理以及其他情况下会被当成List来处理，
     * 而出现一些问题。
     * @param pageNum
     * @param pageSize
     */
    public PageModel(Integer pageNum, Integer pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public static PageModel startPage(Integer pageNum, Integer pageSize) {
        return new PageModel(pageNum,pageSize);
    }

    public PageModel list(List<T> list) {
        if (list instanceof Page) {
            Page<T> page = (Page<T>) list;
            // 结果集
            this.list = page;
            pageModel = new HashMap<>(7);
            // 第几页
            pageModel.put("pageNum",page.getPageNum());
            // 每页记录数
            pageModel.put("pageSize",page.getPageSize());
            // 总记录数
            pageModel.put("total",page.getTotal());
            // 总页数
            pageModel.put("pages",page.getPages());
            // 当前页的数量 <= pageSize，该属性来自ArrayList的size属性
            pageModel.put("size",page.size());
        }
        return this;
    }

}
