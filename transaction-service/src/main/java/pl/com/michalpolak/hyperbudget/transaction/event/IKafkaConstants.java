package pl.com.michalpolak.hyperbudget.transaction.event;


//TODO Move to properties file
interface IKafkaConstants {
    public static String KAFKA_BROKERS = "http://message-broker:9092";
    public static Integer MESSAGE_COUNT=1000;
    public static String CLIENT_ID="transaction-service";
    public static String GROUP_ID_CONFIG="transactionConsumer";
    public static Integer MAX_NO_MESSAGE_FOUND_COUNT=100;
    public static String OFFSET_RESET_LATEST="latest";
    public static String OFFSET_RESET_EARLIER="earliest";
    public static Integer MAX_POLL_RECORDS=1;
}