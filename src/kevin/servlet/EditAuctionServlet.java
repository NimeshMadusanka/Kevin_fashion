package kevin.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kevin.service.AuctionService;

/**
 * Servlet implementation class EditAuctionServlet
 */
@WebServlet("/EditAuctionServlet")
public class EditAuctionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int days = -1, hours = -1, minutes = -1, id;
		double startPrice = -1;

		if (!request.getParameter("id").equals("")) {

			id = Integer.parseInt(request.getParameter("id"));
			
			if (!request.getParameter("price").equals(""))
				startPrice = Double.parseDouble(request.getParameter("price"));
			if (!request.getParameter("days").equals(""))
				days = Integer.parseInt(request.getParameter("days"));
			if (!request.getParameter("hours").equals(""))
				hours = Integer.parseInt(request.getParameter("hours"));
			if (!request.getParameter("minutes").equals(""))
				minutes = Integer.parseInt(request.getParameter("minutes"));
			
			if (AuctionService.getAuctionItem(id).getBidCount() > 0)
				request.setAttribute("result", "Cannot Update the Auction, Bids have already been placed");
			else
				request.setAttribute("result", AuctionService.EditAuction(id, startPrice, days, hours, minutes));

		} else {

			request.setAttribute("result", "Please Select an Item");

		}

		request.getRequestDispatcher("adminauction.jsp").forward(request, response);

	}

}
