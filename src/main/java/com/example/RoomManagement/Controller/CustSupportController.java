package com.example.RoomManagement.Controller;

import com.example.RoomManagement.DTO.SupportRequestDTO;
import com.example.RoomManagement.Entity.CustSupport;
import com.example.RoomManagement.Service.CustSupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/support")
@CrossOrigin(origins = "http://localhost:5173")
public class CustSupportController {

    @Autowired
    private CustSupportService custSupportService;

    @PostMapping("/addrequest")
    public ResponseEntity<CustSupport> addSupportRequest(@RequestBody SupportRequestDTO requestDTO) {
        CustSupport supportRequest = custSupportService.addSupportRequest(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(supportRequest);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CustSupport>> getUserSupportRequests(@PathVariable String userId) {
        List<CustSupport> requests = custSupportService.getSupportRequestsByUser(userId);
        return ResponseEntity.ok(requests);
    }

    @GetMapping("/{requestId}")
    public ResponseEntity<CustSupport> getSupportRequest(@PathVariable String requestId) {
        CustSupport request = custSupportService.getSupportRequestById(requestId);
        return ResponseEntity.ok(request);
    }

    @PatchMapping("/{requestId}/status")
    public ResponseEntity<CustSupport> updateRequestStatus(
            @PathVariable String requestId,
            @RequestParam String status) {
        CustSupport request = custSupportService.updateRequestStatus(requestId, status);
        return ResponseEntity.ok(request);
    }

    @PatchMapping("/{requestId}/response")
    public ResponseEntity<CustSupport> addResponseToRequest(
            @PathVariable String requestId,
            @RequestParam String response) {
        CustSupport request = custSupportService.addResponseToRequest(requestId, response);
        return ResponseEntity.ok(request);
    }
}