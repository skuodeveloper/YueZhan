package com.example.skuo.yuezhan.API;

import com.example.skuo.yuezhan.Entity.BaseEntity;
import com.example.skuo.yuezhan.Entity.Clause.Clause;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 16-12-9.
 */
public interface ClauseAPI {

    /**
     * 获取小区商城服务条款
     * @param EstateID
     * @return
     */
    @GET("Clause/GetEstateMallClause_Json/")
    Observable<BaseEntity<Clause>> httpsGetEstateMallClauseRx(@Query("EstateID") int EstateID);

    /**
     * 获取小区家政服务条款
     * @param EstateID
     * @return
     */
    @GET("Clause/GetEstateHKeepingClause_Json/")
    Observable<BaseEntity<Clause>> httpsGetEstateHKeepingClauseRx(@Query("EstateID") int EstateID);

    /**
     * 获取服务提供商服务规则
     * @param EstateID
     * @return
     */
    @GET("Clause/GetServiceProClause_Json/")
    Observable<BaseEntity<Clause>> httpsGetServiceProClauseRx(@Query("EstateID") int EstateID);

    /**
     * 获取小区维修条款
     * @param EstateID
     * @return
     */
    @GET("Clause/GetEstateRepairClause_Json/")
    Observable<BaseEntity<Clause>> httpsGetEstateRepairClauseRx(@Query("EstateID") int EstateID);

    /**
     * 获取小区建议条款
     * @param EstateID
     * @return
     */
    @GET("Clause/GetEstateComplaintClause_Json/")
    Observable<BaseEntity<Clause>> httpsGetEstateComplaintClauseRx(@Query("EstateID") int EstateID);
}
