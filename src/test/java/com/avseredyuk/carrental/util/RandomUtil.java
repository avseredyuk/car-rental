package com.avseredyuk.carrental.util;

import com.avseredyuk.carrental.dao.impl.factory.MySqlDaoFactory;
import com.avseredyuk.carrental.domain.*;
import com.avseredyuk.carrental.util.DateUtil;

import java.util.Date;
import java.util.Random;

/**
 * Created by lenfer on 1/27/17.
 */
public class RandomUtil {
    private static final char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
    private static final int STRING_LEN = 20;
    private static final Random random = new Random();

    private static String rndStr(int size) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= size; i++) {
            sb.append(chars[random.nextInt(chars.length)]);
        }
        return sb.toString();
    }

    public static DeliveryPlace getPlace() {
        return new DeliveryPlace(rndStr(STRING_LEN), rndStr(STRING_LEN),
                random.nextBoolean() ? DeliveryPlace.DeliveryPlaceType.OFFICE :
                        DeliveryPlace.DeliveryPlaceType.PUBLIC_PLACE);
    }

    public static User getUser() {
        return new User(rndStr(STRING_LEN), rndStr(STRING_LEN), new Date(),
                rndStr(STRING_LEN), rndStr(STRING_LEN), rndStr(STRING_LEN),
                random.nextBoolean() ? User.Role.CLIENT : User.Role.ADMINISTRATOR);
    }

    public static Damage getDamage() {
        return new Damage(random.nextInt(100000), rndStr(STRING_LEN), random.nextBoolean());
    }

    public static Automobile getAutomobile() {
        DeliveryPlace place = getPlace();
        MySqlDaoFactory.getInstance().getDeliveryPlaceDao().persist(place);
        return new Automobile(rndStr(STRING_LEN),
                rndStr(STRING_LEN),
                random.nextInt(2018),
                place,
                Automobile.Category.values()[random.nextInt(Automobile.Category.values().length)],
                Automobile.Fuel.values()[random.nextInt(Automobile.Fuel.values().length)],
                Automobile.Transmission.values()[random.nextInt(Automobile.Transmission.values().length)],
                random.nextInt(10),
                random.nextInt(10),
                random.nextInt(8),
                random.nextInt(10000));
    }

    public static Order getOrder() {
        DeliveryPlace placeFrom = getPlace();
        MySqlDaoFactory.getInstance().getDeliveryPlaceDao().persist(placeFrom);
        DeliveryPlace placeTo = getPlace();
        MySqlDaoFactory.getInstance().getDeliveryPlaceDao().persist(placeTo);
        Automobile automobile = getAutomobile();
        MySqlDaoFactory.getInstance().getAutomobileDao().persist(automobile);
        User user = getUser();
        MySqlDaoFactory.getInstance().getUserDao().persist(user);
        Damage damage = getDamage();
        MySqlDaoFactory.getInstance().getDamageDao().persist(damage);
        Date dateCreated = DateUtil.getDateAfter(new Date());
        Date dateFrom = DateUtil.getDateAfter(dateCreated);
        Date dateTo = DateUtil.getDateAfter(dateFrom);
        return new Order(placeFrom,
                placeTo,
                automobile,
                user,
                damage,
                dateFrom,
                dateTo,
                dateCreated,
                Order.OrderStatus.values()[random.nextInt(Order.OrderStatus.values().length)],
                random.nextInt(10000));
    }
}
