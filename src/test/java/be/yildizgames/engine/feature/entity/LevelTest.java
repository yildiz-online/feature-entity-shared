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

package be.yildizgames.engine.feature.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Grégory Van den Borre
 */
@SuppressWarnings("boxing")
public final class LevelTest {

    @SuppressWarnings("null")
    @Test
    public void testEnergy() {
        Level d = Level.valueOf(10);
        Assertions.assertEquals(10, d.value, 0.000001f);
        d = Level.ZERO;
        Assertions.assertEquals(0, d.value, 0.000001f);
    }

    @Test
    public void testEnergy2() {
        Assertions.assertThrows(AssertionError.class, () -> Level.valueOf(-10));
    }

    @Test
    public void testHashCode() {
        Level d1 = Level.valueOf(5);
        Level d2 = Level.valueOf(5);
        Assertions.assertEquals(d2.hashCode(), d1.hashCode());
    }

    @Test
    public void testEquals() {
        Level d1 = Level.valueOf(5);
        Level d2 = Level.valueOf(5);
        Level d3 = Level.valueOf(6);
        Assertions.assertEquals(d1, d1);
        Assertions.assertEquals(d1, d2);
        Assertions.assertNotEquals(d1, new Object());
        Assertions.assertNotEquals(d1, d3);
    }

}
