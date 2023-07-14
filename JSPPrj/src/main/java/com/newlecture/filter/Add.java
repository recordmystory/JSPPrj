package com.newlecture.filter;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add")
public class Add extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");

		int x = 0;
		int y = 0;
		
		String x_ = req.getParameter("x");
		String y_ = req.getParameter("y");


		if (x_ != null && !x_.equals(""))
			x = Integer.parseInt(x_);
		if (y_ != null && !y_.equals(""))
			y = Integer.parseInt(y_);

		int result = x + y;

		res.getWriter().printf("연산 결과 : "+"%d",result);

	}
}
