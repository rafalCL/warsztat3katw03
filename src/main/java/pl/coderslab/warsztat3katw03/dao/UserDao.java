package pl.coderslab.warsztat3katw03.dao;

import pl.coderslab.warsztat3katw03.db.DbUtil;
import pl.coderslab.warsztat3katw03.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private static final String CREATE_USER_QUERY =
            "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";

    private static final String FIND_ALL_USER_QUERY =
            "SELECT id, username, email, password FROM users;";
    public static User create(User user){
        try(Connection connection = DbUtil.getConnection()) {
            final PreparedStatement ps = connection.prepareStatement(
                    CREATE_USER_QUERY,
                    Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());

            ps.executeUpdate();

            final ResultSet generatedKeys = ps.getGeneratedKeys();
            if(generatedKeys.next()){
                user.setId(generatedKeys.getInt(1));
            }

            return user;
        } catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    } // create()

    public static List<User> findAll() {
        try(Connection connection = DbUtil.getConnection()) {
            final PreparedStatement ps = connection.prepareStatement(
                    FIND_ALL_USER_QUERY);

            final ResultSet rs = ps.executeQuery();

            List<User> users = new ArrayList<>();

            while (rs.next()){
                int id = rs.getInt(1);
                String username = rs.getString(2);
                String email = rs.getString(3);
                String password = rs.getString(4);
                User u = new User(username, email, password);
                u.setId(id);

                users.add(u);
            }

            return users;

        } catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    } // findAll()
}
