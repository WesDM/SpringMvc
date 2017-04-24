package com.wesdm.springmvc.spittr;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.*;
import org.springframework.test.web.servlet.MockMvc;

import com.wesdm.springmvc.spittr.db.SpitterRepository;

public class SpitterControllerTest {

	@Test
	public void shouldShowRegistration() throws Exception {
		SpitterRepository mockRepository = mock(SpitterRepository.class);
		SpitterController controller = new SpitterController(mockRepository);
		MockMvc mockMvc = standaloneSetup(controller).build();
		mockMvc.perform(get("/spitter/register")).andExpect(view().name("registerForm"));
	}

	@Test
	public void shouldProcessRegistration() throws Exception {
		SpitterRepository mockRepository = mock(SpitterRepository.class);
		Spitter unsaved = new Spitter(null, "jbauer", "24hours", "Jack", "Bauer", "email@test.com");
		//Spitter saved = new Spitter(24L, "jbauer", "24hours", "Jack", "Bauer", "email@test.com");
		//when(mockRepository.save(unsaved)).thenReturn(saved);
		doAnswer(new SpitterAnswer()).when(mockRepository).save(unsaved);
		SpitterController controller = new SpitterController(mockRepository);
		MockMvc mockMvc = standaloneSetup(controller).build();		//test in isolation
		mockMvc.perform(post("/spitter/register").param("firstName", "Jack").param("lastName", "Bauer")	//send post with params
				.param("username", "jbauer").param("password", "24hours").param("email", "email@test.com")).andExpect(redirectedUrl("/spitter/jbauer"));
		verify(mockRepository, atLeastOnce()).save(unsaved);
	}
	
	
}

class SpitterAnswer implements Answer<Spitter> {
	@Override
	public Spitter answer(InvocationOnMock invocation) throws Throwable {
		return invocation.getArgumentAt(0, Spitter.class);//new Spitter(24L, "jbauer", "24hours", "Jack", "Bauer", null);
	}		
}

