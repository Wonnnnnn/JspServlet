package chap8;

import chap7.News;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/news")
public class NewsApiService {

    NewsAPIDAO newsAPIDAO;

    public NewsApiService() {
        newsAPIDAO = new NewsAPIDAO();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<News> getNews() {
        List<News> newsList = null;
        try {
            newsList = newsAPIDAO.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newsList;
    }

    @GET
    @Path("/{aid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Object getNews(@PathParam("aid") int aid) {
        News news = null;
        Errors errors = null;
        try {
            news = newsAPIDAO.getNews(aid);
            return news;
        } catch (Exception e) {
//            e.printStackTrace();
            errors = new Errors();
            errors.errors.put("userMsg", "요청한 정보를 찾을 수 없음;");
            errors.errors.put("internalMsg", "news db 검색 오류");
            return errors;
        }

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String addNews(News news) {
        try{
            newsAPIDAO.addNews(news);
            return "등록 완료";
        } catch (Exception e) {
            e.printStackTrace();
            return "등록 실패";
        }
    }

    @DELETE
    @Path("/{aid}")
    public String deleteNews(@PathParam("aid") int aid) {
        try {
            newsAPIDAO.delNews(aid);
            return "삭제완료";
        } catch (Exception e) {
            e.printStackTrace();
            return "삭제 중 오류 발생";
        }
    }




}
