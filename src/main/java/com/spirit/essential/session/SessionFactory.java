package com.spirit.essential.session;

import com.spirit.essential.common.CommonDef;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class SessionFactory extends Session {

    public int add(ChannelHandlerContext ctx, ServiceStatus status) {

        log.info("add ctx: {}", ctx);

        if (CommonDef.SERVICE_TYPE_CONSUMER == status.getType()) {

            consumerSessionMap.put(ctx, status);

            List<ChannelHandlerContext> list = pathChannelHandlerContextListRelationship.get(status.getPath());
            if (CollectionUtils.isEmpty(list)) {
                List<ChannelHandlerContext> tmp = new LinkedList<>();
                tmp.add(ctx);
                pathChannelHandlerContextListRelationship.put(status.getPath(), tmp);
            }
            else {
                if (!list.contains(ctx)) {
                    list.add(ctx);
                }
            }
        }
        else if (CommonDef.SERVICE_TYPE_PROVIDER == status.getType()) {
            providerSessionMap.put(ctx, status);
        }

        return 0;
    }

    public List<ChannelHandlerContext> context(String path) {
        return pathChannelHandlerContextListRelationship.get(path);
    }

    public String remove(ChannelHandlerContext ctx) {

        log.info("remove ctx: {}", ctx);

        boolean phit = false;
        boolean chit = false;

        try {
            if (providerSessionMap.containsKey(ctx)) {
                phit = true;
                ServiceStatus status = providerSessionMap.get(ctx);
                return status.getPath();
            }
            else if (consumerSessionMap.containsKey(ctx)) {
                chit = true;
                ServiceStatus status = consumerSessionMap.get(ctx);
                List<ChannelHandlerContext> list = pathChannelHandlerContextListRelationship.get(status.getPath());
                if (list.contains(ctx)) {
                    list.remove(ctx);
                }
            }
        } catch (Exception e) {
            log.error(e.getLocalizedMessage(), e);
        } finally {
            if (phit) {
                providerSessionMap.remove(ctx);
            }
            if (chit) {
                consumerSessionMap.remove(ctx);
            }
        }
        return null;
    }
}



