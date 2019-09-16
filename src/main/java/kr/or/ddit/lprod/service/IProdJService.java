package kr.or.ddit.lprod.service;

import java.util.List;

import kr.or.ddit.lprod.model.ProdJVo;

public interface IProdJService {
	List<ProdJVo> getProdJList(String lprod_gu);
}
