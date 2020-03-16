package com.liuhuan.hgshop.listen;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.listener.MessageListener;

import com.liuhuan.hgshop.common.HgShopConstant;
import com.liuhuan.hgshop.pojo.Spu;
import com.github.pagehelper.PageInfo;

public class KafkaConsumerListener implements MessageListener<String, String>{
	
	//@Autowired	
	//private ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	private RedisTemplate<String, PageInfo<Spu>> redisTemplate;

	/**
	 *  日志对象
	 */
	private Logger logger = Logger.getLogger(KafkaConsumerListener.class);
	
	{
		System.out.println("》》》》》》》》》》》》》》》》》》》》这里被实例化了。。。。。。。。。。。。。");
	}
	
	
	public void onMessage(ConsumerRecord<String, String> data) {
		
		 System.out.println("get a message .......... "  );
		//获取key值
		String key = null;
		try {
			key = data.key();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(key!=null) {
			 //判断
			 System.out.println("key is "  + key);
			 String value = null;
			try {
				value = data.value();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 System.out.println("value is "  + value);
			 switch (key) {
			case "addspu":
				//
				System.out.println("新添加的商品的id是"  + value );
				//清空缓存
				redisTemplate.delete(HgShopConstant.SPU_CACHE_KEY);
				
				break;
			default:
				break;
			}
		}else {
			logger.info("key为null值");
		}
		
	}

}
