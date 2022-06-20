package com.chi.mybatis.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.DefaultValue;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 * @author chi
 * @Description: TODO
 * @date 2022/6/20 10:35
 * @Version 1.0
 */
@Data
public class BaseDomain implements Serializable {
    /**
     * 创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(value = "create_time",
            type = MySqlTypeConstant.TIMESTAMP,
            isNull = false)
    @DefaultValue("CURRENT_TIMESTAMP")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(value = "update_time",
            type = MySqlTypeConstant.TIMESTAMP,
            isNull = false)
    @DefaultValue("'1970-01-01 08:00:01' ON UPDATE CURRENT_TIMESTAMP")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8")
    private Date updateTime;
}
