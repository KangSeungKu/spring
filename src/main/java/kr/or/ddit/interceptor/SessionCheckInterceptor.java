package kr.or.ddit.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.or.ddit.user.model.User;

public class SessionCheckInterceptor extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// session이 없으면 login화면으로 이동
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("S_USERVO");
		
		if(user == null) {			// session이 없으면 login화면으로 이동
			response.sendRedirect(request.getContextPath() + "/login");
			return false;
		}
		
		// session이 존재하면 통과
		return true;
	}
}
