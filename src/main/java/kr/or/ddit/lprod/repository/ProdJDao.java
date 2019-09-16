package kr.or.ddit.lprod.repository;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.lprod.model.ProdJVo;

@Repository
public class ProdJDao implements IProdJDao {
	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSession;

	@Override
	public List<ProdJVo> getProdJList(String lprod_gu) {
		return sqlSession.selectList("prodj.getprodJList", lprod_gu);
	}

}
