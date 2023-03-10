package kr.bit.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MemberDAO;
import kr.bit.model.MemberVO;


@WebServlet("/memberInsert.do")
public class MemberInsertController extends HttpServlet {
       
   
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//1. 파라미터 수집 (VO)
			request.setCharacterEncoding("UTF-8");
			String id = request.getParameter("id");
			String pass = request.getParameter("pass");
			String name = request.getParameter("name");
			int age = Integer.parseInt(request.getParameter("age"));
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			
			
			
			//파라미터수집(VO)
			//MemberVO mmVO = new MemberVO(id, pass, name, age, email, phone);
			//파라미터수집(VO) - set으로 하는게 기본임
			MemberVO vo = new MemberVO();
			vo.setId(id);
			vo.setPass(pass);
			vo.setName(name);
			vo.setAge(age);
			vo.setEmail(email);
			vo.setPhone(phone);
			
			MemberDAO dao=new MemberDAO();
			int cnt = dao.memberInsert(vo);
			
			PrintWriter out = response.getWriter();
			
			if(cnt>0) {
				//가입 성공
				//out.println("insert success");
				response.sendRedirect("/MVC01/memberList.do");
			} else {
				//가입 실패-> 예외객체를 만들어서 WAS에게 던지자.
				new ServletException("not insert");
			}
	}
}



















