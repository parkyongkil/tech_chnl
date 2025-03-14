package kcredit.tech.chnl.user.plus;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import kcredit.tech.chnl.user.legacy.LegacyPage;
import kcredit.tech.chnl.user.legacy.LegacySearchUserListVO;
import kcredit.tech.chnl.user.legacy.LegacyUserGrade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PlusController {

    private final PlusService plusService; // Service 를 통해서 Mapper 호출 (번거로움)

    private final PlusMapper plusMapper; // Controller 에서 Mapper 를 직접 호출할 때 사용 (비추천)

    @GetMapping("user/plus/insertUser")
    public void insertUser(Model model) {
        PlusUser user = (PlusUser) model.getAttribute("user");
        if (user == null) {
            user = new PlusUser();
            user.setName("몽난희" + Math.ceil(Math.random() * 1000));
            user.setGrade(LegacyUserGrade.A);
            user.setRegDate(new Date());
        }
        plusService.insertUser(user);
    }

    @GetMapping("user/plus/searchUserList")
    public void searchUserList(Model model) {

        LegacyPage legacyPage = (LegacyPage) model.getAttribute("page");
        LegacySearchUserListVO search = (LegacySearchUserListVO) model.getAttribute("searchUser");

        if (legacyPage == null) legacyPage = new LegacyPage();
        if (search == null) search = new LegacySearchUserListVO();

        Page<PlusUser> page1 = new Page<>();
        page1.setSize(legacyPage.getLimit());
        page1.setCurrent(legacyPage.getOffset());

        search.setEndDate(new Date());

        Page<PlusUser> page2 = plusService.searchUserList(page1, search);

        boolean samepage = page1.equals(page2);
        System.out.println("samepage: " + samepage);

        List<PlusUser> searchUserList = page2.getRecords();
        model.addAttribute("searchUserList", searchUserList);
    }

    @GetMapping("user/plus/searchUserListByXmlQuery")
    public void searchUserListByXmlQuery(Model model) {
        LegacySearchUserListVO search = (LegacySearchUserListVO) model.getAttribute("searchUser");
        List<PlusUser> userList = plusMapper.searchUserListByXmlQuery(search);
        model.addAttribute("searchUserList", userList);
    }
}
