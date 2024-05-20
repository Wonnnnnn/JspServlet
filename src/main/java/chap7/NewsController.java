

package chap7;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;

@WebServlet("/news.nhn")
//@MultipartConfig(maxFileSize = 1024*1024*2, location = "/Users/hanbyeol/Documents/KOSTA/WebApp/jsp_webapp/src/main/webapp/img")
@MultipartConfig(maxFileSize = 1024*1024*2, location = "/Users/wonkim/Desktop/강의자료/프로젝트/webapp/jspTest/src/main/webapp/img")
public class NewsController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private NewsDAO dao;
    private ServletContext ctx;

    private final String START_PAGE = "chap7/newsList.jsp";

    public void init(ServletConfig config) throws ServletException{
        super.init(config);
        dao = new NewsDAO();
        ctx = getServletContext();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String action = req.getParameter("action");
        String view = null;
        System.out.println("action: " + action);

        if(action == null){
            ctx.getRequestDispatcher("/news.nhn?action=listNews").forward(req,resp);
        } else{
            switch (action){
                case "listNews" :
                    view = listNews(req); break;
                case "addNews" :
                    view = addNews(req); break;
                case "getNews" :
                    view = getNews(req); break;
                case "deleteNews" :
                    view = deleteNews(req); break;
            }
            if(view.startsWith("redirect:/")){
                view = view.substring("redirect:/".length());
                resp.sendRedirect(view);
            } else {
                ctx.getRequestDispatcher("/chap7/"+ view).forward(req,resp);
            }
        }
    }


    private String addNews(HttpServletRequest req){
        News n = new News();
        try{
            Part part = req.getPart("img");
            System.out.println("part: " + part);
            String fileName = part.getSubmittedFileName();
            System.out.println("fileName: " + fileName);

            System.out.println(part);
            if(fileName != null && !fileName.isEmpty()){
                part.write(fileName);
            }
            BeanUtils.populate(n,req.getParameterMap());
            n.setImg("/img/" + fileName);

            dao.addNews(n);
        } catch (Exception e){
            e.printStackTrace();
            ctx.log("뉴스 추가 과정에서 문제 발생!");
            req.setAttribute("error", "뉴스가 정상적으로 등록되지 않았습니다.");
            return listNews(req);
        }
        return "redirect:/news.nhn?action=listNews";
    }

    public String listNews(HttpServletRequest req){
        List<News> list;
        try{
            list = dao.getAll();
            req.setAttribute("newsList", list);
        } catch (Exception e){
            e.printStackTrace();
            ctx.log("뉴스 목록 과정에서 문제 발생!");
            req.setAttribute("error", "뉴스 목록이 정상적으로 처리되지 않았습니다.");
        }
        return "newsList.jsp";
    }

    public String getNews(HttpServletRequest req){
        int aid = Integer.parseInt(req.getParameter("aid"));
        try{
            News n = dao.getNews(aid);
            req.setAttribute("news", n);
        } catch (Exception e){
            e.printStackTrace();
            ctx.log("뉴스를 가져오는 과정에서 문제 발생!");
            req.setAttribute("error", "뉴스를 정상적으로 가져오지 못했습니다.");
        }
        return "newsView.jsp";
    }

    public String deleteNews(HttpServletRequest req) {
        int aid = Integer.parseInt(req.getParameter("aid"));
        try {
            dao.delNews(aid);
        } catch (Exception e) {
            e.printStackTrace();
            ctx.log("뉴스 삭제 과정에서 문제 발생: " + e.getMessage());
            req.setAttribute("error", "뉴스가 정상적으로 삭제되지 않았습니다. 오류: " + e.getMessage());
            return listNews(req);
        }
        return "redirect:/news.nhn?action=listNews";
    }


}

