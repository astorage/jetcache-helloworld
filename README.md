# jetcache-helloworld
jetcache 主要是引入jetcache-starter-redis包
在spring上下文中使用@EnableCreateCacheAnnotation
然后就可以使用jetcache了，当然一些配置是不能少的
jetcache:
  statIntervalMinutes: 15
  areaInCacheName: false
  local:
    default:
      type: linkedhashmap
      keyConvertor: fastjson
      expireAfterWriteInMillis: 100000
  remote:
    default:
      type: redis
      keyConvertor: fastjson
      #valueEncoder: java
      #valueDecoder: java #kryo
      poolConfig:
        minIdle: 5
        maxIdle: 20
        maxTotal: 50
      host: redisip
      port: 6379
      password: ****
