package action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.hrDAO;

public class HrReviewDelete implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int hno = Integer.parseInt(request.getParameter("hno"));
		
		hrDAO dao = new hrDAO();
		try {
			dao.HrDelete(hno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("Controller?command=HotelReview");
		rd.forward(request, response);
	}
}
