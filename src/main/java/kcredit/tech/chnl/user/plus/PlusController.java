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

    private final PlusService plusService; // Service 를 통해서 Mapper 호출 (입출력 가공이 필요할 때 사용)

    private final PlusMapper plusMapper; // Controller 에서 Mapper 를 직접 호출할 때 사용 (기본제공하는 CRUD 직접 사용)

    @GetMapping("user/plus/insertUser")
    public void insertUser(Model model) {
        PlusUser user = (PlusUser) model.getAttribute("user");
        if (user == null) {
            user = new PlusUser()
                    .setName("몽난희 " + (int) (Math.ceil(Math.random() * 1000)))
                    .setGrade(LegacyUserGrade.A);
        }
        // baseMapper 에서 기본제공하는 함수가 매우 풍부하여 직접사용합니다. (Mapper 직접호출)
        // BaseMapper 에서 제공하는 기능을 꼭 확인하여 주시기 바랍니다. (예: insertOrUpdate)
        plusMapper.insert(user); // 성공하면 자동으로 user.no 에 채번한 시퀀스 값을 넣어 줍니다.
        model.addAttribute("user", user);
    }

    @GetMapping("user/plus/searchUserList")
    public void searchUserList(Model model) {

        LegacyPage legacyPage = (LegacyPage) model.getAttribute("page");
        LegacySearchUserListVO search = (LegacySearchUserListVO) model.getAttribute("searchUser");

        if (legacyPage == null) legacyPage = new LegacyPage();
        if (search == null) search = new LegacySearchUserListVO().setEndDate(new Date());

        Page<PlusUser> page1 = new Page<PlusUser>()
                .setSize(legacyPage.getLimit())
                .setCurrent(legacyPage.getOffset());


        // 쿼리 입출력을 조작하는 부분을 service로 구현하여 재활용
        Page<PlusUser> page2 = plusService.searchUserList(page1, search);

        // page1 과 page2 는 동일한 객체입니다.
        boolean samepage = page1 == page2; // true

        page1.getPages();
        // page1 전체를 넘깁니다. (화면참조)
        model.addAttribute("page1", page1);
    }

    @GetMapping("user/plus/searchUserListByXmlQuery")
    public void searchUserListByXmlQuery(Model model) {
        LegacySearchUserListVO search = (LegacySearchUserListVO) model.getAttribute("searchUser");

        // 복잡한 쿼리는 xml 로 작성하여 Mapper 직접 호출
        List<PlusUser> userList = plusMapper.searchUserListByXmlQuery(search);
        model.addAttribute("searchUserList", userList);
    }
}
