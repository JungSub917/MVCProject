package action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.hrDAO;

public class HrReviewReform implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int hno = Integer.parseInt(request.getParameter("hno"));
		String title = request.getParameter("title");
		String review = request.getParameter("review");
		System.out.println(hno +", "+ title +"," + review);
		hrDAO dao = new hrDAO();
		try {
			dao.HrReform(title, review, hno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("Controller?command=hotelReviewDetail&hno="+hno);
		rd.forward(request, response);
		
	}
}
