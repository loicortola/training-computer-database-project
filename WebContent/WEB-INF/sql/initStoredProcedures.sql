/*---------------------------------------------------*/
DROP PROCEDURE IF EXISTS addComputer;
DELIMITER //
CREATE PROCEDURE addComputer 
(
   IN p_name VARCHAR(255),
   IN p_introduced TIMESTAMP,
   IN p_discontinued TIMESTAMP,
   IN p_id_company INT(11)
)
BEGIN

   INSERT INTO computer
   (
	  name, 
      introduced, 
      discontinued,
      id_company
   ) 
   VALUES 
   (
      p_name,
      p_introduced,
      p_discontinued,
      p_id_company
   );

   SELECT LAST_INSERT_ID() AS id;
   
END //
DELIMITER ;

/*---------------------------------------------------*/
DROP PROCEDURE IF EXISTS addCompany;
DELIMITER //
CREATE PROCEDURE addCompany 
(
   IN p_name VARCHAR(255)
)
BEGIN

   INSERT INTO company
   (
      name
   ) 
   VALUES 
   (
      p_name
   );

   SELECT LAST_INSERT_ID() AS id;
   
END //
DELIMITER ;


/*---------------------------------------------------*/
DROP PROCEDURE IF EXISTS companyExists;
DELIMITER //
CREATE PROCEDURE companyExists
(
	IN p_name VARCHAR(255)
)
BEGIN

   SELECT SUM(CASE WHEN name = p_name THEN 1 ELSE 0 END) as company_exists
   FROM company 
   WHERE name = p_name
   ;
END //
DELIMITER ;

/*---------------------------------------------------*/
DROP PROCEDURE IF EXISTS computerExists;
DELIMITER //
CREATE PROCEDURE computerExists
(
   IN p_name VARCHAR(255),
   IN p_introduced TIMESTAMP,
   IN p_discontinued TIMESTAMP,
   IN p_id_company INT(11)
)
BEGIN

   SELECT COUNT(*)
   FROM company 
   WHERE name = p_name AND introduced = p_introduced AND discontinued = p_discontinued AND id_company = p_id_company
   ;
END //
DELIMITER ;

/*---------------------------------------------------*/
DROP PROCEDURE IF EXISTS getComputer;
DELIMITER //
CREATE PROCEDURE getComputer 
(
	IN p_id INT
)
BEGIN

   SELECT 
   *
   FROM computer 
   WHERE id_computer = p_id;
   
END //
DELIMITER ;

/*---------------------------------------------------*/
DROP PROCEDURE IF EXISTS getComputers;
DELIMITER //
CREATE PROCEDURE getComputers 
(
	IN p_name VARCHAR(255)
)
BEGIN

   SELECT
   *
   FROM computer 
   WHERE LOWER(name) LIKE LOWER(CONCAT('%', p_name, '%')) OR p_name = ''
   ORDER BY name ASC
   ;
   
END //
DELIMITER ;


/*---------------------------------------------------*/
DROP PROCEDURE IF EXISTS getCompany;
DELIMITER //
CREATE PROCEDURE getCompany 
(
	IN p_id INT
)
BEGIN

   SELECT 
   *
   FROM company 
   WHERE id_company = p_id
   ;
   
END //
DELIMITER ;

/*---------------------------------------------------*/
DROP PROCEDURE IF EXISTS getCompanies;
DELIMITER //
CREATE PROCEDURE getCompanies 
(
	IN p_name VARCHAR(255)
)
BEGIN

   SELECT 
   *
   FROM company 
   WHERE p_name = '' OR name = p_name
   ;
   
END //
DELIMITER ;

/*---------------------------------------------------*/
DROP PROCEDURE IF EXISTS updateComputer;
DELIMITER //
CREATE PROCEDURE updateComputer 
(
   IN p_id_computer INT(11),
   IN p_name VARCHAR(255),
   IN p_introduced TIMESTAMP,
   IN p_discontinued TIMESTAMP,
   IN p_id_company INT(11)
)
BEGIN

   UPDATE computer SET name = p_name, introduced = p_introduced, discontinued = p_discontinued, id_company = p_id_company 
   WHERE id_computer = p_id_computer
   ;
END //
DELIMITER ;

/*---------------------------------------------------*/
DROP PROCEDURE IF EXISTS updateCompany;
DELIMITER //
CREATE PROCEDURE updateCompany 
(
   IN p_id_company INT(11),
   IN p_name VARCHAR(255)
)
BEGIN

   UPDATE company SET name = p_name 
   WHERE id_company = p_id_company
   ;
END //
DELIMITER ;


/*---------------------------------------------------*/
DROP PROCEDURE IF EXISTS deleteComputer;
DELIMITER //
CREATE PROCEDURE deleteComputer 
(
   IN p_id INT(11)
)
BEGIN

	DELETE FROM computer WHERE id_computer = p_id
	;

END //
DELIMITER ;

/*---------------------------------------------------*/
DROP PROCEDURE IF EXISTS deleteCompany;
DELIMITER //
CREATE PROCEDURE deleteCompany
(
   IN p_id INT(11)
)
BEGIN

	DELETE FROM company WHERE id_company = p_id
	;

END //
DELIMITER ;