package kcredit.tech.chnl.user.plus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import kcredit.tech.chnl.user.legacy.LegacySearchUserListVO;
import kcredit.tech.chnl.user.legacy.LegacyUserGrade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

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
    public void searchUserList(Model model, @ModelAttribute("search") LegacySearchUserListVO search, @ModelAttribute("page") Page<PlusUser> page) {

        if (search == null) search = new LegacySearchUserListVO().setEndDate(new Date());
        if (page == null) page = new Page<PlusUser>().addOrder(OrderItem.asc("name"));

        LambdaQueryWrapper<PlusUser> wrapper = Wrappers.<PlusUser>lambdaQuery();
        if (search.getNo() > 0) wrapper.eq(PlusUser::getNo, search.getNo());
        if (search.getName() != null) wrapper.like(PlusUser::getName, search.getName());
        if (search.getGrade() != null) wrapper.eq(PlusUser::getGrade, search.getGrade());
        if (search.getStartDate() != null) wrapper.ge(PlusUser::getRegDate, search.getStartDate());
        if (search.getEndDate() != null) wrapper.ge(PlusUser::getRegDate, search.getEndDate());
        page.setSearchCount(true);
        plusMapper.selectPage(page, wrapper);
        page.setTotal(plusMapper.selectCount(wrapper));
        // page1 전체를 넘깁니다. (화면참조)
        model.addAttribute("page", page);
    }

    @GetMapping("user/plus/searchUserListByXmlQuery")
    public void searchUserListByXmlQuery(Model model) {
        LegacySearchUserListVO search = (LegacySearchUserListVO) model.getAttribute("searchUser");

        // 복잡한 쿼리는 xml 로 작성하여 Mapper 직접 호출
        List<PlusUser> userList = plusMapper.searchUserListByXmlQuery(search);
        model.addAttribute("searchUserList", userList);
    }
}
