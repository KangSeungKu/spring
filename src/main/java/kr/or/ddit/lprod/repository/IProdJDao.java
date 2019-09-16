package kr.or.ddit.lprod.repository;

import java.util.List;

import kr.or.ddit.lprod.model.ProdJVo;

public interface IProdJDao {
	List<ProdJVo> getProdJList(String lprod_gu);
}
