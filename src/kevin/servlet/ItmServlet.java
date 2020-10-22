package kevin.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kevin.service.AuctionService;
import kevin.service.ItemService;

/**
 * Servlet implementation class ItmServlet
 */
@WebServlet("/ItmServlet")
public class ItmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (session.getAttribute("user") != null) {

			int itemId = Integer.parseInt(request.getParameter("itmid"));
			session.setAttribute("itm", ItemService.getItem(itemId));
			request.getRequestDispatcher("itm.jsp").forward(request, response);

		} else {
			
			request.setAttribute("result", "Please login to purchase items");
			request.getRequestDispatcher("account.jsp").forward(request, response);
			
		}

		

	}

}
