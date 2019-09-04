package pl.coderslab.warsztat3katw03.model;

import pl.coderslab.warsztat3katw03.db.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class Exercise {
    private final static String FIND_BY_ID_QUERY = "SELECT id, title, description FROM exercises WHERE id=?";
    private int id;
    private String title;
    private String description;

    public Exercise() {
    }

    public Exercise(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static Exercise findById(int idToFindBy){
        try(Connection connection = DbUtil.getConnection()) {
            final PreparedStatement ps = connection.prepareStatement(
                    FIND_BY_ID_QUERY);

            ps.setInt(1,idToFindBy);

            final ResultSet rs = ps.executeQuery();

            if (rs.next()){
                int id = rs.getInt(1);
                String title = rs.getString(2);
                String description = rs.getString(3);

                Exercise ex = new Exercise();
                ex.setId(id);
                ex.setTitle(title);
                ex.setDescription(description);

                return ex;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }
}
