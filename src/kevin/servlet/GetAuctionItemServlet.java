package kevin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kevin.model.AuctionItem;
import kevin.service.AuctionService;
import kevin.service.ItemService;

/**
 * Servlet implementation class GetAuctionItemServlet
 */
@WebServlet("/GetAuctionItemServlet")
public class GetAuctionItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AuctionItem auctionItem = AuctionService.getAuctionItem(Integer.parseInt(request.getParameter("aucId")));
		auctionItem.setColor(ItemService.getColor(auctionItem.getColor()));
		String json = new Gson().toJson(auctionItem);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
		
	}

}
