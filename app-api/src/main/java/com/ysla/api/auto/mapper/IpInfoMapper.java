package com.ysla.api.auto.mapper;

import com.ysla.api.auto.model.IpInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface IpInfoMapper {
    @Delete({
        "delete from s_ip_info",
        "where ip = #{ip,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long ip);

    @Insert({
        "insert into s_ip_info (area_id, area, ",
        "country_id, country, ",
        "region_id, region, ",
        "city_id, city, county_id, ",
        "county, isp_id, isp, ",
        "create_date, last_update_date)",
        "values (#{areaId,jdbcType=VARCHAR}, #{area,jdbcType=VARCHAR}, ",
        "#{countryId,jdbcType=VARCHAR}, #{country,jdbcType=VARCHAR}, ",
        "#{regionId,jdbcType=VARCHAR}, #{region,jdbcType=VARCHAR}, ",
        "#{cityId,jdbcType=VARCHAR}, #{city,jdbcType=VARCHAR}, #{countyId,jdbcType=VARCHAR}, ",
        "#{county,jdbcType=VARCHAR}, #{ispId,jdbcType=VARCHAR}, #{isp,jdbcType=VARCHAR}, ",
        "#{createDate,jdbcType=BIGINT}, #{lastUpdateDate,jdbcType=BIGINT})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="ip", before=false, resultType=Long.class)
    int insert(IpInfo record);

    @InsertProvider(type=IpInfoSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="ip", before=false, resultType=Long.class)
    int insertSelective(IpInfo record);

    @Select({
        "select",
        "ip, area_id, area, country_id, country, region_id, region, city_id, city, county_id, ",
        "county, isp_id, isp, create_date, last_update_date",
        "from s_ip_info",
        "where ip = #{ip,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="ip", property="ip", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="area_id", property="areaId", jdbcType=JdbcType.VARCHAR),
        @Result(column="area", property="area", jdbcType=JdbcType.VARCHAR),
        @Result(column="country_id", property="countryId", jdbcType=JdbcType.VARCHAR),
        @Result(column="country", property="country", jdbcType=JdbcType.VARCHAR),
        @Result(column="region_id", property="regionId", jdbcType=JdbcType.VARCHAR),
        @Result(column="region", property="region", jdbcType=JdbcType.VARCHAR),
        @Result(column="city_id", property="cityId", jdbcType=JdbcType.VARCHAR),
        @Result(column="city", property="city", jdbcType=JdbcType.VARCHAR),
        @Result(column="county_id", property="countyId", jdbcType=JdbcType.VARCHAR),
        @Result(column="county", property="county", jdbcType=JdbcType.VARCHAR),
        @Result(column="isp_id", property="ispId", jdbcType=JdbcType.VARCHAR),
        @Result(column="isp", property="isp", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_date", property="createDate", jdbcType=JdbcType.BIGINT),
        @Result(column="last_update_date", property="lastUpdateDate", jdbcType=JdbcType.BIGINT)
    })
    IpInfo selectByPrimaryKey(Long ip);

    @UpdateProvider(type=IpInfoSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(IpInfo record);

    @Update({
        "update s_ip_info",
        "set area_id = #{areaId,jdbcType=VARCHAR},",
          "area = #{area,jdbcType=VARCHAR},",
          "country_id = #{countryId,jdbcType=VARCHAR},",
          "country = #{country,jdbcType=VARCHAR},",
          "region_id = #{regionId,jdbcType=VARCHAR},",
          "region = #{region,jdbcType=VARCHAR},",
          "city_id = #{cityId,jdbcType=VARCHAR},",
          "city = #{city,jdbcType=VARCHAR},",
          "county_id = #{countyId,jdbcType=VARCHAR},",
          "county = #{county,jdbcType=VARCHAR},",
          "isp_id = #{ispId,jdbcType=VARCHAR},",
          "isp = #{isp,jdbcType=VARCHAR},",
          "create_date = #{createDate,jdbcType=BIGINT},",
          "last_update_date = #{lastUpdateDate,jdbcType=BIGINT}",
        "where ip = #{ip,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(IpInfo record);
}