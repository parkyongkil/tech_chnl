package kcredit.tech.chnl.user.plus;

import com.baomidou.mybatisplus.annotation.*;
import kcredit.tech.chnl.user.legacy.LegacyUserGrade;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;

import java.util.Date;

@Data
@TableName("tech_chnl.user")
public class PlusUser {

    @TableId(type = IdType.AUTO)
    private Long no; // Key 값이 null 인 상황 연출을 위해서 Long 로 설정

    private String name;

    private String pwd;

    @TableField(updateStrategy = FieldStrategy.NOT_NULL) // grade 값이 있을 때만 업데이트, grade == null 이면 업데이트 안함
    private LegacyUserGrade grade;

    @TableField(jdbcType = JdbcType.TIMESTAMP) // 밀리세컨드 값이 정상적으로 작동하도록 설정
    private Date regDate;

    @TableField(exist = false) // 테이블에 존재하지 않는 컬럼
    private Date birthday;

    @TableField(exist = false)// 테이블에 존재하지 않는 컬럼
    private String gender;
}
