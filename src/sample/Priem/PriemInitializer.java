package sample.Priem;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import sample.Main;
import sample.User;

/**
 * Created by Хускар on 23.02.2016.
 */
public class PriemInitializer  extends ChannelInitializer<SocketChannel> {
    ObservableList<User> uu;
    TableView<User> tb;
    public PriemInitializer(TableView<User> tb, ObservableList<User> uu){
        this.tb=tb;
        this.uu=uu;
    }
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline p = socketChannel.pipeline();
        // p.addLast(new StringDecoder());
        //p.addLast(new StringEncoder());
        p.addLast(new ObjectDecoder (ClassResolvers.weakCachingConcurrentResolver(null)));
        p.addLast(new ObjectEncoder ());
        p.addLast( new PriemHandler (uu,tb));
    }
}