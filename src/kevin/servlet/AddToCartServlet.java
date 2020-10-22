package kevin.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kevin.model.Item;
import kevin.model.Promotion;
import kevin.model.User;
import kevin.service.CartService;
import kevin.service.ItemService;
import kevin.service.PromoService;

/**
 * Servlet implementation class AddToCartServlet
 */
@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute("user") != null) {
			
			String userId = ((User)session.getAttribute("user")).getEmail();
			int itemId = Integer.parseInt(request.getParameter("item"));
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			Promotion promo = PromoService.getPromoByItem(itemId);
			double price;
			
			if (promo != null)
				price = ItemService.getItem(itemId).getPrice() * ((100 - promo.getDiscountPrecentage()) / 100.0) * quantity;
			else
				price = ItemService.getItem(itemId).getPrice() * quantity;
		
			Item item = ItemService.getItem(itemId);
			
			if (item.getId() != 0)
				ItemService.updateItem(ItemService.setItem(itemId, "", -1, null, null, null, item.getQuantity() - quantity, "", ""));
			
			request.setAttribute("result", CartService.saveCart(CartService.setCart(userId, itemId, quantity, price)));
			session.setAttribute("itm", ItemService.getItem(itemId));
			
		}
		
		request.getRequestDispatcher("itm.jsp").forward(request, response);
		
	}

}
