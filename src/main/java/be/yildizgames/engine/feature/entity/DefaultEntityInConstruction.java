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
import be.yildiz.common.vector.Point3D;
import be.yildizgames.engine.feature.entity.data.EntityType;

/**
 * An entity in construction contains the state of an entity to be built: its hit points, energy points, position,...
 * Once the real entity is built, it will inherit of this state.
 * A default entity in construction is used to build completely new entities.
 *
 * @author Grégory Van den Borre
 */
public class DefaultEntityInConstruction {

    /**
     * Type of the entity to build.
     */
    private final EntityType type;

    /**
     * Id of the entity to build.
     */
    private final EntityId id;

    /**
     * Owner of the entity to build.
     */
    private final PlayerId owner;

    /**
     * World position of the entity to build.
     */
    private final Point3D position;

    /**
     * World direction of the entity to build.
     */
    private final Point3D direction;

    /**
     * Create a new instance.
     * @param type      Type of the entity to build.
     * @param id        Id of the entity to build.
     * @param owner     Owner of the entity to build.
     * @param position  World position of the entity to build.
     * @param direction World direction of the entity to build.
     * @throws NullPointerException If any parameter is null.
     */
    public DefaultEntityInConstruction(EntityType type, EntityId id, PlayerId owner,  Point3D position, Point3D direction) {
        super();
        assert type != null;
        assert id != null;
        assert owner != null;
        assert position != null;
        assert direction != null;
        this.type = type;
        this.id = id;
        this.owner = owner;
        this.position = position;
        this.direction = direction;
    }

    public EntityType getType() {
        return type;
    }

    public EntityId getId() {
        return id;
    }

    public PlayerId getOwner() {
        return owner;
    }

    public Point3D getPosition() {
        return position;
    }

    public Point3D getDirection() {
        return direction;
    }
}
