package kr.or.ddit.user.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.common.model.Page;
import kr.or.ddit.user.model.User;
import kr.or.ddit.user.model.UserValidate;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.util.FileUtil;
import kr.or.ddit.util.model.FileInfo;

@RequestMapping("user/")
@Controller
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Resource(name = "userService")
	private IUserService userService;
	
	/**
	* Method : userView
	* 작성자 : PC-16
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 상세화면 요청
	*/
	// 사용자가 localhost:8081/spring/user/view.do
	@RequestMapping("view.do")
	public String userView() {
		logger.debug("userController.userView()");
		
		return "user/view";
		
		// prefix + viewName + suffix
		// WEB-INF/views/user/view.jsp
	}
	
	/**
	* Method : userList
	* 작성자 : PC-16
	* 변경이력 :
	* @param model
	* @return
	* Method 설명 :	사용자 전체 리스트 조회
	*/
	@RequestMapping(path = "userList", method = RequestMethod.GET)
	public String userList(Model model) {
		// 사용자 정보 전체 조회
		model.addAttribute("userList", userService.getUserList());
		
		return "user/userList";
	}
	
	@RequestMapping(path = "userListOnlyHalf", method = RequestMethod.GET)
	public String userListOnlyHalf(Model model) {
		model.addAttribute("userListOnlyHalf", userService.getUserListOnlyHalf());
		
		return "user/userListOnlyHalf";
	}
	
	@RequestMapping(path = "userPagingList", method = RequestMethod.GET)
	public String userPagingList(Model model, Page p) {
		logger.debug("userPagingList - Page : {}", p);
		model.addAttribute("pageVo", p);
		
		Map<String, Object> resultMap = userService.getUserPagingList(p); 
//		List<User> userList = (List<User>) resultMap.get("userList");
//		int paginationSize = (Integer) resultMap.get("paginationSize");
//		model.addAttribute("userList", userList);
//		model.addAttribute("paginationSize", paginationSize);
		
		model.addAllAttributes(resultMap);
		
		return "user/userPagingList";
	}
	
	@RequestMapping(path = "user", method = RequestMethod.GET)
	public String getUser(Model model, String userId) {
		model.addAttribute("user", userService.getUser(userId));
		return "user/user";
	}
	
	@RequestMapping(path = "userPicture")
	public void getPicture(Model model, String userId, HttpServletResponse response) throws IOException {
		User user = userService.getUser(userId);
		
		ServletOutputStream sos = response.getOutputStream();
		File picture = new File(user.getRealfilename());
		FileInputStream fis = new FileInputStream(picture);
		byte[] buff = new byte[512];
		int len = 0;
		while( (len = fis.read(buff, 0, 512)) != -1 ) {
			sos.write(buff, 0, len);
		}
		fis.close();
	}
	
	/**
	* Method : userFormView
	* 작성자 : PC-16
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 등록 화면 요청
	*/
	@RequestMapping(path = "userForm", method = RequestMethod.GET)
	public String userFormView() {
		return "user/userForm";
	}
	
	// 사용자 등록 요청
	@RequestMapping(path = "userForm", method = RequestMethod.POST)
	public String userForm(User user, BindingResult result, @RequestPart("picture") MultipartFile picture) {
		new UserValidate().validate(user, result);
		if(result.hasErrors()) return "user/userForm";
		else {
			FileInfo fileInfo = FileUtil.getFileInfo(picture.getOriginalFilename());
			
			if(picture.getSize() > 0) {		// 첨부된 파일이 있을 경우만 업로드처리
				try {
					picture.transferTo(fileInfo.getFile());				// picture.write(path);
					user.setFilename(fileInfo.getOriginalFileName());	// originalFilename
					user.setRealfilename(fileInfo.getFile().getPath());	
					
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
			
			int insertCnt = userService.insertUser(user);
			
			if(insertCnt == 1)
				return "redirect:/user/user?userId=" + user.getUserId();
				// response.sendRedirect(request.getContextPath() + "/user?userId=" + userId);
			else
				return "user/userForm";
		}
	}
	
	@RequestMapping(path = "userModify", method = RequestMethod.GET)
	public String userModifyView(Model model, String userId) {
		logger.debug("userId : {}", userId);
		
		model.addAttribute("muser", userService.getUser(userId));
		
		return "user/userModify";
	}
	
	@RequestMapping(path = "userModify", method = RequestMethod.POST)
	public String userModify(User user, BindingResult result, @RequestPart("picture") MultipartFile picture) {
		logger.debug("user : {}", user);
		new UserValidate().validate(user, result);
		if(result.hasErrors()) return "user/userModify";
		else {
			FileInfo fileInfo = FileUtil.getFileInfo(picture.getOriginalFilename());
			
			if(picture.getSize() > 0) {		// 첨부된 파일이 있을 경우만 업로드처리
				try {
					picture.transferTo(fileInfo.getFile());				// picture.write(path);
					user.setFilename(fileInfo.getOriginalFileName());	// originalFilename
					user.setRealfilename(fileInfo.getFile().getPath());	
					
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}else {											// 사진을 넣지 않았을 때
				user.setFilename(userService.getUser(user.getUserId()).getFilename());	// originalFilename
				user.setRealfilename(userService.getUser(user.getUserId()).getRealfilename());
			}
			
			int updateCnt = userService.updateUser(user);
			
			if(updateCnt == 1)
				return "redirect:/user/user?userId=" + user.getUserId();
			else
				return "user/userModify";
		}
	}
}
