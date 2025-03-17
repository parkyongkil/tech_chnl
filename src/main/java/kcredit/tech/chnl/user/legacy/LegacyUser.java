package kcredit.tech.chnl.user.legacy;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class LegacyUser {
    private long no;
    private String name;
    private String pwd;
    private String grade;
    private Date regDate;
    private Date updDate;
}
