package kcredit.tech.chnl.user.plus;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import kcredit.tech.chnl.user.legacy.LegacySearchUserListVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PlusMapper extends BaseMapper<PlusUser> {

    List<PlusUser> searchUserListByXmlQuery(Page<PlusUser> page, @Param("search") LegacySearchUserListVO vo);

    @Select("select * from tech_chnl.user order by no limit 1")
    PlusUser selectMinUser();
}
