package net.redborder.utils.types.impl;

import java.util.Map;

import java.util.UUID;
import net.redborder.utils.types.Type;

/**
 * Allows the creation of random {@link UUID}s.
 */
public class UuidType implements Type {

    public UuidType(final Map<String, Object> params) { }

    @Override
    public Object get() {
        return UUID.randomUUID();
    }
}
