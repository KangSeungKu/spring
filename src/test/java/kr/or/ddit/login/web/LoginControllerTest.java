package kr.or.ddit.login.web;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.config.test.WebTestConfig;
import kr.or.ddit.user.model.User;
import kr.or.ddit.user.service.IUserService;

public class LoginControllerTest extends WebTestConfig{

	/**
	* Method : loginViewTest
	* 작성자 : PC-16
	* 변경이력 :
	* Method 설명 : 로그인 화면 요청 테스트
	 * @throws Exception 
	*/
	@Test
	public void loginViewTest() throws Exception {
		/***Given***/

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/login")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();

		/***Then***/
		assertEquals("login/login", mav.getViewName());
	}

	@Test
	public void loginProcessTest() throws Exception {
		MockHttpSession session = new MockHttpSession();
		
		mockMvc.perform(post("/login")
				.param("userId", "brown")
				.param("pass", "brown1234")
				.session(session))
					.andExpect(status().isOk())
					.andExpect(view().name("main"));
					//.andExpect(request().sessionAttribute("S_USERVO", user)); -> user객체 셋팅이 번거로움
		
		User user = (User)session.getAttribute("S_USERVO");
		assertNotNull(user);
	}
}
