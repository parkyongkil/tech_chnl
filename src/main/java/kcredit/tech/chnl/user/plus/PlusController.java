package kcredit.tech.chnl.user.plus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import kcredit.tech.chnl.user.legacy.LegacySearchUserListVO;
import kcredit.tech.chnl.user.legacy.LegacyUserGrade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.thymeleaf.util.StringUtils;

import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PlusController {

    private final PlusMapper plusMapper; // Controller 에서 Mapper 를 직접 호출할 때 사용 (기본제공하는 CRUD 직접 사용)

    @GetMapping({"user/plus/insertUser", "user/plus/updateUser"})
    public String insertOrUpdateUpser(Model model, @ModelAttribute("user") PlusUser user) {

        if (user.getName() == null || user.getName().isBlank())
            user.setName("소피아 " + StringUtils.randomAlphanumeric(4)).setGrade(LegacyUserGrade.A);

        plusMapper.insertOrUpdate(user); // 성공하면 자동으로 user.no 에 채번한 시퀀스 값을 넣어 줍니다.

        model.addAttribute("user", user);

        return "user/plus/UserInfo";
    }

    @GetMapping("user/plus/deleteUser")
    public String deleteUser(Model model, @ModelAttribute("user") PlusUser user) {

        if (user.getNo() == null || user.getNo() < 1) {
            PlusUser minUser = plusMapper.selectMinUser();
            if (minUser != null) {
                plusMapper.deleteById(minUser.getNo());
                user = minUser;
            }
        }

        model.addAttribute("user", user);

        return "user/plus/UserInfo";
    }

    @GetMapping("user/plus/userInfo")
    public String userInfo(Model model, @ModelAttribute("user") PlusUser user) {

        PlusUser user2 = plusMapper.selectById(user.getNo());
        if (user2 != null) user = user2;

        model.addAttribute("user", user);

        return "user/plus/UserInfo";
    }

    @GetMapping("user/plus/searchUserList")
    public String searchUserList(Model model, @ModelAttribute("page") Page<PlusUser> page, @ModelAttribute("search") LegacySearchUserListVO search) {

        search.setName("");
        search.setEndDate(new Date());

        LambdaQueryWrapper<PlusUser> qry = new LambdaQueryWrapper<>();
        qry.orderByAsc(PlusUser::getNo);

        if (search.getNo() > 0) qry.eq(PlusUser::getNo, search.getNo());
        if (search.getName() != null && !search.getName().isBlank()) qry.like(PlusUser::getName, search.getName());
        if (search.getStartDate() != null) qry.ge(PlusUser::getRegDate, search.getStartDate());
        if (search.getEndDate() != null) qry.le(PlusUser::getRegDate, search.getEndDate());

        page = plusMapper.selectPage(page, qry);

        model.addAttribute("page", page);

        return "user/plus/UserInfoList";
    }

    @GetMapping("user/plus/searchUserListByXmlQuery")
    public String searchUserListByXmlQuery(Model model, @ModelAttribute("page") Page<PlusUser> page, @ModelAttribute("search") LegacySearchUserListVO search) {

        search.setEndDate(new Date());

        List<PlusUser> userList = plusMapper.searchUserListByXmlQuery(page, search);

        model.addAttribute("searchUserList", userList);

        return "user/plus/UserInfoList";
    }
}
