package test

import com.linecorp.armeria.server.annotation.Post
import com.linecorp.armeria.server.annotation.ProducesJson
import com.linecorp.armeria.server.annotation.Put
import com.linecorp.armeria.server.annotation.RequestConverter
import com.linecorp.armeria.server.protobuf.ProtobufRequestConverterFunction

@RequestConverter(ProtobufRequestConverterFunction::class)
class AdminOfferService {

    @Post("/v1/management/offer")
    @ProducesJson
    fun createOffer(offer: OfferProto.Offer): OfferProto.Offer {
        println("$offer")
        return offer
    }

    @Put("/v1/management/offer")
    @ProducesJson
    fun updateOffer(offer: OfferProto.Offer): OfferProto.Offer {
        println("$offer")
        return offer
    }
}
