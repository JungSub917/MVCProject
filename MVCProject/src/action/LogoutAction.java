package action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int pageNum = Integer.parseInt(request.getParameter("page"));

		request.getSession().removeAttribute("UserId");
		request.getSession().removeAttribute("UserName");
		
		request.getSession().invalidate();
		
		if(pageNum == 1){
			response.sendRedirect("Controller?command=MainPage");
		}else if(pageNum == 2){
			response.sendRedirect("Controller?command=natlist");
		}else if(pageNum ==3 ){
			response.sendRedirect("SearchMain.jsp");
		}else if(pageNum == 4){
			response.sendRedirect("Controller?command=HotelMain");
		}else{
			response.sendRedirect("Controller?command=MainPage");
		}
	}
}
