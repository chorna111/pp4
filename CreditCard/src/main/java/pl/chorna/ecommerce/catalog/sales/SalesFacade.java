package pl.chorna.ecommerce.catalog.sales;

import pl.chorna.ecommerce.catalog.sales.cart.Cart;
import pl.chorna.ecommerce.catalog.sales.cart.CartStorage;
import pl.chorna.ecommerce.catalog.sales.offering.Offer;
import pl.chorna.ecommerce.catalog.sales.offering.OfferCalculator;
import pl.chorna.ecommerce.catalog.sales.payment.PaymentDetails;
import pl.chorna.ecommerce.catalog.sales.payment.PaymentGateway;
import pl.chorna.ecommerce.catalog.sales.payment.RegisterPaymentRequest;
import pl.chorna.ecommerce.catalog.sales.reservation.AcceptOfferRequest;
import pl.chorna.ecommerce.catalog.sales.reservation.Reservation;
import pl.chorna.ecommerce.catalog.sales.reservation.ReservationDetails;
import pl.chorna.ecommerce.catalog.sales.reservation.ReservationRepository;
import java.util.UUID;

public class SalesFacade {
    private CartStorage cartStorage;
    private OfferCalculator offerCalculator;
    private PaymentGateway paymentGateway;
    private ReservationRepository reservationRepository;

    public SalesFacade(CartStorage cartStorage, OfferCalculator offerCalculator, PaymentGateway paymentGateway, ReservationRepository reservationRepository) {
        this.cartStorage = cartStorage;
        this.offerCalculator=offerCalculator;
        this.reservationRepository=reservationRepository;
        this.paymentGateway=paymentGateway;

    }

    public Offer getCurrentOffer(String customerId) {
        ///Cart cart=loadCartForCustomer(customerId);
        Cart cart = cartStorage.findByCustomer(customerId)
                .orElse(Cart.empty());
        return OfferCalculator.calculate(cart.getItems());
    }
    public void addProduct(String customerId, String productId) {
        Cart cart = loadCartForCustomer(customerId);

        cart.addProduct(productId);
        cartStorage.save(customerId,cart);


    }
    private Cart loadCartForCustomer(String customerId) {
        return cartStorage.findByCustomer(customerId)
                .orElse(Cart.empty());
    }

    public ReservationDetails acceptOffer(String customerId, AcceptOfferRequest acceptOfferRequest) {
        String reservationId=UUID.randomUUID().toString();
        Offer offer =this.getCurrentOffer(customerId);
        PaymentDetails paymentDetails=paymentGateway.registerPayment(RegisterPaymentRequest.of(reservationId,acceptOfferRequest,offer.getTotal()));
        Reservation reservation=Reservation.of(reservationId,customerId,acceptOfferRequest,offer,paymentDetails);
        reservationRepository.add(reservation);
        return new ReservationDetails(reservationId,paymentDetails.getPaymentUrl());
    }
}
