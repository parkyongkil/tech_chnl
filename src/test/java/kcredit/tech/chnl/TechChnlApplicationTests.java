package kcredit.tech.chnl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import kcredit.tech.chnl.user.plus.PlusMapper;
import kcredit.tech.chnl.user.plus.PlusUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TechChnlApplicationTests {

    @Autowired
    PlusMapper plusMapper;

    @Test
    void selectPageTest() {
        int i = 0;
        Page<PlusUser> p = new Page<>();
        do {
            p = plusMapper.selectPage(p, null);
            for (PlusUser u : p.getRecords()) System.out.printf("%4d  =>  %s%n", ++i, u);
            System.out.printf("total = %d, size = %d, curpage= %d, maxpage = %d%n", p.getTotal(), p.getSize(), p.getCurrent(), p.getPages());
            p.setCurrent(p.getCurrent() + 1);
        } while (p.getCurrent() <= p.getPages());
    }
}
