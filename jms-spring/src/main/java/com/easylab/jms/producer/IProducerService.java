package com.easylab.jms.producer;

import com.easylab.jms.entity.Student;

import javax.jms.ObjectMessage;

/******************************
 * @author : liuyuan
 * <p>ProjectName:jms-spring  </p>
 * @ClassName :  IProducerService
 * @date : 2018/6/24 0024
 * @time : 8:41
 * @createTime 2018-06-24 8:41
 * @version : 2.0
 * @description :
 *
 *
 *
 *******************************/


public interface IProducerService {


    public void sendMessage(String message);

    public void sendMessageObj(Student message);

    void sendMessageMap(String mapId, String mapValue);

    void sendMessageByte(byte[] bytes);

    void sendMessageStreamMessage();

    void sendMessageByAdapter(String message);

    void sendMessageT(String message);
}
