package kcredit.tech.chnl.user.legacy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor // @AutoWired 를 사용하지 않습니다.
public class LegacyService {

    private final LegacyMapper legacyMapper; // 반드시 final 설정해야 합니다.

    public List<LegacyUser> selectAllUserList() {
        return legacyMapper.selectAllUserListByAnnotation();
        // return legacyMapper.selectAllUserList();
    }

    public List<LegacyUser> searchUserList(LegacySearchUserListVO legacySearchUserListVO, LegacyPage legacyPage) {
        // return legacyMapper.searchUserListWithSigleParameter(legacySearchUserListVO);
        return legacyMapper.searchUserListWithMuliParameters(legacySearchUserListVO, legacyPage);
    }
}
