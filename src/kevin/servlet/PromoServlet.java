package kevin.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kevin.model.Promotion;
import kevin.service.PromoService;

/**
 * Servlet implementation class PromoServlet
 */
@WebServlet("/PromoServlet")
public class PromoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int itemId;
		double percentage;
		String name;
		
		itemId = Integer.parseInt(request.getParameter("itemid"));
		percentage = Double.parseDouble(request.getParameter("percentage"));
		name = request.getParameter("name");
		
		Promotion promo = PromoService.setPromo(itemId, percentage, name);
		
		if (PromoService.validatePromo(promo).equals("Promotion Validated")) {
			request.setAttribute("result", PromoService.savePromo(promo));
		} else {
			request.setAttribute("result", PromoService.validatePromo(promo));
		}
		
		request.getRequestDispatcher("promo.jsp").forward(request, response);
		
	}

}
