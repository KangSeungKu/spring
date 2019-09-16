package kr.or.ddit.lprod.repository;

import java.util.List;

import kr.or.ddit.common.model.Page;
import kr.or.ddit.lprod.model.LprodVo;

public interface ILprodDao {
	List<LprodVo> getLprodList();
	
	/**
	* Method : getLprodPagingList
	* 작성자 : PC-16
	* 변경이력 :
	* @param sqlSession
	* @param page
	* @return
	* Method 설명 : 제품그룹 페이징 리스트 조회
	*/
	List<LprodVo> getLprodPagingList(Page page);
	
	/**
	* Method : getLprodTotalCnt
	* 작성자 : PC-16
	* 변경이력 :
	* @param sqlSession
	* @return
	* Method 설명 : 전체 제품그룹 건수 조회
	*/
	int getLprodTotalCnt();
}
