package kcredit.tech.chnl.user.legacy;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LegacyMapper {

    @Select("select nextval('tech_chnl.seq_user_no') as no")
    long nextvalSeqUserNo();

    @Select("select * from tech_chnl.user where no = #{no}")
    LegacyUser selectUser(@Param("no") long no);

    @Select("delete from tech_chnl.user where no = #{no}")
    Integer deleteUser(@Param("no") long no);

    @Insert("INSERT INTO tech_chnl.user (name, pwd, grade) values (#{name}, #{pwd}, #{grade})")
    Integer insertUser(LegacyUser user);

    @Update("UPDATE tech_chnl.user set name = #{name}, pwd = #{pwd}, grade = #{grade}, upd_date = now() where no = #{no}")
    Integer updateUser(LegacyUser user);

    List<LegacyUser> searchUserList(@Param("data") LegacySearchUser data, @Param("page") LegacyPage page);
}
