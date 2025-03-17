package kcredit.tech.chnl.user.legacy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class LegacyController {

    // @Autowrired 대신에 @RequiredArgsConstructor 를 사용합니다. 반드시 private final 이어야 합니다.
    private LegacyMapper mapper;

    @GetMapping("user/legacy/Hello")
    public String hello(Model model) {
        model.addAttribute("greeting", "Hello World");
        return "user/legacy/Hello";
    }

    @PostMapping("user/legacy/InsertUser")
    public String insertUpser(Model model, @ModelAttribute("user") LegacyUser user) {
        long no = mapper.nextvalSeqUserNo();
        user.setNo(no);
        mapper.insertUser(user);
        model.addAttribute("user", user);
        return "user/legacy/User";
    }

    @PostMapping("user/legacy/UpdateUser")
    public String updateUpser(Model model, @ModelAttribute("user") LegacyUser user) {
        mapper.updateUser(user);
        model.addAttribute("user", user);
        return "user/legacy/User";
    }

    @GetMapping("user/legacy/DeleteUser/{no}")
    public String deleteUpser(Model model, @PathVariable("no") long no) {
        mapper.deleteUser(no);
        return "user/legacy/UserList";
    }

    @GetMapping("user/legacy/SelectUser/{no}")
    public String selectUser(Model model, @PathVariable("no") long no) {
        LegacyUser user = mapper.selectUser(no);
        model.addAttribute("user", user);
        return "user/legacy/User";
    }

    @GetMapping("user/legacy/searchUserList")
    public String searchUserList(Model model, @ModelAttribute("search") LegacySearchUser search, @ModelAttribute("page") LegacyPage page) {
        List<LegacyUser> userList = mapper.searchUserList(search, page);
        model.addAttribute("userList", userList);
        return "user/legacy/UserList";
    }
}
