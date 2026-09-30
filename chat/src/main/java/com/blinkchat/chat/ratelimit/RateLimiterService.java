package com.blinkchat.chat.ratelimit;
import io.github.bucket4j.Bucket;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RateLimiterService {

    private final Map<String, Bucket> bucketMap = new ConcurrentHashMap<>();

    private Bucket createBucket(){

        return Bucket.builder()
                .addLimit(limit->
                        (limit.capacity(5)
                                .refillGreedy(5, Duration.ofSeconds(10)))).build();

    }

    public boolean isAllowed(String sessionToken){
        //Loop if map has this sessionToken's bucket , if not createBucket and store it
        Bucket bucket =  bucketMap.computeIfAbsent(sessionToken, k -> createBucket());

        return bucket.tryConsume(1);
    }



}
