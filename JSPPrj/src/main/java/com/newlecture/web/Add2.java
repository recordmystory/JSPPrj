package com.newlecture.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add2")
public class Add2 extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");

		// 값을 배열로 받을 때는 values 사용
		String[] num_  = req.getParameterValues("num"); 
		
		int result = 0;
		
		for(int i =0 ; i<num_.length; i++) {
			int num= Integer.parseInt(num_[i]);
			result+=num; // num 누적
		}


		res.getWriter().printf("연산 결과 : "+"%d",result);

	}
}
