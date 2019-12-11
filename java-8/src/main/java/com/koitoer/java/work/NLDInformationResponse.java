package com.koitoer.java.work;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder(toBuilder = true)
@ToString
@Getter
public class NLDInformationResponse {

    private String unitUrl;

    private Instant updated;

    private long version;

    private boolean isQualityListing;

    private boolean isOptedIn;

    private boolean isNewListing;

    private boolean isNldEligible;

    private boolean isNLDApplicable;
}