package com.xyc.proj.dao.statistic;

import java.util.List;
import java.util.Map;

import com.xyc.proj.entity.statistic.AlbumPageViewQueryItem;
import com.xyc.proj.entity.statistic.AlbumPageViewResultItem;
import com.xyc.proj.entity.statistic.AlbumRevenueQueryItem;
import com.xyc.proj.entity.statistic.AlbumRevenueResultItem;
import com.xyc.proj.utility.PageView;

public interface AlbumRevenueDao {

    public PageView queryAlbumRevenue(Map<String, Object> paramMap);

    public List<AlbumRevenueResultItem> queryExportAlbumRevenue(
            Map<String, Object> paramMap);

    public Object queryClientAndWebRevenue(AlbumRevenueQueryItem item);

    public PageView albumAndMusicWithCouponStat(Map<String, Object> paramMap);

    public List exportAlbumAndMusicWithCouponStat(Map<String, Object> paramMap);

    public PageView queryPagableAlbumPageView(Map<String, Object> paramMap);

    public Object queryAlbumAndTrackRevenue(AlbumPageViewQueryItem item);

    public List<AlbumPageViewResultItem> queryExportAlbumPageView(
            Map<String, Object> paramMap);
}
