package com.example.skuo.yuezhan.Entity.Tourism;

import java.util.Date;

/**
 * Created by Administrator on 16-12-9.
 */
public class TourismInfo {
    public int ID;
    public int EstateID;
    public String Title;
    public String IntroductionText;
    public String IntroductionImg;
    public String IntroductionUrl;
    public String TravelAgency;
    public Date SignUpStartTime;
    public Date SignUpEndTime;
    public String Remark;
    public Date CreateTime;
    public Integer CreateUserID;
    public Date ModeifyTime;
    public Integer ModeifyUserID;
    public boolean Isvalid;
//    public virtual ICollection<AppointmentTourismMaster> AppointmentTourismMasters { get; set; }
//    public virtual Estate Estate { get; set; }
}
