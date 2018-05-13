/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 *  Copyright (c) 2018 Grégory Van den Borre
 *
 *  More infos available: https://www.yildiz-games.be
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

package be.yildizgames.engine.feature.entity.protocol;

import be.yildizgames.common.geometry.Point3D;
import be.yildizgames.common.model.ActionId;
import be.yildizgames.common.model.EntityId;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * @author Grégory Van den Borre
 */
class ActionDtoTest {

    @Nested
    class Equals {

        @Disabled
        @Test
        void test() {
            /*ActionDto base = new ActionDto(ActionId.valueOf(5), EntityId.valueOf(12L), Point3D.valueOf(4), EntityId.valueOf(7));
            ActionDto same = new ActionDto(ActionId.valueOf(5), EntityId.valueOf(12L), Point3D.valueOf(4), EntityId.valueOf(7));
            ActionDto different = new ActionDto(ActionId.valueOf(6), EntityId.valueOf(12L), Point3D.valueOf(4), EntityId.valueOf(7));
            BaseTest<ActionDto> baseTest = new BaseTest<>(base, same, different);
            baseTest.equalsSame();
            baseTest.equalsDifferent();
            baseTest.equalsDifferentType();
            baseTest.equalsSameInstance();
            baseTest.equalsNull();*/
        }
    }
}