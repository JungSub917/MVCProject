package action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CityLikeDAO;

public class cityLikeBtnAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		int cityId = Integer.parseInt(request.getParameter("cityId"));
		
		CityLikeDAO clDao = new CityLikeDAO();
		
		clDao.citylikeBtn(idx, cityId);
	}

}
