package com.company;

import java.util.ArrayList;
import java.util.List;

interface Observable {                //издатель всем переданным ему Observer-объектам (подписчикам) сообщит о том, что событие наступило.
    void notifyObservers();           //При получении нового сообщения отправляется оповещение всем наблюдателям
    void regObserver(Observer o);
}
interface Observer{
    void Notification(String news);
}
class Groups implements Observable{
    List<Observer> list = new ArrayList<>();
    String news;
    void setNews(String news){
        this.news = news;
        notifyObservers();
    }

    @Override
    public void notifyObservers() {
        for(Observer o: list){
            o.Notification(news);
        }
    }

    @Override
    public void regObserver(Observer o) {
        list.add(o);
    }
}
class USERS implements Observer{
    String name;
    public USERS(String name){
        this.name = name;
    }

    @Override
    public void Notification(String news) {
        System.out.println(name + " got the message: " + news);
    }
}
public class Main {
    public static void main(String[] args) {
        Groups Game   = new Groups();
        Groups Horror = new Groups();
        Groups Minus  = new Groups();
        Groups Res    = new Groups();

        USERS subscriber1 = new USERS("WillowWelly");
        USERS subscriber2 = new USERS("KissyMissy");

        //Имеется два пользователя первый подписан на 3 группы, второй на 4
        //Вывести все оповещения групп для всех пользователей

        Game.regObserver(subscriber1);
        Game.regObserver(subscriber2);
        Horror.regObserver(subscriber1);
        Horror.regObserver(subscriber2);
        Minus.regObserver(subscriber1);
        Minus.regObserver(subscriber2);
        Res.regObserver(subscriber2);


        Game.setNews("Borderlands game download is complete." + "\n");
        Horror.setNews("An update is available in the Phasmophobia game." + "\n");
        Minus.setNews("The Guys user deleted your account." + "\n");
        Res.setNews("Hooji has published a new recipe on his page." + "\n");
    }
}
