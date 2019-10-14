package net.redborder.utils.types.impl;

import java.util.HashMap;
import java.util.Map;

import java.util.Random;
import java.util.UUID;
import net.redborder.utils.types.Type;

/**
 * Allows the creation of random {@link UUID}s with a limited number of values or unbounded if not set.
 */
public class UuidType implements Type {

    private static final Map<Integer, UUID> UUIDS = new HashMap<>();

    final Integer numberOfDistinct;

    public UuidType(final Map<String, Object> params) {
        numberOfDistinct = (Integer) params.get("numberOfDistinct");
    }

    @Override
    public Object get() {
        UUID uuid;
        if (numberOfDistinct != null) {
            final int index = new Random().nextInt(numberOfDistinct);
            synchronized (UUIDS) {
                uuid = UUIDS.get(index);
                if (uuid == null) {
                    uuid = UUID.randomUUID();
                    UUIDS.put(index, uuid);
                }
            }
        } else {
            uuid = UUID.randomUUID();
        }
        return uuid;
    }
}
