package com.lenovo.push.data.speed.stream;

import com.lenovo.push.reids.api.constant.RedisInstance;
import com.lenovo.push.reids.api.util.ShardedRedisUtil;

public class RedisChecker {

	public static void main(String[] args) {
		String[] keys = {"fake.feedback.feedbackId-1432016002325.packName-1432016002325.eventName-1432016002325.20150519"};
		ShardedRedisUtil sru = new ShardedRedisUtil(RedisInstance.REDIS_PUSH_DATA);
		for (String thekey : keys ) {
			System.out.println(thekey + ":" + sru.get(thekey));
		}
		
	}

}
