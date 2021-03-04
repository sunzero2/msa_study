package microservices.book.gateway.configuration;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AvailabilityFilteringRule;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;
import org.springframework.context.annotation.Bean;

// @Configuration을 설정하지 않는다. --> GatewayApplication에서 @RibbonClients로 주입할 예정
public class RibbonConfiguration {
    @Bean
    public IPing ribbonPing(final IClientConfig config) {
        // isSecure: false --> 엔드포인트의 보안이 안전하지 않음을 뜻함
        return new PingUrl(false, "/health");
    }

    @Bean
    public IRule ribbonRule(final IClientConfig config) {
        // AvailabilityFilteringRule: 기본적인 RoundRobinRule의 대안. 인스턴스를 돌아가면서 선택하지만 가용성도 고려함
        // 핑을 보내 체크하고 응답하지 않는 인스턴스는 건너뜀
        return new AvailabilityFilteringRule();
    }
}
