package pl.coderslab.warsztat3katw03.model;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.warsztat3katw03.db.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private final static String FIND_BY_ID_QUERY = "SELECT id, username, email, password FROM users WHERE id=?";

    private int id;
    private String username;
    private String email;
    private String password;

    public User() {
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        setPassword(password);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean isPasswordCorrect(String password){
        return BCrypt.checkpw(password, getPassword());
    }

    public static User findById(int idToFindBy){
        try(Connection connection = DbUtil.getConnection()) {
            final PreparedStatement ps = connection.prepareStatement(
                    FIND_BY_ID_QUERY);

            ps.setInt(1,idToFindBy);

            final ResultSet rs = ps.executeQuery();

            if (rs.next()){
                int id = rs.getInt(1);
                String username = rs.getString(2);
                String email = rs.getString(3);
                String password = rs.getString(4);

                User u = new User();
                u.setId(id);
                u.setUsername(username);
                u.setEmail(email);
                u.setPassword(password);

                return u;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }
}
