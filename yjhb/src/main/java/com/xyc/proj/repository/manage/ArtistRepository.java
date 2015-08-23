/**
 * All rights, including trade secret rights, reserved.
 */
package com.xyc.proj.repository.manage;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xyc.proj.entity.manage.Artist;

/**
 * <p><description></description></p>
 * @author 28850153
 * @version 1.0
 * @date Dec 16, 2014
 * @since JDK 1.6
 */
public interface ArtistRepository extends JpaRepository<Artist, Long> {
	
}
