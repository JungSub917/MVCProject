package action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import dto.UserDTO;

public class LoginNaverAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String nickname = request.getParameter("nickname");
		String token = request.getParameter("token");
		
		UserDAO dao = new UserDAO();
		
		int naverSignInUp = 0;
		try {
			naverSignInUp = dao.naverSignInUp(name, email, nickname, token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		UserDTO userDTO = null;
		try {
			userDTO = dao.NaverUserDTO(email);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(naverSignInUp == 1 || naverSignInUp == 2) {
			request.getSession().setAttribute("userEmail", userDTO.getEmail());
			request.getSession().setAttribute("userNickname", userDTO.getNickName());
			request.getSession().setAttribute("userIdx", userDTO.getIdx());
			response.sendRedirect("Controller?command=MainPage"); 
		}else{
			request.setAttribute("LoginErrMsg", "<script>alert('로그인 오류')</script>"); 
			request.getRequestDispatcher("Login.jsp").forward(request, response);
		}
	}
}
