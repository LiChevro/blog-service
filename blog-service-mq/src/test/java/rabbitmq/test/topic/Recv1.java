package rabbitmq.test.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import rabbitmq.test.ConnectionUtil;

/**
 * Author: Chevro.Lee <br>
 * Description:
 * Date: Create in  10:26 2019-10-16
 **/
public class Recv1 {

    private final static String QUEUE_NAME = "test_queue_topic_work_1";

    private final static String EXCHANGE_NAME = "test_exchange_topic";

    public static void main(String[] argv) throws Exception {
        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 绑定队列到交换机
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "routeKey.*");
        // 同一时刻服务器只会发一条消息给消费者
        channel.basicQos(1);
        // 定义队列的消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        // 监听队列，手动返回完成
        channel.basicConsume(QUEUE_NAME, false, consumer);
        // 获取消息
        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" [Recv_x] Received '" + message + "'");
            Thread.sleep(10);
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }
    }
}
