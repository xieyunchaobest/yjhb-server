package com.xyc.proj.controller.statistic;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.xyc.proj.entity.statistic.AlbumCategory;
import com.xyc.proj.entity.statistic.AlbumPageViewQueryItem;
import com.xyc.proj.entity.statistic.AlbumRevenueQueryItem;
import com.xyc.proj.global.Constants;
import com.xyc.proj.service.statistic.StatisticService;
import com.xyc.proj.utility.DateUtil;
import com.xyc.proj.utility.PageView;
import com.xyc.proj.utility.StringUtil;

@Controller
@SessionAttributes("user")
public class StatisticController {
    @Autowired
    StatisticService service;

    @RequestMapping("/queryAlbumRevenue")
    public String queryAlbumRevenue(
            @RequestParam(value = "albumName", required = false) String albumName,
            @RequestParam(value = "singerName", required = false) String singerName,
            @RequestParam(value = "albumCategory", required = false) String categoryId,
            @RequestParam(value = "startTime", required = false) String startTime,
            @RequestParam(value = "endTime", required = false) String endTime,
            @RequestParam(value = "currentPageNum", required = false, defaultValue = "1") Integer currentPageNum,
            @RequestParam(value = "orderByStr", required = false, defaultValue = "totalRevenue,desc") String orderBy,
            Model model) {

        AlbumRevenueQueryItem item = new AlbumRevenueQueryItem();
        item.setAlbumName(albumName);
        item.setSingerName(singerName);
        categoryId = "0".equals(categoryId) ? "" : categoryId;
        item.setAlbumCategory(categoryId);
 

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("param", item);
        map.put(Constants.CURRENT_PAGENUM, currentPageNum);
        map.put(Constants.ORDERBY, StringUtil.formatSortBy(orderBy));

        List<AlbumCategory> albumCategory = service.queryAlbumCategory();
        model.addAttribute("categories", albumCategory);

        Object result = service.queryClientAndWebRevenue(item);
        Map<String, Object> clientWebRevenue = (Map<String, Object>) result;

        if (clientWebRevenue != null) {
            Object clientRevenue = clientWebRevenue.get("clientRevenue");
            model.addAttribute("clientRevenue", clientRevenue == null ? "0"
                    : clientRevenue.toString());
            Object webRevenue = clientWebRevenue.get("webRevenue");
            model.addAttribute("webRevenue", webRevenue == null ? "0"
                    : webRevenue.toString());
            Object summary = clientWebRevenue.get("totalRevenue");
            model.addAttribute("summary",
                    summary == null ? "0" : summary.toString());

        }

        model.addAttribute("albumName", albumName);
        model.addAttribute("singerName", singerName);
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("startTime", startTime);
        model.addAttribute("endTime", endTime);
        model.addAttribute("orderBy", orderBy);
        PageView pageView = service.queryPagableAlbumRevenue(map);
        model.addAttribute("pageView", pageView);

        return "/AlbumRevenue";
    }

    @RequestMapping("/AlbumRevenue")
    public String getComponentPage(Model model) {
        List<AlbumCategory> albumCategory = service.queryAlbumCategory();
        model.addAttribute("categoryId", "0");
        model.addAttribute("categories", albumCategory);

        PageView page = new PageView(0);

        model.addAttribute("pageView", page);

        model.addAttribute("clientRevenue", "");
        model.addAttribute("webRevenue", "");
        model.addAttribute("summary", "");
        return "AlbumRevenue";
    }

    @RequestMapping("/exportAlbumRevenue")
    public void handleExport(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "albumName", required = false) String albumName,
            @RequestParam(value = "singerName", required = false) String singerName,
            @RequestParam(value = "albumCategory", required = false) String categoryId,
            @RequestParam(value = "startTime", required = false) String startTime,
            @RequestParam(value = "endTime", required = false) String endTime,
            @RequestParam(value = "orderByStr", required = false, defaultValue = "totalRevenue,desc") String orderBy) {

        AlbumRevenueQueryItem item = new AlbumRevenueQueryItem();
        item.setAlbumName(albumName);
        item.setSingerName(singerName);
        categoryId = "0".equals(categoryId) ? "" : categoryId;
        item.setAlbumCategory(categoryId);
 
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("param", item);
        map.put(Constants.ORDERBY, StringUtil.formatSortBy(orderBy));

        response.setHeader("conent-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename="
                + "albumRevenueExport.xls");
        HSSFWorkbook workbook = service.queryExportAlbumRevenue(map);
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping("/stat/albumAndMusicWithCouponStat")
    public String albumAndMusicWithCouponStat(
            @RequestParam(value = "artist", required = false, defaultValue = "") String artist,
            @RequestParam(value = "startTime", required = false) String startTime,
            @RequestParam(value = "endTime", required = false) String endTime,
            @RequestParam(value = "platForm", required = false) String platForm, 
            @RequestParam(value = "currentPageNum", required = false, defaultValue = "1") Integer currentPageNum,
            @RequestParam(value = "orderByStr", required = false, defaultValue = "revenue,desc") String orderBy,
            Model model) {
        Map<String, Object> parmMap = new HashMap<String, Object>();
        parmMap.put("artist", artist);
        if(StringUtil.isBlank(startTime) && StringUtil.isBlank(endTime)) {
        	startTime=endTime=DateUtil.getYesterday();
        }
        parmMap.put("startTime", startTime);
        parmMap.put("endTime", endTime);
        parmMap.put(Constants.CURRENT_PAGENUM, currentPageNum);
        parmMap.put(Constants.ORDERBY, StringUtil.formatSortBy(orderBy));
        parmMap.put("platForm", platForm);
        PageView pv = service.albumAndMusicWithCouponStat(parmMap);
        List artistList = service.getArtistList();
        model.addAttribute("pageView", pv);
        model.addAttribute("artist", artist);
        model.addAttribute("startTime", startTime);
        model.addAttribute("endTime", endTime);
        model.addAttribute("artistList", artistList);
        model.addAttribute("platForm", platForm);
        return "stat/albumAndMusicWithCouponStat";
    }

    @RequestMapping("/stat/exportAlbumAndMusicWithCouponStat")
    public void exportAlbumAndMusicWithCouponStat(
            HttpServletResponse response,
            @RequestParam(value = "artist", required = false, defaultValue = "") String artist,
            @RequestParam(value = "startTime", required = false) String startTime,
            @RequestParam(value = "endTime", required = false) String endTime,
            @RequestParam(value = "currentPageNum", required = false, defaultValue = "1") Integer currentPageNum,
            @RequestParam(value = "orderByStr", required = false, defaultValue = "revenue,desc") String orderBy,
            Model model) {
        Map<String, Object> parmMap = new HashMap<String, Object>();
        parmMap.put("artist", artist);
        parmMap.put("startTime", startTime);
        parmMap.put("endTime", endTime);
        parmMap.put(Constants.CURRENT_PAGENUM, currentPageNum);
        parmMap.put(Constants.ORDERBY, StringUtil.formatSortBy(orderBy));
        HSSFWorkbook workbook = service
                .exportAlbumAndMusicWithCouponStat(parmMap);
        response.setHeader("conent-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename="
                + "albumAndMusicWithCouponRevenue.xls");
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/AlbumPageView")
    public String queryWebClientRevenue(
            @RequestParam(value = "source", required = true) String source,
            @RequestParam(value = "startTime", required = true) String startTime,
            @RequestParam(value = "endTime", required = true) String endTime,
            @RequestParam(value = "albumName", required = false) String albumName,
            @RequestParam(value = "singerName", required = false) String singerName,
            @RequestParam(value = "albumCategory", required = true) String categoryId,
            @RequestParam(value = "currentPageNum", required = false, defaultValue = "1") Integer currentPageNum,
            @RequestParam(value = "orderByStr", required = false, defaultValue = "totalRevenue,desc") String orderBy,
            Model model) {

        List<AlbumCategory> albumCategory = service.queryAlbumCategory();
        model.addAttribute("categories", albumCategory);

        AlbumPageViewQueryItem item = new AlbumPageViewQueryItem();
        item.setSource(source);
        item.setStartTime(startTime);
        item.setEndTime(endTime);
        item.setAlbumName(albumName);
        item.setSingerName(singerName);
        categoryId = "0".equals(categoryId) ? "" : categoryId;
        item.setAlbumCategory(categoryId);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("param", item);
        map.put(Constants.CURRENT_PAGENUM, currentPageNum);
        map.put(Constants.ORDERBY, StringUtil.formatSortBy(orderBy));

        PageView page = service.queryPagableAlbumPageView(map);
        model.addAttribute("pageView", page);

        Object result = service.queryAlbumAndTrackRevenue(item);
        Map<String, Object> albumTrackRevenue = (Map<String, Object>) result;
        if (albumTrackRevenue != null) {
            Object albumRevenue = albumTrackRevenue.get("albumRevenue");
            model.addAttribute("albumRevenue", albumRevenue == null ? "0"
                    : albumRevenue.toString());
            Object trackRevenue = albumTrackRevenue.get("trackRevenue");
            model.addAttribute("trackRevenue", trackRevenue == null ? "0"
                    : trackRevenue.toString());
            Object summary = albumTrackRevenue.get("totalRevenue");
            model.addAttribute("summary",
                    summary == null ? "0" : summary.toString());

        }

        model.addAttribute("source", source);
        model.addAttribute("startTime", startTime);
        model.addAttribute("endTime", endTime);
        model.addAttribute("endTime", endTime);
        model.addAttribute("albumName", albumName);
        model.addAttribute("singerName", singerName);
        model.addAttribute("categoryId", categoryId);
        return "AlbumPageView";
    }

    @RequestMapping("/exportAlbumPageView")
    public void handleExportAlbumRevenue(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "source", required = true) String source,
            @RequestParam(value = "startTime", required = true) String startTime,
            @RequestParam(value = "endTime", required = true) String endTime,
            @RequestParam(value = "albumName", required = false) String albumName,
            @RequestParam(value = "singerName", required = false) String singerName,
            @RequestParam(value = "albumCategory", required = true) String categoryId,
            @RequestParam(value = "orderByStr", required = false, defaultValue = "totalRevenue,desc") String orderBy) {

        AlbumPageViewQueryItem item = new AlbumPageViewQueryItem();
        item.setSource(source);
        item.setStartTime(startTime);
        item.setEndTime(endTime);
        item.setAlbumName(albumName);
        item.setSingerName(singerName);
        categoryId = "0".equals(categoryId) ? "" : categoryId;
        item.setAlbumCategory(categoryId);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("param", item);
        map.put(Constants.ORDERBY, StringUtil.formatSortBy(orderBy));

        response.setHeader("conent-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename="
                + "albumPageViewExport.xls");
        HSSFWorkbook workbook = service.queryExportAlbumPageView(map);
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
