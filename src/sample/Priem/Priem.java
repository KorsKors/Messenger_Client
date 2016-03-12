package sample.Priem;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import sample.User;

/**
 * Created by Хускар on 23.02.2016.
 */
public class Priem implements Runnable{
    ObservableList<User> uu;
    TableView<User> tb;
    public  Priem(){}
    public  Priem(ObservableList<User> uu,TableView<User> tb){
        this.tb=tb;
        this.uu=uu;
    }
    EventLoopGroup group = new NioEventLoopGroup ();
    ServerBootstrap b = new ServerBootstrap();

    public void run()  {
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
    }
}
