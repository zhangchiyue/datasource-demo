package com.hitman_zhang.datasource.common.datasource.aspect;

import com.hitman_zhang.datasource.common.datasource.DataSourceNames;
import com.hitman_zhang.datasource.common.datasource.DynamicDataSource;
import com.hitman_zhang.datasource.common.datasource.TransactionHolder;
import com.hitman_zhang.datasource.common.datasource.annotation.TargetDataSource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.lang.reflect.Method;
import java.util.Stack;


/**
 * @description 多数据源切面处理类
 **/
@Aspect
@Component
@Order(2)
@Slf4j
public class DataSourceAspect {


    @Autowired
    private DynamicDataSource dynamicDataSource;

    @Pointcut(value = "@annotation(com.hitman_zhang.datasource.common.datasource.annotation.TargetDataSource)")
    public void dataSourcePointCut(){ }

    @Before(value = "dataSourcePointCut()")
    public void before(JoinPoint joinPoint) throws Throwable{
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        TargetDataSource ds = method.getAnnotation(TargetDataSource.class);

        String dataSourceName = (ds == null) ? DataSourceNames.FIRST : ds.name();
        DynamicDataSource.setDataSource(dataSourceName);
       /* logger.debug("切换数据源"+dataSourceName);*/
        //获取当前线程事务管理器堆栈
        Stack<DataSourceTransactionManager> transactionManagerStack = TransactionHolder.getTransactionManager();
        if(transactionManagerStack != null){
            //获取当前线程事务状态堆栈
            Stack<TransactionStatus> transactionStatusStack = TransactionHolder.getTransactionStatus();
            TransactionHolder.clearTransactionManager();
            TransactionHolder.clearTransactionStatus();
            DataSourceTransactionManager tm = new DataSourceTransactionManager(dynamicDataSource);
            DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
            definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
            TransactionStatus ts = tm.getTransaction(definition);
            transactionManagerStack.push(tm);
            transactionStatusStack.push(ts);
            TransactionHolder.setTransactionManager(transactionManagerStack);
            TransactionHolder.setTransactionStatus(transactionStatusStack);
        }


    }

    @AfterReturning("dataSourcePointCut()")
    public void after(){
        DynamicDataSource.clearDataSource();
        log.debug("clean datasource");
    }
}
