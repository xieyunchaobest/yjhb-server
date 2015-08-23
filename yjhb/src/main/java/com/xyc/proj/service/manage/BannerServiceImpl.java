package com.xyc.proj.service.manage;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xyc.proj.entity.manage.Banner;
import com.xyc.proj.repository.manage.BannerRepository;

@Service
public class BannerServiceImpl implements BannerService {
	
	@Resource
	private BannerRepository bannerRepository;
	
	@Override
	public void saveBanner(Banner banner) {
		bannerRepository.save(banner);
	}

	@Override
	public Banner findBannerById(Long id) {
		return bannerRepository.getOne(id);
	}

	@Override
	public void updateBanner(String bannerName, Long id) {
		bannerRepository.update(bannerName, id);
	}

}
