package com.xyc.proj.service.manage;

import com.xyc.proj.entity.manage.Banner;

public interface BannerService {
	void saveBanner(Banner banner);
	
	Banner findBannerById(Long id);
	
	void updateBanner(String bannerName, Long id);
}
