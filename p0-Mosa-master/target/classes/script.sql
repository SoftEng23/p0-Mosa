CREATE TABLE `p0_1`.`p0_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `user_type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
)


INSERT INTO `p0_1`.`p0_user` (`first_name`, `last_name`, `username`, `password`, `user_type`) VALUES ('admin', 'test', 'admin', 'admin', 'EMPLOYEE');




CREATE TABLE `p0_1`.`account` (
  `account_id` INT NOT NULL AUTO_INCREMENT,
  `owner_id` INT NOT NULL,
  `balance` DOUBLE NULL,
  `account_type` VARCHAR(45) NULL,
  `approved` TINYINT NULL,
  PRIMARY KEY (`account_id`),
  INDEX `fkey1_idx` (`owner_id` ASC) VISIBLE,
  CONSTRAINT `fkey1`
    FOREIGN KEY (`owner_id`)
    REFERENCES `p0_1`.`p0_user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
    INSERT INTO `p0_1`.`account` (`owner_id`, `balance`, `account_type`, `approved`) VALUES ('2', '25', 'SAVINGS', '1');

    INSERT INTO `p0_1`.`account` (`owner_id`, `balance`, `account_type`, `approved`) VALUES ('1', '15', 'CHECKING', '1');

    


CREATE TABLE `p0_1`.`transaction` (
  `transaction_id` INT NOT NULL AUTO_INCREMENT,
  `from_account_id` INT NULL,
  `to_account_id` INT NULL,
  `amount` DOUBLE NULL,
  `transaction_type` VARCHAR(45) NULL,
  `timestamp` TIMESTAMP(2) NULL,
  PRIMARY KEY (`transaction_id`),
  INDEX `fkey2_idx` (`from_account_id` ASC) VISIBLE,
  CONSTRAINT `fkey2`
    FOREIGN KEY (`from_account_id`)
    REFERENCES `p0_1`.`p0_user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

    
    ALTER TABLE `p0_1`.`transaction` ADD INDEX `fkey3_idx` (`to_account_id` ASC) VISIBLE;
;
ALTER TABLE `p0_1`.`transaction` 
ADD CONSTRAINT `fkey3`
  FOREIGN KEY (`to_account_id`)
  REFERENCES `p0_1`.`p0_user` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
  
  
  
  ALTER TABLE `p0_1`.`transaction` 
DROP FOREIGN KEY `fkey2`,
DROP FOREIGN KEY `fkey3`;
ALTER TABLE `p0_1`.`transaction` 
ADD INDEX `fkey2_idx` (`from_account_id` ASC) VISIBLE,
ADD INDEX `fkey3_idx` (`to_account_id` ASC) VISIBLE,
DROP INDEX `fkey3_idx` ,
DROP INDEX `fkey2_idx` ;
;
ALTER TABLE `p0_1`.`transaction` 
ADD CONSTRAINT `fkey2`
  FOREIGN KEY (`from_account_id`)
  REFERENCES `p0_1`.`account` (`account_id`),
ADD CONSTRAINT `fkey3`
  FOREIGN KEY (`to_account_id`)
  REFERENCES `p0_1`.`account` (`account_id`);

  
  INSERT INTO `p0_1`.`transaction` (`transaction_id`, `from_account_id`, `to_account_id`, `amount`, `transaction_type`) VALUES ('1', '2', '3', '100', 'DEPOSIT');

