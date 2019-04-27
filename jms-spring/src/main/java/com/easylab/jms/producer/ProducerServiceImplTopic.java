package com.easylab.jms.producer;

import com.easylab.jms.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.*;

/******************************
 * @author : liuyuan
 * <p>ProjectName:jms-spring  </p>
 * @ClassName :  ProducerServiceImpl
 * @date : 2018/6/24 0024
 * @time : 8:42
 * @createTime 2018-06-24 8:42
 * @version : 2.0
 * @description :
 *******************************/
@Service
public class ProducerServiceImplTopic implements IProducerService {

    @Autowired
    JmsTemplate jmsTemplate;

    //指定主题模式
    @Resource(name = "topicDestination")
    Destination destination;


    public void sendMessage( final String message) {
        // 使用JmsTemplate发送消息
        jmsTemplate.send(destination, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                // 创建一个消息
                TextMessage textMessage = session.createTextMessage(message);

                return textMessage;
            }
        });
        System.out.println("发送消息" + message);
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

    @Override
    public void sendMessageT(String message) {

    }
}
