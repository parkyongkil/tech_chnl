package kcredit.tech.chnl;

import kcredit.tech.chnl.user.legacy.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class TechChnlApplicationTests {

    @Autowired
    LegacyMapper mapper;

    @Test
    void test() {
        String delim = StringUtils.repeat("-", 100);
        List<LegacyUser> users = insertUpserTest();
        for (LegacyUser user : users) {
            LegacyUser user2 = mapper.selectUser(user.getNo());
            if (user.equals(user2)) {
                System.out.println("User1: " + user);
                System.out.println("User2: " + user2);
                System.out.println(delim);
            }
            if (user.getName().matches("^.+[13579]$")) {
                mapper.deleteUser(user.getNo());
            } else {
                String pwd = StringUtils.randomAlphanumeric(6);
                user.setName(user.getName().replaceAll("\\w+", pwd));
                user.setGrade(LegacyUserGrade.B.toString()).setPwd(pwd);
                mapper.updateUser(user);
            }
        }
        LegacySearchUser data = new LegacySearchUser();
        LegacyPage page = new LegacyPage();
        for (int i = 0; i < 100; i++) {
            List<LegacyUser> list = mapper.searchUserList(data, page);
            list.forEach(System.out::println);
            if (list.size() == 0) break;
            page.setOffset(page.getLimit() + list.size());
        }
    }

    List<LegacyUser> insertUpserTest() {
        List<LegacyUser> users = new ArrayList<>();
        for (int i = 0; i < 17; i++) {
            LegacyUser user = new LegacyUser().setName("예쁜희 " + StringUtils.randomAlphanumeric(4)).setPwd("*****").setGrade(LegacyUserGrade.A.toString());
            mapper.insertUser(user);
            users.add(user);
        }
        return users;
    }
}
