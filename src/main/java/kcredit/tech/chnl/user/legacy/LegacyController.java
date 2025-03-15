package kcredit.tech.chnl.user.legacy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class LegacyController {

    // @Autowrired 대신에 @RequiredArgsConstructor 를 사용합니다. 반드시 private final 이어야 합니다.
    private final LegacyService legacyService;

    @GetMapping("user/legacy/hello")
    public void hello(Model model) {
        model.addAttribute("greeting", "Hello World");
    }

    @GetMapping("user/legacy/selectAllUserList")
    public void selectAllUserList(Model model) {
        List<LegacyUser> selectAllUserList = legacyService.selectAllUserList();
        model.addAttribute("selectAllUserList", selectAllUserList);
    }

    @GetMapping("user/legacy/searchUserList")
    public void searchUserList(Model model) {
        LegacySearchUserListVO legacySearchUserListVO = new LegacySearchUserListVO();
        Date startDate = new Date();
        Date endDate = new Date();
        startDate.setTime(startDate.getTime() - 86400000L * 90); // before 90 days
        legacySearchUserListVO.setStartDate(startDate);
        legacySearchUserListVO.setEndDate(endDate);

        // legacySearchUserListVO.setName("옙");
        legacySearchUserListVO.setGrade(LegacyUserGrade.A);

        LegacyPage legacyPage = new LegacyPage();
        legacyPage.setLimit(10);

        List<LegacyUser> searchUserList = legacyService.searchUserList(legacySearchUserListVO, legacyPage);
        model.addAttribute("searchUserList", searchUserList);
    }
}
