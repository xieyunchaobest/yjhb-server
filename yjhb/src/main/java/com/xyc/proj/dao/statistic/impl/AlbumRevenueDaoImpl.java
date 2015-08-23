package com.xyc.proj.dao.statistic.impl;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xyc.proj.dao.base.MyBatisBaseDao;
import com.xyc.proj.dao.statistic.AlbumRevenueDao;
import com.xyc.proj.entity.statistic.AlbumPageViewQueryItem;
import com.xyc.proj.entity.statistic.AlbumPageViewResultItem;
import com.xyc.proj.entity.statistic.AlbumRevenueQueryItem;
import com.xyc.proj.entity.statistic.AlbumRevenueResultItem;
import com.xyc.proj.utility.PageView;

@Component
public class AlbumRevenueDaoImpl<T extends AlbumRevenueResultItem> extends
        MyBatisBaseDao<T> implements AlbumRevenueDao {

    @Autowired
    public AlbumRevenueDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    public PageView queryAlbumRevenue(Map<String, Object> paramMap) {

        PageView result = getEntityPageList(paramMap, "queryAlbumRevenueCount",
                "getAlbumRevenuePageList");
        return result;
    }

    public List<AlbumRevenueResultItem> queryExportAlbumRevenue(
            Map<String, Object> paramMap) {
        List<AlbumRevenueResultItem> exportData = getSession().selectList(
                "getExportAlbumRevenueResultList", paramMap);
        return exportData;
    }

    public Object queryClientAndWebRevenue(AlbumRevenueQueryItem item) {
        Object result = getSession().selectOne("queryAlbumWebAndClientRevenue",
                item);

        return result;
    }

    @Override
    public PageView albumAndMusicWithCouponStat(Map<String, Object> paramMap) {
        PageView result = getEntityPageList(paramMap,
                "qryCondition4getAlbumAndMusicWithCouponStatCount",
                "qryCondition4getAlbumAndMusicWithCouponStat");
        return result;
    }

    public List exportAlbumAndMusicWithCouponStat(Map<String, Object> paramMap) {
        List resList = getSession().selectList(
                "exportAlbumAndMusicWithCouponStat", paramMap);
        return resList;
    }

    public PageView queryPagableAlbumPageView(Map<String, Object> paramMap) {
        return getEntityPageList(paramMap, "queryAlbumPageViewCount",
                "getAlbumPageViewPageList");
    }

    public Object queryAlbumAndTrackRevenue(AlbumPageViewQueryItem item) {
        Object result = getSession().selectOne("queryAlbumAndTrackRevenue",
                item);

        return result;
    }

    public List<AlbumPageViewResultItem> queryExportAlbumPageView(
            Map<String, Object> paramMap) {
        List<AlbumPageViewResultItem> exportData = getSession().selectList(
                "getExportAlbumPageViewResultList", paramMap);
        return exportData;
    }
}
