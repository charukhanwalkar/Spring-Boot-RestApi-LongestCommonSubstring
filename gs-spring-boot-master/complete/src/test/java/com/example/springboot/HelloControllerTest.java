package com.example.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class HelloControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void getHello() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/LCS/").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("Greetings from Charu!")));
	}

	@Test
	//check if 202 is return for correct input
	public void checkOkay() throws Exception {

		String inputJson = "[\"communication\", \"comcast\",\"combat\",\"decommission\"]";
		System.out.println(inputJson);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/LCS/").contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		System.out.println(status);
		assertEquals(202, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "com");
	}
	@Test
	//check if  is return for correct input
	public void checkDuplicate() throws Exception {

		String inputJson = "[\"hardwork\", \"diehard\",\"hardly\",\"hardly\"]";
		System.out.println(inputJson);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/LCS/").contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		System.out.println(status);
		assertEquals(422, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "The set of Strings is not unique");
	}
	@Test
	//check if  is return for correct input
	public void checkEmpty() throws Exception {

		String inputJson = "[]";
		System.out.println(inputJson);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/LCS/").contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		System.out.println(status);
		assertEquals(422, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "The set of Strings is empty");
	}

}
