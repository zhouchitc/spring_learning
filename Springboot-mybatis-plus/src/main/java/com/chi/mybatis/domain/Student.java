package com.chi.mybatis.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.Data;

import javax.persistence.Id;

/**
 * @author chi
 * @Description: TODO
 * @date 2022/6/20 09:27
 * @Version 1.0
 */
@Data
@TableName("t_student")    // 需要使用mybatis-plus的注解,否则建表时无异常,但是搜索时会按照类名搜索
public class Student extends BaseDomain {
    @Id
    @Column(name = "`id`",
            type = MySqlTypeConstant.BIGINT,
            length = 64,
            isNull = false,
            comment = "主键")
    Long id;

    @Column(name = "`name`",
            type = MySqlTypeConstant.VARCHAR,
            length = 64,
            isNull = true,
            comment = "名字")
    String name;

    @Column(name = "`score`",
            type = MySqlTypeConstant.BIGINT,
            length = 64,
            isNull = true,
            comment = "分数")
    Long score;
}
