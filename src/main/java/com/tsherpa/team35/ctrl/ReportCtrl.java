package com.tsherpa.team35.ctrl;

import com.tsherpa.team35.biz.MarketService;
import com.tsherpa.team35.biz.ReportService;
import com.tsherpa.team35.entity.Market;
import com.tsherpa.team35.entity.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Controller
public class ReportCtrl {

    @Autowired
    private ReportService reportService;

    @GetMapping("/report/getReport")
    public String getReportForm (HttpServletRequest request, Principal principal, Model model) {


        int marketNo = Integer.parseInt(request.getParameter("marketNo"));
        model.addAttribute("marketNo", marketNo);

        String reporter = request.getParameter("reporter");
        model.addAttribute("reporter", reporter);

        return "report/ReportInsert";
    }

    @PostMapping("report/reportPro")
    @ResponseBody
    public String reportPro (HttpServletResponse response, HttpServletRequest request, Principal principal){

        String sid = principal != null ? principal.getName() : "";
        int marketNo = Integer.parseInt(request.getParameter("marketNo"));
        String reporter = request.getParameter("reporter");
        String reason = request.getParameter("reason");

        Report report = new Report();
        report.setReporter(reporter);
        report.setLoginId(sid);
        report.setReason(reason);
        report.setMarketNo(marketNo);

        try {
            reportService.reportInsert(report);
            System.out.println("성공");
            return "success";
        } catch (Exception e) {
            System.out.println("실패");
            return "error";
        }

    }


}
