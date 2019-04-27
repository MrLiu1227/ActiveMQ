package com.easylab.jms.producer;

import com.easylab.jms.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.*;

/*****************************
 * @author : liuyuan
 * <p>ProjectName:jms-spring  </p>
 * @ClassName :  ProducerServiceImpl
 * @date : 2018/6/24 0024
 * @time : 8:42
 * @createTime 2018-06-24 8:42
 * @version : 2.0
 * @description :
 ******************************/


@Service
public class ProducerServiceImplTransaction implements IProducerService {

    //以下是使用事务管理相关

    @Resource(name = "queueDestinationTransaction")
    Destination destinationT;

    @Autowired
    JmsTemplate jmsTemplate;

    @Override
    public void sendMessageT(String message){

        // 使用JmsTemplate发送消息
        jmsTemplate.send(destinationT, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage(message);

                return textMessage;
            }
        });
    }


    @Override
    public void sendMessage(String message) {

    }

    @Override
    public void sendMessageObj(Student message) {

    }

    @Override
    public void sendMessageMap(String mapId, String mapValue) {

    }

    @Override
    public void sendMessageByte(byte[] bytes) {

    }

    @Override
    public void sendMessageStreamMessage() {

    }

    @Override
    public void sendMessageByAdapter(String message) {

    }




}
