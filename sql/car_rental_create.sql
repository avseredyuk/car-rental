DROP DATABASE IF EXISTS lab3;

CREATE DATABASE lab3 DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;

USE lab3;

CREATE TABLE `delivery_places` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` TEXT NOT NULL,
	`address` TEXT NOT NULL,
	`place_type` TEXT NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `automobiles` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`manufacturer` TEXT NOT NULL,
	`model` TEXT NOT NULL,
	`yearOfProduction` INT NOT NULL,
	`place_id` INT NOT NULL,
	`category` TEXT NOT NULL,
	`fuel_type` TEXT NOT NULL,
	`transmission` TEXT NOT NULL,
	`passenger_capacity` INT NOT NULL,
	`cargo_capacity` INT NOT NULL,
	`doors_count` INT NOT NULL,
	`price_per_day` INT NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `users` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`login` VARCHAR(50) NOT NULL,
	`email` VARCHAR(50) NOT NULL,
	`registered_datetime` TIMESTAMP NOT NULL,
	`password` TEXT NOT NULL,
	`name` TEXT NOT NULL,
	`surname` TEXT NOT NULL,
	`role` TEXT NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `orders` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`place_from_id` INT NOT NULL,
	`place_to_id` INT NOT NULL,
	`automobileId` INT NOT NULL,
	`userId` INT NOT NULL,
	`damageId` INT,
	`dateFrom` TIMESTAMP NOT NULL,
	`dateTo` TIMESTAMP NOT NULL,
	`dateCreated` TIMESTAMP NOT NULL,
	`status` TEXT NOT NULL,
	`sum` INT NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `damages` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`damageSum` INT NOT NULL,
	`description` TEXT NOT NULL,
	`paid` BOOLEAN NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB;

ALTER TABLE `automobiles` ADD CONSTRAINT `automobiles_fk0` FOREIGN KEY (`place_id`) REFERENCES `delivery_places`(`id`);

ALTER TABLE `orders` ADD CONSTRAINT `orders_fk0` FOREIGN KEY (`automobileId`) REFERENCES `automobiles`(`id`);

ALTER TABLE `orders` ADD CONSTRAINT `orders_fk1` FOREIGN KEY (`userId`) REFERENCES `users`(`id`);

ALTER TABLE `orders` ADD CONSTRAINT `orders_fk2` FOREIGN KEY (`damageId`) REFERENCES `damages`(`id`);

ALTER TABLE `orders` ADD CONSTRAINT `orders_fk3` FOREIGN KEY (`place_from_id`) REFERENCES `delivery_places`(`id`);

ALTER TABLE `orders` ADD CONSTRAINT `orders_fk4` FOREIGN KEY (`place_to_id`) REFERENCES `delivery_places`(`id`);

ALTER TABLE `users` ADD CONSTRAINT `users_uc1` UNIQUE (`login`);

ALTER TABLE `users` ADD CONSTRAINT `users_uc2` UNIQUE (`email`);

