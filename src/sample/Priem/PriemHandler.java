package sample.Priem;

import io.netty.channel.*;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import sample.User;

import java.util.ArrayList;

/**
 * Created by Хускар on 23.02.2016.
 */
public class PriemHandler extends SimpleChannelInboundHandler<Object> {
    ObservableList<User> uu;
    TableView<User> tb;

    public PriemHandler(ObservableList<User> uu, TableView<User> tb) {
        this.tb = tb;
        this.uu = uu;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object o) {

        ArrayList<Object> k = (ArrayList) o;
        switch ((String) k.get (0)) {

        }
    }
    protected void write(Channel channel){
        while(true){
        channel.writeAndFlush(404505);}
    }
}



