package action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.hrDAO;

public class HrReviewWrite implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		int hotelId = Integer.parseInt(request.getParameter("hotel_id"));
		int idx = Integer.parseInt(request.getParameter("idx"));
		String review = request.getParameter("review");
		
		hrDAO dao = new hrDAO();
		try {
			dao.HrWrite(title, hotelId, idx, review);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("Controller?command=HotelReview");
		rd.forward(request, response);
	}

}
