package com.mall.common.mybatis.injector;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.extension.injector.methods.InsertBatchSomeColumn;

import java.util.List;

/**
 * Custom SQL injector for batch operations
 */
public class CustomSqlInjector extends DefaultSqlInjector {
    
    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass, TableInfo tableInfo) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass, tableInfo);
        
        // Add batch insert method (excluding logical delete and auto-fill fields)
        methodList.add(new InsertBatchSomeColumn(i -> !i.isLogicDelete() && 
                                                      !"create_time".equals(i.getColumn()) &&
                                                      !"update_time".equals(i.getColumn()) &&
                                                      !"create_by".equals(i.getColumn()) &&
                                                      !"update_by".equals(i.getColumn())));
        
        return methodList;
    }
}