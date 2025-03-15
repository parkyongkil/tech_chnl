package kcredit.tech.chnl.user.plus;

import com.baomidou.mybatisplus.annotation.*;
import kcredit.tech.chnl.user.legacy.LegacyUserGrade;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true) // 더 이상 @Builder 를 사용하지 않습니다.
// User.Builder().no(no); --> new User().setNo(no);
// Bulder는 레퍼런싱 및 추적이 어려운 문제가 있고, 생성자 제한 문제가 발생합니다.
@TableName("tech_chnl.user")
public class PlusUser {

    @TableId(type = IdType.AUTO)
    private Long no; // Key 값이 null 인 상황 연출을 위해서 Long 로 설정

    @TableField(updateStrategy = FieldStrategy.NOT_NULL)
    private String name;

    @TableField(updateStrategy = FieldStrategy.NOT_EMPTY) // 빈문자열 업데이트를 방지합니다.
    private String pwd;

    @TableField(updateStrategy = FieldStrategy.NOT_NULL) // grade 값이 있을 때만 업데이트, grade == null 이면 업데이트 안함
    private LegacyUserGrade grade;

    @TableField(updateStrategy = FieldStrategy.NOT_NULL)
    private Date regDate;

    @TableField(updateStrategy = FieldStrategy.NOT_NULL)
    private Date updDate;

    @TableField(exist = false) // 테이블에 존재하지 않는 컬럼
    private int age;

    @TableField(exist = false) // 테이블에 존재하지 않는 컬럼
    private String gender;
}
