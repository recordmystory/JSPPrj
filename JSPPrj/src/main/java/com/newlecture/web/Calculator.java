package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calculator")
public class Calculator extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		Cookie[] cookies = req.getCookies();

		String exp = "0";
		if (cookies != null)
			for (Cookie c : cookies)
				if (c.getName().equals("exp")) {
					exp = c.getValue();
					break;
				}

		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		out.write("<!DOCTYPE html>");
		out.write("<html>");

		out.write("<head>");
		out.write("<meta charset=\"UTF-8\">");
		out.write("<title>계산을 위한 입력 폼</title>");
		out.write("<style>");
		out.write("input{");
		out.write("width:50px;");
		out.write("height:50px;");
		out.write("}");
		out.write(".output{");
		out.write("height:50px;");
		out.write("background-color: #e9e9e9;");
		out.write("font-size: 24px;");
		out.write("font-weight:bold;");
		out.write("text-align: right;");
		out.write("padding: 0px 5px;");
		out.write("}");
		out.write("</style>");
		out.write("</head>");

		out.write("<body>");
		out.write("<form method=\"post\">");
		out.write("<table>");
		out.write("<tr>");

		out.printf("<td class=\"output\" colspan=\"4\">%s</td>", exp);
		out.write("</td>");
		out.write("</tr>");
		out.write("<tr>");
		out.write("<td> <input type=\"submit\" name=\"operator\" value=\"CE\" />");
		out.write("</td>");
		out.write("<td> <input type=\"submit\" name=\"operator\" value=\"C\" />");
		out.write("</td>");
		out.write("<td> <input type=\"submit\" name=\"operator\" value=\"BS\" />");
		out.write("</td>");
		out.write("<td> <input type=\"submit\" name=\"operator\" value=\"/\" />");
		out.write("</td>");
		out.write("</tr>");
		out.write("<tr>");
		out.write("<td> <input type=\"submit\" name=\"value\" value=\"7\" />");
		out.write("</td>");
		out.write("<td> <input type=\"submit\" name=\"value\" value=\"8\" />");
		out.write("</td>");
		out.write("<td> <input type=\"submit\" name=\"value\" value=\"9\" />");
		out.write("</td>");
		out.write("<td> <input type=\"submit\" name=\"operator\" value=\"*\" />");
		out.write("</td>");
		out.write("</tr>");
		out.write("<tr>");
		out.write("<td> <input type=\"submit\" name=\"value\" value=\"4\" />");
		out.write("</td>");
		out.write("<td> <input type=\"submit\" name=\"value\" value=\"5\" />");
		out.write("</td>");
		out.write("<td> <input type=\"submit\" name=\"value\" value=\"6\" />");
		out.write("</td>");
		out.write("<td> <input type=\"submit\" name=\"operator\" value=\"-\" />");
		out.write("</td>");
		out.write("</tr>");
		out.write("<tr>");
		out.write("<td> <input type=\"submit\" name=\"value\" value=\"1\" />");
		out.write("</td>");
		out.write("<td> <input type=\"submit\" name=\"value\" value=\"2\" />");
		out.write("</td>");
		out.write("<td> <input type=\"submit\" name=\"value\" value=\"3\" />");
		out.write("</td>");
		out.write("<td> <input type=\"submit\" name=\"operator\" value=\"+\" />");
		out.write("</td>");
		out.write("</tr>");
		out.write("<tr>");
		out.write("<td> ");
		out.write("</td>");
		out.write("<td> <input type=\"submit\" name=\"value\" value=\"0\" />");
		out.write("</td>");
		out.write("<td> <input type=\"submit\" name=\"dot\" value=\".\" />");
		out.write("</td>");
		out.write("<td> <input type=\"submit\" name=\"operator\" value=\"=\" />");
		out.write("</td>");
		out.write("</tr>");
		out.write("</table>");

		out.write("</form>");

		out.write("</body>");

		out.write("</html>");

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Cookie[] cookies = req.getCookies();

		String value = req.getParameter("value");
		String operator = req.getParameter("operator");
		String dot = req.getParameter("dot");

		String exp = "";

		if (cookies != null) // 쿠키 읽어오기
			for (Cookie c : cookies)
				if (c.getName().equals("exp")) {
					exp = c.getValue();
					break;
				}

		if (operator != null && operator.equals("=")) {
			ScriptEngine engine = new ScriptEngineManager().getEngineByName("graal.js");
			try {
				exp = String.valueOf(engine.eval(exp));
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (operator != null && operator.equals("C")) {
			exp = "";
		} else {
			exp += (value == null) ? "" : value;
			exp += (operator == null) ? "" : operator;
			exp += (dot == null) ? "" : dot;

		}

		Cookie expCookie = new Cookie("exp", exp);
		if (operator != null && operator.equals("="))
			expCookie.setMaxAge(0); // 쿠키 소멸시키기
		expCookie.setPath("/calculator");
		res.addCookie(expCookie); // 쿠키 저장
		res.sendRedirect("calculator");
	}

}
