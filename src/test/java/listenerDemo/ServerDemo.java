package listenerDemo;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

/**
 * @author: 邹祥发
 * @date: 2021/4/30 09:21
 * 需求：不需要监听，当前是服务器，只要上线的时候，创建servers的子文件夹
 * 下线的时候，删除名字对应的文件夹
 */
public class ServerDemo {
    public static void main(String[] args) throws Exception {
        ServerDemo client = new ServerDemo();
        //1:连接 zk
        client.connect();
        //2:创建servers的子文件夹
        client.createNode(args[0]);
        //3:保持程序运行
        client.keepRunning();
    }

    private void createNode(String hostname) throws Exception {
        String parentNode = "/servers";
        zk.create(parentNode + "/" + hostname, hostname.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
    }

    //3台zk
    private final static String connectString = "hadoop102:2181,hadoop103:2181,hadoop104:2181";
    private final static int sessionTimeout = 2000;
    private ZooKeeper zk = null;

    private void connect() throws Exception {
        zk = new ZooKeeper(connectString, sessionTimeout, watchedEvent -> {
        });
    }

    private void keepRunning() {
        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
