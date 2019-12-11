package com.koitoer.java.work;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.homeaway.pricing.poqs.api.NLDDetailsResponse;
import com.homeaway.pricing.poqs.entity.PromotionalOfferView;
import com.homeaway.pricing.poqs.entity.UnitRulesFailures;
import com.homeaway.pricing.poqs.entity.embedded.Indicator;
import com.homeaway.pricing.poqs.entity.embedded.NewListingEventData;
import com.homeaway.pricing.poqs.entity.embedded.QualificationEvent;

import lombok.extern.slf4j.Slf4j;

/**
 * Encapsulate the logic to transform entities to response objects.
 */
@Slf4j
public class NldConverter {

    private static final String NEW_LISTING_DISCOUNT = "NEW_LISTING_DISCOUNT";

    /**
     * Execute the conversion process to give proper response to the API
     */
    public static NLDInformationResponse convert(PromotionalOfferView promotionalOfferView) {

        NewListingEventData newListingEventData = getNewListingEventData(promotionalOfferView);
        if (newListingEventData == null)
            return null;

        return NLDInformationResponse.builder()
            .unitUrl(promotionalOfferView.getUnitUrl())
            .updated(promotionalOfferView.getUpdated())
            .version(promotionalOfferView.getVersion())
            .isQualityListing(newListingEventData.isQualityListing())
            .isNewListing(newListingEventData.isNewListing())
            .isOptedIn(newListingEventData.isOptedIn())
            .isNldEligible(newListingEventData.isNldEligible())
            .isNLDApplicable(newListingEventData.isNLDApplicable())
            .build();
    }

    public static NLDDetailsResponse convert(PromotionalOfferView promotionalOfferView, UnitRulesFailures unitRulesFailures) {

        NewListingEventData newListingEventData = getNewListingEventData(promotionalOfferView);
        if (newListingEventData == null || unitRulesFailures == null)
            return null;

        return NLDDetailsResponse.builder()
            .unitUrl(unitRulesFailures.getUnitUrl())
            .program(NEW_LISTING_DISCOUNT)
            .isQualityListing(newListingEventData.isQualityListing())
            .isNewListing(newListingEventData.isNewListing())
            .isOptedIn(newListingEventData.isOptedIn())
            .isNLDEligible(newListingEventData.isNldEligible())
            .isNLDApplicable(newListingEventData.isNLDApplicable())
            .failures(unitRulesFailures.getFailureCodes())
            .build();
    }

    private static NewListingEventData getNewListingEventData(PromotionalOfferView promotionalOfferView) {
        String unitUrl = Optional.ofNullable(promotionalOfferView)
            .map(PromotionalOfferView::getUnitUrl).orElse(null);

        List<Indicator> indicators = Optional.ofNullable(promotionalOfferView)
            .map(PromotionalOfferView::getQualificationEvent)
            .map(QualificationEvent::getIndicator)
            .orElse(Collections.emptyList());

        NewListingEventData newListingEventData = indicators.stream()
            .filter(indicator -> NEW_LISTING_DISCOUNT.equals(indicator.getName()))
            .findFirst()
            .map(Indicator::getEventData)
            .map(eventData -> (NewListingEventData) eventData)
            .orElse(null);

        if (newListingEventData == null) {
            log.info("event=eventDataForNewListingNotAvailable unit={}, promotionalOffer={}", unitUrl, promotionalOfferView);
        }

        return newListingEventData;
    }

}
