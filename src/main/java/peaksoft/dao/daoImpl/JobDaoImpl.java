package peaksoft.dao.daoImpl;

import peaksoft.config.Configuration;
import peaksoft.dao.JobDao;
import peaksoft.model.Job;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobDaoImpl implements JobDao {
    public void createJobTable() {
        String sql = "create table jobs (" +
                "id serial primary key," +
                "position varchar," +
                "profession varchar," +
                "description varchar," +
                "experience int );";
        try (Connection connection = Configuration.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Successfully created!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addJob(Job job) {
        String sql = "insert into jobs (position,profession, description,experience)" +
                "values(?,?,?,?);";
        try (Connection connection = Configuration.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, job.getPosition());
            statement.setString(2, job.getProfession());
            statement.setString(3, job.getDescription());
            statement.setInt(4, job.getExperience());
            statement.executeUpdate();
            System.out.printf("Job with position %s ", job.getPosition());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public Job getJobById(Long jobId) {

        Job job = null;
        String sql = " select * from jobs where id = ?;";
        try (Connection connection = Configuration.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, jobId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                job = new Job(result.getLong("id"),
                        result.getString("position"),
                        result.getString("profession"),
                        result.getString("description"),
                        result.getInt("experience"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return job;
    }

    public List<Job> sortByExperience(String ascOrDesc) {
        List<Job> jobs = new ArrayList<>();
        String sql = " select * from jobs order by experience " + ascOrDesc;
        try (Connection connection = Configuration.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                jobs.add(new Job(result.getLong("id"),
                        result.getString("position"),
                        result.getString("profession"),
                        result.getString("description"),
                        result.getInt("experience")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return jobs;
    }

    public Job getJobByEmployeeId(Long employeeId) {
        Job job = null;
        String sql = "select * from jobs join employees e on jobs.id = e.job_id where e.id = ?;";
        try (Connection connection = Configuration.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, employeeId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                job = new Job(
                        result.getLong("id"),
                        result.getString("position"),
                        result.getString("profession"),
                        result.getString("description"),
                        result.getInt("experience")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return job;
    }

    public void deleteDescriptionColumn() {
        String sql = "alter table jobs drop column description;";
        try (Connection connection = Configuration.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Successfully deleted...");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
