package com.xyc.proj.service.statistic;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.xyc.proj.entity.statistic.AlbumCategory;
import com.xyc.proj.entity.statistic.AlbumPageViewQueryItem;
import com.xyc.proj.entity.statistic.AlbumRevenueQueryItem;
import com.xyc.proj.utility.PageView;

public interface StatisticService {

    public PageView queryPagableAlbumRevenue(Map<String, Object> paramMap);

    public List<AlbumCategory> queryAlbumCategory();

    public HSSFWorkbook queryExportAlbumRevenue(Map<String, Object> paramMap);

    public Object queryClientAndWebRevenue(AlbumRevenueQueryItem item);

    public PageView albumAndMusicWithCouponStat(Map parmMap);

    public List getArtistList();

    public HSSFWorkbook exportAlbumAndMusicWithCouponStat(
            Map<String, Object> paramMap);

    public List getExportAlbumAndMusicWithCouponStatList(
            Map<String, Object> paramMap);

    public PageView queryPagableAlbumPageView(Map<String, Object> parmMap);

    public Object queryAlbumAndTrackRevenue(AlbumPageViewQueryItem item);

    public HSSFWorkbook queryExportAlbumPageView(Map<String, Object> paramMap);

}
