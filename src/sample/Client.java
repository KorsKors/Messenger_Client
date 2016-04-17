package sample;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Хускар on 19.01.2016.
 */
public class Client implements MyCallBack {
    ObservableList<User> uu;
    ArrayList ar = new ArrayList ();
    static String mylogin;
    MyCallBack my;
    User user;
    Channel channel = null;
    EventLoopGroup group = new NioEventLoopGroup ();
    static String str;
    Boolean rez;
    TableView tb;

    public Client() {
    }

    public Client(ObservableList<User> uu, TableView tb) {
        this.uu = uu;
        this.tb = tb;
    }

    public Client(ArrayList ar) {
        this.ar = ar;
    }

    public Client(User user, Main main, MyCallBack my) {
        this.user = user;
        this.main = main;
        this.my = my;
    }

    public Client(String str) {
        this.str = str;
    }

    public void addUser() throws InterruptedException {

    }

    public Boolean runs(Integer s) {
        try {
            Bootstrap b = new Bootstrap ();
            b.group (group)
                    .channel (NioSocketChannel.class)
                    .handler ((new SmartHouseClientInitializer (tb,uu)));
            channel = null;
            System.out.println ("Пытаюсь подключиться к серверу!");
            channel = b.connect ("127.0.0.1", 9989).sync ().channel ();//88.201.204.21
            System.out.println ("Подключение удалось!");
            switch (s) {
                case (101): {//Запрос на регистрацию
                    Boolean t = sendData (channel);
                    //channel.close ();

                    return t;

                }
                case (202): {//Запрос на авторизацию
                    Boolean t = auten (channel);
                    //channel.close ();
                    return t;
                }
                case (2021): {//Запрос на выгрузку списка контактов(прозвон онлайнов)
                    // ArrayList<Object> or=new ArrayList<Object> ();
                    ar = vigruzka (channel, mylogin);
                    Osnova.input = ar;
                    for (int i = 0; i < ar.size (); i++) {
                        System.out.println ("Контакт:" + ar.get (i));
                    }
                    return true;
                }
                case (303): {//Запрос на поиск юзера
                    System.out.print ("Поиск юзера Сlient.runs303:" + str);

                    String t = search (channel, mylogin, str);
                    System.out.println("Добавляем нового пользователя"+t);
                   // channel.close ();
                    if (!t.equals ("0")) {
                        // if(DB.search (mylogin,str)) {
                        System.out.println ("Ник в базу данных:" + str);
                        // DB.addUserOne (mylogin,str);
                        boolean n = false;
                        for (int i = 0; i < uu.size (); i++) {
                            if (str.equals (uu.get (i))) {
                                n = true;
                                break;
                            }
                        }
                        if (!n) {
                            System.out.println("Добавляем нового пользователя");
                            User user = new User (str);
                            uu = tb.getItems ();
                            uu.add (user);
                            for (int i = 0; i < uu.size (); i++) {
                                System.out.print (uu.get (i));
                            }
                            return true;
                        }
                        //return true;}
                        // else return false;
                        //нужно обновить в форме список контактов

                    } else
                        System.out.print ("Не найдено!");
                    return false;
                }
            }

            //
            //System.out.print("Канал создан!");


            // SmartHouseClientHandler handler = channel.pipeline().get(SmartHouseClientHandler.class);
            //ArrayList<Object>convert=convert(user);
            //Boolean t=handler.addUser(channel, convert,this);

            //System.out.print (t);


            // addUser (channel,convert);
            // channel.close();

            // try {//Эти строки нужны чтобы всё четко работало!
            //    sendData(channel);
            //} catch (IOException e) {
            //    e.printStackTrace();
            //  }*/
        } catch (ConnectException e) {
            //e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace ();
        } catch (IOException e) {
            e.printStackTrace ();
        } finally

        {

          //  group.shutdownGracefully ();
        }
        return null;
    }

    Main main;

    //отоправляем данные на конвертацию и отправляем на сервер User для проверки возможности регистрации
    public Boolean sendData(Channel channel) throws InterruptedException, IOException {
        SmartHouseClientHandler handler = channel.pipeline ().get (SmartHouseClientHandler.class);
        ArrayList<Object> convert = convert (user, 101);
        Boolean s = handler.addUser (channel, convert, this);
        return s;
    }

    public ArrayList<Object> vigruzka(Channel channel, String mylogin) {
        SmartHouseClientHandler handler = channel.pipeline ().get (SmartHouseClientHandler.class);
        ArrayList<Object> otpr = new ArrayList<Object> ();
        otpr.add ("2021");
        otpr.add (mylogin);
        try {
            ArrayList<Object> poluch = handler.vigruzka (channel, otpr);

            return poluch;
        } catch (InterruptedException e) {
            e.printStackTrace ();
        }
        return null;
    }

    public String search(Channel channel, String mylogin, String loginsearch) {
        SmartHouseClientHandler handler = channel.pipeline ().get (SmartHouseClientHandler.class);
        ArrayList<Object> otpr = new ArrayList<Object> ();
        otpr.add ("303");
        otpr.add (mylogin);
        otpr.add (loginsearch);
        try {
            String s = handler.search (channel, otpr);
            return s;
        } catch (InterruptedException e) {
            e.printStackTrace ();
        }
        return null;
    }//поиск клиента по нику

    //Отправляем данные на сервер для проверки возможности войти в программу
    public Boolean auten(Channel channel) throws InterruptedException, IOException {
        SmartHouseClientHandler handler = channel.pipeline ().get (SmartHouseClientHandler.class);
        ArrayList<Object> convert = convert (user, 202);
        Boolean s = handler.addUser (channel, convert, this);
        return s;
    }

    //Установление внешнего IP адреса
    public static String ipadress() throws IOException {
        URL whatismyip = new URL ("http://checkip.amazonaws.com");
        BufferedReader in = new BufferedReader (new InputStreamReader (
                whatismyip.openStream ()));

        String ip = in.readLine (); //you get the IP as a String
        return ip;
    }//Получаю внешний адрес сервера

    public ArrayList convert(User user, Integer in) throws IOException {
        ArrayList<Object> kk = new ArrayList<Object> ();
        user.setID (1);
        Integer userID = user.getID ();
        String userIDStr = userID.toString ();
        //Integer userIP= user.getIP();
        user.setIP (ipadress ());
        String command = in.toString ();
        kk.add (command);
        //kk.add (userID);
        kk.add (user.getLogin ());
        kk.add (user.getPassword ());
        kk.add (user.getIP ());

        return kk;


    }//Конвертируем экземпляр User ArrayList чтобы передавать

    public void user(String login, String password) {

    }

    @Override
    public void onGetResult(String str) {
        this.str = str;

    }
}
