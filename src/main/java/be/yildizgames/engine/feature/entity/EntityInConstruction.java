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

package be.yildizgames.engine.feature.entity;

import be.yildiz.common.id.EntityId;
import be.yildiz.common.id.PlayerId;
import be.yildiz.common.util.Checker;
import be.yildiz.common.vector.Point3D;
import be.yildizgames.engine.feature.entity.data.EntityType;

/**
 * This extends the entity in construction to provide additional information for a previously built entity.
 * This is usually used to build entities retrieved from an existing context: database loading...
 *
 * @author Grégory Van den Borre
 */
public class EntityInConstruction extends DefaultEntityInConstruction {

    public static final EntityInConstruction WORLD = new EntityInConstruction(
            EntityType.WORLD,
            EntityId.WORLD,
            PlayerId.WORLD,
            "World",
            Point3D.ZERO,
            Point3D.INVERT_Z,
            0,
            0);

    /**
     * Name of the entity.
     */
    private final String name;

    /**
     * Entity hit points when built.
     */
    private final int hp;

    /**
     * Entity energy points when built.
     */
    private final int energy;

    /**
     * Create a new instance.
     *
     * @param type      Type of the entity to build.
     * @param id Entity id.
     * @param owner Owner of the entity.
     * @param name      Name of the entity.
     * @param position Position when built.
     * @param direction Direction when built.
     * @param hp        Entity hit points when built.
     * @param energy    Entity energy points when built.
     * @throws NullPointerException     if any parameter is null.
     * @throws IllegalArgumentException If hp or energy is not a positive value.
     */
    public EntityInConstruction(EntityType type, EntityId id, PlayerId owner, String name, Point3D position, Point3D direction, int hp, int energy) {
        super(type, id, owner, position, direction);
        Checker.exceptionNotPositive(hp);
        Checker.exceptionNotPositive(energy);
        assert name != null;
        this.name = name;
        this.hp = hp;
        this.energy = energy;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getEnergy() {
        return energy;
    }
}
