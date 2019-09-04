package pl.coderslab.warsztat3katw03.model;

import pl.coderslab.warsztat3katw03.db.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Solution {
    private static final String FIND_RECENT_QUERY =
            "SELECT id, created, updated, description, users_id, exercises_id FROM solutions ORDER BY updated DESC LIMIT ?;";

    private int id;
    private LocalDateTime created;
    private LocalDateTime updated;
    private String description;
    private int usersId;
    private int exercisesId;

    public Solution() {
    }

    public Solution(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUsersId() {
        return usersId;
    }

    public void setUsersId(int usersId) {
        this.usersId = usersId;
    }

    public int getExercisesId() {
        return exercisesId;
    }

    public void setExercisesId(int exercisesId) {
        this.exercisesId = exercisesId;
    }

    public static List<Solution> findRecent(int maxCount){
        try(Connection connection = DbUtil.getConnection()) {
            final PreparedStatement ps = connection.prepareStatement(
                    FIND_RECENT_QUERY);

            ps.setInt(1, maxCount);

            final ResultSet rs = ps.executeQuery();

            List<Solution> solutions = new ArrayList<>();

            while (rs.next()){
                int id = rs.getInt(1);
                LocalDateTime created = rs.getTimestamp(2).toLocalDateTime();
                LocalDateTime updated = rs.getTimestamp(3).toLocalDateTime();
                String description = rs.getString(4);
                int usersId = rs.getInt(5);
                int exercisesId = rs.getInt(6);

                Solution solution = new Solution();
                solution.setId(id);
                solution.setCreated(created);
                solution.setUpdated(updated);
                solution.setDescription(description);
                solution.setUsersId(usersId);
                solution.setExercisesId(exercisesId);

                solutions.add(solution);
            }

            return solutions;

        } catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }
}
