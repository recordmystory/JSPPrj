package com.newlecture.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oracle.js.parser.ir.RuntimeNode.Request;

//Model 부분
@WebServlet("/spag")
public class Spag extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int num = 0;
		String num_ = request.getParameter("n");
		if (num_ != null && num_.equals("")) // num 값이 아예 없거나 빈 문자열이 들어왔을 때
			num = Integer.parseInt(num_);

		String result;

		if (num % 2 != 0)
			result = "홀수";
		else
			result = "짝수";
		
		request.setAttribute("result", result);

		String[] names = {"newlec", "dragon"};
		request.setAttribute("names", names);

		Map<String, Object> notice=new HashMap<String, Object>();
		notice.put("id", 1);
		notice.put("title", "EL은 좋아요");
		request.setAttribute("notice", notice);

		// redirect : 현재 작업했던 내용과 상관없이 새로운 요청을 하게 만드는 것.
		// forward : 현재 작업했던 내용을 이어갈 수 있도록 그대로 이어가는 것.

		RequestDispatcher dispatcher = request.getRequestDispatcher("spag.jsp"); //jsp파일로 요청
		dispatcher.forward(request, response);
	}
}
