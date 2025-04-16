package com.example.RoomManagement.Controller;
import com.example.RoomManagement.Service.CustSupportService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/support")
@CrossOrigin(origins = "http://localhost:5173")
public class CustSupportController {

    private CustSupportService custService;

    @PostMapping("/addrequest")
    public String addrequest()
    {
        custService.addrequest();
        return "your request has sent";
    }
}
