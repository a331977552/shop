package org.shop.common;

import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RedisService {

	boolean expire(String key, long time);

	boolean hasKey(String key);

	long getExpire(String key);

	void delete(String... key);

	Object get(String key);

	boolean set(String key, Object value);

	boolean set(String key, Object value, long expireInMilliseconds);

	long increment(String key, long delta);

	long decrement(String key, long delta);

	// ================================Map=================================
	Object hashGet(String key, String item);

	Map<Object, Object> hashGetAll(String key);

	boolean hashSetAll(String key, Map<String, Object> map);

	boolean hashSetAll(String key, Map<String, Object> map, long time);

	boolean hashSet(String key, String item, Object value);

	boolean hashSet(String key, String item, Object value, long time);

	void hashDelete(String key, Object... item);

	boolean hasHashKey(String key, String item);

	double hashIncrement(String key, String item, double by);

	double hashDecrement(String key, String item, double by);

	// ============================set=============================
	Set<Object> getSet(String key);

	boolean hashKeyInSet(String key, Object value);

	long addToSet(String key, Object... values);

	long addToSetAndExpireIn(String key, long time, Object... values);

	long getSetSize(String key);

	long deleteValuesInSet(String key, Object... values);

	// ===============================list=================================
	List<Object> getList(String key, long start, long end);

	long getListSize(String key);

	Object getIndexInList(String key, long index);

	boolean pushToList(String key, Object value);

	boolean rightPushToList(String key, Object value, long time);

	boolean rightPushAll(String key, List<Object> value);

	boolean rightPushAllWithExpire(String key, List<Object> value, long time);

	boolean setListValue(String key, long index, Object value);

	long removeListValue(String key, long count, Object value);

	// ===============================sorted set=================================
	boolean addToZSet(String key, Object member, double score, long time);

	Set<Object> rangeByScoreForZSet(String key, double minScore, double maxScore);

	Double scoreForZSet(String key, Object member);

	Long rankForZSet(String key, Object member);

	Cursor<ZSetOperations.TypedTuple<Object>> scanForZSet(String key);
}
