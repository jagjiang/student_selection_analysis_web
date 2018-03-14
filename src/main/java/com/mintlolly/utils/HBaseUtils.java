package com.mintlolly.utils;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.PrefixFilter;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Create by jag on 2018/2/26
 */
public class HBaseUtils {
    Connection connection = null;

    private HBaseUtils(){
        Configuration conf = new Configuration();
        conf.set("hbase.zookeeper.quorum","hadoop:2181");
        conf.set("hbase.rootdir","hdfs://192.168.220.210:9000/hbase");
        try {
            connection = ConnectionFactory.createConnection(conf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static HBaseUtils instance = null;

    public static synchronized HBaseUtils getInstance(){
        if(null == instance){
            instance = new HBaseUtils();
        }
        return instance;
    }
    //获取表实例
    public Table getTable(String tableName){
        Table table = null;
        try {
            table = connection.getTable(TableName.valueOf(tableName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return table;
    }

    /**
     *添加一条记录到HBase表中
     * @param tableName
     * @param rowkey
     * @param cf
     * @param column
     * @param value
     */
    public void put(String tableName, String rowkey, String cf, String column, String value) {
        Table table = getTable(tableName);
        Put put = new Put(Bytes.toBytes(rowkey));
        put.addColumn(Bytes.toBytes(cf), Bytes.toBytes(column), Bytes.toBytes(value));
        try {
            table.put(put);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Map<String, Long> query(String tableName, String condition){
        Map<String, Long> map = new HashMap<>();
        Table table = getTable(tableName);
        String cf = "info";
        String qualifier = "student_quantity";
        Scan scan = new Scan();
        Filter filter = new PrefixFilter(Bytes.toBytes(condition));
        scan.setFilter(filter);
        ResultScanner rs = null;
        try {
            rs = table.getScanner(scan);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(Result result : rs) {
            String row = Bytes.toString(result.getRow());
            long studentQuantity = Bytes.toLong(result.getValue(Bytes.toBytes(cf),Bytes.toBytes(qualifier)));
            map.put(row, studentQuantity);
        }
        return  map;
    }
    public Map<String, Long> query(String tableName){

        Map<String, Long> map = new HashMap<>();

        Table table = getTable(tableName);
        String cf = "info";
        String qualifier = "student_quantity";
        Scan scan = new Scan();
        ResultScanner rs = null;

        try {
            rs = table.getScanner(scan);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(Result result : rs) {
            String row = Bytes.toString(result.getRow());
            long studentQuantity = Bytes.toLong(result.getValue(Bytes.toBytes(cf),Bytes.toBytes(qualifier)));
            map.put(row, studentQuantity);
        }
        return  map;
    }

}
