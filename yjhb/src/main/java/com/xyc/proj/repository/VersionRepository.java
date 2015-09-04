package com.xyc.proj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xyc.proj.entity.Version;



public interface VersionRepository extends JpaRepository<Version, Long> {

	Version findByPfType(String pfType);
}
