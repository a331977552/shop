package org.shop;//package org.shop.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
@Log4j2
public class RedisServiceImpl implements RedisService {

	private final RedisTemplate<String,Object> redisTemplate;

	public RedisServiceImpl(RedisTemplate<String,Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@Override
	public boolean expire(String key, long time) {
		try {
			if (time > 0) {
				redisTemplate.expire(key, time, TimeUnit.MILLISECONDS);
			}
			return true;
		} catch (Exception e) {
			log.error("redis error: ", e);
			return false;
		}
	}

	@Override
	public long getExpire(String key) {
		return redisTemplate.getExpire(key, TimeUnit.MILLISECONDS);
	}

	@Override
	public boolean hasKey(String key) {
		return redisTemplate.hasKey(key);
	}

	@Override
	public void delete(String... key) {
		if (key != null && key.length > 0) {
			if (key.length == 1) {
				redisTemplate.delete(key[0]);
			} else {
				redisTemplate.delete(Arrays.asList(key));
			}
		}
	}

	@Override
	public Object get(String key) {
		return key == null ? null : redisTemplate.opsForValue().get(key);
	}

	@Override
	public boolean set(String key, Object value) {
		Objects.requireNonNull(key);
		Objects.requireNonNull(value);
		redisTemplate.opsForValue().set(key, value);
		return true;
	}

	@Override
	public boolean set(String key, Object value, long time) {
		if (time > 0) {
			Objects.requireNonNull(key);
			Objects.requireNonNull(value);
			redisTemplate.opsForValue().set(key, value, time, TimeUnit.MILLISECONDS);
			return true;
		} else {
			return set(key, value);
		}
	}

	@Override
	public long increment(String key, long delta) {
		if (delta < 0) {
			throw new RuntimeException("invalid delta, only positive allowed "+ delta);
		}
		return redisTemplate.opsForValue().increment(key, delta);
	}

	@Override
	public long decrement(String key, long delta) {
		if (delta < 0) {
			throw new RuntimeException("递减因子必须大于0");
		}
		return redisTemplate.opsForValue().increment(key, -delta);
	}

	@Override
	public Object hashGet(String key, String item) {
		return redisTemplate.opsForHash().get(key, item);	}

	@Override
	public Map<Object, Object> hashGetAll(String key) {
		return redisTemplate.opsForHash().entries(key);
	}

	@Override
	public boolean hashSetAll(String key, Map<String, Object> map) {
		redisTemplate.opsForHash().putAll(key, map);
		return true;
	}

	@Override
	public boolean hashSetAll(String key, Map<String, Object> map, long time) {
		redisTemplate.opsForHash().putAll(key, map);
		if (time > 0) {
			expire(key, time);
		}
		return true;
	}

	@Override
	public boolean hashSet(String key, String item, Object value) {
		redisTemplate.opsForHash().put(key, item, value);
		return true;
	}

	@Override
	public boolean hashSet(String key, String item, Object value, long time) {
		redisTemplate.opsForHash().put(key, item, value);
		if (time > 0) {
			expire(key, time);
		}
		return true;
	}

	@Override
	public void hashDelete(String key, Object... item) {
		redisTemplate.opsForHash().delete(key, item);
	}

	@Override
	public boolean hasHashKey(String key, String item) {
		return redisTemplate.opsForHash().hasKey(key, item);
	}

	@Override
	public double hashIncrement(String key, String item, double by) {
		return redisTemplate.opsForHash().increment(key, item, by);
	}

	@Override
	public double hashDecrement(String key, String item, double by) {
		return redisTemplate.opsForHash().increment(key, item, -by);
	}

	@Override
	public Set<Object> getSet(String key) {
		return redisTemplate.opsForSet().members(key);
	}

	@Override
	public boolean hashKeyInSet(String key, Object value) {
		return redisTemplate.opsForSet().isMember(key, value);
	}

	@Override
	public long addToSet(String key, Object... values) {
		return redisTemplate.opsForSet().add(key, values);
	}

	@Override
	public long addToSetAndExpireIn(String key, long time, Object... values) {
		Long count = redisTemplate.opsForSet().add(key, values);
		if (time > 0)
			expire(key, time);
		return count;
	}

	@Override
	public long getSetSize(String key) {
		return redisTemplate.opsForSet().size(key);
	}

	@Override
	public long deleteValuesInSet(String key, Object... values) {
		Long count = redisTemplate.opsForSet().remove(key, values);
		return count;
	}

	@Override
	public List<Object> getList(String key, long start, long end) {
		return redisTemplate.opsForList().range(key, start, end);
	}

	@Override
	public long getListSize(String key) {
		return redisTemplate.opsForList().size(key);
	}

	@Override
	public Object getIndexInList(String key, long index) {
		return redisTemplate.opsForList().index(key, index);
	}

	@Override
	public boolean pushToList(String key, Object value) {
		redisTemplate.opsForList().rightPush(key, value);
		return true;
	}

	@Override
	public boolean rightPushToList(String key, Object value, long time) {
		redisTemplate.opsForList().rightPush(key, value);
		if (time > 0)
			expire(key, time);
		return true;
	}

	@Override
	public boolean rightPushAll(String key, List<Object> value) {
		redisTemplate.opsForList().rightPushAll(key, value);
		return true;
	}

	@Override
	public boolean rightPushAllWithExpire(String key, List<Object> value, long time) {
		redisTemplate.opsForList().rightPushAll(key, value);
		if (time > 0)
			expire(key, time);
		return true;
	}

	@Override
	public boolean setListValue(String key, long index, Object value) {
		redisTemplate.opsForList().set(key, index, value);
		return true;
	}

	@Override
	public long removeListValue(String key, long count, Object value) {
		Long remove = redisTemplate.opsForList().remove(key, count, value);
		return remove;
	}

	@Override
	public boolean addToZSet(String key, Object member, double score, long time) {
		redisTemplate.opsForZSet().add(key, member, score);
		if (time > 0)
			expire(key, time);
		return true;
	}

	@Override
	public Set<Object> rangeByScoreForZSet(String key, double minScore, double maxScore) {
		return redisTemplate.opsForZSet().rangeByScore(key, minScore, maxScore);	}

	@Override
	public Double scoreForZSet(String key, Object member) {
		return redisTemplate.opsForZSet().score(key, member);
	}

	@Override
	public Long rankForZSet(String key, Object member) {
		return redisTemplate.opsForZSet().rank(key, member);
	}

	@Override
	public Cursor<ZSetOperations.TypedTuple<Object>> scanForZSet(String key) {
		Cursor<ZSetOperations.TypedTuple<Object>> cursor = redisTemplate.opsForZSet().scan(key, ScanOptions.NONE);
		return cursor;
	}
}
