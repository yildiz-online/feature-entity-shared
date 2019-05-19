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

import be.yildizgames.common.util.Checker;
import be.yildizgames.engine.feature.entity.data.EntityType;
import be.yildizgames.engine.feature.resource.ResourceValue;

import java.time.Duration;

/**
 * Contains all the data proper to an Entity type.
 * Immutable class.
 *
 * @author Grégory Van den Borre
 */
//@specfield size:int:Entity size, always > 0
//@specfield moduleAllowed:List:List of modules allowed to be used.
//@invariant size > 0
//@invariant moduleAllowed != null
public class GameEntityData implements EntityData {

    /**
     * Entity size, must be > 0
     */
    private final int size;

    /**
     * Flag to tell if the entity can be built by the player.
     */
    private final boolean buildable;

    private final ResourceValue price;

    private final Duration timeToBuild;

    private final EntityType type;

    private final Instance maxInstance;

    private final Level level;

    /**
     * Full constructor.
     *
     * @param type      Object type.
     * @param size      Object size.
     * @param instances Number of units of that type allowed.
     * @param level     Level required to build this object.
     * @param price Price to build.
     * @param timeToBuild Time to build.
     * @param buildable Flag to tell if the entity can be built by the player.
     */
    public GameEntityData(final EntityType type, final int size, final Instance instances,
                             final Level level, final ResourceValue price,
                             final Duration timeToBuild, final boolean buildable) {
        super();
        assert price != null;
        assert timeToBuild != null;
        Checker.exceptionNotGreaterThanZero(size);
        this.type = type;
        this.maxInstance = instances;
        this.level = level;
        this.size = size;
        this.buildable = buildable;
        this.price = price;
        this.timeToBuild = timeToBuild;
    }

    @Override
    public final int getSize() {
        return this.size;
    }

    public final boolean isBuildable() {
        return this.buildable;
    }

    public final ResourceValue getPrice() {
        return this.price;
    }

    public final Duration getTimeToBuild() {
        return this.timeToBuild;
    }

    @Override
    public final EntityType getType() {
        return this.type;
    }

    public final Instance getMaxInstances() {
        return this.maxInstance;
    }

    public final Level getRequiredLevel() {
        return this.level;
    }
}
