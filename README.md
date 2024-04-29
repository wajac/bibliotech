# Bibliotech

A web application to manage books in a library with many functionalities such as: adding books, authors and searching...

## Installation

This project is using the following tools and technologies:

- Jakarta EE 8 (EJB 3.2, JPA 2.1, JSF 2.3)
- PrimeFaces 7.0
- Apache Maven 3.x
- WildFly 22 / OpenJDK 11
- MySQL / MariaDB 5.5
- Eclipse JBoss Tools / Hibernate Tools 5.3

Before you can build this project, you must install and configure the MySQL database and the WildFly application server on your machine.

Run this command to clone this project repository:

```bash
git clone https://github.com/wajac/bibliotech
```

Change to the project directory:

```bash
cd bibliotech
```

Import the database (create a database first using mysql):

```bash
mysql -D [your database] < database.sql
```

To deploy the project run the maven goals:

```bash
mvn install wildfly:deploy
```

To undeploy it run the maven goals:

```bash
mvn wildfly:undeploy
```
