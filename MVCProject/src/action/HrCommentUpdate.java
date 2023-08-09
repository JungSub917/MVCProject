package action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.hrDAO;

public class HrCommentUpdate implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int hrno = Integer.parseInt(request.getParameter("hrno"));
		String comment = request.getParameter("comment");
		
		hrDAO dao = new hrDAO();
		try {
			dao.HrCommentUpdate(hrno, comment);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	

}
