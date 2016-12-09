package com.example.skuo.yuezhan.Entity.Tourism;

import com.example.skuo.yuezhan.Entity.Register.Estate;

import java.util.Date;

/**
 * Created by Administrator on 16-12-9.
 */
public class AppointmentTourismMaster {
    public int ID;
    public int EstateID;
    public Date AppointmenTime;
    public int UserAccountID;
    public Integer SignUpNumber;
    public String Tel;
    public String Address;
    public String Contacts;
    public String Remark;
    public int TourismInfoID;
    public int Status;
    public Date CreateTime;
    public Integer CreateUserID;
    public Date ModeifyTime;
    public Integer ModeifyUserID;
    public boolean Isvalid;
    public volatile Estate estate;
//    public virtual TourismInfo TourismInfo;
//    public virtual ICollection<AppointmentTourismSlaver> AppointmentTourismSlavers { get; set; }
}
