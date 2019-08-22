package board.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import board.bean.BoardDTO;
import board.dao.BoardDAO;
import member.dao.MemberDAO;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="/board/boardWriteForm.do")
	public ModelAndView boardWriteForm() {
		// 1) 화면 네비게이션
		ModelAndView modelAndView = new ModelAndView();
		//return "boardWriteForm";	// ./boardWriteForm.jsp
		
		
		//modelAndView.setViewName("boardWriteForm.jsp");
		modelAndView.addObject("display","../board/boardWriteForm.jsp");
		modelAndView.setViewName("../main/index.jsp");
		return modelAndView;
	}
	
	
	
	@RequestMapping(value="/board/boardWrite.do")
	public ModelAndView boardWrite(HttpServletRequest request) {
		// 1) 데이터 처리
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String id = (String)session.getAttribute("memId");
		String name = (String) session.getAttribute("memName");
		// 2) DB
		BoardDTO boardDTO = new BoardDTO();
		ModelAndView modelAndView =new ModelAndView();
		boardDTO.setId(id);
		boardDTO.setName(name);
		boardDTO.setSubject(subject);
		boardDTO.setContent(content);
		
		//BoardDAO boardDAO = new BoardDAO();
		int result = boardService.boardWrite(boardDTO);
		// 3) 데이터 공유
		//request.setAttribute("result", result);
		modelAndView.addObject("result",result);
		// 4) 화면 네비게이션
		//return "boardWrite";	// ./boardWrite.jsp
		
		modelAndView.setViewName("boardWrite.jsp");
		return modelAndView;
	}
	
	@RequestMapping(value="/board/boardList.do")
	public ModelAndView boardList(HttpServletRequest request) {
		System.out.println("목록 처리");
		// 1) 데이터 처리
		int pg = Integer.parseInt(request.getParameter("pg"));
		// 목록처리 (5줄씩)
		int endNum = pg * 5;
		int startNum = endNum - 4;
		// 2) DB 처리
		//BoardDAO boardDAO = new BoardDAO();
		List<BoardDTO> list = boardService.boardList(startNum, endNum);
		ModelAndView modelAndView =new ModelAndView();
		// 페이징 처리
		int totalA = boardService.getTotalA();	// 총목록수
		int totalP = (totalA + 4) / 5;		// 총 페이지수
		
		int startPage = (pg-1)/3*3 + 1;
		int endPage = startPage + 2;
		if(endPage > totalP) endPage = totalP;
		modelAndView.addObject("pg",pg);
		modelAndView.addObject("list",list);
		modelAndView.addObject("totalP",totalP);
		modelAndView.addObject("startPage",startPage);
		modelAndView.addObject("endPage",endPage);
		

		// 3) 데이터 공유 처리
		//request.setAttribute("pg", pg);
		//request.setAttribute("list", list);
		//request.setAttribute("totalP", totalP);
		//request.setAttribute("startPage", startPage);
		//request.setAttribute("endPage", endPage);
		// 4) 화면 네비게이션
		//return "boardList";	// ./boardList.jsp
		//return modelAndView;
		//modelAndView.setViewName("boardList.jsp");
		modelAndView.addObject("display","../board/boardList.jsp");
		modelAndView.setViewName("../main/index.jsp");
		return modelAndView;
	}
	
	@RequestMapping(value="/board/boardView.do")
	public ModelAndView boardView(HttpServletRequest request) {
		System.out.println("글상세보기 처리");
		// 1) 데이터 처리
		int seq = Integer.parseInt(request.getParameter("seq"));
		int pg = Integer.parseInt(request.getParameter("pg"));
		// 2) DB
		//BoardDAO boardDAO = new BoardDAO();
		boardService.updateHit(seq);	// 조회수 증가
		BoardDTO boardDTO = boardService.boardView(seq);
		ModelAndView modelAndView = new ModelAndView();
		// 3) 데이터 공유
		modelAndView.addObject("seq",seq);
		modelAndView.addObject("pg",pg);
		modelAndView.addObject("boardDTO",boardDTO);
		
		//request.setAttribute("seq", seq);
		//request.setAttribute("pg", pg);
		//request.setAttribute("boardDTO", boardDTO);
		// 4) 화면 네비게이션
		//return "boardView";	// ./boardView.jsp
		
		
		//modelAndView.setViewName("boardView.jsp");
		modelAndView.addObject("display","../board/boardView.jsp");
		modelAndView.setViewName("../main/index.jsp");
		return modelAndView;
	}
	
	@RequestMapping(value="/board/boardDelete.do")
	public ModelAndView boardDelete(HttpServletRequest request) {
		// 1) 데이터 처리
		int pg = Integer.parseInt(request.getParameter("pg"));
		int seq = Integer.parseInt(request.getParameter("seq"));
		// 2) DB
		//BoardDAO boardDAO = new BoardDAO();
		ModelAndView modelAndView =new ModelAndView();
		int result = boardService.boardDelete(seq);
		// 3) 데이터 공유
		modelAndView.addObject("pg",pg);
		modelAndView.addObject("seq",seq);
		modelAndView.addObject("result",result);
		//request.setAttribute("pg", pg);
		//request.setAttribute("seq", seq);
		//request.setAttribute("result", result);
		// 4) 화면 네비게이션
		//return "boardDelete";	// ./boardDelete.jsp
		
		modelAndView.setViewName("boardDelete.jsp");
		return modelAndView;
		
	}
	
	
	
}
