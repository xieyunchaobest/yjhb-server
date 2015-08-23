/**
 * All rights, including trade secret rights, reserved.
 */
package com.sonymobile.sonyselect.music.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.security.Principal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.sonymobile.sonyselect.music.Application;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
/**
 * controller unit test
 * @author xp016429
 *
 */
public class ControllerTestCase {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	private Principal principal;

	@Before
	public void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
		principal = new Principal() {
			public String getName() {
				return "szz";
			}
		};
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void workerCannot() throws Exception {
		mvc.perform(get("/album_categoryies").principal(new Principal() {
			public String getName() {
				return "1";
			}
		})).andExpect(status().is2xxSuccessful());
	}

	 

}
