package com.guilin.java.mongodb;

import com.mongodb.*;
import com.mongodb.util.JSON;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.mapreduce.GroupByResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.util.*;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.limit;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

/**
 * Created by guilin1 on 15/4/7.
 */
public class MongodbTest {

    private static MongoTemplate mongoTemplate = null;
    private String collectionName = "mobile_user";

    @BeforeClass
    public static void before() throws UnknownHostException {
        MongoClient client = new MongoClient("10.0.1.27", 27017);
        mongoTemplate = new MongoTemplate(client, "aleiye_app");
    }

    @Test
    public void test111() throws UnknownHostException {
        MongoClient client = new MongoClient("aleiyeb", 27017);
        MongoTemplate mongoTemplate1 = new MongoTemplate(client, "aleiye_app");
        Map map = new HashMap();
        map.put("appId", "A6975997718426");
        mongoTemplate1.insert(map, "t_app_version");
    }


    @Test
    public void testFindAndRemove() throws Exception {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is("1aa"));
        mongoTemplate.find(query, Student.class, collectionName);
    }

    @Test
    public void testa1() throws Exception {
        String appId = "A6975997718426";
        long from = new DateTime("2015-05-20").withTimeAtStartOfDay().getMillis();
        long to = new DateTime("2015-05-25").withTimeAtStartOfDay().getMillis();
        int type = 1;
        Criteria criteria = Criteria.where("appId").is(appId);
//        if (appVersion != null) {
//            criteria.and("appVersion").is(appVersion);
//        }
        criteria.and("time").gte(from).lt(to);
//        if (type == Constants.TYPE1) {//日志在用户手机生成日期与上传日期为同一天
        criteria.and("uploadTime").gte(from).lt(to);
//        }

        GroupBy gb = GroupBy.key("appVersion");
        gb.initialDocument("{" +
                "'newUserNum':0" +
                "}");
        gb.reduceFunction("function(doc,prev){" +
                "++prev.newUserNum;" +
                "}");

        GroupByResults result = mongoTemplate.group(criteria, collectionName
                , gb, Map.class);
        DBObject obj = result.getRawResults();

        // 不将配置数据库暴露给上一层
        obj.removeField("serverUsed");
        System.out.println(obj);

        //{ "retval" : [ ] , "count" : 0 , "keys" : 0 , "ok" : 1.0}
        //{ "retval" : [ { "appVersion" : "2.5.0" , "newUserNum" : 4.0} , { "appVersion" : "2.5.1" , "newUserNum" : 11.0} , { "appVersion" : "2.2.2" , "newUserNum" : 1.0}] , "count" : 16 , "keys" : 3 , "ok" : 1.0}
    }


    @Test
    public void test01() throws UnknownHostException {
        BasicDBObject object = new BasicDBObject();
        object.put("_id", 0);
        object.put("model", 0);
        object.put("_class", 0);
        object.put("logType", 0);
        object.put("systemType", 0);
        object.put("jailbroken", 0);
        object.put("width", 0);
        object.put("widgetVersion", 0);
        object.put("resolution", 0);
        object.put("operator", 0);
        object.put("engineVersion", 0);
        object.put("isNewVersion", 0);
        object.put("height", 0);
        object.put("connectedType", 0);
        object.put("systemVersion", 0);
        object.put("manufacturer", 0);
//        object.put("uploadTime",0);
        object.put("userIp", 0);
        object.put("areaCode", 0);
        object.put("serverHandleTime", 0);
//        object.put("time",0);
        object.put("isUpgrade", 0);

//        MongoClient client = new MongoClient("10.0.1.27", 27017);
//        MongoTemplate mongoTemplate = new MongoTemplate(client, "aleiye_app");

        DBCursor cu = mongoTemplate.getCollection("mobile_user").find(new BasicDBObject(), object);

        System.out.println(cu.toArray().size());
    }

    @Test
    public void test11() throws Exception {
        String appId = "A6975997736566";
        MongoClient client = new MongoClient("127.0.0.1", 27017);
        MongoTemplate mongoTemplate = new MongoTemplate(client, "aleiye_app");
        DBObject dbObject = new BasicDBObject();
        dbObject.put("distinct", "mobile_user");
        dbObject.put("key", "appVersion");
        DBObject object = new BasicDBObject();
        object.put("appId", appId);
        dbObject.put("query", object);
        DBObject result = mongoTemplate.executeCommand(dbObject);
        System.out.println(result);

        String str = "{\"bulketsName\":\"root\",\"children\":[{\"avg\":660.4396939924266,\"bulketsName\":\"ss_sum_duration\",\"children\":[],\"count\":195420,\"max\":44722,\"min\":0,\"sum\":1.29063125E8,\"value\":\"1.29063125E8\"},{\"avg\":0,\"bulketsName\":\"ss_dcount_deviceId\",\"children\":[],\"count\":0,\"max\":-9.223372036854776E18,\"min\":9.223372036854776E18,\"sum\":0,\"value\":\"73507\"},{\"avg\":0,\"bulketsName\":\"ss_count_deviceId\",\"children\":[],\"count\":0,\"max\":-9.223372036854776E18,\"min\":9.223372036854776E18,\"sum\":0,\"value\":\"195420\"}]}";
    }

    @Test
    public void testInsertMobileApp1() throws Exception {
        MongoClient client = new MongoClient("10.0.1.27", 27017);
        MongoTemplate mongoTemplate = new MongoTemplate(client, "aleiye_app");

//        MongoClient client2 = new MongoClient("10.0.1.241", 27017);
//        MongoTemplate mongoTemplate2 = new MongoTemplate(client2, "aleiye_app");

        Query query = new Query();
        List list = mongoTemplate.find(query, Map.class, "mobile_user");
        Random random = new Random();

        System.out.println(list.size());
        for (Object obj : list) {
            JSONObject jsonObject = JSONObject.fromObject(obj);
            String appId = jsonObject.getString("appId");

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("_id", appId);
            map.put("_class", "com.aleiye.web.application.mobile.entity.MobileAppEntity");
            map.put("userId", "1");
            map.put("appName", appId);
            map.put("appDesc", appId);
            map.put("appType", random.nextInt(4));
            map.put("hasIos", true);
            map.put("hasAndroid", true);
            map.put("enabled", true);
            map.put("yNewUser", "");
            map.put("yActiveUser", "");

            Query query2 = new Query();
            query2.addCriteria(Criteria.where("_id").is(appId));
            List list2 = mongoTemplate.find(query2, Map.class, "mobile_app");
            if (list2.size() > 0) {

            } else {
                mongoTemplate.insert(map, "mobile_app");
            }

        }
        client.close();

    }

    @Test
    public void testInsertMobileApp() throws Exception {
        //1426780800000
        MongoClient client = new MongoClient("127.0.0.1", 27017);
        MongoTemplate mongoTemplate = new MongoTemplate(client, "aleiye_app");


//        MongoClient client2 = new MongoClient("10.0.1.241", 27017);
//        MongoTemplate mongoTemplate2 = new MongoTemplate(client, "aleiye_app");


        Query query = new Query();
        query.addCriteria(Criteria.where("time").is(1426780800000l));
        query.addCriteria(Criteria.where("type").is(0));
        List list = mongoTemplate.find(query, Map.class, "mobile_count_data");
        Random random = new Random();

        System.out.println(list.size());
        for (Object obj : list) {
            JSONObject jsonObject = JSONObject.fromObject(obj);
            String appId = jsonObject.getString("appId");
            String newUserNum = jsonObject.getString("newUserNum");
            String activeUserNum = jsonObject.getString("activeUserNum");

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("_id", appId);
            map.put("_class", "com.aleiye.web.application.mobile.entity.MobileAppEntity");
            map.put("userId", "1");
            map.put("appName", appId);
            map.put("appDesc", appId);
            map.put("appType", random.nextInt(4));
            map.put("hasIos", true);
            map.put("hasAndroid", true);
            map.put("enabled", true);
            map.put("yNewUser", newUserNum + "");
            map.put("yActiveUser", activeUserNum + "");

            Query query2 = new Query();
            query2.addCriteria(Criteria.where("_id").is(appId));
            List list2 = mongoTemplate.find(query2, Map.class, "mobile_app");
            if (list2.size() > 0) {

            } else {
                mongoTemplate.insert(map, "mobile_app");
            }

        }
        client.close();
//        client2.close();

    }

    @Test
    public void testAggregation() throws Exception {
        MongoClient client = new MongoClient("aleiyeb", 27017);
        MongoTemplate mongoTemplate = new MongoTemplate(client, "aleiye_app");

        String mobileAppId = "A10";
        String appVersion = null;
        long from = new DateTime("2015-03-20").withTimeAtStartOfDay().getMillis();
        long to = new DateTime("2015-05-28").withTimeAtStartOfDay().getMillis();
        Criteria criteria = Criteria.where("appId").is(mobileAppId);
        if (appVersion != null) {
            criteria.and("appVersion").is(appVersion);
        }
        criteria.and("time").gte(from).lt(to);

        AggregationOperation match = Aggregation.match(criteria);
        AggregationOperation sort = Aggregation.sort(Sort.Direction.DESC, "countD");

        Aggregation agg = newAggregation(
                match,
                Aggregation.group("day").count().as("countD"),
                sort,
                limit(100)
        );
        AggregationResults results = mongoTemplate.aggregate(agg, "mobile_user", Map.class);
        System.out.println(results.getMappedResults());

//        List<Map<String,String>> list = results.getMappedResults();
//        System.out.println(list);
//        for(Map<String,String> map:list){
//            System.out.println(map.get("_id"));
//            System.out.println();
//        }

    }

    @Test
    public void testGroup() throws Exception {

        MongoClient client = new MongoClient("127.0.0.1", 27017);
        MongoTemplate mongoTemplate = new MongoTemplate(client, "aleiye_app");

        String appId = "A6975997718426";
        String appVersion = null;
        long from = new DateTime("2015-05-1").withTimeAtStartOfDay().getMillis();
        long to = new DateTime("2015-05-27").withTimeAtStartOfDay().getMillis();
        String groupByKey = "appId";

        Criteria criteria = Criteria.where("appId").is(appId);
        if (appVersion != null) {
            criteria.and("appVersion").is(appVersion);
        }
        criteria.and("time").gte(from).lt(to);

        GroupBy gb = GroupBy.key(groupByKey, "appVersion");
        gb.initialDocument("{" +
                "'newUserNum':0," +
                "'activeUserNum':0," +
                "'totalUserNum':0," +
                "'updateUserNum':0," +
                "'startNum':0," +
                "'useTime':0," +
                "'newUserNumByArea':{}" +
                "}");
        gb.reduceFunction("function(doc,prev){" +
                "prev.newUserNum+=doc.newUserNum;" +
                "prev.activeUserNum+=doc.activeUserNum;" +
                "if(doc.totalUserNum>prev.totalUserNum){prev.totalUserNum=doc.totalUserNum;}" +
                "prev.updateUserNum+= doc.updateUserNum;" +
                "prev.startNum+=doc.startNum;" +
                "prev.useTime+=doc.useTime;" +

                "var pV=prev.newUserNumByArea;" +
                "var dV=doc.newUserNumByArea;" +
                "for(var k in dV){" +
                "if(pV[k])" +
                "{pV[k] += dV[k];}" +
                "else" +
                "{pV[k]=dV[k];}" +
                "}" +

                "}");

        GroupByResults result = mongoTemplate.group(criteria, "mobile_count_data"
                , gb, Map.class);
        DBObject obj = result.getRawResults();

        // 不将配置数据库暴露给上一层
        obj.removeField("serverUsed");

        System.out.println(obj);

    }

    @Test
    public void testDeleteCollection() throws UnknownHostException {
        MongoClient client = new MongoClient("127.0.0.1", 27017);
        client.getDB("aleiye_app_test").getCollection("user_behavior").remove(new BasicDBObject());
        client.close();
    }

    @Test
    public void testInsert1() throws UnknownHostException {
        MongoClient client = new MongoClient("127.0.0.1", 27017);
        MongoTemplate template = new MongoTemplate(client, "aleiye_app_test");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", "12345");
        map.put("name", "xiaohua");
        map.put("age", 22);


        List<Map<String, Integer>> scores = new ArrayList<Map<String, Integer>>();
        for (int i = 0; i < 3; i++) {
            Map<String, Integer> score = new HashMap<String, Integer>();
            score.put("chinese", 40 + i * 2);
            score.put("math", 50 + i * 2);
            score.put("english", 60 + i * 2);
            scores.add(score);
        }

        map.put("score", scores);
        template.insert(map, "test");
    }

    @Test
    public void testUpdate1() throws UnknownHostException {
        MongoClient client = new MongoClient("127.0.0.1", 27017);
        MongoTemplate template = new MongoTemplate(client, "aleiye_app_test");
        Query query = Query.query(Criteria.where("id").is("1234"));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", "1234");
        map.put("name", "zhangsan");
        map.put("age", 45);

        List<Map<String, Integer>> scores = new ArrayList<Map<String, Integer>>();
        for (int i = 0; i < 3; i++) {
            Map<String, Integer> score = new HashMap<String, Integer>();
            score.put("chinese", 50 + i * 2);
            score.put("math", 60 + i * 2);
            score.put("english", 60 + i * 2);
            score.put("computer", 70 * 2);
            scores.add(score);
        }
        map.put("score", scores);

//        DBObject obj = new BasicDBObject(map);
//        Update update2=Update.fromDBObject(obj);

        Update update = new Update();
        Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> m = iterator.next();
            update.set(m.getKey(), m.getValue());
        }

        template.updateFirst(query, update, "test");
    }

    @Test
    public void testInsertCollection() throws Exception {
        File file = new File("/Users/guilin1/Downloads/user_behavior.txt");
        BufferedReader fr = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String line = null;
        MongoClient client = new MongoClient("127.0.0.1", 27017);
//        MongoClient client = new MongoClient("127.0.0.1", 27017);
        DB db = client.getDB("aleiye_app_test");
        DBCollection collection = db.getCollection("user_behavior");

        String[] fields = {"appId", "appVersion", "deviceId", "", "field4", "field5", "field6", "field7", "field8", "field9"};

        while ((line = fr.readLine()) != null) {
            String[] arr = line.split("\\|");

            BasicDBObjectBuilder builder = BasicDBObjectBuilder.start();
            for (int i = 0; i < arr.length; i++) {
                String val = arr[i];
                if (i == 3) {
                    JSONObject jsonObject = JSONObject.fromObject(val);
                    Iterator<String> iter = jsonObject.keys();
                    while (iter.hasNext()) {
                        String key = iter.next();
                        builder.add(key, jsonObject.get(key));

                    }
                } else {
                    builder.add(fields[i], val);
                }

            }
            collection.insert(builder.get());
        }

        fr.close();

    }


    @Test
    public void testInsertMongodb() throws Exception {
        File file = new File("/Users/guilin1/Downloads/user_behavior.txt");
        BufferedReader fr = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String line = null;
        MongoClient client = new MongoClient("127.0.0.1", 27017);
//        MongoClient client = new MongoClient("127.0.0.1", 27017);
        DB db = client.getDB("aleiye_app_test");
        DBCollection collection = db.getCollection("user_behavior");

        while ((line = fr.readLine()) != null) {
            String[] arr = line.split("\\|");

            BasicDBObjectBuilder builder = BasicDBObjectBuilder.start();
            for (int i = 0; i < arr.length; i++) {
                String val = arr[i];
                if (i == 3) {
                    JSONObject jsonObject = JSONObject.fromObject(val);
                    Iterator<String> iter = jsonObject.keys();
                    while (iter.hasNext()) {
                        String key = iter.next();
                        builder.add("field3_" + key, jsonObject.get(key));

                    }
                } else {
                    builder.add("field" + i, val);
                }

            }
            collection.insert(builder.get());
        }

        fr.close();

    }

    @Test
    public void testFindMongodb() throws UnknownHostException {
        MongoClient client = new MongoClient("127.0.0.1", 27017);
        DB db = client.getDB("aleiye_app_test");
//        DBCollection collection=db.getCollection("user_behavior");
        DBCollection collection = db.getCollection("test1");
        DBCursor cursor = collection.find();
        int count = 0;
        while (cursor.hasNext()) {
            DBObject dbObject = cursor.next();
//            System.out.println(JSONArray.fromObject(dbObject.get("field8")));
            System.out.println(dbObject.toString());
            count++;
            if (count == 10) {
                break;
            }
        }
        client.close();
    }

    @Test
    public void test1() {
        System.out.println(new Date(1427383420084l));//field9
        System.out.println(new Date(1427385825651l));//field4
//        System.out.println("1427385900000".compareTo("1427385982190"));

        System.out.println(new Date(1427384435157l));
        System.out.println(new Date(1427386444441l));

        System.out.println(new Random().nextInt(100));
    }

    //机型
    @Test
    public void testfield3_manufacturer() throws Exception {
        BasicDBList list = (BasicDBList) count("field3_manufacturer", "", "1427387511978", "1427386864993");
        System.out.println(list);

    }

    //分辨率
    @Test
    public void testField3_resolution() throws Exception {
        BasicDBList list = (BasicDBList) count("field3_resolution", "", "1427387511978", "1427386864993");
        System.out.println(list);
    }

    //操作系统
    @Test
    public void testField3_systemType() throws Exception {
        BasicDBList list = (BasicDBList) count("field3_systemType", "", "1427387511978", "1427386864993");
        System.out.println(list);
    }

    //联网方式
    @Test
    public void testField3_connectedType() throws Exception {
        BasicDBList list = (BasicDBList) count("field3_connectedType", "", "1427387511978", "1427386864993");
        System.out.println(list);
    }


    /**
     * 分组统计
     *
     * @param key
     * @param version
     * @param beginTime
     * @param endTime
     * @return
     * @throws Exception
     */
    private static DBObject count(String key, String version, String beginTime, String endTime) throws Exception {
        MongoClient client = new MongoClient("127.0.0.1", 27017);
        DB db = client.getDB("aleiye_app_test");
        DBCollection collection = db.getCollection("test1");

        DBObject keyObj = new BasicDBObject(key, true);

        BasicDBObject cond = new BasicDBObject();
        if (StringUtils.isNotEmpty(version)) {
            cond.append("field3_systemVersion", version);
        }
        cond.append("field4", new BasicDBObject("$gte", beginTime));
        cond.append("field9", new BasicDBObject("$lte", endTime));

        BasicDBObject initial = new BasicDBObject("count", 0);

        String reduce = "function(doc,out){out.count++}";

        DBObject dbObject = collection.group(keyObj, cond, initial, reduce);

        client.close();

        return dbObject;
    }

    /**
     * keyColumn : new String[]{"xxxName","xxxType"} <br>
     * condition : 查询条件 ，可为空<br>
     * initial : 分组统计初始变量，为空时自动为每列提供初始变量<br>
     * reduce ： 记录处理function<br>
     * finalize : finalize函数，可为空 <br>
     */
    public static BasicDBList group(DBCollection coll, String[] keyColumn, DBObject condition,
                                    DBObject initial, String reduce, String finalize) {
        DBObject key = new BasicDBObject();
        for (int i = 0; i < keyColumn.length; i++) {
            key.put(keyColumn[i], true);
        }
        condition = (condition == null) ? new BasicDBObject() : condition;
        if (StringUtils.isEmpty(finalize)) {
            finalize = null;
        }
        if (initial == null) {      //定义一些初始变量
            initial = new BasicDBObject();
            for (int i = 0; i < keyColumn.length; i++) {
                DBObject index = new BasicDBObject();
                index.put("count", 0);
                index.put("sum", 0);
                index.put("max", 0);
                index.put("min", 0);
                index.put("avg", 0);
                index.put("self", "");
                initial.put(keyColumn[i], index);
            }
        }
        BasicDBList resultList = (BasicDBList) coll.group(key, condition,
                initial, reduce, finalize);
        return resultList;
    }


    @Test
    public void testInsertData() throws Exception {
        MongoClient mongo = new MongoClient("localhost", 27017);
        DB db = mongo.getDB("test1");

        // get a single collection
        DBCollection collection = db.getCollection("dummyColl");

        // BasicDBObject example
        System.out.println("BasicDBObject example...");
        BasicDBObject document = new BasicDBObject();
        document.put("database", "mkyongDB");
        document.put("table", "hosting");

        BasicDBObject documentDetail = new BasicDBObject();
        documentDetail.put("records", "99");
        documentDetail.put("index", "vps_index1");
        documentDetail.put("active", "true");
        document.put("detail", documentDetail);

        collection.insert(document);

        DBCursor cursorDoc = collection.find();
        while (cursorDoc.hasNext()) {
            System.out.println(cursorDoc.next());
        }

        collection.remove(new BasicDBObject());

        // BasicDBObjectBuilder example
        System.out.println("BasicDBObjectBuilder example...");
        BasicDBObjectBuilder documentBuilder = BasicDBObjectBuilder.start()
                .add("database", "mkyongDB")
                .add("table", "hosting");

        BasicDBObjectBuilder documentBuilderDetail = BasicDBObjectBuilder.start()
                .add("records", "99")
                .add("index", "vps_index1")
                .add("active", "true");

        documentBuilder.add("detail", documentBuilderDetail.get());

        collection.insert(documentBuilder.get());

        DBCursor cursorDocBuilder = collection.find();
        while (cursorDocBuilder.hasNext()) {
            System.out.println(cursorDocBuilder.next());
        }

        collection.remove(new BasicDBObject());

        // Map example
        System.out.println("Map example...");
        Map<String, Object> documentMap = new HashMap<String, Object>();
        documentMap.put("database", "mkyongDB");
        documentMap.put("table", "hosting");

        Map<String, Object> documentMapDetail = new HashMap<String, Object>();
        documentMapDetail.put("records", "99");
        documentMapDetail.put("index", "vps_index1");
        documentMapDetail.put("active", "true");

        documentMap.put("detail", documentMapDetail);

        collection.insert(new BasicDBObject(documentMap));

        DBCursor cursorDocMap = collection.find();
        while (cursorDocMap.hasNext()) {
            System.out.println(cursorDocMap.next());
        }

        collection.remove(new BasicDBObject());

        // JSON parse example
        System.out.println("JSON parse example...");

        String json = "{'database' : 'mkyongDB','table' : 'hosting'," +
                "'detail' : {'records' : 99, 'index' : 'vps_index1', 'active' : 'true'}}}";

        DBObject dbObject = (DBObject) JSON.parse(json);

        collection.insert(dbObject);

        DBCursor cursorDocJSON = collection.find();
        while (cursorDocJSON.hasNext()) {
            System.out.println(cursorDocJSON.next());
        }

        collection.remove(new BasicDBObject());

    }
}
