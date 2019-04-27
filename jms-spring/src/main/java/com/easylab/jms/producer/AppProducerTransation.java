package com.easylab.jms.producer;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;

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


public class AppProducerTransation {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("transaction.xml");

        IProducerService service = (IProducerService)context.getBean(ProducerServiceImplTransaction.class);


        //事务管理
        for (int i = 2; i < 3; i++) {
            service.sendMessageT("test" + i);
        }

    }

}
