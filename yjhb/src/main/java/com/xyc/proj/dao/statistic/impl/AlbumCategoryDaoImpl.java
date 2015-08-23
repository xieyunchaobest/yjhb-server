package com.xyc.proj.dao.statistic.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xyc.proj.dao.base.MyBatisBaseDao;
import com.xyc.proj.dao.statistic.AlbumCategoryDao;
import com.xyc.proj.entity.statistic.AlbumCategory;

@Component
public class AlbumCategoryDaoImpl extends MyBatisBaseDao<AlbumCategory>
        implements AlbumCategoryDao {

    @Autowired
    public AlbumCategoryDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<AlbumCategory> getAlbumCategories() {
        List<AlbumCategory> categoryList = getSession().selectList(
                "getAlbumCategoryList");
        return categoryList;
    }

}
