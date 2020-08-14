package com.hitman_zhang.datasource.common.datasource.aspect;

import com.hitman_zhang.datasource.common.datasource.TransactionHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;

import java.util.Stack;

/**
 * @description
 **/
@Aspect
@Component
@Order(1)
@Slf4j
public class TransactionAspect {


    @Pointcut("@annotation(com.hitman_zhang.datasource.common.datasource.annotation.TransactionSource)")
    public void transactionPointCut(){}

    @Around("transactionPointCut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable{
        TransactionHolder.setTransactionManager(new Stack<DataSourceTransactionManager>());
        TransactionHolder.setTransactionStatus(new Stack<TransactionStatus>());
        try {
            //执行业务
            Object proceed = pjp.proceed();
            commit();
            return proceed;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            rollback();
            log.error(String.format(
                    "TransactionAspect, method:%s-%s occors error:",pjp.getTarget().getClass().getSimpleName(),
                    pjp.getSignature().getName()),throwable);
            throw throwable;
        }

    }
    //提交事务
    private void commit(){
        Stack<DataSourceTransactionManager> transactionManagerStack = TransactionHolder.getTransactionManager();
        Stack<TransactionStatus> transactionStatusStack = TransactionHolder.getTransactionStatus();
        while (!transactionManagerStack.isEmpty()){
            transactionManagerStack.pop().commit(transactionStatusStack.pop());
        }
        TransactionHolder.clearTransactionManager();
        TransactionHolder.clearTransactionStatus();
    }
    //事务回滚
    private void rollback(){
        Stack<DataSourceTransactionManager> transactionManagerStack = TransactionHolder.getTransactionManager();
        Stack<TransactionStatus> transactionStatusStack = TransactionHolder.getTransactionStatus();
        while (!transactionManagerStack.isEmpty()){
            transactionManagerStack.pop().rollback(transactionStatusStack.pop());
        }
        TransactionHolder.clearTransactionManager();
        TransactionHolder.clearTransactionStatus();
    }
}
