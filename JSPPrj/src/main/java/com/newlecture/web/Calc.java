package com.newlecture.web;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calc")
public class Calc extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
	
		String x_ = req.getParameter("x");
		String y_ = req.getParameter("y");
		String op=req.getParameter("operator");
		int x = 0;
		int y = 0;
	

		if (!x_.equals(""))
			x = Integer.parseInt(x_);
		if (!y_.equals(""))
			y = Integer.parseInt(y_);
		

		int result = 0;
		result = x+y;
		result = x-y;

		
		if(op.equals("덧셈"))
			result=x+y;
		else
			result=x-y;

		res.getWriter().printf("연산 결과 : "+"%d",result);

	}
}
