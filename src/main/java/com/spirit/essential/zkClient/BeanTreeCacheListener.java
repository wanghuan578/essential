package com.spirit.essential.zkClient;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.springframework.stereotype.Component;


@Component
public class BeanTreeCacheListener implements TreeCacheListener {

    @Override
    public void childEvent(CuratorFramework curatorFramework, TreeCacheEvent treeCacheEvent) throws Exception {

        ChildData data = treeCacheEvent.getData();
        if (data == null)
            return;

        String path = data.getPath();
        switch (treeCacheEvent.getType()) {
            case NODE_ADDED:
                if (path.split("/").length == 3) {
                    String val = new String(curatorFramework.getData().forPath(path), "UTF-8");
                    System.out.println("NODE_ADDED " + val);
                }
                break;
            case NODE_UPDATED:
                if (path.split("/").length == 3) {
                    String val = new String(curatorFramework.getData().forPath(path), "UTF-8");
                    System.out.println("NODE_UPDATED " + val);
                }
                break;
            case NODE_REMOVED:
                if (path.split("/").length == 3) {
                    System.out.println("NODE_REMOVED " + path);
                }
                break;

            default:
                break;
        }
    }
}
