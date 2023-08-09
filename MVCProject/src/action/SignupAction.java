package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;

public class SignupAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String email = request.getParameter("email");
		String name = request.getParameter("inputName");
		String nickname = request.getParameter("inputNick");
		String pw = request.getParameter("inputPass");
		
		int result = 0;
		try {
			result = new UserDAO().singup(email, name, nickname, pw);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(result == 1) {
			request.setAttribute("signupSuccess", "<script>alert('회원가입에 성공하셨습니다!')</script>");
			RequestDispatcher rd = request.getRequestDispatcher("Controller?command=MainPage");
			rd.forward(request, response);
		}else {
			request.getSession().setAttribute("messageType", "실패메시지");
			request.getSession().setAttribute("messageContent", "이미 있음");
			response.sendRedirect("Signup.jsp");
		}
	}
}
