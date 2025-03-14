package kcredit.tech.chnl.user.legacy;

import lombok.Data;

import java.util.Date;

@Data
public class LegacyUser {
    private long no;
    private String name;
    private String pwd;
    private LegacyUserGrade grade;
    private Date regDate;
}
