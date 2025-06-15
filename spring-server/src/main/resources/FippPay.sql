

CREATE TABLE `database`.`SystemUser` (
    `user_id` INT NOT NULL AUTO_INCREMENT,
    `user_name` VARCHAR(70) NOT NULL ,
    `user_cpf` VARCHAR(20) NOT NULL ,
    `user_email` VARCHAR(25) NOT NULL ,
    `user_dt_nasc` TIMESTAMP NOT NULL ,
    `user_address` VARCHAR(120) NOT NULL ,
    `user_password` VARCHAR(30) NOT NULL ,
    `user_level` VARCHAR(5) NOT NULL ,
        PRIMARY KEY (`user_id`),
        UNIQUE `UNIQUE` (`user_cpf`)
    )

ENGINE = InnoDB;

ALTER TABLE `SystemUser` CHANGE `user_id` `user_id` INT NOT NULL ;
ALTER TABLE `SystemUser` CHANGE `user_id` `user_id` INT NOT NULL AUTO_INCREMENT;
