package kevin.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kevin.model.Cart;
import kevin.model.Item;
import kevin.service.CartService;
import kevin.service.ItemService;

/**
 * Servlet implementation class RemoveFromCartServlet
 */
@WebServlet("/RemoveFromCartServlet")
public class RemoveFromCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		Cart cart = CartService.getCart(id);
		Item item = ItemService.getItem(cart.getItemId());
		ItemService.updateItem(ItemService.setItem(item.getId(), "", -1, null, null, null, (item.getQuantity() + cart.getQuantity()), "", ""));
		
		request.setAttribute("result", CartService.removeCart(id));
		request.getRequestDispatcher("cart.jsp").forward(request, response);
		
	}

}
