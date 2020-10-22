package kevin.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kevin.model.Track;
import kevin.model.User;
import kevin.service.TrackService;

/**
 * Servlet implementation class TrackServlet
 */
@WebServlet("/TrackServlet")
public class TrackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int itemId;
		String userId;
		HttpSession session = request.getSession(false);
		
		if (!request.getParameter("aucid").equals("")) {
			
			userId = ((User) session.getAttribute("user")).getEmail();
			itemId = Integer.parseInt(request.getParameter("aucid"));
			Track track = TrackService.setTrack(itemId, userId);
			request.setAttribute("result", TrackService.saveTrack(track));
			
		} else {
			
			request.setAttribute("result", "Please Select an Auction to Track");
			
		}
		
		request.getRequestDispatcher("track.jsp").forward(request, response);
		
	}

}
