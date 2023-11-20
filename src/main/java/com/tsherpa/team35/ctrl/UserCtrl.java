package com.tsherpa.team35.ctrl;

import com.tsherpa.team35.biz.UserService;
import com.tsherpa.team35.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class UserCtrl {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/login")
    public ModelAndView login(@RequestParam(value = "exception", required = false) String exception){

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("exception", exception);
        modelAndView.setViewName("user/login");
        return modelAndView;
    }

    @GetMapping("/join")
    public ModelAndView join(){

        ModelAndView modelAndView = new ModelAndView();
        User user = new User();

        modelAndView.addObject("user", user);
        modelAndView.setViewName("user/join");
        return modelAndView;
    }

    @PostMapping("/join")
    public ModelAndView joinPro(User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.getUserByLoginId(user.getLoginId());
        if (userExists != null) {
            bindingResult
                    .rejectValue("loginId", "error.loginId","사용이 불가한 아이디입니다.");
        }

        if(!user.getPassword().equals(user.getPasswordConfirm())) {
            bindingResult
                    .rejectValue("password", "error.password","비밀번호와 비밀번호 확인이 다릅니다.");
        }

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("/user/join");
        } else {
            int cnt = userService.saveUser(user);
            if(cnt == 1) {
                modelAndView.setViewName("redirect:/");
            } else {
                modelAndView.setViewName("/user/join");
            }
        }

        return modelAndView;
    }

    //마이페이지
    @GetMapping("/mypage")
    public String mypage(Model model,Principal principal) {
        String sid = principal != null ? principal.getName() : "";
        User user = userService.getUserByLoginId(sid);

        model.addAttribute("user",user);
        return "user/mypage";
    }


    //회원 정보 수정
    @GetMapping("/userEdit")
    public String userEdit(Model model, Principal principal) {
        String sid = principal != null ? principal.getName() : "";

        System.out.println("따란");

        User user = userService.getUserByLoginId(sid);
        model.addAttribute("user",user);
        System.out.println("user >>> " + user.toString());

        return "user/userEdit";
    }
    //회원 정보 수정 처리
    @PostMapping("/userEdit")
    public String userEditPro(User user,Model model){
        userService.userEdit(user);

        model.addAttribute("msg","회원 정보가 수정되었습니다.");
        return "user/userEdit";
    }

    //비밀번호 변경
    @GetMapping("/pwEdit")
    public String pwEdit(Model model, Principal principal) {
        String sid = principal != null ? principal.getName() : "";
        User user = new User();
        model.addAttribute("user",user);

        return "user/pwEdit";
    }

    //비밀번호 변경 처리
    @PostMapping("/pwEdit")
    public String pwEditPro(User user, BindingResult bindingResult,Principal principal,Model model) {
        String sid = principal != null ? principal.getName() : "";
        User userInfo = userService.getUserByLoginId(sid);
        user.setLoginId(sid);

        boolean chkEditPw = false;
        //변경할 비밀번호 일치 여부 확인
        if(!user.getPassword().equals(user.getPasswordConfirm())) {
            bindingResult
                    .rejectValue("password", "error.password","비밀번호와 비밀번호 확인이 다릅니다.");
        } else {
            chkEditPw = true;
        }

        //현재 비밀번호 일치여부 확인
        boolean chk = bCryptPasswordEncoder.matches(user.getNowPassword(),userInfo.getPassword());
//        System.out.println("user.getNowPassword() " + user.getNowPassword());
//        System.out.println("userInfo.getPassword() " + userInfo.getPassword());
//        System.out.println("chk : "+chk);
//        System.out.println("chkEditPw : "+chkEditPw);

        //변경할 비밀번호로 업데이트
        if (chk && chkEditPw){
            userService.pwEdit(user);
            model.addAttribute("msg","비밀번호가 변경되었습니다.");
        } else {
            model.addAttribute("msg","입력 값이 잘못되었습니다.");
        }

        return "user/pwEdit";
    }
}