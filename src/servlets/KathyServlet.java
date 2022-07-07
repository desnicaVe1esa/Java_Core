//package servlets;
//
//public class KathyServlet  extends HttpServlet {
//    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        String title = "PhraseOMatic has generated the following phrase";
//
//        response.setContentType("text/htm");
//        PrintWriter out = response.getWriter();
//
//        out.println("<HTML><HEAD><TITLE>");
//        out.println("PhraseOMatic");
//        out.println("</TITLE></HEAD><BODY>");
//        out.println("<H1>" + title + "</H1>");
//        out.println("<P>" + PhraseOMatic.makePhrase());
//        out.println("<P><a href="\"KathyServlet\">Создать другую фразу</a></P>);
//        out.println("</BODY></HTML>");
//        out.close();
//    }
//}
