/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 *  Copyright (c) 2019 Grégory Van den Borre
 *
 *  More infos available: https://engine.yildiz-games.be
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 *  documentation files (the "Software"), to deal in the Software without restriction, including without
 *  limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 *  of the Software, and to permit persons to whom the Software is furnished to do so,
 *  subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all copies or substantial
 *  portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 *  WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
 *  OR COPYRIGHT  HOLDERS BE LIABLE FOR ANY CLAIM,
 *  DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE  SOFTWARE.
 *
 */

package be.yildizgames.engine.feature.entity.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Grégory Van den Borre
 */
@SuppressWarnings({"null", "boxing"})
public final class EntityTypeTest {

    public static final EntityType TYPE_OK = new EntityType(4, "test");

    @BeforeEach
    public void enableAssert() {
        EntityType.class.getClassLoader().setClassAssertionStatus(EntityType.class.getCanonicalName(), true);
    }

    @Test
    public void testEntityType() {
        EntityType t1 = new EntityType(6, "test");
        assertEquals(6, t1.type);
        assertEquals("test", t1.name);
    }

    @Test
    public void testEntityTypeNameNull() {
        assertThrows(AssertionError.class, () -> new EntityType(2, null));
    }

    @Test
    public void testEntityTypeNegative() {
        assertThrows(AssertionError.class, () -> new EntityType(-1, "test"));
    }

    @Test
    public void testEntityTypeDuplicate() {
        EntityType e = TYPE_OK;
        assertThrows(AssertionError.class, () -> new EntityType(4, "test2"));
    }

    @Test
    public void testGet() {
        EntityType t1 = new EntityType(12, "test");
        assertEquals(t1, EntityType.valueOf(12));
    }

    @Test
    public void testGetUnexistingType() {
        assertThrows(AssertionError.class, () -> EntityType.valueOf(1));
    }

    @Test
    public void testToString() {
        EntityType t1 = new EntityType(7, "test--");
        assertEquals("test--", t1.toString());
    }

}
