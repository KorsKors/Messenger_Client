package sample;

import io.netty.channel.*;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Хускар on 19.01.2016.
 */
public class SmartHouseClientHandler extends SimpleChannelInboundHandler<Object> {
private final BlockingQueue<String> block= new LinkedBlockingQueue<String>();
    private final BlockingQueue<Boolean> bllo= new LinkedBlockingQueue<Boolean>();
    ArrayList<String>spisok=new ArrayList<String> ();
    ObservableList<User> uu;
    Channel channel;
    static Boolean s;
     Main m;

    Client client;
    MyCallBack mCallBack;
    ChannelPipeline p;
    TableView<User> tb;
    public SmartHouseClientHandler (MyCallBack callBack ){
        mCallBack = callBack;
    }
   // public void channelReadComplete(ChannelHandlerContext ctx)  {
       // ctx.close();
    //}

public  SmartHouseClientHandler(TableView<User> tb){
        this.tb=tb;
    }
    public Boolean addUser(Channel channel, ArrayList convert, Client client) throws  InterruptedException{
        try{
        this.client=client;
        channel.writeAndFlush(convert);
        Boolean z = bllo.take();
            block.clear ();
            return z;
        }
        //ChannelFuture future=channel.writeAndFlush (convert);
        //future.addListener(ChannelFutureListener.CLOSE);
            catch(InterruptedException e) {}
        channel.close();
        return null;
    }
    public String search(Channel channel, ArrayList convert) throws  InterruptedException{
        try{
            this.client=client;
            channel.writeAndFlush(convert);
           // Boolean z = block.take();
            String z = block.take ();
            block.clear ();
            return z;
        }

        catch(InterruptedException e) {}
        channel.close();
        return null;
    }
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object o) throws Exception {
        //String s=(String)o;РАБОТАЕТ
        //System.out.print(s);РАБОТАЕТ
        ArrayList<Object> k=(ArrayList)o;
        switch ((String)k.get(0)) {
            case("101"):{
                bllo.add((Boolean)k.get(1));
                return;
           }
            case("202"):{bllo.add((Boolean)k.get(1));
            return;}
            case("2021"):{
                for (int i=1;i<k.size ();i++){
                    spisok.add((String)k.get(i));}
                    //System.out.println("Массив:"+spisok.get(i));}
                    block.add((String)k.get(0));

                return;}
            case("303"):{
                //System.out.println ("Дошла до сюда");
                //mCallBack.onGetResult ((String) k.get (0));
                if(k.get(1)!=null){
                    System.out.println("Логин нашего контакта"+k.get(1));
                block.add ((String) k.get (1));}
                else block.add("0");
                return;
            }
            case("404"):{
                System.out.print("Получил запрос на добавление логина");
                Boolean znak=false;
                for(int i =0;i<uu.size ();i++){
                    if(((String)k.get(1)).equals (uu.get(i))){
                        znak=true;
                        break;
                    }
                }
                if(znak==false){
                User user = new User ((String)k.get(1));
                uu = tb.getItems ();
                uu.add (user);}
            }
        }
      //  s=(Boolean)o;
      //  System.out.println ("Получил "+s);
       // mCallBack.onGetResult (s);
        //client.closes (s);
        //prov(s);

    }
    public  ArrayList vigruzka(Channel channel,ArrayList mylogin) throws InterruptedException {
        channel.writeAndFlush(mylogin);
        // Boolean z = block.take();
        String z = block.take ();
        block.clear ();
        return spisok;
    }

}
