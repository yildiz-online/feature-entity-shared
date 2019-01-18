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

import be.yildizgames.common.util.Checker;

/**
 * Simple wrapper class to represent hit points.
 * Immutable class.
 *
 * @author Grégory Van den Borre
 */
public final class HitPoint {

    /**
     * Constant for 0.
     */
    public static final HitPoint ZERO = new HitPoint(0);

    /**
     * Hit point value.
     */
    public final int points;

    /**
     * Full constructor.
     *
     * @param hitPoint Hit points value.
     */
    public HitPoint(final int hitPoint) {
        super();
        Checker.exceptionNotPositive(hitPoint);
        this.points = hitPoint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        HitPoint hitPoint = (HitPoint) o;

        return points == hitPoint.points;
    }

    @Override
    public int hashCode() {
        return points;
    }
}
