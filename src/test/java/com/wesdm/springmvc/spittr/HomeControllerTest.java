package com.wesdm.springmvc.spittr;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.wesdm.springmvc.Config;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={Config.class})
@ActiveProfiles("dev")
public class HomeControllerTest {
	@Test
	public void testHomePage() throws Exception {
		HomeController controller = new HomeController();
		MockMvc mockMvc = standaloneSetup(controller).build();
		mockMvc.perform(get("/homepage")).andExpect(view().name("home"));
	}

	
}
