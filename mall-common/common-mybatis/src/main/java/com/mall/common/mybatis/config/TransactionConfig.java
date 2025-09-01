package com.mall.common.mybatis.config;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Transaction configuration
 */
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
public class TransactionConfig {
    
    private static final int TX_METHOD_TIMEOUT = 30;
    private static final String AOP_POINTCUT_EXPRESSION = "execution(* com.mall..service.*.*(..))";
    
    @Autowired
    private PlatformTransactionManager transactionManager;
    
    /**
     * Transaction interceptor
     */
    @Bean
    public TransactionInterceptor txAdvice() {
        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
        
        // Read-only transaction for query methods
        RuleBasedTransactionAttribute readOnlyTx = new RuleBasedTransactionAttribute();
        readOnlyTx.setReadOnly(true);
        readOnlyTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_SUPPORTS);
        
        // Required transaction for write methods
        RuleBasedTransactionAttribute requiredTx = new RuleBasedTransactionAttribute();
        requiredTx.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        requiredTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        requiredTx.setTimeout(TX_METHOD_TIMEOUT);
        
        // Requires new transaction
        RuleBasedTransactionAttribute requiresNewTx = new RuleBasedTransactionAttribute();
        requiresNewTx.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        requiresNewTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        requiresNewTx.setTimeout(TX_METHOD_TIMEOUT);
        
        Map<String, TransactionAttribute> txMap = new HashMap<>();
        
        // Read-only methods
        txMap.put("get*", readOnlyTx);
        txMap.put("query*", readOnlyTx);
        txMap.put("find*", readOnlyTx);
        txMap.put("list*", readOnlyTx);
        txMap.put("select*", readOnlyTx);
        txMap.put("count*", readOnlyTx);
        txMap.put("check*", readOnlyTx);
        txMap.put("validate*", readOnlyTx);
        
        // Write methods
        txMap.put("save*", requiredTx);
        txMap.put("insert*", requiredTx);
        txMap.put("add*", requiredTx);
        txMap.put("create*", requiredTx);
        txMap.put("update*", requiredTx);
        txMap.put("modify*", requiredTx);
        txMap.put("edit*", requiredTx);
        txMap.put("delete*", requiredTx);
        txMap.put("remove*", requiredTx);
        txMap.put("batch*", requiredTx);
        txMap.put("process*", requiredTx);
        txMap.put("handle*", requiredTx);
        
        // Requires new transaction
        txMap.put("asyncProcess*", requiresNewTx);
        txMap.put("newTransaction*", requiresNewTx);
        
        source.setNameMap(txMap);
        
        return new TransactionInterceptor(transactionManager, source);
    }
    
    /**
     * Transaction advisor
     */
    @Bean
    public Advisor txAdviceAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
        return new DefaultPointcutAdvisor(pointcut, txAdvice());
    }
}