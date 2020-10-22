package kevin.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import kevin.model.CreditCard;
import kevin.model.User;
import kevin.service.CreditCardService;

/**
 * Servlet implementation class GetCreditCardsServlet
 */
@WebServlet("/GetCreditCardsServlet")
public class GetCreditCardsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		String userId = ((User)session.getAttribute("user")).getEmail();
		
		ArrayList<CreditCard> ccs = CreditCardService.getCreditCards(userId);
		String json = new Gson().toJson(ccs);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
		
	}

}
