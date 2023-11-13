package com.tsherpa.team35.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminCtrl {

    @GetMapping("/admin/dashboard")
    public String getDashboard() {
        return "admin/adminDashboard";
    }
}
