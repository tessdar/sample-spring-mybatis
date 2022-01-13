package com.common.aspect;

import java.util.Collections;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.interceptor.MatchAlwaysTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

@Configuration
@Aspect
public class TrxAspect {

	private static final String AOP_POINTCUT_EXPRESSION = "execution(* com.example.service.*Service.*(..))";

	@Autowired
	private TransactionManager transactionManager;

	@Bean
	public TransactionInterceptor txAdvice() {
		MatchAlwaysTransactionAttributeSource source = new MatchAlwaysTransactionAttributeSource();
		RuleBasedTransactionAttribute transactionAttribute1 = new RuleBasedTransactionAttribute();

		transactionAttribute1.setName("get*");
		transactionAttribute1.setReadOnly(true);
		source.setTransactionAttribute(transactionAttribute1);
		
		RuleBasedTransactionAttribute transactionAttribute2 = new RuleBasedTransactionAttribute();

		transactionAttribute2.setName("set*");
		transactionAttribute2.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
		source.setTransactionAttribute(transactionAttribute2);
		
		TransactionInterceptor txAdvice = new TransactionInterceptor(transactionManager, source);
		return txAdvice;
	}

	@Bean
	public Advisor txAdviceAdvisor() {
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
		return new DefaultPointcutAdvisor(pointcut, txAdvice());
	}

}
