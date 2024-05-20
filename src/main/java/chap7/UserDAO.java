package chap7;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
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

    public void addUser(User user){
        open();
        String sql = "INSERT INTO user(id, pw) values (?,?)";

        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getId());
            pstmt.setString(2, user.getPw());

            pstmt.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public void delUser(int uid) {
        open();
        String sql = "DELETE FROM news WHERE uid = ?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, uid);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error deleting news with uid: " + uid);
        } finally {
            close();
        }
    }


    public List<User> getUserAll(){
        open();
        List<User> userList = new ArrayList<>();

        try{
            pstmt = conn.prepareStatement("SELECT uid, id, pw FROM user");
            rs = pstmt.executeQuery();

            while (rs.next()){
                User user = new User();
                user.setUid(rs.getInt("uid"));
                user.setId(rs.getString("id"));
                user.setPw(rs.getString("pw"));

                userList.add(user);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            close();
        }
        return userList;
    }

    public User getuser(int uid){
        open();
        User user = null;
        String sql = "SELECT uid, id, pw from user where uid = ?";

        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, uid);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUid(rs.getInt("uid"));
                user.setId(rs.getString("id"));
                user.setPw(rs.getString("pw"));

            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            close();
        }
        return user;
    }
}
