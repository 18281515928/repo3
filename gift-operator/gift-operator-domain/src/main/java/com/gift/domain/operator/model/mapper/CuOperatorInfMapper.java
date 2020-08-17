package com.gift.domain.operator.model.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gift.domain.operator.model.entity.CuOperatorInf;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 企业操作员信息表 Mapper 接口
 * </p>
 *
 * @author yangquan
 * @since 2020-05-19
 */
public interface CuOperatorInfMapper extends BaseMapper<CuOperatorInf> {
    
}
