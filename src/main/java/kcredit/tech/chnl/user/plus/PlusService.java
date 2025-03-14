
package kcredit.tech.chnl.user.plus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import kcredit.tech.chnl.user.legacy.LegacySearchUserListVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlusService {

    private final PlusMapper plusMapper;

    public PlusUser insertUser(PlusUser user) {
        plusMapper.insert(user);
        return user;
    }

    public PlusUser updateUser(PlusUser user) {
        plusMapper.updateById(user);
        return user;
    }

    public PlusUser findUser(PlusUser user) {
        return plusMapper.selectById(user.getNo());
    }

    public Page<PlusUser> searchUserList(Page<PlusUser> page, LegacySearchUserListVO search) {
        LambdaQueryWrapper<PlusUser> w = Wrappers.lambdaQuery();
        if (search.getNo() > 0) w.eq(PlusUser::getNo, search.getNo());
        if (search.getName() != null) w.like(PlusUser::getName, search.getName());
        if (search.getGrade() != null) w.eq(PlusUser::getGrade, search.getGrade());
        if (search.getStartDate() != null) w.ge(PlusUser::getRegDate, search.getStartDate());
        if (search.getEndDate() != null) w.ge(PlusUser::getRegDate, search.getEndDate());
        w.orderByAsc(PlusUser::getName);
        return plusMapper.selectPage(page, w);
    }
}
