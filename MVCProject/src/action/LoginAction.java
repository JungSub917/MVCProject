package action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import dto.UserDTO;

public class LoginAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String userEmail = request.getParameter("id");
		String userPassword = request.getParameter("pw");
		
		UserDAO dao = new UserDAO();
		UserDTO userDTO = null;
		try {
			userDTO = dao.getUserDTO(userEmail, userPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(userDTO.getEmail() != null){
			request.getSession().setAttribute("userEmail",userDTO.getEmail());
			request.getSession().setAttribute("userNickname", userDTO.getNickName());
			request.getSession().setAttribute("userIdx", userDTO.getIdx());
			response.sendRedirect("Controller?command=MainPage");
		}else{
			request.setAttribute("LoginErrMsg", "<script>alert('로그인 오류')</script>"); 
			request.getRequestDispatcher("Login.jsp").forward(request, response);
		}
	}

}
