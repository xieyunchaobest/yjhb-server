package com.sonymobile.sonyselect.music.repository.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sonymobile.sonyselect.music.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { Application.class })
public class AlbumRepositoryTestCase extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	AlbumRepository albumRepository;
	
	@Test
	public void testAllAlbums() {
	}
}
