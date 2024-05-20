package chap7;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NewsDAO {
    Connection conn = null;
    PreparedStatement pstmt;
    ResultSet rs;

    final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    final String JDBC_URL = "jdbc:mysql://localhost:3306/webapp?serverTimezone=Asia/Seoul";

    public void open(){
        try{
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(JDBC_URL, "root", "dnjs9653");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void close(){
        try{
            pstmt.close();
            conn.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void addNews(News news){
        open();
        String sql = "INSERT INTO news(title, img, date, content) values (?,?,CURRENT_TIMESTAMP(),?)";
        System.out.println("addnews started");
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, news.getTitle());
            pstmt.setString(2, news.getImg());
            pstmt.setString(3, news.getContent());

            pstmt.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public void delNews(int aid) {
        open();
        String sql = "DELETE FROM news WHERE aid = ?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, aid);
            if(pstmt.executeUpdate() == 0){
                throw new SQLException("추가 에러");
            }
//            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error deleting news with aid: " + aid);
        } finally {
            close();
        }
    }


    public List<News> getAll(){
        open();
        List<News> newsList = new ArrayList<>();

        try{
            pstmt = conn.prepareStatement("SELECT aid, title, date_format(date, '%Y-%m-%d %h:%m:%s') as cdate FROM news");
            rs = pstmt.executeQuery();

            while (rs.next()){
                News news = new News();
                news.setAid(rs.getInt("aid"));
                news.setTitle(rs.getString("title"));
                news.setDate(rs.getString("cdate"));

                newsList.add(news);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            close();
        }
        return newsList;
    }

    public News getNews(int aid){
        open();
        News news = null;
        String sql = "SELECT aid, title, img, date_format(date, '%Y-%m-%d %h:%m:%s') as cdate, content from news where aid = ?";

        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, aid);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                news = new News();
                news.setAid(rs.getInt("aid"));
                news.setTitle(rs.getString("title"));
                news.setImg(rs.getString("img"));
                news.setDate(rs.getString("cdate"));
                news.setContent(rs.getString("content"));
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            close();
        }
        return news;
    }

}

