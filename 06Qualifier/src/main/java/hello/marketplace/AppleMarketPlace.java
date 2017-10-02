package hello.marketplace;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
@Qualifier("IOS")
public class AppleMarketPlace implements MarketPlace {
	
    @Override
    public String toString() {
        return "apple";
    }

}
