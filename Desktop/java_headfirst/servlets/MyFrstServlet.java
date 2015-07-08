import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class MyFrstServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException , IOException{
		String title = "PhraseOMatic has generated the following phrase.";
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.println("<HTML><HEAD><TITLE>");
		out.println("PhraseOmatic");
		out.println("</TITLE></HEAd><BODY>");
		out.println("<H1>" + title + "/H!");
		out.println("<P><a href =\"MyFrstServlet\">make another phrase</a></p>");
		out.println("</BODY></HTML>");
		out.close();
	
	}
}