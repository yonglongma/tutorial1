package com.alibaba.tutorial1.tool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 马永龙 on 2016/9/21.
 */
public class Sequence {
    private static long sequence = 0L;
    private static long lastTimestamp = -1L;

    public static synchronized Long getNewId() {
        long timestamp = System.currentTimeMillis();
        if(lastTimestamp == timestamp) {
            sequence = (sequence + 1) & ~(-1L << 12L);
        } else {
            sequence = 0L;
        }
        lastTimestamp = timestamp;
        return ((timestamp - 1288834974657L) << 22L) | (1L << 17L) | (1L << 12L) | sequence;
    }

    public static synchronized List<Long> getNewIds(int count) {
        List<Long> result = new ArrayList<Long>();
        for(int i = 0; i < count; i++) {
            result.add(getNewId());
        }
        return result;
    }
}
