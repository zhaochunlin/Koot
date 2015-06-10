

package demo.zk;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ClassName:ZKDemo01 <br/>
 * Function: 创建连接，完成一个基本的会话. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015年6月4日 上午8:39:46 <br/>
 * @author chunlin.zhao
 * @version 1.0.0
 */
public class ZKDemo01 implements Watcher{

	static Logger logger = LoggerFactory.getLogger(ZKDemo01.class);
	
	private static CountDownLatch cd = new CountDownLatch(1);
	private static ZooKeeper zk = null;
	private static String rootPath = "/ZKDemo";
	
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

	public static void main(String[] args) {
		
		
		byte[] data = "".getBytes();
		
		ZKDemo01 zkd = new ZKDemo01();
		// TODO Auto-generated method stub
		try {
			zk = new ZooKeeper("10.100.142.30:2181", 5000, zkd);
			System.out.println(zk.getState());
			cd.await();
			Stat rootStat = zk.exists(rootPath, false);
			if(null == rootStat){

				String p1 = zk.create(rootPath, data, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
				logger.info("Sucess create znode: {}", p1);
			}
			logger.info("RootStat:{}", rootStat);
			String path = "/zk-test-ephemeral-";
			String p1 = zk.create(rootPath + path, data, Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
			logger.info("Sucess create znode: {}", p1);
			// 查询子节点
			List<String> kids = zk.getChildren(rootPath, true);
			logger.info("Root Kids :{}", kids);
			p1 = zk.create(rootPath + path, data, Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
			logger.info("Sucess create znode: {}", p1);
			
			kids = zk.getChildren(rootPath, false);
			for (String kid:kids) {
				logger.info("del children node:{}", kid);
				zk.delete(rootPath + "/"+ kid, 0);
			}
			zk.delete(rootPath, 0);
		} catch (IOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (KeeperException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (InterruptedException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		System.out.println("ZooKeeper session established.");
	}

	
	/**
	* TODO 简单描述该方法的实现功能（可选）.
	* @see org.apache.zookeeper.Watcher#process(org.apache.zookeeper.WatchedEvent)
	*/
	
	@Override
	public void process(WatchedEvent event) {
		
		// TODO Auto-generated method stub
		System.out.println("Receive watched event:"+event);
		
		
		if(KeeperState.SyncConnected == event.getState()){
			if(EventType.None == event.getType() && null == event.getPath()) {
				cd.countDown();
			}else if(event.getType() == EventType.NodeChildrenChanged){
				try {
					List<String> kids = zk.getChildren(rootPath, true);
					logger.info("Reget Root Kids :{}", kids);
				} catch (KeeperException | InterruptedException e) {
					
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
			}
		}
	}

}

