package action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.hrDAO;

public class HrCommentWrite implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int hno = Integer.parseInt(request.getParameter("hno"));
		String hrComment = request.getParameter("comment");
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		hrDAO dao = new hrDAO();
		try {
			dao.HrCommentWrite(hrComment, idx, hno);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
