package kr.or.ddit.lprod.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.common.model.Page;
import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.lprod.service.ILprodService;
import kr.or.ddit.lprod.service.IProdJService;

@RequestMapping("lprod")
@Controller
public class LprodController {
	private static final Logger logger = LoggerFactory.getLogger(LprodController.class);
	
	@Resource(name = "lprodService")
	private ILprodService lprodService;
	
//	@Resource(name = "prodJService")
//	private IProdJService prodJService;
	
	@RequestMapping(path = "lprodList", method = RequestMethod.GET)
	public String lprodList(Model model) {
		model.addAttribute("lprodList", lprodService.getLprodList());
		
		return "lprod/lprodList";
	}
	
	@RequestMapping(path = "lprodPagingList", method = RequestMethod.GET)
	public String lprodPagingList(Model model, Page p) {
		model.addAttribute("pageVo", p);
		
		Map<String, Object> resultMap = lprodService.getLprodPagingList(p); 
		model.addAllAttributes(resultMap);
		
		return "lprod/lprodPagingList";
	}
}
