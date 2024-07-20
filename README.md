# Book-Store-Application-using-React-and-Springboot

## Commands to Start Kafka

Start Zookeeper
```
bin/zookeeper-server-start.sh config/zookeeper.properties
```
Start Kafka Server
```
bin/kafka-server-start.sh config/server.properties
```
Create Kafka Topic
```
bin/kafka-topics.sh --create --topic user-registration --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
```

## Redis
Start Redis Server
```
redis-server
```