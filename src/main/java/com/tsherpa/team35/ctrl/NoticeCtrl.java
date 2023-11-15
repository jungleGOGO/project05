package com.tsherpa.team35.ctrl;

import com.tsherpa.team35.biz.NoticeService;
import com.tsherpa.team35.entity.Notice;
import com.tsherpa.team35.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class NoticeCtrl {

    @Autowired
    private NoticeService noticeService;

    @GetMapping("/notice/list")
    public String NoticeList(HttpServletRequest request, Model model) {

        int curPage = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;

        Page page = new Page();

        int total = noticeService.getCount(page);
        page.makeBlock(curPage, total);
        page.makeLastPageNum(total);
        page.makePostStart(curPage, total);


        List<Notice> list = noticeService.getList(page);
        model.addAttribute("list", list);
        model.addAttribute("curPage", curPage);
        model.addAttribute("page", page);
        return "notice/noticeList";
    }

    @GetMapping("/notice/detail")
    public String NoticeDetail (@RequestParam("no") int no, Model model) {

        Notice notice = noticeService.getNotice(no);
        noticeService.cntUpdate(no);
        model.addAttribute("notice", notice);

        return "notice/noticeDetail";
    }


}
