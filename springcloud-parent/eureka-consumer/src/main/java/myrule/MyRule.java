package myrule;

import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyRule {
    @Bean
    public IRule getRule(){
        return new KzouRandomRule();
    }
}
