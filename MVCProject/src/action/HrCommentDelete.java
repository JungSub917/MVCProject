package action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.hrDAO;

public class HrCommentDelete implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int hrno = Integer.parseInt(request.getParameter("hrno"));
		
		hrDAO dao = new hrDAO();
		try {
			dao.HrCommentDelete(hrno);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
