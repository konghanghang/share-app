package com.ysla.api.auto.mapper;

import com.ysla.api.auto.model.IpInfo;
import org.apache.ibatis.jdbc.SQL;

public class IpInfoSqlProvider {

    public String insertSelective(IpInfo record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("s_ip_info");
        
        if (record.getAreaId() != null) {
            sql.VALUES("area_id", "#{areaId,jdbcType=VARCHAR}");
        }
        
        if (record.getArea() != null) {
            sql.VALUES("area", "#{area,jdbcType=VARCHAR}");
        }
        
        if (record.getCountryId() != null) {
            sql.VALUES("country_id", "#{countryId,jdbcType=VARCHAR}");
        }
        
        if (record.getCountry() != null) {
            sql.VALUES("country", "#{country,jdbcType=VARCHAR}");
        }
        
        if (record.getRegionId() != null) {
            sql.VALUES("region_id", "#{regionId,jdbcType=VARCHAR}");
        }
        
        if (record.getRegion() != null) {
            sql.VALUES("region", "#{region,jdbcType=VARCHAR}");
        }
        
        if (record.getCityId() != null) {
            sql.VALUES("city_id", "#{cityId,jdbcType=VARCHAR}");
        }
        
        if (record.getCity() != null) {
            sql.VALUES("city", "#{city,jdbcType=VARCHAR}");
        }
        
        if (record.getCountyId() != null) {
            sql.VALUES("county_id", "#{countyId,jdbcType=VARCHAR}");
        }
        
        if (record.getCounty() != null) {
            sql.VALUES("county", "#{county,jdbcType=VARCHAR}");
        }
        
        if (record.getIspId() != null) {
            sql.VALUES("isp_id", "#{ispId,jdbcType=VARCHAR}");
        }
        
        if (record.getIsp() != null) {
            sql.VALUES("isp", "#{isp,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateDate() != null) {
            sql.VALUES("create_date", "#{createDate,jdbcType=BIGINT}");
        }
        
        if (record.getLastUpdateDate() != null) {
            sql.VALUES("last_update_date", "#{lastUpdateDate,jdbcType=BIGINT}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(IpInfo record) {
        SQL sql = new SQL();
        sql.UPDATE("s_ip_info");
        
        if (record.getAreaId() != null) {
            sql.SET("area_id = #{areaId,jdbcType=VARCHAR}");
        }
        
        if (record.getArea() != null) {
            sql.SET("area = #{area,jdbcType=VARCHAR}");
        }
        
        if (record.getCountryId() != null) {
            sql.SET("country_id = #{countryId,jdbcType=VARCHAR}");
        }
        
        if (record.getCountry() != null) {
            sql.SET("country = #{country,jdbcType=VARCHAR}");
        }
        
        if (record.getRegionId() != null) {
            sql.SET("region_id = #{regionId,jdbcType=VARCHAR}");
        }
        
        if (record.getRegion() != null) {
            sql.SET("region = #{region,jdbcType=VARCHAR}");
        }
        
        if (record.getCityId() != null) {
            sql.SET("city_id = #{cityId,jdbcType=VARCHAR}");
        }
        
        if (record.getCity() != null) {
            sql.SET("city = #{city,jdbcType=VARCHAR}");
        }
        
        if (record.getCountyId() != null) {
            sql.SET("county_id = #{countyId,jdbcType=VARCHAR}");
        }
        
        if (record.getCounty() != null) {
            sql.SET("county = #{county,jdbcType=VARCHAR}");
        }
        
        if (record.getIspId() != null) {
            sql.SET("isp_id = #{ispId,jdbcType=VARCHAR}");
        }
        
        if (record.getIsp() != null) {
            sql.SET("isp = #{isp,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateDate() != null) {
            sql.SET("create_date = #{createDate,jdbcType=BIGINT}");
        }
        
        if (record.getLastUpdateDate() != null) {
            sql.SET("last_update_date = #{lastUpdateDate,jdbcType=BIGINT}");
        }
        
        sql.WHERE("ip = #{ip,jdbcType=BIGINT}");
        
        return sql.toString();
    }
}