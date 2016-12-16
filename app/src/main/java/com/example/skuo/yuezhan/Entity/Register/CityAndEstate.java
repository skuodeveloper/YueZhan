package com.example.skuo.yuezhan.Entity.Register;

import java.util.List;

/**
 * Created by Administrator on 16-12-15.
 */
public class CityAndEstate {

    private int TotalCount;
    private List<EstateInfosBean> EstatesNew;

    public int getTotalCount() {
        return TotalCount;
    }

    public void setTotalCount(int TotalCount) {
        this.TotalCount = TotalCount;
    }

    public List<EstateInfosBean> getEstateInfos() {
        return EstatesNew;
    }

    public void setEstateInfos(List<EstateInfosBean> EstateInfos) {
        this.EstatesNew = EstateInfos;
    }

    public static class EstateInfosBean {
        /**
         * EstateID : 10
         * EstateName : 悦站体验中心
         * CityID : 304
         * CityName : 嘉兴
         */

        private int EstateID;
        private String EstateName;
        private int CityID;
        private String CityName;

        public int getEstateID() {
            return EstateID;
        }

        public void setEstateID(int EstateID) {
            this.EstateID = EstateID;
        }

        public String getEstateName() {
            return EstateName;
        }

        public void setEstateName(String EstateName) {
            this.EstateName = EstateName;
        }

        public int getCityID() {
            return CityID;
        }

        public void setCityID(int CityID) {
            this.CityID = CityID;
        }

        public String getCityName() {
            return CityName;
        }

        public void setCityName(String CityName) {
            this.CityName = CityName;
        }
    }
}
