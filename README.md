# Apache Flink example

Run a Pub/Sub Consumer and put info in a bucket

## Compile and package 
```
sbt clean assembly
```

## Run

```
export GOOGLE_APPLICATION_CREDENTIALS=
export PROJECT=
export BUCKET=
export TOPIC
 

./bin/flink run PATH_TO_/pubsub-apacheflink/target/scala-2.11/pubsub-apacheflink-assembly-0.1.jar --project $PROJECT --subscription $TOPIC --output gs://$BUCKET
```