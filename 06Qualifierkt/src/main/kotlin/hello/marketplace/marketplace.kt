package hello.marketplace

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

interface MarketPlace

@Component
@Qualifier("IOS")
class AppleMarketPlace:MarketPlace {
	
    override fun toString() = "apple"
}

@Component
@Qualifier("ANDROID")
class AndroidMarketPlace:MarketPlace {
	
    override fun toString() = "android"
}