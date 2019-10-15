package net.redborder.utils.types.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

import com.google.common.collect.ImmutableMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import net.redborder.utils.types.TypeManager;
import org.junit.Test;

public class UuidTypeTest {

    @Test
    public void uuidTest() {
        UuidType uuidType = new UuidType(ImmutableMap.<String, Object>of("numberOfDistinct", 10));
        Set<UUID> distinct = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            distinct.add((UUID) uuidType.get());
        }
        assertEquals("The number of distinct elements is bounded", 10, distinct.size());

        uuidType = new UuidType(ImmutableMap.<String, Object>of());
        distinct = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            distinct.add((UUID) uuidType.get());
        }
        assertEquals("The number of distinct elements is unbounded", 100, distinct.size());
    }

    @Test
    public void testUuidReuse() {
        final UuidType uuidType1 = (UuidType) TypeManager.newType(
                new HashMap<>(ImmutableMap.<String, Object>of("type", "uuid", "numberOfDistinct", 1)));

        final UuidType uuidType2 = (UuidType) TypeManager.newType(
                new HashMap<>(ImmutableMap.<String, Object>of("type", "uuid", "numberOfDistinct", 1)));

        assertEquals(uuidType1.get(), uuidType2.get());
    }
}
