package kr.bit.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MyCalc;


@WebServlet("/calc.do")
public class CalcController extends HttpServlet {

	
	//Controller 
	// 브라우저 input 파라미터 값 보냄 (form 태그 이용) => 서버 통해서 servlet 이 비즈니스 로직 처리하고, 다시 브라우저로 값을 출력
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//클라이언트에서 넘어오는 폼 파라메터를 받기(파라메터 수집, su1, su2)
		int su1 = Integer.parseInt(request.getParameter("su1"));
		int su2 = Integer.parseInt(request.getParameter("su2"));
		
		//su1,su2=?
		MyCalc mc = new MyCalc();
		
		
		int sum  =mc.hap(su1, su2);
	
		
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<table border='1'>");
		out.println("<tr>");
		out.println("<th>");
		out.println("TOTAL");
		out.println("</th>");
		out.println("<td>");
		out.println(sum);
		out.println("</td>");
		out.println("<tr>");
		out.println("</table>");
		out.println("</body>");
		out.println("</html>");
	}

}
