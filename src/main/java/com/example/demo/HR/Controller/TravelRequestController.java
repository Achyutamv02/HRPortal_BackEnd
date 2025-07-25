package com.example.demo.HR.Controller;


import com.example.demo.HR.Model.TravelRequest;
import com.example.demo.HR.Repo.TravelRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/travel-request")
public class TravelRequestController {

    @Autowired
    private TravelRequestRepository repository;

    @PostMapping
    public String submitRequest(@RequestBody TravelRequest request) {
        request.setStatus("Pending");
        int rows = repository.save(request);
        return rows == 1 ? "Travel request submitted!" : "Failed to submit travel request.";
    }

    @GetMapping
    public List<TravelRequest> getAllRequests() {
        return repository.findAll();
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @RequestBody Map<String, String> body) {
        String newStatus = body.get("status");
        int rows = repository.updateStatus(id, newStatus);
        if (rows == 1) {
            return ResponseEntity.ok("Status updated");
        } else {
            return ResponseEntity.status(404).body("Travel request not found");
        }
    }



}
