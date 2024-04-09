//package com.salon.common.mybatis.utils;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.TransactionStatus;
//import org.springframework.transaction.support.TransactionCallback;
//import org.springframework.transaction.support.TransactionTemplate;
//
//import java.util.function.Consumer;
//
///**
// * @Author：xieshaowei
// * @Package：com.salon.common.core.utils.mybatis
// * @Project：salon
// * @name：TransactionalUtil
// * @Date：2024/3/20 15:50
// */
//@Component
//@RequiredArgsConstructor
//public class TransactionalUtil {
//
//    private final TransactionTemplate transactionTemplate;
//
//    public <T> T execute(TransactionCallback<T> action) {
//        return transactionTemplate.execute(action);
//    }
//
//    public void executeWithoutResult(Consumer<TransactionStatus> action) {
//        transactionTemplate.executeWithoutResult(action);
//    }
//
//}
