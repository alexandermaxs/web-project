package team.database.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import team.exception.DAOException;
import team.model.bean.Project;

public class ProjectDAO extends AbstractDAO{
    private String sqlReadByCipher = "SELECT cipher, date, cost FROM team.project WHERE cipher = ?";
    private String sqlAddProject = "INSERT INTO team.project (cipher, date, cost) VALUES (?, ?, ?)";
    private String sqlDeleteProjectByCipher = "DELETE FROM team.project WHERE cipher = ?";
    private String sqlGetAllProjects = "SELECT cipher, date, cost FROM team.project WHERE cipher NOT IN ('SPARE')";

    public Project read(String cipher) throws DAOException {
        Project project = new Project();
        try {
            this.connection = getConnection();
            PreparedStatement stm = connection.prepareStatement(sqlReadByCipher);
            stm.setString(1, cipher);
            ResultSet rs = stm.executeQuery();
            rs.next();
            project.setCipher(rs.getString("cipher"));
            project.setDate(rs.getString("date"));
            project.setCost(rs.getDouble("cost"));
        } catch (SQLException e) {
            throw new DAOException("Problems with DAO request", e);
        } finally {
            try {
                super.close();
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
        return project;
    }

    public void add(Project project) throws DAOException {
        try {
            this.connection = getConnection();
            PreparedStatement stm = connection.prepareStatement(sqlAddProject);
            stm.setString(1, project.getCipher());
            stm.setString(2, project.getDate());
            stm.setDouble(3, project.getCost());
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Problems with DAO request", e);
        } finally {
            try {
                super.close();
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
    }

    public void delete(String cipher) throws DAOException {
        try {
            this.connection = getConnection();
            PreparedStatement stm = connection.prepareStatement(sqlDeleteProjectByCipher);
            stm.setString(1, cipher);
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Problems with DAO request", e);
        } finally {
            try {
                super.close();
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
    }

    public List<Project> getAll() throws DAOException {
        List<Project> list = new ArrayList<>();
        try {
            this.connection = getConnection();
            PreparedStatement stm = connection.prepareStatement(sqlGetAllProjects);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Project p = new Project();
                p.setCipher(rs.getString("cipher"));
                p.setDate(rs.getString("date"));
                p.setCost(rs.getDouble("cost"));
                list.add(p);
            }
        } catch (SQLException e) {
            throw new DAOException("Problem with DAO request", e);
        } finally {
            try {
                super.close();
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
        return list;
    }
}
