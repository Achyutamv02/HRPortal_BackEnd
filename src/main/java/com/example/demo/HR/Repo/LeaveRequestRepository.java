package com.example.demo.HR.Repo;

import com.example.demo.HR.Model.LeaveRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class LeaveRequestRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int save(LeaveRequest request) {
        String sql = "INSERT INTO leave_requests (name, reason, start_date, end_date, status) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                request.getName(),
                request.getReason(),
                request.getStart_date(),
                request.getEnd_date(),
                request.getStatus()
        );
    }

    public int updateStatus(Long id, String status) {
        String sql = "UPDATE leave_requests SET status = ? WHERE id = ?";
        return jdbcTemplate.update(sql, status, id);
    }


    public List<LeaveRequest> findAll() {
        String sql = "SELECT * FROM leave_requests";
        return jdbcTemplate.query(sql, new LeaveRequestRowMapper());
    }

    private static class LeaveRequestRowMapper implements RowMapper<LeaveRequest> {
        @Override
        public LeaveRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
            LeaveRequest req = new LeaveRequest();
            req.setId(rs.getLong("id"));
            req.setName(rs.getString("name"));
            req.setReason(rs.getString("reason"));
            req.setStart_date(rs.getDate("start_date"));
            req.setEnd_date(rs.getDate("end_date"));
            req.setStatus(rs.getString("status"));

            return req;
        }
    }
}
