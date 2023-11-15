package com.tsherpa.team35.ctrl;

import com.tsherpa.team35.biz.MarketService;
import com.tsherpa.team35.biz.ReportService;
import com.tsherpa.team35.entity.Market;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class ReportCtrl {

    @Autowired
    private ReportService reportService;

    @Autowired
    private MarketService marketService;

    @GetMapping("/report/getReport")
    public String getReportForm (HttpServletRequest request, Principal principal, Model model) {

        String sid = principal != null ? principal.getName() : "";
        model.addAttribute("sid", sid);

        int marketNo = Integer.parseInt(request.getParameter("marketNo"));
        model.addAttribute("marketNo", marketNo);

        //Market market = marketService.getMarket(marketNo);
        //model.addAttribute("market", market);

        return "report/ReportInsert";
    }


}
