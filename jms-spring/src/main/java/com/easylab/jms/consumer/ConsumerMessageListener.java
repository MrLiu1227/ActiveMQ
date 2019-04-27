package com.easylab.jms.consumer;

import javax.jms.*;

import com.easylab.jms.entity.Student;
import org.springframework.jms.listener.SessionAwareMessageListener;

/******************************
 * @author : liuyuan
 * <p>ProjectName:jms-spring  </p>
 * @ClassName :  ConsumerMessageListener
 * @date : 2018/6/24 0024
 * @time : 9:16
 * @createTime 2018-06-24 9:16
 * @version : 2.0
 * @description :
 *
 *
 *
 *******************************/


public class ConsumerMessageListener implements SessionAwareMessageListener<Message> {//需要使用session，需要实现SessionAwareMessageListener

    @Override
    public void onMessage(Message message, Session session)  throws JMSException {
        if (message instanceof TextMessage){
            String msg = ((TextMessage) message).getText();

            System.out.println("------------------------------------------");
            System.out.println("消费者收到的消息：" + msg);
            System.out.println("------------------------------------------");

            try {
                if ("test2".equals(msg)) {
                    throw new RuntimeException("故意抛出的异常");
                }
                // 确认消息。只要被确认后  就会出队，接受失败没有确认成功，会在原队列里面
                message.acknowledge();

            } catch (Exception e) {
                // 此不可省略 重发信息使用，如果不写此方法，将不会实现重发操作。失败的消息将会一直在队列中，因为没有进行消息确认。
                // 下次还会监听到这条消息。效果将会是：第一次接受一个消息2。第二次接受2个，依次累加
                      session.recover();

            }
        }
        //接收实体类
        if (message instanceof ObjectMessage){
            Student s = (Student) ((ObjectMessage) message).getObject();
            System.out.println("我接收到了实体类消息:"+s);
            message.acknowledge();
        }
        //接收map
        if (message instanceof MapMessage){
            MapMessage mm = (MapMessage) message;
            System.out.println("我接收到了map消息:");
            System.out.println(" get textMessage：\t" + mm.getString("mapId"));
            message.acknowledge();
        }

        //接收byte[]
        if (message instanceof BytesMessage) {
            byte[] b = new byte[1024];
            int len = -1;
            BytesMessage bm = (BytesMessage) message;
            while ((len = bm.readBytes(b)) != -1) {
                System.out.println(new String(b, 0, len));
            }
            message.acknowledge();
        }
        // 如果是Stream消息
        if (message instanceof StreamMessage) {
            StreamMessage sm = (StreamMessage) message;
            System.out.println(sm.readString());
            System.out.println(sm.readInt());
            message.acknowledge();
        }
    }
}
