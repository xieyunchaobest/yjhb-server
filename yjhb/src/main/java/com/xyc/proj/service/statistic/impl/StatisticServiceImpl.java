package com.xyc.proj.service.statistic.impl;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyc.proj.dao.statistic.AlbumCategoryDao;
import com.xyc.proj.dao.statistic.AlbumRevenueDao;
import com.xyc.proj.entity.statistic.AlbumCategory;
import com.xyc.proj.entity.statistic.AlbumPageViewQueryItem;
import com.xyc.proj.entity.statistic.AlbumPageViewResultItem;
import com.xyc.proj.entity.statistic.AlbumRevenueQueryItem;
import com.xyc.proj.entity.statistic.AlbumRevenueResultItem;
import com.xyc.proj.repository.manage.ArtistRepository;
import com.xyc.proj.service.statistic.StatisticService;
import com.xyc.proj.utility.PageView;

@Service
public class StatisticServiceImpl implements StatisticService {
    @Autowired
    AlbumRevenueDao albumRevenueDao;

    @Autowired
    AlbumCategoryDao albumCategoryDao;
    @Autowired
    ArtistRepository artistRepository;

    @Override
    public PageView queryPagableAlbumRevenue(Map<String, Object> paramMap) {
        return albumRevenueDao.queryAlbumRevenue(paramMap);
    }

    public List<AlbumCategory> queryAlbumCategory() {

        return albumCategoryDao.getAlbumCategories();
    }

    public HSSFWorkbook queryExportAlbumRevenue(Map<String, Object> paramMap) {
        return createAlbumRevenueWorkbook(albumRevenueDao
                .queryExportAlbumRevenue(paramMap));
    }

    private HSSFWorkbook createAlbumRevenueWorkbook(
            List<AlbumRevenueResultItem> data) {
        String columns[] = { "Album Title", "Artist Title", "Total Revenue",
                "Full Album Revenue", "Single Track Revenue", "Album Payment",
                "Track Payment" };
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();

        HSSFRow headerRow = sheet.createRow(0);
        for (int i = 0; i < columns.length; i++) {
            HSSFCell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
        }

        for (int i = 0; i < data.size(); i++) {
            HSSFRow row = sheet.createRow(i + 1);
            AlbumRevenueResultItem item = data.get(i);

            row.createCell(0).setCellValue(item.getAlbumTitle());

            row.createCell(1).setCellValue(item.getArtistTitle());

            row.createCell(2).setCellValue(item.getTotalRevenue());

            row.createCell(3).setCellValue(item.getAlbumRevenue());

            row.createCell(4).setCellValue(item.getTrackRevenue());

            row.createCell(5).setCellValue(item.getAlbumPaymentCount());

            row.createCell(6).setCellValue(item.getTrackPaymentCount());

        }

        return workbook;
    }

    public Object queryClientAndWebRevenue(AlbumRevenueQueryItem item) {
        return albumRevenueDao.queryClientAndWebRevenue(item);
    }

    public PageView albumAndMusicWithCouponStat(Map parmMap) {
        return albumRevenueDao.albumAndMusicWithCouponStat(parmMap);
    }

    public List getArtistList() {
        return artistRepository.findAll();
    }

    public List getExportAlbumAndMusicWithCouponStatList(
            Map<String, Object> paramMap) {
        return albumRevenueDao.exportAlbumAndMusicWithCouponStat(paramMap);
    }

    public HSSFWorkbook exportAlbumAndMusicWithCouponStat(
            Map<String, Object> paramMap) {
        List data = getExportAlbumAndMusicWithCouponStatList(paramMap);
        String columns[] = { "歌曲名称", "专辑名称", "Revenue", "Album Payment",
                "Track Payment", "Coupon" };
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();

        HSSFRow headerRow = sheet.createRow(0);
        for (int i = 0; i < columns.length; i++) {
            HSSFCell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
        }

        for (int i = 0; i < data.size(); i++) {
            HSSFRow row = sheet.createRow(i + 1);
            Map item = (Map) data.get(i);

            row.createCell(0).setCellValue((String) item.get("musicName"));
            row.createCell(1).setCellValue((String) item.get("albumName"));
            row.createCell(2).setCellValue(
                    ((java.math.BigDecimal) item.get("revenue")).doubleValue());
            row.createCell(3).setCellValue((Integer) item.get("albumPayCount"));
            row.createCell(4).setCellValue((Integer) item.get("trackPayCount"));
            row.createCell(5).setCellValue(
                    ((java.math.BigDecimal) item.get("couponAmount"))
                            .doubleValue());
        }
        return workbook;

    }

    public PageView queryPagableAlbumPageView(Map<String, Object> parmMap) {
        return albumRevenueDao.queryPagableAlbumPageView(parmMap);
    }

    public Object queryAlbumAndTrackRevenue(AlbumPageViewQueryItem item) {
        return albumRevenueDao.queryAlbumAndTrackRevenue(item);
    }

    public HSSFWorkbook queryExportAlbumPageView(Map<String, Object> paramMap) {
        return createAlbumPageViewWorkbook(albumRevenueDao
                .queryExportAlbumPageView(paramMap));
    }

    private HSSFWorkbook createAlbumPageViewWorkbook(
            List<AlbumPageViewResultItem> data) {
        String columns[] = { "Album Title", "Artist Title", "Total Revenue",
                "Page View", "Revenue/Page View", "Full Album Revenue",
                "Single Track Revenue", "Album Payment", "Track Payment" };
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();

        HSSFRow headerRow = sheet.createRow(0);
        for (int i = 0; i < columns.length; i++) {
            HSSFCell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
        }

        for (int i = 0; i < data.size(); i++) {
            HSSFRow row = sheet.createRow(i + 1);
            AlbumPageViewResultItem item = data.get(i);

            row.createCell(0).setCellValue(item.getAlbumTitle());

            row.createCell(1).setCellValue(item.getArtistTitle());

            row.createCell(2).setCellValue(item.getTotalRevenue());

            row.createCell(3).setCellValue(item.getPageView());

            row.createCell(4).setCellValue(item.getRvRate());

            row.createCell(5).setCellValue(item.getAlbumRevenue());

            row.createCell(6).setCellValue(item.getTrackRevenue());

            row.createCell(7).setCellValue(item.getAlbumPaymentCount());

            row.createCell(8).setCellValue(item.getTrackPaymentCount());

        }

        return workbook;
    }
}
