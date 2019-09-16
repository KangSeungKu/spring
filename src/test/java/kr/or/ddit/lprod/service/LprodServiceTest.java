package kr.or.ddit.lprod.service;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.common.model.Page;
import kr.or.ddit.config.test.RootTestConfig;
import kr.or.ddit.lprod.model.LprodVo;

public class LprodServiceTest extends RootTestConfig{

	@Resource(name = "lprodService")
	private ILprodService lprodService;
	
	@Test
	public void getLprodList() {
		/***Given***/
		
		/***When***/
		List<LprodVo> lprodList = lprodService.getLprodList();
		
		/***Then***/
		assertEquals(10, lprodList.size());
	}
	
	@Test
	public void getLprodPagingList() {
		/***Given***/
		Page page = new Page();
		page.setPage(2);
		page.setPagesize(5);

		/***When***/
		Map<String, Object> resultMap = lprodService.getLprodPagingList(page);
		List<LprodVo> lprodList = (List<LprodVo>) resultMap.get("lprodList");
		int paginationSize = (Integer) resultMap.get("paginationSize");
		
		/***Then***/
		assertEquals(5, lprodList.size());
		assertEquals(2, paginationSize);
	}
	
	@Test
	public void ceilingTest() {
		/***Given***/
		int totalCnt = 11;
		int pagesize = 5;

		/***When***/
		double paginationSize = Math.ceil((double)totalCnt / pagesize);
		
		/***Then***/
		assertEquals(3, (int)paginationSize);
	}
}
