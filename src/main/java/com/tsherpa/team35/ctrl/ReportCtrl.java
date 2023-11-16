package com.tsherpa.team35.ctrl;

import com.tsherpa.team35.biz.MarketService;
import com.tsherpa.team35.biz.ReportService;
import com.tsherpa.team35.biz.RequestService;
import com.tsherpa.team35.entity.Market;
import com.tsherpa.team35.entity.Report;
import com.tsherpa.team35.entity.Request;
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

    @Autowired
    private RequestService requestService;

    @Autowired
    private MarketService marketService;

    @GetMapping("/report/getReportMar")
    public String getReportMarForm (@RequestParam("marketNo")int marketNo, Principal principal, Model model) {


//        Market mar = marketService.marketDetail(marketNo);

        model.addAttribute("marketNo", marketNo);
//        model.addAttribute("mar", mar);

        return "report/reportMarInsert";
    }

    @GetMapping("/report/getReportReq")
    public String getReportReqForm (@RequestParam("reqNo")int reqNo, Principal principal, Model model) throws Exception {

        Request req =requestService.requestDetail(reqNo);

        model.addAttribute("reqNo", reqNo);
        model.addAttribute("req", req);

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

        int chk1 = reportService.reportchkMar(report);

        if (chk1 == 0) {
            reportService.reportMarInsert(report);
            return "report/reportSuc";
        } else {
            return "report/reportF";
        }

    }

    @PostMapping("report/reportReqPro")
    public String reportReqPro (HttpServletRequest request, Principal principal){

        String sid = principal != null ? principal.getName() : "";
        int reqNo = Integer.parseInt(request.getParameter("reqNo"));
        String reporter = request.getParameter("reporter");
        String reason = request.getParameter("reason");
        String title = request.getParameter("title");

        Report report = new Report();
        report.setReporter(reporter);
        report.setLoginId(sid);
        report.setTitle(title);
        report.setReason(reason);
        report.setReqNo(reqNo);

        int chk1 = reportService.reportchkReq(report);
        if (chk1 == 0) {
            reportService.reportReqInsert(report);
            return "report/reportSuc";
        }else {
            return "report/reportF";
        }

    }

    @GetMapping("/report/reportSuc")
    public String reportSuc(){
        return "report/reportSuc";
    }

}
