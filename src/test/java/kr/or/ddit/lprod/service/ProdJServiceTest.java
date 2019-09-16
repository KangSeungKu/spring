package kr.or.ddit.lprod.service;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.config.test.RootTestConfig;
import kr.or.ddit.lprod.model.ProdJVo;

public class ProdJServiceTest extends RootTestConfig{

	@Resource(name = "prodJService")
	private IProdJService prodJService;
	
	@Test
	public void getProdJListTest() {
		/***Given***/
		String lprod_gu = "P202";

		/***When***/
		List<ProdJVo> list = prodJService.getProdJList(lprod_gu);

		/***Then***/
		assertEquals(21, list.size());
	}

}
