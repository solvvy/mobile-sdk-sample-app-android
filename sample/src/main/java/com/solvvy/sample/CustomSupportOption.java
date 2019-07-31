package com.solvvy.sample;

import com.solvvy.sdk.model.ContactType;
import com.solvvy.sdk.model.SupportOption;

/**
 * Created by sreekumar on 9/24/18.
 */

public class CustomSupportOption extends SupportOption {

    @Override
    public ContactType getType() {
        return ContactType.CUSTOM;
    }
}
