package com.xyc.proj.repository.manage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.xyc.proj.entity.manage.Banner;



public interface BannerRepository extends JpaRepository<Banner, Long> {
	
	@Modifying
	@Query("update Banner m set m.name=?1 where m.id=?2")
	public void update(String bannerName, Long id);

}
