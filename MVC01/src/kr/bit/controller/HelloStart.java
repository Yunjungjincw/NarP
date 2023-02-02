package kr.bit.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hs.do")
public class HelloStart extends HttpServlet {

	
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 클라이언트의 요청을 받는 작업 (파라미터 수집)- controller (servlet)
		// 2. 처리하는 작업 (비즈니스 로직) ->model
		int sum=0;
		
		for(int i=0; i<=100; i++) {
			sum += i;
		}
		//3. 요청한 클라이언트에게 응답하는 작업 (프리젠테이션 로직) -> view (jsp)
		PrintWriter out =response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.print(sum);
		out.println("</body>");
		out.println("</html>");
		
		
		// 서블릿과 모델로 회원관리 만들기.
	}
}
