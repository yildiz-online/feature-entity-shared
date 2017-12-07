/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 *  Copyright (c) 2017 Grégory Van den Borre
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

package be.yildizgames.engine.feature.entity.construction;

import be.yildiz.common.util.Time;
import be.yildizgames.engine.feature.entity.data.EntityType;

/**
 *  An entity representation construction is the state of the build of an entity,
 * it contains the type, and the unique build index.
 * @author Grégory Van den Borre
 */
public class EntityConstructionStatus {

    /**
     * Type of the entity to build.
     */
    public final EntityType type;

    /**
     * Construction unique index.
     */
    public final int index;

    /**
     * Time left before the construction is complete.
     */
    private Time timeLeft;

    public EntityConstructionStatus(EntityType type, int index, Time timeLeft) {
        super();
        this.type = type;
        this.index = index;
        this.timeLeft = timeLeft;
    }

    /**
     * @return The time before construction completion in milliseconds.
     */
    public long getTime() {
        return this.timeLeft.timeInMs;
    }

    /**
     * Update the time left.
     *
     * @param timeToRemove Time spent since the last update.
     */
    public void reduceTimeLeft(final long timeToRemove) {
        long t = timeLeft.subtractMs(timeToRemove);
        if (t < 0) {
            t = 0;
        }
        this.timeLeft = Time.milliSeconds(t);
    }

    /**
     * @return True if the time required to build the entity is elapsed.
     */
    public boolean isTimeElapsed() {
        return this.timeLeft.timeInMs <= 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EntityConstructionStatus that = (EntityConstructionStatus) o;

        return index == that.index && type.equals(that.type) && timeLeft.equals(that.timeLeft);
    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + index;
        result = 31 * result + timeLeft.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "EntityRepresentationConstruction{" +
                "type:" + this.type +
                ", index:" + this.index +
                ", timeLeft:" + this.timeLeft +
                '}';
    }
}

