package com.example.demo.HR.Controller;

import com.example.demo.HR.Model.LeaveRequest;
import com.example.demo.HR.Repo.LeaveRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leave-request")
public class LeaveRequestController {

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;


    @PostMapping
    public String submitLeave(@RequestBody LeaveRequest request) {
        request.setStatus("Pending");
        int rows = leaveRequestRepository.save(request);
        return rows == 1 ? "Leave request submitted!" : "Failed to submit leave request.";
    }

    @PutMapping("/{id}/status")
    public String updateStatus(@PathVariable Long id, @RequestBody String status) {
        int rows = leaveRequestRepository.updateStatus(id, status.replace("\"", ""));
        return rows == 1 ? "Status updated" : "Failed to update status";
    }



    @GetMapping
    public List<LeaveRequest> getAllLeaves() {
        return leaveRequestRepository.findAll();
    }
}
