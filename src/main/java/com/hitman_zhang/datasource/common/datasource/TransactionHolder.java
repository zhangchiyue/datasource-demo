package com.hitman_zhang.datasource.common.datasource;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;

import java.util.Stack;

/**
 * 暂存事务管理器和事务状态
 */
public class TransactionHolder {

    private static ThreadLocal<Stack<DataSourceTransactionManager>> dataSourceTransactionManagerStack = new ThreadLocal<>();
    private static ThreadLocal<Stack<TransactionStatus>> transactionStatusStack = new ThreadLocal<>();

    /**
     * 将启动的事务管理器和当前线程绑定
     * @param stack Stack<DataSourceTransactionManager>
     */
    public static void setTransactionManager(Stack<DataSourceTransactionManager> stack){
        dataSourceTransactionManagerStack.set(stack);
    }

    /**
     * 从线程中取出事务管理器
     * @return Stack<DataSourceTransactionManager>
     */
    public static Stack<DataSourceTransactionManager> getTransactionManager(){
        return dataSourceTransactionManagerStack.get();
    }

    public static void setTransactionStatus(Stack<TransactionStatus> status){
        transactionStatusStack.set(status);
    }

    public static Stack<TransactionStatus> getTransactionStatus(){
        return  transactionStatusStack.get();
    }

    public static void clearTransactionManager(){
        dataSourceTransactionManagerStack.remove();
    }

    public static void clearTransactionStatus(){
        transactionStatusStack.remove();
    }
}