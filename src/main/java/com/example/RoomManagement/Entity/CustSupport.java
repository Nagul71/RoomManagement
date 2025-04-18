package com.example.RoomManagement.Entity;
import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import jakarta.persistence.*;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@Entity
@Table(name = "cust_support")
public class CustSupport {

    @Id
    @Column(name = "req_id", nullable = false, unique = true)
    private String reqId;

    @Column(name = "issue_date")
    private LocalDateTime issueDate;

    @Column(name = "status")
    private String status;

    @Column(name = "issue_desc")
    private String issueDesc;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @Column(name = "cus_response")
    private String cusResponse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @PrePersist
    public void generateId() {
        this.timestamp = LocalDateTime.now();

        SecureRandom random = new SecureRandom();
        char[] alphabet = NanoIdUtils.DEFAULT_ALPHABET;
        int size = 8;

        this.reqId = "req_" + NanoIdUtils.randomNanoId(random, alphabet, size);
    }

    public CustSupport() {}

    public CustSupport(String reqId, LocalDateTime issueDate, String status, String issueDesc, LocalDateTime timestamp, String cusResponse, User user) {
        this.reqId = reqId;
        this.issueDate = issueDate;
        this.status = status;
        this.issueDesc = issueDesc;
        this.timestamp = timestamp;
        this.cusResponse = cusResponse;
        this.user = user;
    }

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public LocalDateTime getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDateTime issueDate) {
        this.issueDate = issueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIssueDesc() {
        return issueDesc;
    }

    public void setIssueDesc(String issueDesc) {
        this.issueDesc = issueDesc;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getCusResponse() {
        return cusResponse;
    }

    public void setCusResponse(String cusResponse) {
        this.cusResponse = cusResponse;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
