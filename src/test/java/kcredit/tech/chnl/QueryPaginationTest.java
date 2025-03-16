package kcredit.tech.chnl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import kcredit.tech.chnl.user.legacy.LegacySearchUserListVO;
import kcredit.tech.chnl.user.plus.PlusMapper;
import kcredit.tech.chnl.user.plus.PlusUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class QueryPaginationTest {

    @Autowired
    PlusMapper plusMapper;

    @Test
    void test1() {
        LegacySearchUserListVO vo = new LegacySearchUserListVO();
        Page<PlusUser> page = new Page<>(1, 2);
        plusMapper.selectPage(page, null);
        page.getRecords().forEach(System.out::println);
        System.out.println(page.getTotal());
    }
}
