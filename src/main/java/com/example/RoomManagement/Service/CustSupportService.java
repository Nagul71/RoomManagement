package com.example.RoomManagement.Service;

import com.example.RoomManagement.DTO.SupportRequestDTO;
import com.example.RoomManagement.Entity.CustSupport;
import com.example.RoomManagement.Entity.User;
import com.example.RoomManagement.Repository.CustSupportRepository;
import com.example.RoomManagement.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CustSupportService {

    @Autowired
    private CustSupportRepository custSupportRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public CustSupport addSupportRequest(SupportRequestDTO requestDTO) {
        User user = userRepository.findById(requestDTO.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        CustSupport supportRequest = new CustSupport();
        supportRequest.setIssueDesc(requestDTO.getIssueDesc());
        supportRequest.setStatus("OPEN");
        supportRequest.setIssueDate(LocalDateTime.now());
        supportRequest.setTimestamp(LocalDateTime.now());
        supportRequest.setUser(user);

        return custSupportRepository.save(supportRequest);
    }

    @Transactional(readOnly = true)
    public List<CustSupport> getSupportRequestsByUser(String userId) {
        return custSupportRepository.findByUserUserId(userId);
    }

    @Transactional(readOnly = true)
    public CustSupport getSupportRequestById(String requestId) {
        return custSupportRepository.findById(requestId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Support request not found"));
    }

    @Transactional
    public CustSupport updateRequestStatus(String requestId, String status) {
        CustSupport request = getSupportRequestById(requestId);
        request.setStatus(status);
        return custSupportRepository.save(request);
    }

    @Transactional
    public CustSupport addResponseToRequest(String requestId, String response) {
        CustSupport request = getSupportRequestById(requestId);
        request.setCusResponse(response);
        return custSupportRepository.save(request);
    }
}