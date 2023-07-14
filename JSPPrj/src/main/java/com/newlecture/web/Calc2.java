package com.newlecture.web;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/calc2")
public class Calc2 extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		ServletContext application = req.getServletContext(); // 어플리케이션 저장소
		HttpSession session = req.getSession();
		Cookie[] cookies = req.getCookies();

		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");

		String v_ = req.getParameter("v");
		String op = req.getParameter("operator");
		int v = 0;
		if (!v_.equals(""))
			v = Integer.parseInt(v_);

		// 계산
		if (op.equals("=")) {

			// int x = (Integer)application.getAttribute("value"); //값 꺼내기
			// int x = (Integer)session.getAttribute("value");
			int x = 0;
			for (Cookie c : cookies)
				if (c.getName().equals("value")) {
					x = Integer.parseInt(c.getValue());
					break;
				}

			int y = v;
			// String operator=(String)application.getAttribute("op");
			// String operator = (String) session.getAttribute("op");

			String operator="";
			for (Cookie c : cookies)
				if (c.getName().equals("op")) {
					operator = c.getValue();
					break;
				}
			int result = 0;

			if (operator.equals("+"))
				result = x + y;
			else
				result = x - y;

			res.getWriter().printf("연산 결과 : " + "%d", result);

		}

		// 값을 저장
		else {
//			session.setAttribute("value", v);
//			session.setAttribute("op", op);

//			application.setAttribute("value", v);
//			application.setAttribute("op", op);

			Cookie valueCookie = new Cookie("value", String.valueOf(v)); // 문자열로 변환
			Cookie opCookie = new Cookie("op", op);
			valueCookie.setPath("/calc2");
			valueCookie.setMaxAge(24*60*60);
			opCookie.setPath("/calc2");
			res.addCookie(valueCookie);
			res.addCookie(opCookie);

			res.sendRedirect("calc2.html");
		}

	}
}
