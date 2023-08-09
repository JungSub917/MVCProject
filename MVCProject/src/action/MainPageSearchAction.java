package action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.natDAO;
import dto.natDTO;

public class MainPageSearchAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		natDAO dao = new natDAO();
		ArrayList<natDTO> mainSearch = null;
		
		try {
			mainSearch = dao.getNatCityDto();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("mainsearch", mainSearch);
		RequestDispatcher rd = request.getRequestDispatcher("Main.jsp");
		rd.forward(request, response);
	}
}
