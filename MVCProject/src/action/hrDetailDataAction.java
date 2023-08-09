package action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.hrDAO;
import dto.hrDTO;

public class hrDetailDataAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int hno = Integer.parseInt(request.getParameter("hno"));
		hrDAO hrdao = new hrDAO();
		
		hrDTO hrdto = null;
		int commentCnt = 0;
		ArrayList<hrDTO> commentList = null;
		try {
			hrdto = hrdao.getHotelReviewDetailList(hno);
			commentCnt = hrdao.HrCountComment(hno);
			commentList = hrdao.HrCommentList(hno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("hotelreviewdetaillist", hrdto);
		request.setAttribute("hrCommentCnt", commentCnt);
		request.setAttribute("hrCommnetList", commentList);
		
		RequestDispatcher rd = request.getRequestDispatcher("HotelReviewDe.jsp");
		rd.forward(request, response);
	}

}
