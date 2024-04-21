
# Kafka Learning Project

This project is a learning project for Kafka. It is based on the course "Kafka with Spring Boot" by JMaster

## Technologies
* Java 21
* Spring Boot 3.2.4
* Kafka 3.7
* H2 Database

## Architecture
<p align="center">
  <img width="634" alt="Screenshot 2024-04-19 at 16 08 24" src="https://github.com/duongminhhieu/KafkaLearning/assets/76527212/1fb3f94c-9c03-412a-bbcf-947717658396">
</p>

### Flow

1. When you create a new account with _name_ and _email_. **AccountService** (producer) will send your infomation to **notification** topic and **statistic** topic.
  * If the message is sent, the polling service will store your information in database with the status **true**
  * After 1s, Polling Service will rerun and send these messages with have status **false**.
  * After 60 seconds, Polling Service will automatically delete these messages sent to the database
2. At **NotificationService** (consumer), when receiving the message from **Kafka**, the NotificationService will send an email based on the email registered
3. At **StatisticService** (consumer), if have the exception, it will retry 5 times, after that if fails then store into statistic.DLT topic

## Getting Started

This tutorial uses MacOs and HomeBrew to start Kafka.

### 1. Run Zookeeper and Kafka

* First, you must install Kafka with the command:

```
brew install kafka
```

* Start ZK and Kafka default with one broker (localhost:9092)

```
brew services start zookeeper
brew services start kafka
```

### 2. Run Multiple Brokers

* Find infomartion of Kafka on MacOs
```
brew info kafka
```

* Cd to fodler contain **kafka-server-start** file. After that, run a new Broker with (new port, new broker.id and new kafka-logs fodler)
```
kafka-server-start /opt/homebrew/etc/kafka/server.properties --override listeners=PLAINTEXT://:9093 --override broker.id=1 --override log.dirs=/opt/homebrew/var/lib/kafka-logs-1
```
## References
* [Kafka 3.7 Documentation](https://kafka.apache.org/documentation/).
* [Springboot with Kafka](https://www.youtube.com/playlist?list=PLsfLgp1K1xQ42CWP8dsIa7OT2EJFnRGGd).

