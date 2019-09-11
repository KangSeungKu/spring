package kr.or.ddit.user.service;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.common.model.Page;
import kr.or.ddit.user.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:kr/or/ddit/config/spring/context-root.xml",
		"classpath:kr/or/ddit/config/spring/context-datasource.xml",
		"classpath:kr/or/ddit/config/spring/context-transaction.xml"
		})
public class UserServiceTest {
	@Resource(name = "userService")
	private IUserService userService;
	
	private String userId = "brownTest";
	
	@Before
	public void setup() {
		userService.deleteUser(userId);
	}
	
	/**
	* Method : getUserListTest
	* 작성자 : PC-16
	* 변경이력 :
	* Method 설명 : getUserList 테스트
	*/
	@Test
	public void getUserListTest() {
		/***Given***/
		
		/***When***/
		List<User> userList = userService.getUserList();

		/***Then***/
		assertTrue(userList.size() > 100);
	}
	
	/**
	* Method : getUserTest
	* 작성자 : PC-16
	* 변경이력 :
	* Method 설명 : 사용자 정보 조회 테스트
	*/
	@Test
	public void getUserTest() {
		/***Given***/
		String userId = "brown";

		/***When***/
		User userVo = userService.getUser(userId);
		
		/***Then***/
		assertEquals("brown", userVo.getUserId());
		//assertEquals("c6347b73d5b1f7c77f8be828ee3e871c819578f23779c7d5e082ae2b36a44", userVo.getPass());
	}
	
	@Test
	public void getUserListOnlyHalf() {
		/***Given***/

		/***When***/
		List<User> userList = userService.getUserListOnlyHalf();
		
		/***Then***/
		assertEquals(50, userList.size());
	}
	
	/**
	* Method : getUserPagingList
	* 작성자 : PC-16
	* 변경이력 :
	* @param sqlSession
	* @param page
	* @return
	* Method 설명 : 사용자 페이징 리스트 조회
	*/
	@Test
	public void getUserPagingList() {
		/***Given***/
		Page page = new Page();
		page.setPage(3);
		page.setPagesize(10);

		/***When***/
		Map<String, Object> resultMap = userService.getUserPagingList(page);
		List<User> userList = (List<User>) resultMap.get("userList");
		int paginationSize = (Integer) resultMap.get("paginationSize");

		/***Then***/
		assertEquals(10, userList.size());
		assertEquals("xuserid22", userList.get(0).getUserId());
		assertEquals(11, paginationSize);
	}
	
	@Test
	public void ceilingTest() {
		/***Given***/
		int totalCnt = 105;
		int pagesize = 10;

		/***When***/
		double paginationSize = Math.ceil((double)totalCnt / pagesize);
		
		/***Then***/
		assertEquals(11, (int)paginationSize);
	}
	
	@Test
	public void insertUser() throws ParseException{
		/***Given***/
		User user = new User();
		user.setUserId(userId);
		user.setUserNm("브라운테스트");
		user.setPass("brownTest1234");
		user.setReg_dt(new SimpleDateFormat("yyyy-MM-dd").parse("2019-08-08"));
		user.setAlias("곰테스트");
		user.setAddr1("대전광역시 중구 중앙로 76");
		user.setAddr2("영민빌딩 2층 DDIT");
		user.setZipcode("34940");

		/***When***/
		int insertCnt = userService.insertUser(user);

		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	@Test
	public void updateUserTest() throws ParseException {
		/***Given***/
		User user = new User();
		//'brownTest', '브라운테스트', 'brownTest1234', '2019-08-08', 
		//'곰테스트', '대전광역시 중구 중앙로 76', '영민빌딩 2층 DDIT', '34940'
		user.setUserId(userId);
		user.setUserNm("브라운수정");
		user.setPass("brownUpdt1234");
		user.setReg_dt(new SimpleDateFormat("yyyy-MM-dd").parse("2019-08-27"));
		user.setAlias("곰수정");
		user.setAddr1("대전광역시 중구 수정로 76");
		user.setAddr2("영민빌딩 2층 DDIT 수정");
		user.setZipcode("34940");
		user.setFilename("update.png");
		user.setRealfilename("update_0123.png");

		/***When***/
		userService.insertUser(user);
		int updateCnt = userService.updateUser(user);

		/***Then***/
		assertEquals(1, updateCnt);
	}
}
