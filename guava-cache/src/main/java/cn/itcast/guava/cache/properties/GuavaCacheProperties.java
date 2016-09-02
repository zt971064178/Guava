package cn.itcast.guava.cache.properties;

import cn.itcast.guava.cache.annotation.PropertyConfigiration;
import cn.itcast.guava.cache.annotation.Value;

@PropertyConfigiration(location="guava-cache.properties")
public class GuavaCacheProperties {
	@Value("${guava.cache.refreshAfterWrite}")
	private boolean refreshAfterWrite ;
	@Value("${guava.cache.refreshTimeType}")
	private String refreshTimeType ;
	@Value("${guava.cache.refreshTime}")
	private long refreshTime ;
	@Value("${guava.cache.expireAfterWrite}")
	private boolean expireAfterWrite ;
	@Value("${guava.cache.expireAfterAccess}")
	private boolean expireAfterAccess ;
	@Value("${guava.cache.expireTimeType}")
	private String expireTimeType ;
	@Value("${guava.cache.expireTime}")
	private long expireTime ;
	@Value("${guava.cache.autoInvalidate}")
	private boolean autoInvalidate ;
	@Value("${guava.cache.validateType}")
	private String validateType ;
	@Value("${guava.cache.maximumSize}")
	private long maximumSize ;
	@Value("${guava.cache.ticker}")
	private boolean ticker ;
	@Value("${guava.cache.concurrencyLevel}")
	private int concurrencyLevel ;
	@Value("${guava.cache.stats}")
	private boolean stats ;
	@Value("${guava.cache.initialCapacity}")
	private int initialCapacity ;

	public int getInitialCapacity() {
		return initialCapacity;
	}

	public void setInitialCapacity(int initialCapacity) {
		this.initialCapacity = initialCapacity;
	}

	public boolean isRefreshAfterWrite() {
		return refreshAfterWrite;
	}

	public void setRefreshAfterWrite(boolean refreshAfterWrite) {
		this.refreshAfterWrite = refreshAfterWrite;
	}

	public String getRefreshTimeType() {
		return refreshTimeType;
	}

	public void setRefreshTimeType(String refreshTimeType) {
		this.refreshTimeType = refreshTimeType;
	}

	public long getRefreshTime() {
		return refreshTime;
	}

	public void setRefreshTime(long refreshTime) {
		this.refreshTime = refreshTime;
	}

	public boolean isExpireAfterWrite() {
		return expireAfterWrite;
	}

	public void setExpireAfterWrite(boolean expireAfterWrite) {
		this.expireAfterWrite = expireAfterWrite;
	}

	public boolean isExpireAfterAccess() {
		return expireAfterAccess;
	}

	public void setExpireAfterAccess(boolean expireAfterAccess) {
		this.expireAfterAccess = expireAfterAccess;
	}

	public String getExpireTimeType() {
		return expireTimeType;
	}

	public void setExpireTimeType(String expireTimeType) {
		this.expireTimeType = expireTimeType;
	}

	public long getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(long expireTime) {
		this.expireTime = expireTime;
	}

	public long getMaximumSize() {
		return maximumSize;
	}

	public void setMaximumSize(long maximumSize) {
		this.maximumSize = maximumSize;
	}

	public boolean isTicker() {
		return ticker;
	}

	public void setTicker(boolean ticker) {
		this.ticker = ticker;
	}

	public int getConcurrencyLevel() {
		return concurrencyLevel;
	}

	public void setConcurrencyLevel(int concurrencyLevel) {
		this.concurrencyLevel = concurrencyLevel;
	}

	public boolean isStats() {
		return stats;
	}

	public void setStats(boolean stats) {
		this.stats = stats;
	}

	public boolean isAutoInvalidate() {
		return autoInvalidate;
	}

	public void setAutoInvalidate(boolean autoInvalidate) {
		this.autoInvalidate = autoInvalidate;
	}

	public String getValidateType() {
		return validateType;
	}

	public void setValidateType(String validateType) {
		this.validateType = validateType;
	}
}
