package hello.marketplace;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier(value="ANDROID")
class GoogleMarketPlace implements MarketPlace {

    @Override
    public String toString() {
        return "android";
    }
}
