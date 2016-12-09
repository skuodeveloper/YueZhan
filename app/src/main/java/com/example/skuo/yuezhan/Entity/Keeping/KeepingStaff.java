package com.example.skuo.yuezhan.Entity.Keeping;

import java.util.Date;

/**
 * Created by Administrator on 16-12-8.
 */
public class KeepingStaff {
    /// <summary>
    /// 服务人员ID
    /// </summary>
    public int Id;

    /// <summary>
    /// 服务人员编号
    /// </summary>
    public String Code;

    /// <summary>
    /// 服务人员姓名
    /// </summary>
    public String Name;

    /// <summary>
    /// 服务人员性别（1-男 2-女）
    /// </summary>
    public int Sex;

    /// <summary>
    /// 服务人员出生日期
    /// </summary>
    public Date Birth;

    /// <summary>
    /// 联系电话
    /// </summary>
    public String MobileNo;

    /// <summary>
    /// 服务人员照片（给出照片名称，自行从服务器下载照片）
    /// </summary>
    public String Picture;

    /// <summary>
    /// 忙碌的服务人员ID
    /// </summary>
    public Integer StaffID;
}
