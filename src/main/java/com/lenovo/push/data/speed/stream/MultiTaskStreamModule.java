package com.lenovo.push.data.speed.stream;

import java.io.File;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.lenovo.czlib.nodex.conf.ZKProperties;
import com.lenovo.lps.push.common.eventbus.PushEventBus;
import com.lenovo.lps.push.common.eventbus.Topics;
import com.lenovo.lps.push.common.stat.GroupCounter;
import com.lenovo.push.data.speed.stream.key.MultiTaskKeyAssembler;

public class MultiTaskStreamModule {
	private static Logger logger = Logger.getLogger(MultiTaskStreamModule.class);
	private static final String log4jFileName = "log4j.xml";
	
	private static final PushEventBus DATA_EVENT_BUS = new PushEventBus(new ZKProperties(new String[] { "/data/eventbus" }, false));
	//private static final ShardedRedisUtil SRU = new ShardedRedisUtil(RedisInstance.REDIS_PUSH_DATA);
	private static final GroupCounter COUNTER = new GroupCounter();
	private static final String GROUP_ID = Topics.PUSH_DATA_FEEDBACK + ".multitask";

	
	
	public static void main(String[] args) {
		logger.info("Config log4j...");
		String moduleHome = System.getProperty("module.home");
    	if (moduleHome == null) {
    		throw new RuntimeException("module.home is null");
    	}
    	DOMConfigurator.configure(moduleHome + File.separator + "conf" + File.separator + log4jFileName);
    	logger.info("Config log4j done!");
    	
    	while (true) {
			byte[] msg = DATA_EVENT_BUS.take(Topics.PUSH_DATA_FEEDBACK, GROUP_ID);
			String feedbackEvent = new String(msg);
			String thekey = MultiTaskKeyAssembler.assemble(feedbackEvent);
			if (thekey != null) {
				logger.debug(thekey);
				COUNTER.incBy(thekey, 1, new GroupCounter.CounterUnit[] { GroupCounter.CounterUnit.HOUR, GroupCounter.CounterUnit.DAY});
			}
		}
	}
}
