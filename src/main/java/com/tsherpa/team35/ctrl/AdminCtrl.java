package com.tsherpa.team35.ctrl;

import com.tsherpa.team35.biz.NoticeService;
import com.tsherpa.team35.biz.QnaService;
import com.tsherpa.team35.entity.Notice;
import com.tsherpa.team35.entity.Qna;
import com.tsherpa.team35.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AdminCtrl {

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private QnaService qnaService;

    @GetMapping("/admin/dashboard")
    public String getDashboard() {
        return "admin/adminDashboard";
    }

    @GetMapping("/admin/noticeAdmin")
    public String getNoticeList(HttpServletRequest request, Model model){
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

        return "admin/noticeMgmt";
    }

    @GetMapping("/admin/noticeInsert")
    public String NoticeInsertForm(Notice notice, Model model) {
        return "admin/noticeInsert";
    }

    @PostMapping("/admin/noticeInsert")
    public String NoticeInsertPro(Notice param) {
        noticeService.noticeInsert(param);
        return "redirect:/admin/noticeAdmin";
    }

    @GetMapping("/admin/noticeUpdate")
    public String noticeUpdateForm(@RequestParam("no") int no, Model model){
        Notice notice = noticeService.getNotice(no);
        model.addAttribute("notice", notice);
        return "/admin/noticeUpdate";
    }

    @PostMapping("/admin/noticeUpdate")
    public String noticeUpdate(Notice param, Model model){
        noticeService.noticeUpdate(param);
        return "redirect:/admin/noticeAdmin";
    }

    @GetMapping("/admin/noticeDelete")
    public String noticeDelete(@RequestParam("no") int no, Model model){
        noticeService.noticeDelete(no);
        return "redirect:/admin/noticeAdmin";
    }

    @GetMapping("/admin/questionList")
    public String questionList(HttpServletRequest request, Model model){
        //Page
        int curPage = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
        Page page = new Page();
        int total = qnaService.noAnswerCount(page);

        page.makeBlock(curPage, total);
        page.makeLastPageNum(total);
        page.makePostStart(curPage, total);
        model.addAttribute("curPage", curPage);     // 현재 페이지
        model.addAttribute("page", page);           // 페이징 데이터

        //QnaList
        List<Qna> noAnswerList = qnaService.noAnswerList(page);
        model.addAttribute("noAnswerList", noAnswerList);     //QnA 목록
        return "/admin/qnaMgmt";
    }

    @GetMapping("/qna/answerInsert")
    public String getAnswerInsert(HttpServletRequest request, Model model) throws Exception {
        int qno = Integer.parseInt(request.getParameter("qno"));
        Qna qna = qnaService.qnaDetail(qno);
        model.addAttribute("qna", qna);
        return "/qna/answerInsert";
    }


    @PostMapping("/qna/answerInsert")
    public String getAnswerInsertPro(Qna qna, HttpServletRequest request, Model model) throws Exception {
        qnaService.answerInsert(qna);
        return "redirect:/qna/list";
    }


}
