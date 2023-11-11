package com.tsherpa.team35.ctrl;

import com.tsherpa.team35.biz.QnaService;
import com.tsherpa.team35.entity.Qna;
import com.tsherpa.team35.entity.User;
import com.tsherpa.team35.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/qna/detail")
    public String getQna(@RequestParam("qno")int qno, Model model){

        Qna detail = qnaService.qnaDetail(qno);
        model.addAttribute("detail", detail);
        
        return "qna/qnaDetail";
    }

    @GetMapping("/qna/insert")
    public ModelAndView qnaInsert(){

        ModelAndView modelAndView = new ModelAndView();
        Qna qna = new Qna();

        modelAndView.addObject("qna", qna);
        modelAndView.setViewName("qna/qnaInsert");
        return modelAndView;
    }

    @PostMapping("/qna/insert")
    public ModelAndView qnaInsertPro(Qna qna, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        qnaService.questionInsert(qna);
        qnaService.parUpdate(qna);
        modelAndView.setViewName("redirect:/qna/list");

        return modelAndView;
    }


    @GetMapping("/qna/edit")
    public String qnaUpdateForm(@RequestParam("qno") int qno, Model model){
        Qna qna = qnaService.qnaDetail(qno);
        model.addAttribute("qna", qna);

        return "qna/qnaEdit";
    }

    @PostMapping("/qna/edit")
    public String getQnaEditPro(HttpServletRequest request) throws Exception {

        int qno = Integer.parseInt(request.getParameter("qno"));
        Qna dto = new Qna();
        dto.setQno(qno);
        dto.setTitle(request.getParameter("title"));
        dto.setContent(request.getParameter("content"));
        qnaService.qnaEdit(dto);
        return "redirect:detail?qno=" + qno + "&page=1";

    }

    @GetMapping("/qna/delete")
    public String getQnaDelete(HttpServletRequest request, Model model) throws Exception {
        int qno = Integer.parseInt(request.getParameter("qno"));
        qnaService.qnaDelete(qno);
        return "redirect:list";
    }

}
