package com.example.demo.HR.Repo;

import com.example.demo.HR.Model.TravelRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TravelRequestRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Save method
    public int save(TravelRequest request) {
        String sql = "INSERT INTO travel_requests " +
                "(name, travel_location, reason, travel_type, status, start_date, end_date, amount) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(
                sql,
                request.getName(),
                request.getTravel_location(),
                request.getReason(),
                request.getTravel_type(),
                request.getStatus(),
                request.getStart_date(),
                request.getEnd_date(),
                request.getAmount()
        );
    }

    // Find all method
    public List<TravelRequest> findAll() {
        String sql = "SELECT * FROM travel_requests";
        return jdbcTemplate.query(sql, new TravelRequestRowMapper());
    }

    public int updateStatus(Long id, String status) {
        String sql = "UPDATE travel_requests SET status = ? WHERE id = ?";
        return jdbcTemplate.update(sql, status, id);
    }



    // RowMapper inner class
    private static class TravelRequestRowMapper implements RowMapper<TravelRequest> {
        @Override
        public TravelRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
            TravelRequest req = new TravelRequest();
            req.setId(rs.getLong("id"));
            req.setName(rs.getString("name"));
            req.setTravel_location(rs.getString("travel_location"));
            req.setReason(rs.getString("reason"));
            req.setTravel_type(rs.getString("travel_type"));
            req.setStatus(rs.getString("status"));
            req.setStart_date(rs.getDate("start_date")); // New
            req.setEnd_date(rs.getDate("end_date"));     // New
            req.setAmount(rs.getDouble("amount"));

            return req;
        }
    }

}
