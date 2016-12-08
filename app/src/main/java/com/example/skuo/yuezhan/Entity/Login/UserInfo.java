package com.example.skuo.yuezhan.Entity.Login;

/**
 * Created by Administrator on 16-11-28.
 */
public class UserInfo {
    /// <summary>
    /// 用户ID
    /// </summary>
    public int ID;

    /// <summary>
    /// 用户名
    /// </summary>
    public String UserName;

    /// <summary>
    /// 昵称
    /// </summary>
    public String NickName;

    /// <summary>
    /// 手机
    /// </summary>
    public String Phone;

    /// <summary>
    /// 省份ID
    /// </summary>
    public int ProvinceID;

    /// <summary>
    /// 城市ID
    /// </summary>
    public int CityID;

    /// <summary>
    /// 物业公司ID
    /// </summary>
    public int PropertyID;

    /// <summary>
    /// 小区ID
    /// </summary>
    public int EstateID;

    /// <summary>
    /// 小区名称
    /// </summary>
    public String EstateName;

    /// <summary>
    /// 楼幢ID
    /// </summary>
    public int BuildingID;

    /// <summary>
    /// 楼幢名称
    /// </summary>
    public String BuildingName;

    /// <summary>
    /// 单元ID
    /// </summary>
    public int CellID;

    /// <summary>
    /// 单元名称
    /// </summary>
    public String CellName;

    /// <summary>
    /// 房号ID
    /// </summary>
    public int HouseID;

    /// <summary>
    /// 房号名称
    /// </summary>
    public String HouseName;

    /// <summary>
    /// 是否认证 0-未认证 1-认证
    /// </summary>
    public int IsAuthen;

    /**
     * 关系
     */
    public String Relations;
}
