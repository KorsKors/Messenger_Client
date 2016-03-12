package sample;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Хускар on 20.02.2016.
 */
public class DB {
   /* public static boolean proverka(String login) {
        Statement stat = null;
        Connection connect = null;
        ResultSet r = null;
        try {
            connect = DriverManager.getConnection ("jdbc:h2:file://F:\\JAVA\\Messenger with DB\\Tet\\db\\users", "", "");
            stat = connect.createStatement ();
            r = stat.executeQuery ("SELECT * FROM " + login + ";");
            return true;
        } catch (SQLException e) {
            return false;

            //e.printStackTrace ();
        } finally {
            try {
                if (connect != null) connect.close ();
                if (stat != null) stat.close ();
                if (r != null) r.close ();
            } catch (SQLException e) {
                return false;
            }
        }
    }//Проверка на наличие таблицы этого пользователя

    public static Boolean createTableUser(String login) {

        Connection connection = null;
        ResultSet r = null;
        Statement stat = null;
        try {
            Class.forName ("org.h2.Driver");
            connection = DriverManager.getConnection ("jdbc:h2:file://F:\\JAVA\\Messenger with DB\\Tet\\db\\users", "", "");
            //stat = connection.prepareStatement("CREATE TABLE "+login+" (Friends CHAR not null );");
            //stat.executeUpdate();
            stat = connection.createStatement ();
            stat.execute ("CREATE TABLE " + login + " (Friends CHAR not null);");
            //r = stat.executeQuery("CREATE TABLE '"+login+"' (Friends CHAR not null );");

            return true;

        }
        //stat = conn.createStatement();
        // r = stat.executeQuery("SELECT * FROM USER");

        //  while (r.next()) {
        //     System.out.print(r.getString("Login"));
        // }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace ();
            return false;
        } finally {
            try {
                if (connection != null) connection.close ();
                if (stat != null) stat.close ();

            } catch (SQLException e) {
                return false;
            }

        }
    }//Создание базы данных

    public static Boolean addUser(ArrayList ar,String login) {
        PreparedStatement stat = null;
        Connection conn = null;
        ResultSet r = null;
        try {
            System.out.println ("Синхронизация началась");
            for (int i=0;i<ar.size ();i++){
                System.out.println ("Выгружаю данные в БД:"+ar.get(i));
            }
            Class.forName ("org.h2.Driver");
            conn = DriverManager.getConnection ("jdbc:h2:file://F:\\JAVA\\Messenger with DB\\Tet\\db\\users", "", "");

            String sql = "INSERT INTO "+login+"(FRIENDS) VALUES (?)";

            //stat.setInt(1, id);
            for (int i = 0; i < ar.size (); i++) {
                stat = conn.prepareStatement (sql);
                System.out.print ("Добавляю в БД"+ar.get (i));
                stat.setString (1, (String) ar.get (i));
                stat.executeUpdate ();
            }
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace ();
            return false;
        } finally {
            try {
                if (conn != null) conn.close ();
                if (stat != null) stat.close ();
            } catch (SQLException e) {
            }

        }
    }//Синхранизируем базу данных клиентов пользователя с сервером

    public static Boolean addUserOne(String my,String str) {
        PreparedStatement stat = null;
        Connection conn = null;
        ResultSet r = null;
        try {
            Class.forName ("org.h2.Driver");
            conn = DriverManager.getConnection ("jdbc:h2:file://F:\\JAVA\\Messenger with DB\\Tet\\db\\users", "", "");

            String sql = "INSERT INTO "+my+"(Friends) VALUES (?)";
            stat = conn.prepareStatement (sql);
            //stat.setInt(1, id);
            stat.setString (1, str);

            stat.executeUpdate ();
            System.out.print("addUserOne клиент добавлен");
            return true;

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace ();
            return false;
        } finally {
            try {
                if (conn != null) conn.close ();
                if (stat != null) stat.close ();
            } catch (SQLException e) {
            }

        }
    }//добавление пользователя в базу данных клиента

    public static Boolean search(String my,String login) {
        String log=null;
        Statement stat = null;
        Connection connect = null;
        ResultSet r = null;
        try {
            connect = DriverManager.getConnection("jdbc:h2:file://F:\\JAVA\\Messenger with DB\\Tet\\db\\users", "", "");
            stat = connect.createStatement();
            r = stat.executeQuery("SELECT * FROM "+my+" WHERE FRiends ='" + login + "';");
            while (r.next()) {
                log = r.getString("Name");
                System.out.println(r.getString("Name"));
            }
            if (log == null) {
                System.out.println(" Такого логина нет в базе данных!" + log);
                return true;
            } else if (log != null) {
                System.out.println("Такой логин есть в базе данных:" + log);
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (r != null) r.close();
                if (connect != null) connect.close();
                if (stat != null) stat.close();
            } catch (SQLException e) {
            }

        }
        return null;
    }//поиск в базе данных

    public static ArrayList output(String login){
        ArrayList<Object> arr= new ArrayList<Object>();
        Statement stat = null;
        Connection connect = null;
        ResultSet r = null;
        try {
            connect = DriverManager.getConnection("jdbc:h2:file://F:\\JAVA\\Messenger with DB\\Tet\\db\\users", "", "");
            stat = connect.createStatement();
            r = stat.executeQuery("SELECT Friends FROM "+login+";");
            while (r.next()) {
                arr.add(r.getString("Friends"));
                //System.out.println(r.getString("Login"));
            }

            return arr;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connect != null) connect.close();
                if (stat != null) stat.close();
                if (r != null) r.close();
            } catch (SQLException e) {
            }
        }
        return arr;
    }*/
}
//insert into user(name) values ('asdasd');