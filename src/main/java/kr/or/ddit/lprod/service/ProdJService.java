package kr.or.ddit.lprod.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.lprod.model.ProdJVo;
import kr.or.ddit.lprod.repository.IProdJDao;

@Service
public class ProdJService implements IProdJService {
	
	@Resource(name = "prodJDao")
	private IProdJDao prodJDao;
	
	public ProdJService() {   }
	
	public ProdJService(IProdJDao prodJDao) {
		this.prodJDao = prodJDao;
	}
	
	@Override
	public List<ProdJVo> getProdJList(String lprod_gu) {
		return prodJDao.getProdJList(lprod_gu);
	}

}
