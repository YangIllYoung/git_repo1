package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import member.dao.MemberDAO;

@Controller
public class LoginController{
	@RequestMapping(value="/member/login.do")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("로그인 처리");
		// 1) 사용자 입력 정보 추출
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		// 2) DB 처리
		MemberDAO memberDAO = new MemberDAO();
		String name = memberDAO.login(id, pwd);
		// 3) 화면 네비게이션
		//ModelAndView 클래스 =공유데이터 +화면네비게이션 정보를 저장
		ModelAndView modelAndView = new ModelAndView();
		String viewPage = "";
		if(name != null) {	// log on 상태
			HttpSession session = request.getSession();
			session.setAttribute("memId", id);
			session.setAttribute("memName", name);
			viewPage = "redirect:../board/boardList.do?pg=1";//redirect방식으로 보내고 싶을 때			
		} else {			// log off 상태
			viewPage = "loginForm.jsp";	// loginForm.jsp
		}
		modelAndView.setViewName(viewPage);
		//return viewPage;
		return modelAndView;
	}

}




