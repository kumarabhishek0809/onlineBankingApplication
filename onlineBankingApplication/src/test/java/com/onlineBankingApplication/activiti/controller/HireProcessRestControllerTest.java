package com.onlineBankingApplication.activiti.controller;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.BaseMatcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

//@RunWith(SpringRunner.class)
//@WebMvcTest(HireProcessRestController.class)
public class HireProcessRestControllerTest {

//	@Autowired
//	private HireProcessRestController hireProcessRestController;
	
	@Autowired
	private MockMvc mvc;
	
	@Test
	public void canGetUserList() throws Exception {
//		ResultActions result = mvc.perform(get("/user/all").param("",""));
//		result.andExpect(status().isCreated())
//		.andExpect((ResultMatcher) jsonPath("a","b").value(Matchers.anyList()));

				
	}
}
