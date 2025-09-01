
package com.mall.common.mybatis.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * Base mapper interface
 * @param <T> Entity type
 */
public interface SuperMapper<T> extends BaseMapper<T> {
    // Custom common mapper methods can be added here
}
