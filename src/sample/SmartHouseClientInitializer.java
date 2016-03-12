package sample;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

/**
 * Created by ������ on 19.01.2016.
 */
public class SmartHouseClientInitializer extends ChannelInitializer<SocketChannel> {
    MyCallBack mcb;
    ObservableList<User> uu;
    Main m;
    TableView<User> tb;
    public SmartHouseClientInitializer(TableView<User> tb, ObservableList<User> uu){
        this.tb=tb;
        this.uu=uu;
    }
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline p = socketChannel.pipeline();
        //p.addLast(new StringDecoder());
       // p.addLast(new StringEncoder());
        p.addLast(new ObjectDecoder(ClassResolvers.weakCachingConcurrentResolver(null)));
        p.addLast(new ObjectEncoder());
        p.addLast(new SmartHouseClientHandler(mcb));

    }
}
