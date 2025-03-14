package kcredit.tech.chnl.user.legacy;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum LegacyUserGrade {

    A("A", "귀빈고객"), B("B", "우수고객"), C("C", "일반고객"), D("D", "진상고객"), X("X", "미지정");

    @EnumValue /* DB 에서 enum 값으로 처리할 필드 지정 */
    final String code;

    final String desc;

    public static LegacyUserGrade code(String code) {
        for (LegacyUserGrade grade : LegacyUserGrade.values()) {
            if (grade.code.equals(code)) {
                return grade;
            }
        }
        return null;
    }

    public static LegacyUserGrade desc(String desc) {
        for (LegacyUserGrade grade : LegacyUserGrade.values()) {
            if (desc.equals(grade.desc)) {
                return grade;
            }
        }
        return null;
    }
}
