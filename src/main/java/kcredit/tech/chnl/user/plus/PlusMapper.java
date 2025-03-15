package kcredit.tech.chnl.user.plus;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import kcredit.tech.chnl.user.legacy.LegacySearchUserListVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlusMapper extends BaseMapper<PlusUser> {

    // BaseMapper 에서 기본적인 CRUD 가 구현되어 있습니다.
    // 복잡한 쿼리도 QueryWrapper 를 통해 outerjoin union 을 비롯한 표준구문 대부분 구현가능하지만
    // QueryWrapper 문법을 배워야 합니다.

    // 전통방식의 xml 쿼리 이용, 기존에 구현한 쿼리서비스를 포팅할 때 유용합니다.
    List<PlusUser> searchUserListByXmlQuery(LegacySearchUserListVO vo);
}
