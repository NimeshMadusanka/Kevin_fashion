package kevin.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kevin.model.AuctionItem;
import kevin.model.Bid;
import kevin.model.CreditCard;
import kevin.model.User;
import kevin.service.AuctionService;
import kevin.service.BidService;
import kevin.service.CreditCardService;
import kevin.service.UserService;

/**
 * Servlet implementation class BidServlet
 */
@WebServlet("/BidServlet")
public class BidServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (session.getAttribute("user") != null) {

			
			String userId = ((User) session.getAttribute("user")).getEmail();
			long creditCard = Long.parseLong(request.getParameter("card"));
			double bidAmount = Double.parseDouble(request.getParameter("bid"));
			
			CreditCard cc = CreditCardService.getCreditCard(creditCard);

			if (CreditCardService.isCreditCardExpired(cc)) {

				request.setAttribute("result", "Credit Card is Expired");
				request.getRequestDispatcher("auctionitem.jsp").forward(request, response);

			} else {

				if (CreditCardService.isCreditLimitExeeded(bidAmount, cc)) {

					request.setAttribute("result", "Your credit limit is too low to make this bid");
					request.getRequestDispatcher("auctionitem.jsp").forward(request, response);

				} else {

					AuctionItem item = AuctionService.getAuctionItem(Integer.parseInt(request.getParameter("id")));
					
					String bidDate, bidExpireDate;
					int bidDurationD = item.getDurationDays();
					int bidDurationH = item.getDurationHours();
					int bidDurationM = item.getDurationMinutes();
					Calendar cal;
					
					cal = Calendar.getInstance();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

					bidDate = sdf.format(cal.getTime());
					cal.add(Calendar.DATE, bidDurationD);
					cal.add(Calendar.HOUR_OF_DAY, bidDurationH);
					cal.add(Calendar.MINUTE, bidDurationM);

					bidExpireDate = sdf.format(cal.getTime());
					
					Bid bid = BidService.setBid(item.getId(), userId, bidAmount, bidDate, bidExpireDate, creditCard);
					request.setAttribute("result", BidService.saveBid(bid));
					request.setAttribute("exp", bidExpireDate);
					BidService.setExpireTimer(cal, bid);
					request.getRequestDispatcher("auctionitem.jsp").forward(request, response);

				}

			}
			
		} else {

			request.getRequestDispatcher("auction.jsp").forward(request, response);

		}

	}

}
