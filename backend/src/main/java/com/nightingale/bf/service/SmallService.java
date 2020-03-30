package com.nightingale.bf.service;

import com.nightingale.bf.model.LangType;
import com.nightingale.bf.model.token.LangSpecificToken;
import com.nightingale.bf.model.token.SmallToken;

import java.util.Arrays;
import java.util.Collection;

public class SmallService extends BaseBitService {
    @Override
    protected LangType getType() {
        return LangType.SMALL;
    }

    @Override
    public Collection<LangSpecificToken> getAvailableTokens() {
        return Arrays.asList(SmallToken.values());
    }
}
