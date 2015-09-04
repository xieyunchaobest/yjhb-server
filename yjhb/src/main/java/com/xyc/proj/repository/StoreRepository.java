package com.xyc.proj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xyc.proj.entity.Store;



public interface StoreRepository extends JpaRepository<Store, Long> {

	List findBySts(String sts);
}
