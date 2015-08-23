package com.xyc.proj.repository.system;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xyc.proj.entity.SysUser;



public interface SysUserRepository extends JpaRepository<SysUser, Long> {
	SysUser findByUserNameAndPassword(String name,String pwd);

}
