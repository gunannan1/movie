package com.dubbo.movie.service;

import com.dubbo.movie.dao.TransactionMessageMapper;
import com.dubbo.movie.model.TransactionMessage;
import com.dubbo.movie.vo.message.TransactionMessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionMessageServiceImpl {
    @Autowired
    TransactionMessageMapper transactionMessageMapper;

    public boolean save(TransactionMessageVO transactionMessageVO){
        TransactionMessage transactionMessage=new TransactionMessage();

        transactionMessage.setId(transactionMessageVO.getId());
        transactionMessage.setTopic(transactionMessageVO.getTopic());
        transactionMessage.setMessage(transactionMessageVO.getMessage());
        transactionMessage.setStatus(transactionMessageVO.getStatus());
        transactionMessage.setTryCount(transactionMessageVO.getTryCount());

        return transactionMessageMapper.insert(transactionMessage)>0;

    }

    public boolean update(String key,Integer status){
        TransactionMessage transactionMessage=new TransactionMessage();
        transactionMessage.setId(key);
        transactionMessage.setStatus(status);
        return transactionMessageMapper.updateById(transactionMessage)>0;

    }

    public TransactionMessageVO search(String key){
        TransactionMessage transactionMessage=transactionMessageMapper.selectById(key);
        if(transactionMessage!=null){
            TransactionMessageVO transactionMessageVO=new TransactionMessageVO();
            transactionMessageVO.setId(transactionMessage.getId());
            transactionMessageVO.setMessage(transactionMessage.getMessage());
            transactionMessageVO.setStatus(transactionMessage.getStatus());
            transactionMessageVO.setTopic(transactionMessage.getTopic());
            transactionMessageVO.setTryCount(transactionMessage.getTryCount());
            return transactionMessageVO;
        }

        return null;

    }

//    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false, rollbackFor = Exception.class)
    public void delete(String key){
        System.out.println("删除消息");
        transactionMessageMapper.deleteById(key);
    }

}
