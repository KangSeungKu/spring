package kr.or.ddit.lprod.repository;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.config.test.RootTestConfig;
import kr.or.ddit.lprod.model.ProdJVo;

public class ProdDaoTest extends RootTestConfig{
	private static final Logger logger = LoggerFactory.getLogger(ProdDaoTest.class);

	@Resource(name = "prodJDao")
	private IProdJDao prodJDao;
	
	@Test
	public void getProdJListTest() {
		/***Given***/
		String lprod_gu = "P202";

		/***When***/
		List<ProdJVo> list = prodJDao.getProdJList(lprod_gu);

		/***Then***/
		assertEquals(21, list.size());
	}

}
