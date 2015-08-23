package com.xyc.proj.repository.system;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xyc.proj.entity.manage.SysRole;



public interface SysRoleRepository extends JpaRepository<SysRole, Long> {
	
	@Query("select ru.roleId from SysRoleUser ru,SysUser u where u.id=ru.userId and u.id=:userId")
	public Long getSysRoleByUserId(@Param("userId")  long userId);
}
