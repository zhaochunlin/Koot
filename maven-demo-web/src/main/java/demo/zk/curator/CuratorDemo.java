

package demo.zk.curator;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import com.netflix.curator.framework.CuratorFramework;
import com.netflix.curator.framework.CuratorFrameworkFactory;
import com.netflix.curator.retry.RetryNTimes;

/**
 * ClassName:CuratorDemo <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015年6月4日 下午2:15:43 <br/>
 * @author chunlin.zhao
 * @version 1.0.0
 */
public class CuratorDemo {

	/**
	 * main:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author chunlin.zhao
	 * @param args
	 * @since 1.0.0
	 * @history
	 */

	public static void main(String[] args) throws Exception{

		 String path = "/test_path";  
	     CuratorFramework client = CuratorFrameworkFactory.builder().connectString("10.100.142.30:2181").namespace("/brokers").retryPolicy(new RetryNTimes(Integer.MAX_VALUE, 1000)).connectionTimeoutMs(5000).build();  
	     // 启动 上面的namespace会作为一个最根的节点在使用时自动创建  
	     client.start();  
	  
	     // 创建一个节点  
	     client.create().forPath("/head", new byte[0]);  
	  
	     // 异步地删除一个节点  
	     client.delete().inBackground().forPath("/head");  
	  
	     // 创建一个临时节点  
	     client.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath("/head/child", new byte[0]);  
	  
	     // 取数据  
	     client.getData().watched().inBackground().forPath("/test");  
	  
	     // 检查路径是否存在  
	     client.checkExists().forPath(path);  
	  
	     // 异步删除  
	     client.delete().inBackground().forPath("/head");  
	  
	     // 注册观察者，当节点变动时触发  
	     client.getData().usingWatcher(new Watcher() {  
	         @Override  
	         public void process(WatchedEvent event) {  
	             System.out.println("node is changed");  
	         }  
	     }).inBackground().forPath("/test");  
	  
	     // 结束使用  
	     client.close();  
	}

}

