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
@CrossOrigin(origins = "*")
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
    @ResponseBody
    public String reportMarPro (HttpServletResponse response, HttpServletRequest request, Principal principal){

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
            reportService.reportMarInsert(report);
            System.out.println("성공");
            return "success";
        } catch (Exception e) {
            System.out.println("실패");
            return "error";
        }

    }

    @PostMapping("report/reportReqPro")
    @ResponseBody
    public String reportReqPro (@RequestParam("reqNo") int reqNo,@RequestParam("reporter") String reporter, @RequestParam("reason") String reason, Principal principal){

        String sid = principal != null ? principal.getName() : "";

        System.out.println(reqNo);
        System.out.println(reporter);
        System.out.println(reason);
        System.out.println(sid);

        Report report = new Report();
        report.setReporter(reporter);
        report.setLoginId(sid);
        report.setReason(reason);
        report.setReqNo(reqNo);

        boolean result = false;

        try {
            reportService.reportReqInsert(report);
            System.out.println("성공");
            result=true;
            //return result;

        } catch (Exception e) {
            System.out.println("실패");
            result=false;
            //return result;
        }
        return "return";
    }


}
