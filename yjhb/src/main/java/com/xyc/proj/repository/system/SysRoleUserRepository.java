package com.xyc.proj.repository.system;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xyc.proj.entity.manage.SysRoleUser;



public interface SysRoleUserRepository extends JpaRepository<SysRoleUser, Long> {
	
	SysRoleUser findByUserId(Long userId);
}
