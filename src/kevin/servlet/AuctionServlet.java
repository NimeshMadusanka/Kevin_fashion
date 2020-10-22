package kevin.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kevin.model.Item;
import kevin.service.AuctionService;
import kevin.service.ItemService;

/**
 * Servlet implementation class AuctionServlet
 */
@WebServlet("/AuctionServlet")
public class AuctionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int itemId, durationDays, durationHours, durationMinutes;
		double startPrice;
		
		durationDays = Integer.parseInt(request.getParameter("days"));
		durationHours = Integer.parseInt(request.getParameter("hours"));
		durationMinutes = Integer.parseInt(request.getParameter("minutes"));
		itemId = Integer.parseInt(request.getParameter("id"));
		startPrice = Double.parseDouble(request.getParameter("price"));
		Item item = ItemService.getItem(itemId);
		
		request.setAttribute("result", AuctionService.saveAuctionItem(AuctionService.addToAuction(item, startPrice, durationDays, durationHours, durationMinutes)));
		request.getRequestDispatcher("adminauction.jsp").forward(request, response);
		
		ItemService.deleteItem(itemId);
			
	}

}
