package com.spirit.essential.client;


import com.spirit.essential.netty.NettyClient;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        //NettyClient client = new NettyClient("192.168.219.133", 9000);
        NettyClient client = new NettyClient("192.168.131.42", 9999);

        //启动client服务
        try {
			client.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
        while(true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}
