package com.easylab.jms.consumer;

import com.easylab.jms.entity.Student;

public class  myListenner {
    //方法里面的参数可以是那五种消息类型，在接受的时候会自动通过反射转换，而不是想别的监听器需要在onmessage房中手动转换
    public void handleMessage(Student message) {
        System.out.println("ConsumerListener通过handleMessage接收到一个纯文本消息，消息内容是：" + message.toString());
    }

    public void receiveMessage(Student message) {
        System.out.println("ConsumerListener通过receiveMessage接收到一个纯文本消息，消息内容是：" + message.toString());
    }

    public void handleMessage(String message) {
        System.out.println("ConsumerListener通过handleMessage接收到一个纯文本消息，消息内容是：" + message.toString());
    }

    //一旦defaultListenerMethod设置的方法，返回值不是null，就说明要把返回值自动回复给发送者
    public String receiveMessage(String message) {
        System.out.println("ConsumerListener通过receiveMessage接收到一个纯文本消息，消息内容是：" + message.toString());
        return"自动给你回复消息了哦，你接到了吗？";
    }
}
