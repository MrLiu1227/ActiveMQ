package com.easylab.jms.producer;

import com.easylab.jms.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
public class ProducerServiceImpl implements IProducerService {

    @Autowired
    JmsTemplate jmsTemplate;

    @Resource(name = "queueDestination")
    Destination destination;

    /**
     *  发送字符串消息
     * @param message
     */
    @Override
    public void sendMessage(final String message) {

        // 使用JmsTemplate发送消息
        jmsTemplate.send(destination, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                // 创建一个文本消息
                TextMessage textMessage = session.createTextMessage(message);

                return textMessage;
            }
        });
       /* System.out.println("发送消息" + message);*/
    }


    /**
     * 发送实体消息
     * @param message
     */
    @Override
    public void sendMessageObj(Student message) {

        // 使用JmsTemplate发送消息
        jmsTemplate.send(destination, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {

                // 创建一个实体类消息
                ObjectMessage obj = session.createObjectMessage();
                obj.setObject(message);
                return obj;
            }
        });
    }

    /**
     * 发送map消息
     * @param mapId
     * @param mapValue
     */
    @Override
    public void sendMessageMap(String mapId, String mapValue){

        // 使用JmsTemplate发送消息
        jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {

                // 创建一个map类消息
                MapMessage mapMessage = session.createMapMessage();
                //构建map
                mapMessage.setString(mapId, mapValue);
                return mapMessage;
            }
        });
    }

    /**
     * 发送bytes[]消息
     * @param bytes
     */
    @Override
    public void sendMessageByte( byte[] bytes){

        // 使用JmsTemplate发送消息
        jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {

                BytesMessage bytesMessage = session.createBytesMessage();
                bytesMessage.writeBytes(bytes);
                return bytesMessage;
            }
        });
    }

    /**
     * 发送StreamMessage消息
     */
    @Override
    public void sendMessageStreamMessage(){

        // 使用JmsTemplate发送消息
        jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {

                StreamMessage message = session.createStreamMessage();
                message.writeString("我是stream string");
                message.writeInt(11111);
                return message;
            }
        });
    }

//以下是使用MessageListenerAdapter监听器相关

    @Resource(name = "adapterQueue")
    Destination destinationBack;

    @Override
    public void sendMessageByAdapter(String message){

        // 使用JmsTemplate发送消息
        jmsTemplate.send(destinationBack, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage(message);
                System.out.println("发送消息：");
                //通过setJMSReplyTo设置监听器回复消息的队列，也可以通过xml中配置defaultResponseDestination设置
                /* textMessage.setJMSReplyTo(responseQueue);*/
                return textMessage;
            }
        });
    }

    @Override
    public void sendMessageT(String message) {

    }

}
