package sample.Priem;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import sample.SmartHouseClientHandler;
import sample.SmartHouseClientInitializer;
import sample.User;

/**
 * Created by Хускар on 23.02.2016.
 */
public class Priem implements Runnable {
    ObservableList<User> uu;
    TableView<User> tb;

    public Priem() {
    }

    public Priem(ObservableList<User> uu, TableView<User> tb) {
        this.tb = tb;
        this.uu = uu;
    }

    EventLoopGroup group = new NioEventLoopGroup ();
    ServerBootstrap b = new ServerBootstrap ();
    public void run(){
        Bootstrap b = new Bootstrap ();
        b.group (group)
                .channel (NioSocketChannel.class)
                .handler ((new PriemInitializer (tb,uu)));
        Channel channel = null;
        System.out.println ("Пытаюсь подключиться к серверу!");
        try {
            channel = b.connect ("127.0.0.1", 9990).sync ().channel ();//88.201.204.21
        } catch (InterruptedException e) {
            e.printStackTrace ();
        }
        System.out.println ("Подключение удалось!");
        PriemHandler handler = channel.pipeline ().get (PriemHandler.class);
        handler.write (channel);
    }
   /* public void run()  {
        b.group(group)
                .channel(NioServerSocketChannel.class)
                .childHandler(new PriemHandler (uu,tb));

        ChannelFuture future= null;
        try {
            future = b.bind(9990).sync();
        } catch (InterruptedException e) {
            e.printStackTrace ();
        }
        try {
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace ();
        }
        group.shutdownGracefully();
    }*/
}