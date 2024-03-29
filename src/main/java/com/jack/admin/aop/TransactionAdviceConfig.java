package com.jack.admin.aop;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;

/**
 * 事务管理Aop实现
 *
 * @author crazyjack262
 * @date 2020-07-01 09:12
 */
@Aspect
@Configuration
public class TransactionAdviceConfig {

    private static final String AOP_POINTCUT_EXPRESSION = "execution(* com.jack.admin.service.*.*(..))";

    @Autowired
    private TransactionManager transactionManager;

    @Bean
    public TransactionInterceptor txAdvice() {

        DefaultTransactionAttribute txAttrRequired = new DefaultTransactionAttribute();
        txAttrRequired.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        DefaultTransactionAttribute txAttrRequiredReadonly = new DefaultTransactionAttribute();
        txAttrRequiredReadonly.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        txAttrRequiredReadonly.setReadOnly(true);

        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();

        source.addTransactionalMethod("save*", txAttrRequired);
        source.addTransactionalMethod("delete*", txAttrRequired);
        source.addTransactionalMethod("update*", txAttrRequired);
        source.addTransactionalMethod("exec*", txAttrRequired);
        source.addTransactionalMethod("set*", txAttrRequired);
        source.addTransactionalMethod("get*", txAttrRequiredReadonly);
        source.addTransactionalMethod("query*", txAttrRequiredReadonly);
        source.addTransactionalMethod("find*", txAttrRequiredReadonly);
        source.addTransactionalMethod("list*", txAttrRequiredReadonly);
        source.addTransactionalMethod("count*", txAttrRequiredReadonly);
        source.addTransactionalMethod("is*", txAttrRequiredReadonly);

        return new TransactionInterceptor(transactionManager, source);
    }

    @Bean
    public Advisor txAdviceAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
        return new DefaultPointcutAdvisor(pointcut, txAdvice());
    }

}
