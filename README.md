
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
  * If message sent, the polling service will store your information into database with status **true**
  * After 1s, Polling Service will rerun again and send these messages have status **false**.
  * After 60s, Polling Service will automaticly delete these messages sent in database
2. At **NotificationService** (consumer), when reiceved message from **Kafka**, the NotificationService will send a email based on the email registered
3. At **StatisticService** (consumer), if have a exception, it will retry 5 times, after that if fail then store into statistic.DLT topic

## Getting Started

This tutorial use MacOs and HomeBrew to start Kafka.

### 1. Run Zookeeper and Kafka

* First, you must install Kafka with command:

```
brew install kafka
```

* Start ZK and Kafka default with one broker (localhost:9092)

```
brew services start zookeeper
brew services start kafka
```

### 2. Run Multiple Brokers

* Find info of Kafka on MacOs
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

