#Create topics
kafka-topics --bootstrap-server localhost:9092 --topic first_topic --create
kafka-topics --bootstrap-server localhost:9092 --topic second_topic --create --partitions 3

#List all topics
kafka-topics --bootstrap-server localhost:9092 --list

#Describe topic
kafka-topics --bootstrap-server localhost:9092 --describe --topic first_topic
kafka-topics --bootstrap-server localhost:9092 --describe --topic second_topic