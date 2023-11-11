package com.tsherpa.team35.ctrl;

import com.tsherpa.team35.biz.QnaService;
import com.tsherpa.team35.entity.Qna;
import com.tsherpa.team35.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class QnaCtrl {

    @Autowired
    private QnaService qnaService;

    @GetMapping("/qna/list")
    public String getList(HttpServletRequest request, Model model){
        int curPage = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;

        Page page = new Page();

        int total = qnaService.getCount(page);
        page.makeBlock(curPage, total);
        page.makeLastPageNum(total);
        page.makePostStart(curPage, total);

        List<Qna> list = qnaService.getList(page);
        model.addAttribute("list", list);
        model.addAttribute("curPage", curPage);
        model.addAttribute("page", page);

        return "qna/qnaList";
    }
}
