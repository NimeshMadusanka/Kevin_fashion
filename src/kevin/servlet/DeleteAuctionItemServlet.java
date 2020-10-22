package kevin.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kevin.service.AuctionService;

/**
 * Servlet implementation class DeleteAuctionItemServlet
 */
@WebServlet("/DeleteAuctionItemServlet")
public class DeleteAuctionItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String message;
		int id = Integer.parseInt(request.getParameter("aucId"));
		
		if (AuctionService.getAuctionItem(id).getBidCount() > 0)
			message = "Cannot Delete the Auction, Bids have already been placed";
		else
			message = AuctionService.deleteAuctionItem(id);
		
		String json = new Gson().toJson(message);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
		
	}

}
