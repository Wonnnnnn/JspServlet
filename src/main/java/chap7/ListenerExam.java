package chap7;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebServlet("/test")
public class ListenerExam implements ServletContextListener, ServletContextAttributeListener,
        HttpSessionListener, HttpSessionAttributeListener {

    ServletContext sc;

//    @Override
//    public void init(ServletConfig config) throws ServletException {
//        super.init(config);
//        sc = config.getServletContext();
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        sc.setAttribute("name","hong");
//        sc.setAttribute("name","홍");
//        sc.removeAttribute("name");
//
//        HttpSession session = req.getSession();
//        session.setAttribute("session_id", session.getId());
//    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().log("servlet context started!");
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        sce.getServletContext().log("servlet context destroyed!");
    }

    @Override
    public void attributeAdded(ServletContextAttributeEvent event) {
//        event.getSession().g
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent event) {
        event.getServletContext().log("servlet context removed!"+event.getValue());
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent event) {
        event.getServletContext().log("servlet context replaced!");
    }
}
