package com.spirit.essential.zkClient;

import com.spirit.essential.common.exception.MainStageException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.ACLProvider;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;
import org.springframework.util.StringUtils;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.spirit.essential.common.exception.ExceptionCode.DUPLICATED_REGISTER_EXCEPTION;


@Data
@Slf4j
public class ZkClient {

    private Integer baseSleepTimeMs;
    private Integer maxRetries;
    private Integer sessionTimeoutMs;
    private Integer connectionTimeoutMs;
    private String namespace;
    private String digest;
    private String serverAddr;

    private CuratorFramework client;
    public TreeCache cache;

    private Set<String> listenChildrenPath;

    public ZkClient(String serverAddr, Integer baseSleepTimeMs, Integer maxRetries,
                    Integer sessionTimeoutMs, Integer connectionTimeoutMs,
                    String namespace, String digest){
        this.serverAddr = serverAddr;
        this.baseSleepTimeMs = baseSleepTimeMs;
        this.maxRetries = maxRetries;
        this.sessionTimeoutMs = sessionTimeoutMs;
        this.connectionTimeoutMs = connectionTimeoutMs;
        this.namespace = namespace;
        this.digest = digest;
        listenChildrenPath = new LinkedHashSet();
    }

    public void init() {

        try{
            RetryPolicy retryPolicy = new ExponentialBackoffRetry(baseSleepTimeMs, maxRetries);
            CuratorFrameworkFactory.Builder builder = CuratorFrameworkFactory.builder()
                    .connectString(serverAddr).retryPolicy(retryPolicy)
                    .sessionTimeoutMs(sessionTimeoutMs)
                    .connectionTimeoutMs(connectionTimeoutMs)
                    .namespace(namespace);

            if(!StringUtils.isEmpty(digest)){

                builder.authorization("digest", digest.getBytes("UTF-8"));

                builder.aclProvider(new ACLProvider() {
                    @Override
                    public List<ACL> getDefaultAcl() {
                        return ZooDefs.Ids.CREATOR_ALL_ACL;
                    }

                    @Override
                    public List<ACL> getAclForPath(final String path) {
                        return ZooDefs.Ids.CREATOR_ALL_ACL;
                    }
                });
            }

            client =  builder.build();
            client.start();


            //setListenterThreeThree("/services/translate/providers");
//            TreeCache treeCache = new TreeCache(client, "/essential");
//            treeCache.getListenable().addListener(beanTreeCacheListener);
//            treeCache.start();



//            client.getConnectionStateListenable().addListener(new ConnectionStateListener() {
//                public void stateChanged(CuratorFramework client, ConnectionState state) {
//                    if (state == ConnectionState.LOST) {
//                        //连接丢失
//                        log.info("lost session with zookeeper");
//                    } else if (state == ConnectionState.CONNECTED) {
//                        //连接新建
//                        log.info("connected with zookeeper");
//                    } else if (state == ConnectionState.RECONNECTED) {
//                        log.info("reconnected with zookeeper");
//                    }
//                }
//            });
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //3.Tree Cache
    // 监控 指定节点和节点下的所有的节点的变化--无限监听  可以进行本节点的删除(不在创建)
    private void setListenterThreeThree(String path) throws Exception{
        //ExecutorService pool = Executors.newCachedThreadPool();
        //设置节点的cache
        TreeCache treeCache = new TreeCache(client, path);
        //设置监听器和处理过程
        treeCache.getListenable().addListener(new TreeCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, TreeCacheEvent event) throws Exception {
                ChildData data = event.getData();
                if(data !=null){
                    switch (event.getType()) {
                        case NODE_ADDED:
                            System.out.println("==========> NODE_ADDED : "+ data.getPath() +"  数据:"+ new String(data.getData()));
                            break;
                        case NODE_REMOVED:
                            System.out.println("==========>NODE_REMOVED : "+ data.getPath() +"  数据:"+ new String(data.getData()));
                            break;
                        case NODE_UPDATED:
                            System.out.println("==========>NODE_UPDATED : "+ data.getPath() +"  数据:"+ new String(data.getData()));
                            break;

                        default:
                            break;
                    }
                }else{
                    System.out.println( "========================>data is null : "+ event.getType());
                }
            }
        });
        //开始监听
        treeCache.start();

    }


    public void stop() {
        client.close();
    }

    public CuratorFramework getClient() {
        return client;
    }


    /**
     * 创建节点
     * @param mode       节点类型
     * 1、PERSISTENT 持久化目录节点，存储的数据不会丢失。
     * 2、PERSISTENT_SEQUENTIAL顺序自动编号的持久化目录节点，存储的数据不会丢失
     * 3、EPHEMERAL临时目录节点，一旦创建这个节点的客户端与服务器端口也就是session 超时，这种节点会被自动删除
     *4、EPHEMERAL_SEQUENTIAL临时自动编号节点，一旦创建这个节点的客户端与服务器端口也就是session 超时，这种节点会被自动删除，并且根据当前已近存在的节点数自动加 1，然后返回给客户端已经成功创建的目录节点名。
     * @param path  节点名称
     * @param nodeData  节点数据
     */
    public void createNode(CreateMode mode, String path , byte[] nodeData) throws MainStageException {
        try {
            //使用creatingParentContainersIfNeeded()之后Curator能够自动递归创建所有所需的父节点
            client.create().creatingParentsIfNeeded().withMode(mode).forPath(path, nodeData);
        } catch (Exception e) {
            throw new MainStageException(e.getMessage());
        }
    }

    /**
     * 创建节点
     * @param mode       节点类型
     *                   1、PERSISTENT 持久化目录节点，存储的数据不会丢失。
     *                   2、PERSISTENT_SEQUENTIAL顺序自动编号的持久化目录节点，存储的数据不会丢失
     *                   3、EPHEMERAL临时目录节点，一旦创建这个节点的客户端与服务器端口也就是session 超时，这种节点会被自动删除
     *                   4、EPHEMERAL_SEQUENTIAL临时自动编号节点，一旦创建这个节点的客户端与服务器端口也就是session 超时，这种节点会被自动删除，并且根据当前已近存在的节点数自动加 1，然后返回给客户端已经成功创建的目录节点名。
     * @param path  节点名称
     */
    public void createNode(CreateMode mode, String path ) {
        try {
            //使用creatingParentContainersIfNeeded()之后Curator能够自动递归创建所有所需的父节点
            client.create().creatingParentsIfNeeded().withMode(mode).forPath(path);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage(), e);
        }
    }

    public void deleteNode(final String path) {
        try {
            deleteNode(path,true);
        } catch (Exception ex) {
            log.error("{}",ex);
        }
    }

    public void deleteNode(final String path, Boolean deleteChildre){

        log.info("revoke node: {}", path);

        try {
            if(deleteChildre){
                //guaranteed()删除一个节点，强制保证删除,
                // 只要客户端会话有效，那么Curator会在后台持续进行删除操作，直到删除节点成功
                client.delete().guaranteed().deletingChildrenIfNeeded().forPath(path);
            }else{
                client.delete().guaranteed().forPath(path);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 设置指定节点的数据
     * @param path
     * @param datas
     */
    public void setNodeData(String path, byte[] datas) throws MainStageException {
        try {
            client.setData().forPath(path, datas);
        }catch (Exception e) {
            log.error(e.getLocalizedMessage(), e);
            throw new MainStageException(e.getMessage());
        }
    }

    /**
     * 获取指定节点的数据
     * @param path
     * @return
     */
    public byte[] getNodeData(String path){
        Byte[] bytes = null;
        try {
            if(cache != null){
                ChildData data = cache.getCurrentData(path);
                if(data != null){
                    return data.getData();
                }
            }
            client.getData().forPath(path);
            return client.getData().forPath(path);
        }catch (Exception ex) {
            log.error("{}",ex);
        }
        return null;
    }

    /**
     * 获取数据时先同步
     * @param path
     * @return
     */
    public byte[] synNodeData(String path){
        client.sync();
        return getNodeData( path);
    }

    /**
     * 判断路径是否存在
     *
     * @param path
     * @return
     */
    public boolean isExistNode(final String path) {
        client.sync();
        try {
            return null != client.checkExists().forPath(path);
        } catch (Exception ex) {
            return false;
        }
    }


    /**
     * 获取节点的子节点
     * @param path
     * @return
     */
    public List<String> getChildren(String path) {
        List<String> childrenList = new ArrayList<>();
        try {
            childrenList = client.getChildren().forPath(path);
        } catch (Exception e) {
            log.error("获取子节点出错", e);
        }
        return childrenList;
    }

    /**
     * 随机读取一个path子路径, "/"为根节点对应该namespace
     * 先从cache中读取，如果没有，再从zookeeper中查询
     * @param path
     * @return
     * @throws Exception
     */
    public String getRandomData(String path)  {
        try{
            Map<String,ChildData> cacheMap = cache.getCurrentChildren(path);
            if(cacheMap != null && cacheMap.size() > 0) {
                log.debug("get random value from cache,path="+path);
                Collection<ChildData> values = cacheMap.values();
                List<ChildData> list = new ArrayList<>(values);
                Random rand = new Random();
                byte[] b = list.get(rand.nextInt(list.size())).getData();
                return new String(b,"utf-8");
            }
            if(isExistNode(path)) {
                log.debug("path [{}] is not exists,return null",path);
                return null;
            } else {
                log.debug("read random from zookeeper,path="+path);
                List<String> list = client.getChildren().forPath(path);
                if(list == null || list.size() == 0) {
                    log.debug("path [{}] has no children return null",path);
                    return null;
                }
                Random rand = new Random();
                String child = list.get(rand.nextInt(list.size()));
                path = path + "/" + child;
                byte[] b = client.getData().forPath(path);
                String value = new String(b,"utf-8");
                return value;
            }
        }catch(Exception e){
            log.error("{}",e);
        }
        return null;

    }

//    /**
//     * 可重入共享锁  -- Shared Reentrant Lock
//     * @param lockPath
//     * @param time
//     * @param dealWork 获取
//     * @return
//     */
//    public Object getSRLock(String lockPath,long time, SRLockDealCallback<?> dealWork){
//        InterProcessMutex lock = new InterProcessMutex(client, lockPath);
//        try {
//            if (!lock.acquire(time, TimeUnit.SECONDS)) {
//                log.error("get lock fail:{}", " could not acquire the lock");
//                return null;
//            }
//            log.debug("{} get the lock",lockPath);
//            Object b = dealWork.deal();
//            return b;
//        }catch(Exception e){
//            log.error("{}", e);
//        }finally{
//            try {
//                lock.release();
//            } catch (Exception e) {
//                //log.error("{}",e);
//            }
//        }
//        return null;
//    }

    /**
     * 获取读写锁
     * @param path
     * @return
     */
    public InterProcessReadWriteLock getReadWriteLock(String path){
        InterProcessReadWriteLock readWriteLock = new InterProcessReadWriteLock(client, path);
        return readWriteLock;
    }

    /**
     * 在注册监听器的时候，如果传入此参数，当事件触发时，逻辑由线程池处理
     */
    ExecutorService pool = Executors.newFixedThreadPool(2);

    /**
     * 监听数据节点的变化情况
     * @param watchPath
     * @param listener
     */
    public void watchPath(String watchPath,TreeCacheListener listener){
        //   NodeCache nodeCache = new NodeCache(client, watchPath, false);
        TreeCache cache = new TreeCache(client, watchPath);
        cache.getListenable().addListener(listener, pool);
        try {
            cache.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PathChildrenCache watchPathChildren(String nodePath, PathChildrenCacheListener listener) {

        listenChildrenPath.add(nodePath);

        try {
            //1. 创建一个PathChildrenCache
            PathChildrenCache pathChildrenCache = new PathChildrenCache(client, nodePath, true);

            //2. 添加目录监听器
            pathChildrenCache.getListenable().addListener(listener);

            //3. 启动监听器
            pathChildrenCache.start(PathChildrenCache.StartMode.BUILD_INITIAL_CACHE);

            return pathChildrenCache;
        } catch (Exception e) {
            log.error(e.getLocalizedMessage(), e);
        }
        return null;
    }

    public PathChildrenCache watchPathChildrenExclusive(String nodePath, PathChildrenCacheListener listener) throws MainStageException {

        if (listenChildrenPath.contains(nodePath)) {
            throw new MainStageException(DUPLICATED_REGISTER_EXCEPTION);
        }
        return watchPathChildren(nodePath, listener);
    }
}

