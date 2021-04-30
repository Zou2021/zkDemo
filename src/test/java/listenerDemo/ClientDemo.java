package listenerDemo;

import org.apache.zookeeper.ZooKeeper;

import java.util.List;

/**
 * @author: 邹祥发
 * @date: 2021/4/30 08:20
 * 需求：开发一个可以监听zk中servers下面的内容发生变化的程序
 * 如果发生变化，立马执行监听器的方法（打印名单）
 */
public class ClientDemo {
    public static void main(String[] args) throws Exception {
        ClientDemo client = new ClientDemo();
        //1:连接 zk
        client.connect();
        //2:查询所有节点
        client.showServerList();
        //3:保持程序运行
        client.keepRunning();
    }

    //3台zk
    private final static String connectString = "hadoop102:2181,hadoop103:2181,hadoop104:2181";
    private final static int sessionTimeout = 2000;
    private ZooKeeper zk = null;

    private void connect() throws Exception {
        zk = new ZooKeeper(connectString, sessionTimeout, watchedEvent -> {
            //打印名单
            try {
                showServerList();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void showServerList() throws Exception {
        String parentNode = "/servers";
        System.out.println("打印服务器的名单");
        //true表示启用监听器
        List<String> servers = zk.getChildren(parentNode, true);
        System.out.println(servers);

    }

    private void keepRunning(){
        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
