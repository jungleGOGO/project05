package com.tsherpa.team35.ctrl;

import com.tsherpa.team35.biz.MarketService;
import com.tsherpa.team35.biz.ReportService;
import com.tsherpa.team35.entity.Market;
import com.tsherpa.team35.entity.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Controller
public class ReportCtrl {

    @Autowired
    private ReportService reportService;

    @GetMapping("/report/getReportMar")
    public String getReportMarForm (HttpServletRequest request, Principal principal, Model model) {


        int marketNo = Integer.parseInt(request.getParameter("marketNo"));
        model.addAttribute("marketNo", marketNo);

        String reporter = request.getParameter("reporter");
        model.addAttribute("reporter", reporter);

        return "report/reportMarInsert";
    }

    @GetMapping("/report/getReportReq")
    public String getReportReqForm (@RequestParam("reqNo")int reqNo, @RequestParam("reporter") String reporter, Principal principal, Model model) {

        model.addAttribute("reqNo", reqNo);
        model.addAttribute("reporter", reporter);

        return "report/reportReqInsert";
    }

    @PostMapping("report/reportMarPro")
    public String reportMarPro (HttpServletRequest request, Principal principal){

        String sid = principal != null ? principal.getName() : "";
        int marketNo = Integer.parseInt(request.getParameter("marketNo"));
        String reporter = request.getParameter("reporter");
        String reason = request.getParameter("reason");
        String title=request.getParameter("title");

        Report report = new Report();
        report.setReporter(reporter);
        report.setLoginId(sid);
        report.setTitle(title);
        report.setReason(reason);
        report.setMarketNo(marketNo);

        reportService.reportMarInsert(report);

        return "report/reportSuc";
    }

    @PostMapping("report/reportReqPro")
    public String reportReqPro (HttpServletRequest request, Principal principal){

        String sid = principal != null ? principal.getName() : "";
        int reqNo = Integer.parseInt(request.getParameter("reqNo"));
        String reporter = request.getParameter("reporter");
        String reason = request.getParameter("reason");
        String title=request.getParameter("title");

        Report report = new Report();
        report.setReporter(reporter);
        report.setLoginId(sid);
        report.setTitle(title);
        report.setReason(reason);
        report.setReqNo(reqNo);

        reportService.reportReqInsert(report);

        return "report/reportSuc";
    }

    @GetMapping("/report/reportSuc")
    public String reportSuc(){
        return "report/reportSuc";
    }

}
