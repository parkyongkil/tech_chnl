package kcredit.tech.chnl.user.legacy;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class LegacySearchUserListVO {
    private long no;
    private String name;
    private LegacyUserGrade grade;
    private Date startDate;
    private Date endDate;

    private int limit = 100;
    private int offset;
}
