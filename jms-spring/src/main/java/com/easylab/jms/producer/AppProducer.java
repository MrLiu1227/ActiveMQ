package com.easylab.jms.producer;

import com.easylab.jms.entity.Student;
import javafx.application.Application;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;

import javax.jms.*;

/******************************
 * @author : liuyuan
 * <p>ProjectName:jms-test  </p>
 * @ClassName :  AppProducer
 * @date : 2018/6/20 0020
 * @time : 20:46
 * @createTime 2018-06-20 20:46
 * @version : 2.0
 * @description :
 *******************************/

public class AppProducer {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"common.xml", "transaction.xml"});

        IProducerService service = (IProducerService)context.getBean(ProducerServiceImpl.class);

        //发送一个字符串
      service.sendMessage("test2" );

        //发送字符串
       /* for (int i = 0; i < 4; i++) {

            service.sendMessage("test" + i);
        }
*/

        //发送实体
      /*  service.sendMessageObj(new Student("1", "张三"));*/


       /* //发送map
        service.sendMessageMap("mapId", "mapValue");*/

       /* //发送bytes[]消息
        service.sendMessageByte("dsfadgaewgasdvgagsdga".getBytes());*/

     /*   //发送字节流
        service.sendMessageStreamMessage();*/

        //切换到主题模式下再发送几条
     /*  service = (IProducerService)context.getBean(ProducerServiceImplTopic.class);
        for (int i = 0; i < 10; i++) {
            service.sendMessage("test" + i);
        }*/

      //测试MessageListenerAdapter相关
     // service.sendMessageByAdapter("测试MessageListenerAdapter");

    }

}
