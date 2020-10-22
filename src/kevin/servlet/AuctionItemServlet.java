package kevin.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kevin.service.AuctionService;

/**
 * Servlet implementation class AuctionItemServlet
 */
@WebServlet("/AuctionItemServlet")
public class AuctionItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int itemId = Integer.parseInt(request.getParameter("aucid"));
		HttpSession session = request.getSession(true);
		session.setAttribute("auc", AuctionService.getAuctionItem(itemId));
		
		request.getRequestDispatcher("auctionitem.jsp").forward(request, response);
		
	}

}
