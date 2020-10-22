package kevin.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kevin.model.Item;
import kevin.model.User;
import kevin.service.SearchService;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (!request.getParameter("query").equals("")) {

			if (session.getAttribute("user") != null) {

				User user = (User) session.getAttribute("user");

				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String dateTime = sdf.format(calendar.getTime());

				String query = request.getParameter("query");

				if (SearchService.saveSearch(SearchService.setSearch(user.getEmail(), dateTime, query))
						.equals("Search Successful")) {

					ArrayList<Item> items = SearchService.searchFor(query);
					request.setAttribute("items", items);

				}

			} else {

				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String dateTime = sdf.format(calendar.getTime());

				String query = request.getParameter("query");

				if (SearchService.saveSearch(SearchService.setSearch(dateTime, query)).equals("Search Successful")) {

					ArrayList<Item> items = SearchService.searchFor(query);
					request.setAttribute("items", items);

				}

			}
			
		} else {
			
			request.setAttribute("result", "Please enter something");
			
		}

		request.getRequestDispatcher("search.jsp").forward(request, response);

	}

}
