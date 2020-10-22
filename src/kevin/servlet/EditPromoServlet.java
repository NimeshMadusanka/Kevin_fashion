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
 * Servlet implementation class EditPromoServlet
 */
@WebServlet("/EditPromoServlet")
public class EditPromoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		double discountPercentage = Double.parseDouble(request.getParameter("val"));
		int id = Integer.parseInt(request.getParameter("ind"));
		
		Promotion promo = PromoService.setPromo(0, discountPercentage, "");
		
		String validationResult = PromoService.validatePromoEdit(promo);
		
		if (validationResult.equals("Promotion Validated"))
			request.setAttribute("result", PromoService.updatePromo(id, promo));
		else
			request.setAttribute("result", validationResult);
		
		request.getRequestDispatcher("promo.jsp").forward(request, response);
		
	}

}
