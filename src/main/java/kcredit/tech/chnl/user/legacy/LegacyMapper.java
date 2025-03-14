package kcredit.tech.chnl.user.legacy;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LegacyMapper {

    @Select("select * from tech_chnl.user where no > 0 limit 1000")
    List<LegacyUser> selectAllUserListByAnnotation(); // By Annotation Query

    List<LegacyUser> selectAllUserList(); // By Mapper.Xml Query

    List<LegacyUser> searchUserListWithSigleParameter(LegacySearchUserListVO legacySearchUserListVO);

    List<LegacyUser> searchUserListWithMuliParameters(@Param("data") LegacySearchUserListVO legacySearchUserListVO, @Param("page") LegacyPage legacyPage);
}
