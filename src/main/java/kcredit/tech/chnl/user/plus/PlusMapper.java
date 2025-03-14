package kcredit.tech.chnl.user.plus;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import kcredit.tech.chnl.user.legacy.LegacySearchUserListVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlusMapper extends BaseMapper<PlusUser> {
    // BaseMapper 를 통하여 기본적인 CRUD 가 구현되어 있습니다.

    // 전통방식의 xml 쿼리 이용
    List<PlusUser> searchUserListByXmlQuery(LegacySearchUserListVO vo);
}
