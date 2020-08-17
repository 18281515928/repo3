package com.gift.domain.operator.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>查询所有用户输出实体类</P>
 *
 * @author zhuzh@belink.com
 * @version 0.0.1
 * @className QueryAlloperatorDTO
 * @sine 2020/3/24 10:49
 */
@Data
public class QueryAllOperatorOutDTO implements Serializable {

    private static final long serialVersionUID = -3465448051826657108L;
    private List<CuOperator> cuoperatorList;
}
