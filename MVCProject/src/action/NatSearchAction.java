package action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dao.natDAO;
import dto.natDTO;

public class NatSearchAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String searchNat = request.getParameter("dodbogi");
		
		response.getWriter().write(searchJSON(searchNat));
	}
	public String searchJSON(String searchNat) {
		/* if(option == null) option = ""; */ // option이 null이면 공백?
		StringBuffer result = new StringBuffer("");
		JSONArray jsonArray1 = new JSONArray();
	
		natDAO natDao = new natDAO();
		ArrayList<natDTO> searchnat = null;
		try {
			searchnat = natDao.natSearch(searchNat);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		for(natDTO dto : searchnat) {
			JSONObject jsonObject1 = new JSONObject();
			jsonObject1.put("natId", dto.getNatId());
      jsonObject1.put("korName", dto.getNatKorname());
      jsonObject1.put("engName", dto.getNatEngname());
      jsonArray1.add(jsonObject1);
		}
		
		JSONObject jsonResult = new JSONObject();
		jsonResult.put("result1", jsonArray1);

		result.append(jsonResult.toJSONString());
		return result.toString();
	}
}
