package com.util.snowflake;

import java.net.Inet4Address;
import java.net.UnknownHostException;

/**
 * 雪花算法
 * @author 唐小甫
 * @datetime 2020-12-06 16:27:52
 */
public class SnowflakeIdWorker {
 
    /** 机器id所占的位数 */
    private static final long workerIdBits = 5L;
 
    /** 数据标识id所占的位数 */
    private static final long dataCenterIdBits = 5L;
 
    /** 支持的最大机器id，结果是31 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数) */
    private static final long maxWorkerId = -1L ^ (-1L << workerIdBits);
 
    /** 支持的最大数据标识id，结果是31 */
    private static final long maxDataCenterId = -1L ^ (-1L << dataCenterIdBits);
 
    /** 序列在id中占的位数 */
    private static final long sequenceBits = 12L;
 
    /** 机器ID向左移12位 */
    private static final long workerIdShift = sequenceBits;
 
    /** 数据标识id向左移17位(12+5) */
    private static final long dataCenterIdShift = sequenceBits + workerIdBits;
 
    /** 时间截向左移22位(5+5+12) */
    private static final long timestampLeftShift = sequenceBits + workerIdBits + dataCenterIdBits;
 
    /** 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095) */
    private static final long sequenceMask = -1L ^ (-1L << sequenceBits);
 
    /** 工作机器ID(0~31) */
    private static long workerId;
 
    /** 数据中心ID(0~31) */
    private static long dataCenterId;
 
    /** 毫秒内序列(0~4095) */
    private static long sequence = 0L;
 
    /** 上次生成ID的时间截 */
    private static long lastTimestamp = -1L;
    
    /**
     * 初始化配置
     * @author 唐小甫
     * @datetime 2020-12-06 16:29:03
     */
    static {
        workerId = getWorkId();
        dataCenterId = getDataCenterId();
    }
    
 
    /**
     * 获得下一个ID (该方法是线程安全的)
     * @return long
     * @author 唐小甫
     * @datetime 2020-12-06 16:29:03
     */
    public static synchronized long nextId() {
        long timestamp = System.currentTimeMillis();
 
        //如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format("Clock moved backwards. Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }
 
        //如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            //毫秒内序列溢出
            if (sequence == 0) {
                //阻塞到下一个毫秒,获得新的时间戳
                timestamp = lastTimestamp + 1;
            }
        } else {
            //时间戳改变，毫秒内序列重置
            sequence = 0L;
        }
 
        //上次生成ID的时间截
        lastTimestamp = timestamp;
 
        //移位并通过或运算拼到一起组成64位的ID
        return (timestamp << timestampLeftShift)
                | (dataCenterId << dataCenterIdShift)
                | (workerId << workerIdShift)
                | sequence;
    }
    
 
    /**
     * 获取当前主机地址的id值
     * @return Long
     * @author 唐小甫
     * @datetime 2020-12-06 15:46:54
     */
    private static Long getWorkId(){
        try {
            String hostAddress = Inet4Address.getLocalHost().getHostAddress();
            long sums = 0;
            for(int i = 0; i < hostAddress.length(); i++){
                sums += hostAddress.codePointAt(i);
            }
            return sums % (maxWorkerId + 1);
        } catch (UnknownHostException e) {
            // 如果获取失败，则使用随机数备用
            return (long)(Math.random() * 32);
        }
    }
    
 
    /**
     * 获取当前主机名的id值
     * @return
     * @throws UnknownHostException Long
     * @author 唐小甫
     * @datetime 2020-12-06 15:47:26
     */
    private static Long getDataCenterId() {
        String hostName;
        try {
            hostName = Inet4Address.getLocalHost().getHostName();
            long sums = 0;
            for(int i = 0; i < hostName.length(); i++){
                sums += hostName.codePointAt(i);
            }
            return sums % (maxDataCenterId + 1);
        } catch (UnknownHostException e) {
            return (long)(Math.random() * 32);
        }
    }
}