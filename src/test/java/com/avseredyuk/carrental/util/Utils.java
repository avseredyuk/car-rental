package com.avseredyuk.carrental.util;

import com.avseredyuk.carrental.dao.impl.pool.PoolWorker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lenfer on 1/25/17.
 */
public class Utils {
    public static final int NON_EXISTENT_ID = 123;
    public static final int TOTAL_COUNT_ADDED = 10;
    public static final int FIND_RANGE_START = 0;
    public static final int FIND_RANGE_SIZE = 3;
    public static final int NEGATIVE_RANGE = -2;
    public static final int ZERO_RANGE = 0;
    public static final int POSITIVE_RANGE = 2;
    public static final int TOO_BIG_RANGE = 212;
    public static final String RECREATE_TESTDB = "DROP DATABASE IF EXISTS lab3test;\n" +
            "CREATE DATABASE lab3test DEFAULT CHARACTER SET utf8\n" +
            "  DEFAULT COLLATE utf8_general_ci;\n" +
            "USE lab3test;\n" +
            "CREATE TABLE `delivery_places` (\n" +
            "\t`id` INT NOT NULL AUTO_INCREMENT,\n" +
            "\t`name` TEXT NOT NULL,\n" +
            "\t`address` TEXT NOT NULL,\n" +
            "\t`place_type` TEXT NOT NULL,\n" +
            "\tPRIMARY KEY (`id`)\n" +
            ");\n" +
            "CREATE TABLE IF NOT EXISTS `automobiles` (\n" +
            "\t`id` INT NOT NULL AUTO_INCREMENT,\n" +
            "\t`manufacturer` TEXT NOT NULL,\n" +
            "\t`model` TEXT NOT NULL,\n" +
            "\t`yearOfProduction` INT NOT NULL,\n" +
            "\t`place_id` INT NOT NULL,\n" +
            "\t`category` TEXT NOT NULL,\n" +
            "\t`fuel_type` TEXT NOT NULL,\n" +
            "\t`transmission` TEXT NOT NULL,\n" +
            "\t`passenger_capacity` INT NOT NULL,\n" +
            "\t`cargo_capacity` INT NOT NULL,\n" +
            "\t`doors_count` INT NOT NULL,\n" +
            "\t`price_per_day` INT NOT NULL,\n" +
            "\tPRIMARY KEY (`id`)\n" +
            ") ENGINE=InnoDB;\n" +
            "CREATE TABLE IF NOT EXISTS `users` (\n" +
            "\t`id` INT NOT NULL AUTO_INCREMENT,\n" +
            "\t`login` VARCHAR(50) NOT NULL,\n" +
            "\t`email` VARCHAR(50) NOT NULL,\n" +
            "\t`registered_datetime` TIMESTAMP NOT NULL,\n" +
            "\t`password` TEXT NOT NULL,\n" +
            "\t`name` TEXT NOT NULL,\n" +
            "\t`surname` TEXT NOT NULL,\n" +
            "\t`role` TEXT NOT NULL,\n" +
            "\tPRIMARY KEY (`id`)\n" +
            ") ENGINE=InnoDB;\n" +
            "CREATE TABLE IF NOT EXISTS `orders` (\n" +
            "\t`id` INT NOT NULL AUTO_INCREMENT,\n" +
            "\t`place_from_id` INT NOT NULL,\n" +
            "\t`place_to_id` INT NOT NULL,\n" +
            "\t`automobileId` INT NOT NULL,\n" +
            "\t`userId` INT NOT NULL,\n" +
            "\t`damageId` INT,\n" +
            "\t`dateFrom` TIMESTAMP NOT NULL,\n" +
            "\t`dateTo` TIMESTAMP NOT NULL,\n" +
            "\t`dateCreated` TIMESTAMP NOT NULL,\n" +
            "\t`status` TEXT NOT NULL,\n" +
            "\t`sum` INT NOT NULL,\n" +
            "\tPRIMARY KEY (`id`)\n" +
            ") ENGINE=InnoDB;\n" +
            "CREATE TABLE IF NOT EXISTS `damages` (\n" +
            "\t`id` INT NOT NULL AUTO_INCREMENT,\n" +
            "\t`damageSum` INT NOT NULL,\n" +
            "\t`description` TEXT NOT NULL,\n" +
            "\t`paid` BOOLEAN NOT NULL,\n" +
            "\tPRIMARY KEY (`id`)\n" +
            ") ENGINE=InnoDB;\n" +
            "ALTER TABLE `automobiles` ADD CONSTRAINT `automobiles_fk0` FOREIGN KEY (`place_id`) REFERENCES `delivery_places`(`id`);\n" +
            "ALTER TABLE `orders` ADD CONSTRAINT `orders_fk0` FOREIGN KEY (`automobileId`) REFERENCES `automobiles`(`id`);\n" +
            "ALTER TABLE `orders` ADD CONSTRAINT `orders_fk1` FOREIGN KEY (`userId`) REFERENCES `users`(`id`);\n" +
            "ALTER TABLE `orders` ADD CONSTRAINT `orders_fk2` FOREIGN KEY (`damageId`) REFERENCES `damages`(`id`);\n" +
            "ALTER TABLE `orders` ADD CONSTRAINT `orders_fk3` FOREIGN KEY (`place_from_id`) REFERENCES `delivery_places`(`id`);\n" +
            "ALTER TABLE `orders` ADD CONSTRAINT `orders_fk4` FOREIGN KEY (`place_to_id`) REFERENCES `delivery_places`(`id`);\n" +
            "ALTER TABLE `users` ADD CONSTRAINT `users_uc1` UNIQUE (`login`);\n" +
            "ALTER TABLE `users` ADD CONSTRAINT `users_uc2` UNIQUE (`email`);";

    public static boolean resetDB() {
        try (Connection connection = PoolWorker.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(RECREATE_TESTDB)) {
            statement.execute();
            statement.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

}
