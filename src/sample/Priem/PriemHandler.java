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
import sample.User;

import java.util.ArrayList;

/**
 * Created by Хускар on 23.02.2016.
 */
public class PriemHandler extends SimpleChannelInboundHandler<Object> {
    ObservableList<User> uu;
    TableView<User> tb;
    public  PriemHandler(ObservableList<User> uu,TableView<User> tb){
        this.tb=tb;
        this.uu=uu;
    }
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object o) {

        ArrayList<Object> k = (ArrayList) o;
        switch ((String) k.get (0)) {
            case ("404"): {
                System.out.print ("Получил запрос на добавление логина");
                Boolean znak = false;
                for (int i = 0; i < uu.size (); i++) {
                    if (((String) k.get (1)).equals (uu.get (i))) {
                        znak = true;
                        break;
                    }
                }
                if (znak == false) {
                    User user = new User ((String) k.get (1));
                    uu = tb.getItems ();
                    uu.add (user);
                }
            }
        }
    }
}


