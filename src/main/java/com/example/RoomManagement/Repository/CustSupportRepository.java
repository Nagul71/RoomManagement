package com.example.RoomManagement.Repository;

import com.example.RoomManagement.Entity.CustSupport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustSupportRepository extends JpaRepository<CustSupport, String> {
    List<CustSupport> findByUserUserId(String userId);
}